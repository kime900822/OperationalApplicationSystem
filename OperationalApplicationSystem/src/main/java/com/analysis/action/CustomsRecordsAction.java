package com.analysis.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.analysis.biz.CustomsRecordsBIZ;
import com.analysis.model.CustomsRecords;
import com.analysis.model.Instruction;
import com.google.gson.Gson;
import com.kime.base.ActionBase;
import com.kime.infoenum.Message;
import com.kime.model.User;
import com.kime.utils.CommonUtil;
import com.kime.utils.ExcelUtil;

@Controller
@Scope("prototype")
@ParentPackage("DataImport")
public class CustomsRecordsAction extends ActionBase {

	@Autowired
	CustomsRecordsBIZ customsRecordsBIZ;
	
	
	@Action(value="queryCustomsRecords",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String queryCustomsRecords() throws UnsupportedEncodingException{
		
		List<CustomsRecords> lCustomsRecords=customsRecordsBIZ.query("");		
		reslutJson=new ByteArrayInputStream(new Gson().toJson(lCustomsRecords).getBytes("UTF-8"));  
		return SUCCESS;
	}
	
	
	/**
     * excel导入
     * @return
     * @throws IOException 
     * @throws FileNotFoundException 
     */
	@Action(value="importCustomsRecords",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
    public String  importCustomsRecords() throws FileNotFoundException, IOException{
        try {
	    	if (upfile!=null) {
	    		User user=(User)session.getAttribute("user");
				customsRecordsBIZ.fileToData(user, upfile, first, upfileFileName[0], 2);
				result.setMessage(Message.UPLOAD_MESSAGE_SUCCESS);
				result.setStatusCode("200");
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
	@Action(value="exportCustomsRecordsExcel",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson",
					"contentType","application/vnd.ms-excel",
					"contentDisposition","attachment;filename=%{fileName}",
					"bufferSize","1024"
			})})
    public String exportCustomsRecordsExcel() {
        try {
        	
    		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");		 
    		fileName = "CustomsRecords"+CommonUtil.getDate()+ ".xls";
    		fileName= new String(fileName.getBytes(), "ISO8859-1");
    		
        	List<CustomsRecords> lCustomsRecords=customsRecordsBIZ.query("");
        	Class c = (Class) new CustomsRecords().getClass();  
        	ByteArrayOutputStream os=ExcelUtil.exportExcel("CustomsRecords", c, lCustomsRecords, "yyy-MM-dd");
        	byte[] fileContent = os.toByteArray();
        	ByteArrayInputStream is = new ByteArrayInputStream(fileContent);
        	

    		//文件流
            reslutJson = is;          
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return "success";
    }
}
