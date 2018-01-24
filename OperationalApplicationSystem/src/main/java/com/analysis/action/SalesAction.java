package com.analysis.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.analysis.biz.SalesBIZ;
import com.analysis.model.Instruction;
import com.analysis.model.Sales;
import com.google.gson.Gson;
import com.kime.action.ActionBase;
import com.kime.infoenum.Message;
import com.kime.model.Result;
import com.kime.utils.ExcelUtil;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
@ParentPackage("DataImport")
public class SalesAction extends ActionBase {
	@Autowired
	private SalesBIZ salesBIZ;
	@Autowired
	private Sales sales;

	
	public SalesBIZ getSalesBIZ() {
		return salesBIZ;
	}
	public void setSalesBIZ(SalesBIZ salesBIZ) {
		this.salesBIZ = salesBIZ;
	}
	public Sales getSales() {
		return sales;
	}
	public void setSales(Sales sales) {
		this.sales = sales;
	}
	
	
	@Action(value="getSales",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String GetSales() throws UnsupportedEncodingException{
		
		List<Instruction> lInstructions=salesBIZ.GetSales("");		
		reslutJson=new ByteArrayInputStream(new Gson().toJson(lInstructions).getBytes("UTF-8"));  
		return SUCCESS;
	}
	
	/**
     * excel导入
     * @return
     * @throws IOException 
     * @throws FileNotFoundException 
     */
	@Action(value="importSales",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
    public String  ImportSales() throws FileNotFoundException, IOException{
        try {
	    	if (upfile!=null) {
	    		List<Sales> lSales=ExcelUtil.FileToList(sales.getClass(),upfile,first,upfileFileName[0],2);
	    		if (lSales.size()>0) {
					salesBIZ.DeleteSales("");
					salesBIZ.SaveSales(lSales);
					result.setMessage(Message.UPLOAD_MESSAGE_SUCCESS);
					result.setStatusCode("200");
				}else{
					result.setMessage(Message.UPLOAD_MESSAGE_ERROR_NODATA);
					result.setStatusCode("200");					
				}
	            
			}else{
				result.setMessage(Message.UPLOAD_MESSAGE_ERROE);
				result.setStatusCode("300");
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			result.setStatusCode("300");
		}
        reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8"));  
    	return SUCCESS;
    }
	
	 /**
     * excel导出
     * @return
     */
	@Action(value="exportSalesExcel",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson",
					"contentType","application/vnd.ms-excel",
					"contentDisposition","attachment;filename=%{fileName}",
					"bufferSize","1024"
			})})
    public String ExportSalesExcel() {
        try {

        	List<Sales> lSales=salesBIZ.GetSales("");
        	Class c = (Class) sales.getClass();  
        	ByteArrayOutputStream os=ExcelUtil.exportExcel("Sales", c, lSales, "yyy-MM-dd");
        	byte[] fileContent = os.toByteArray();
        	ByteArrayInputStream is = new ByteArrayInputStream(fileContent);
        	
    		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");		 
    		fileName = "Sales"+sf.format(new Date()).toString()+ ".xls";
    		fileName= new String(fileName.getBytes(), "ISO8859-1");
    		//文件流
            reslutJson = is;            
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return "success";
    }

}
