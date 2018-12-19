package com.customs.action;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.customs.biz.CustomsMaterialMappingBIZ;
import com.customs.model.CustomsMaterialMapping;
import com.customs.other.CustomsMaterialMappinglHelp;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kime.base.ActionBase;
import com.kime.infoenum.Message;

@Controller
@Scope("prototype")
@ParentPackage("Customs")
public class CustomsMaterialMappingAction extends ActionBase{

	@Autowired
	CustomsMaterialMappingBIZ customsMaterialMappingBIZ;
	
	String id;
	String oldMaterialNo;
	String newMaterialNo;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOldMaterialNo() {
		return oldMaterialNo;
	}
	public void setOldMaterialNo(String oldMaterialNo) {
		this.oldMaterialNo = oldMaterialNo;
	}
	public String getNewMaterialNo() {
		return newMaterialNo;
	}
	public void setNewMaterialNo(String newMaterialNo) {
		this.newMaterialNo = newMaterialNo;
	}
	
	@Action(value="deleteMaterialMapping",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String deleteMaterialMapping() throws UnsupportedEncodingException{
		List<CustomsMaterialMapping> list=new Gson().fromJson(json, new TypeToken<ArrayList<CustomsMaterialMapping>>() {}.getType());
		try {
			String r = customsMaterialMappingBIZ.delete(list);
			if (r==null) {
				result.setMessage("Success!");
				result.setStatusCode("200");
			}else{
				result.setMessage(r);
				result.setStatusCode("300");
			}
		} catch (Exception e) {
			logUtil.logError(CustomsMaterialMappinglHelp.title,"删除报错："+e.getMessage());
			result.setMessage(e.getMessage());
			result.setStatusCode("300");
		}

		reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8"));  
		return SUCCESS;
	}
	
	
	
	@Action(value="updateMaterialMapping",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String updateMaterialMapping() throws UnsupportedEncodingException{
		try {
			CustomsMaterialMapping customsMaterialMapping=new CustomsMaterialMapping();
			customsMaterialMapping.setId(id);
			customsMaterialMapping.setNewMaterialNo(newMaterialNo);
			customsMaterialMapping.setOldMaterialNo(oldMaterialNo);
			String r;
			if (id==null||id.equals("")) {
				r = customsMaterialMappingBIZ.save(customsMaterialMapping);
			}else{
				r = customsMaterialMappingBIZ.update(customsMaterialMapping);
			}
			if (r==null) {
				customsMaterialMapping=customsMaterialMappingBIZ.query("where oldMaterialNo='"+oldMaterialNo+"'").get(0);
				String rString=callback+"("+new Gson().toJson(customsMaterialMapping)+")";
				reslutJson=new ByteArrayInputStream(rString.getBytes("UTF-8"));  
			}else{
				result.setMessage(r);
				result.setStatusCode("300");
				reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8"));  
			}
		} catch (Exception e) {
			logUtil.logError(CustomsMaterialMappinglHelp.title,"报错："+e.getMessage());
			result.setMessage(e.getMessage());
			result.setStatusCode("300");
			reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8"));  
		}

		return SUCCESS;
	}
	

	@Action(value="queryMaterialMapping",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String queryMaterialMapping() throws UnsupportedEncodingException{	
		
		List list  =customsMaterialMappingBIZ.query("", Integer.parseInt(pageSize),Integer.parseInt(pageCurrent));
		int total=customsMaterialMappingBIZ.query("").size();
		
		queryResult.setList(list);
		queryResult.setTotalRow(total);
		queryResult.setFirstPage(Integer.parseInt(pageCurrent)==1?true:false);
		queryResult.setPageNumber(Integer.parseInt(pageCurrent));
		queryResult.setLastPage(total/Integer.parseInt(pageSize) +1==Integer.parseInt(pageCurrent)&&Integer.parseInt(pageCurrent)!=1?true:false);
		queryResult.setTotalPage(total/Integer.parseInt(pageSize) +1);
		queryResult.setPageSize(Integer.parseInt(pageSize));
		String r=callback+"("+new Gson().toJson(queryResult)+")";
		
		reslutJson=new ByteArrayInputStream(r.getBytes("UTF-8"));  
		return SUCCESS;
	}
	
	/**
     * excel导入
     * @return
     * @throws IOException 
     * @throws FileNotFoundException 
     */
	@Action(value="importMaterialMapping",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
    public String importMaterialMapping() throws FileNotFoundException, IOException{
        try {
	    	if (upfile!=null) {
	    		customsMaterialMappingBIZ.importData(getUser(), upfile, first, upfileFileName[0], 2);
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
