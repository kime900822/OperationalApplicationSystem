package com.analysis.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.analysis.biz.IrrecoverableVatBIZ;
import com.analysis.model.Instruction;
import com.analysis.model.IrrecoverableVat;
import com.analysis.model.Sales;
import com.google.gson.Gson;
import com.kime.action.ActionBase;
import com.kime.infoenum.Message;
import com.kime.model.Result;
import com.kime.utils.ExcelUtil;
import com.opensymphony.xwork2.ActionSupport;

@Scope("prototype")
@ParentPackage("DataImport")
public class IrrecoverableVatAction extends ActionBase {

	@Autowired
	private IrrecoverableVatBIZ IrrecoverableVatBIZ;
	@Autowired
	private IrrecoverableVat irrecoverableVat;

	
	public IrrecoverableVatBIZ getIrrecoverableVatBIZ() {
		return IrrecoverableVatBIZ;
	}
	public void setIrrecoverableVatBIZ(IrrecoverableVatBIZ irrecoverableVatBIZ) {
		IrrecoverableVatBIZ = irrecoverableVatBIZ;
	}
	public IrrecoverableVat getIrrecoverableVat() {
		return irrecoverableVat;
	}
	public void setIrrecoverableVat(IrrecoverableVat irrecoverableVat) {
		this.irrecoverableVat = irrecoverableVat;
	}

	
	@Action(value="getIrrecoverableVat",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String GetIrrecoverableVat() throws UnsupportedEncodingException{
		
		List<IrrecoverableVat> lInstructions=IrrecoverableVatBIZ.GetIrrecoverableVat("");
		reslutJson=new ByteArrayInputStream(new Gson().toJson(lInstructions).getBytes("UTF-8"));  
		return SUCCESS;
	}
	
	
	/**
     * excel导入
     * @return
     * @throws IOException 
     * @throws FileNotFoundException 
     */
	@Action(value="importIrrecoverableVat",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
    public String  ImportIrrecoverableVat() throws FileNotFoundException, IOException{
        try {
	    	if (upfile!=null) {
	    		List<IrrecoverableVat> lIrrecoverableVats=ExcelUtil.FileToList(irrecoverableVat.getClass(),upfile,first,upfileFileName[0],2);
	    		if (lIrrecoverableVats.size()>0) {
					IrrecoverableVatBIZ.DeleteIrrecoverableVat("");
					IrrecoverableVatBIZ.SaveIrrecoverableVat(lIrrecoverableVats);
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
	@Action(value="exportIrrecoverableVatExcel",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson",
					"contentType","application/vnd.ms-excel",
					"contentDisposition","attachment;filename=%{fileName}",
					"bufferSize","1024"
			})})
    public String ExportIrrecoverableVatExcel() {
        try {

        	List<IrrecoverableVat> lIrrecoverableVats=IrrecoverableVatBIZ.GetIrrecoverableVat("");
        	Class c = (Class) irrecoverableVat.getClass();  
        	ByteArrayOutputStream os=ExcelUtil.exportExcel("Sales", c, lIrrecoverableVats, "yyy-MM-dd");
        	byte[] fileContent = os.toByteArray();
        	ByteArrayInputStream is = new ByteArrayInputStream(fileContent);
        	
    		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");		 
    		fileName = "IrrecoverableVat"+sf.format(new Date()).toString()+ ".xls";
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
