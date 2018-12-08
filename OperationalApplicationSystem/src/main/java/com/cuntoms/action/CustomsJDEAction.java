package com.cuntoms.action;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cuntoms.biz.CUstomsJDEBIZ;
import com.google.gson.Gson;
import com.kime.base.ActionBase;
import com.kime.infoenum.Message;

@Controller
@Scope("prototype")
@ParentPackage("Customs")
public class CustomsJDEAction extends ActionBase {

	@Autowired
	CUstomsJDEBIZ customsJDEBIZ;
	

	@Action(value="queryCustomsJDE",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String queryCustomsJDE() throws UnsupportedEncodingException{	
		
		List list  =customsJDEBIZ.query("", Integer.parseInt(pageSize),Integer.parseInt(pageCurrent));
		int total=customsJDEBIZ.query("").size();
		
		queryResult.setList(list);
		queryResult.setTotalRow(total);
		queryResult.setFirstPage(Integer.parseInt(pageCurrent)==1?true:false);
		queryResult.setPageNumber(Integer.parseInt(pageCurrent));
		queryResult.setLastPage(total/Integer.parseInt(pageSize) +1==Integer.parseInt(pageCurrent)&&Integer.parseInt(pageCurrent)!=1?true:false);
		queryResult.setTotalPage(total/Integer.parseInt(pageSize) +1);
		queryResult.setPageSize(Integer.parseInt(pageSize));
		String r=callback+"("+new Gson().toJson(queryResult)+")";
		
		reslutJson=new ByteArrayInputStream(r.getBytes("UTF-8"));  
		
		logUtil.logInfo("查询海关 上传2.进（JDE）");
		return SUCCESS;
	}
	
	
	/**
     * excel导入
     * @return
     * @throws IOException 
     * @throws FileNotFoundException 
     */
	@Action(value="importCustomsJDE",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
    public String  importCustomsJDE() throws FileNotFoundException, IOException{
        try {
	    	if (upfile!=null) {
	    		customsJDEBIZ.importData(getUser(), upfile, first, upfileFileName[0], 2);
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
	
	
	
}
