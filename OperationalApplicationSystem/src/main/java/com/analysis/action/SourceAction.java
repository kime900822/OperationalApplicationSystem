package com.analysis.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.analysis.biz.SourceBIZ;
import com.analysis.model.Sales;
import com.analysis.model.Source;
import com.google.gson.Gson;
import com.kime.action.ActionBase;
import com.kime.infoenum.Message;
import com.kime.model.Result;
import com.kime.utils.ExcelUtil;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
@ParentPackage("DataImport")
public class SourceAction extends ActionBase {

	@Autowired
	private Source source;
	@Autowired
	private SourceBIZ sourceBIZ;
	
	
	public Source getSource() {
		return source;
	}
	public void setSource(Source source) {
		this.source = source;
	}
	public SourceBIZ getSourceBIZ() {
		return sourceBIZ;
	}
	public void setSourceBIZ(SourceBIZ sourceBIZ) {
		this.sourceBIZ = sourceBIZ;
	}
	
	
	@Action(value="getSource",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String GetSource() throws UnsupportedEncodingException{
		
		List<Source> lInstructions=sourceBIZ.GetSource("");		
		reslutJson=new ByteArrayInputStream(new Gson().toJson(lInstructions).getBytes("UTF-8"));  
		return SUCCESS;
	}
	
	
	
	/**
     * excel导入
     * @return
     * @throws IOException 
     * @throws FileNotFoundException 
     */
	@Action(value="importSource",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
    public String  ImportSource() throws Exception{
		try {
	    	if (upfile!=null) {
	    		List<Source> lSources= new ArrayList<>();
	    	
	    		//POIFSFileSystem fs=new POIFSFileSystem(new FileInputStream(upfile));   
	        	//HSSFWorkbook wb = new HSSFWorkbook(fs); 
	        	Workbook wb=ExcelUtil.getWorkbook(new FileInputStream(upfile), upfileFileName[0]);
	        	Sheet sheet = wb.getSheetAt(0); 
	        	int tmp=sheet.getLastRowNum();
	            // 循环遍历表,sheet.getLastRowNum()是获取一个表最后一条记录的记录号
	            for (int i = Integer.parseInt(first)-1 ; i <= tmp; i++) {
	                // 创建一个行对象
	                Row row = sheet.getRow(i);
	                if (row == null) {
	                    break;
	                }
	                	Source source=new Source();
	                	source.setProject_Code(ExcelUtil.getCellValue(row.getCell(0)));
	                	source.setType(ExcelUtil.getCellValue(row.getCell(1)));
	                	//50
	                	source.setLabour_Cost_50(ExcelUtil.getCellValue(row.getCell(2))); //A
	                	source.setMaterial_50(ExcelUtil.getCellValue(row.getCell(21))); //B
	                	source.setConsumption_50(ExcelUtil.getCellValue(row.getCell(51))); //C
	                	source.setGoods_Transport_50(ExcelUtil.getCellValue(row.getCell(60))); //D
	                	source.setOther_50(ExcelUtil.getCellValue(row.getCell(63))); //E
	                	source.setIndirect_Labour_Cost_50(ExcelUtil.getCellValue(row.getCell(98))); //F
	                	source.setGeneral_MFG_Expenses_50(ExcelUtil.getCellValue(row.getCell(111))); //H
	                	source.setResearch_Development_50(ExcelUtil.getCellValue(row.getCell(145))); //J
	                	source.setFactory_Amortization_50(ExcelUtil.getCellValue(row.getCell(149))); //L
	                	source.setSales_Marketing_50(ExcelUtil.getCellValue(row.getCell(153))); //M
	                	source.setGeneral_Administration_Cost_50(ExcelUtil.getCellValue(row.getCell(156))); //N
	                	//60
	                	source.setLabour_Cost_60(ExcelUtil.getCellValue(row.getCell(162)));
	                	source.setMaterial_60(ExcelUtil.getCellValue(row.getCell(181)));
	                	source.setConsumption_60(ExcelUtil.getCellValue(row.getCell(211)));
	                	source.setGoods_Transport_60(ExcelUtil.getCellValue(row.getCell(220)));
	                	source.setOther_60(ExcelUtil.getCellValue(row.getCell(223)));
	                	source.setIndirect_Labour_Cost_60(ExcelUtil.getCellValue(row.getCell(258)));
	                	source.setGeneral_MFG_Expenses_60(ExcelUtil.getCellValue(row.getCell(268)));
	                	source.setResearch_Development_60(ExcelUtil.getCellValue(row.getCell(305)));
	                	source.setFactory_Amortization_60(ExcelUtil.getCellValue(row.getCell(309)));
	                	source.setSales_Marketing_60(ExcelUtil.getCellValue(row.getCell(313)));
	                	source.setGeneral_Administration_Cost_60(ExcelUtil.getCellValue(row.getCell(316)));
	                	//70
	                	source.setLabour_Cost_70(ExcelUtil.getCellValue(row.getCell(323)));
	                	source.setMaterial_70(ExcelUtil.getCellValue(row.getCell(342)));
	                	source.setConsumption_70(ExcelUtil.getCellValue(row.getCell(372)));
	                	source.setGoods_Transport_70(ExcelUtil.getCellValue(row.getCell(381)));
	                	source.setOther_70(ExcelUtil.getCellValue(row.getCell(384)));
	                	source.setIndirect_Labour_Cost_70(ExcelUtil.getCellValue(row.getCell(419)));
	                	source.setGeneral_MFG_Expenses_70(ExcelUtil.getCellValue(row.getCell(432)));
	                	source.setResearch_Development_70(ExcelUtil.getCellValue(row.getCell(466)));
	                	source.setFactory_Amortization_70(ExcelUtil.getCellValue(row.getCell(470)));
	                	source.setSales_Marketing_70(ExcelUtil.getCellValue(row.getCell(474)));
	                	source.setGeneral_Administration_Cost_70(ExcelUtil.getCellValue(row.getCell(477)));
	                	
	    				lSources.add(source);
					
	                
	            }
	    		
	    		
	    		
	    		
	    		if (lSources.size()>0) {
					sourceBIZ.DeleteSource("");
					sourceBIZ.SaveSource(lSources);
					logUtil.logInfo("导入Source成功！文件："+fileName);
					result.setMessage(Message.UPLOAD_MESSAGE_SUCCESS);
					result.setStatusCode("200");
				}else{
					result.setMessage(Message.UPLOAD_MESSAGE_ERROR_NODATA);
					result.setStatusCode("200");					
				}
	            
			}else{
				logUtil.logInfo("导入Source失败！文件上传失败："+fileName);
				result.setMessage(Message.UPLOAD_MESSAGE_ERROE);
				result.setStatusCode("300");
			}
		} catch (Exception e) {
			logUtil.logInfo("导入Source失败！"+e.getMessage());
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
	@Action(value="exportSourceExcel",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson",
					"contentType","application/vnd.ms-excel",
					"contentDisposition","attachment;filename=%{fileName}",
					"bufferSize","1024"
			})})
    public String ExportSourceExcel() {
        try {

        	List<Source> lSales=sourceBIZ.GetSource("");
        	Class c = (Class) source.getClass();  
        	ByteArrayOutputStream os=ExcelUtil.exportExcel("Source", c, lSales, "yyy-MM-dd");
        	byte[] fileContent = os.toByteArray();
        	ByteArrayInputStream is = new ByteArrayInputStream(fileContent);
        	
    		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");		 
    		fileName = "Source"+sf.format(new Date()).toString()+ ".xls";
    		fileName= new String(fileName.getBytes(), "ISO8859-1");
    		//文件流
            reslutJson = is;            
            logUtil.logInfo("导出Source！"+fileName);
        }
        catch(Exception e) {
        	logUtil.logInfo("导出Source！"+e.getMessage());
            e.printStackTrace();
        }

        return "success";
    }
	
}
