package com.sign.action;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kime.base.ActionBase;
import com.kime.infoenum.Message;
import com.sign.biz.CheckBIZ;
import com.sign.model.Check;

@Controller
public class CheckAction extends ActionBase{

	@Autowired
	private CheckBIZ checkBIZ;

	private String id;
	private String type;
	private String fistUID;
	private String fistUname;
	private String uName;
	private String secondUID;
	private String secondUname;
	private String thirdUID;
	private String thirdUname;
	private String attachment;

	public CheckBIZ getCheckBIZ() {
		return checkBIZ;
	}
	public void setCheckBIZ(CheckBIZ checkBIZ) {
		this.checkBIZ = checkBIZ;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFistUID() {
		return fistUID;
	}
	public void setFistUID(String fistUID) {
		this.fistUID = fistUID;
	}
	public String getFistUname() {
		return fistUname;
	}
	public void setFistUname(String fistUname) {
		this.fistUname = fistUname;
	}
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public String getSecondUID() {
		return secondUID;
	}
	public void setSecondUID(String secondUID) {
		this.secondUID = secondUID;
	}
	public String getSecondUname() {
		return secondUname;
	}
	public void setSecondUname(String secondUname) {
		this.secondUname = secondUname;
	}
	public String getThirdUID() {
		return thirdUID;
	}
	public void setThirdUID(String thirdUID) {
		this.thirdUID = thirdUID;
	}
	public String getThirdUname() {
		return thirdUname;
	}
	public void setThirdUname(String thirdUname) {
		this.thirdUname = thirdUname;
	}
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	

	@Action(value="getCheck4Select",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String getCheck4Select() throws UnsupportedEncodingException{	

		
		List<Check> list  =(List<Check>)checkBIZ.query("");		
		List<Map<String, String>> lMaps=new ArrayList<>();		
		for (Check check : list) {
			Map<String, String>map=new HashMap<>();
			map.put(check.getId(),check.getType());
			lMaps.add(map);
		}
		reslutJson=new ByteArrayInputStream(new Gson().toJson(lMaps).toString().getBytes());  
		return SUCCESS;
	}
	
	
	@Action(value="getCheck",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String getCheck() throws UnsupportedEncodingException{	
		String where="";
		if (type!=null&&!type.equals("")) {
			where+=" where type like'%"+type+"%' ";
		}
		
		List<Check> list  =(List<Check>)checkBIZ.query(where, Integer.parseInt(pageSize),Integer.parseInt(pageCurrent));
		int total=checkBIZ.query(where).size();	
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
	
	
	@Action(value="deleteCheck",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String deleteCheck() throws UnsupportedEncodingException{
		List<Check> list=new Gson().fromJson(json, new TypeToken<ArrayList<Check>>() {}.getType());
		if (checkBIZ.delete(list)==null) {
			result.setStatusCode("200");
			result.setMessage(Message.DEL_MESSAGE_SUCCESS);
		}else{
			result.setStatusCode("300");
			result.setMessage(Message.DEL_MESSAGE_SUCCESS);		
		}
		
		reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8"));  
		return SUCCESS;
	}
	
	@Action(value="modeCheck",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String modeCheck() throws UnsupportedEncodingException{
		Check check=new Check();
		check.setFistUID(fistUID);
		check.setFistUname(fistUname);
		check.setType(type);
		check.setSecondUID(secondUID);
		check.setSecondUname(secondUname);
		check.setThirdUID(thirdUID);
		check.setThirdUname(thirdUname);
			
		if (!"".equals(id) && id != null) {
			check.setId(id);
		} else {
			check.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		}
		
		String err=null;
		String rString="";
		if (!"".equals(id) && id != null) {
			err=checkBIZ.update(check);
			if (err==null) {
				rString=callback + "(" + new Gson().toJson(check) + ")";
			}else{
				result.setMessage(Message.MOD_MESSAGE_ERROE);
				result.setStatusCode("300");
			}
		} else {
			err=checkBIZ.save(check);
			if (err==null) {
				rString=callback + "(" + new Gson().toJson(check) + ")";
			}else{
				result.setMessage(Message.SAVE_MESSAGE_ERROR);
				result.setStatusCode("300");
			}

		}
		if (err==null) {
			reslutJson = new ByteArrayInputStream(rString.getBytes("UTF-8"));
		}else {
			reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8"));  
		}		
		return SUCCESS;
		
	}
}
