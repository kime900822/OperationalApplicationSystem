package com.sign.action;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kime.action.ActionBase;
import com.kime.infoenum.Message;
import com.sign.biz.SignManBIZ;
import com.sign.model.Beneficiary;
import com.sign.model.SignMan;

@Controller
public class SignManAction extends ActionBase {

	@Autowired
	SignManBIZ signManBIZ;
	
	private String sid;	
	private String uid;
	private String type;
	private String did;
	public SignManBIZ getSignManBIZ() {
		return signManBIZ;
	}
	public void setSignManBIZ(SignManBIZ signManBIZ) {
		this.signManBIZ = signManBIZ;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDid() {
		return did;
	}
	public void setDid(String did) {
		this.did = did;
	}
	
	
	@Action(value="editSignMan",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String editSignMan() throws UnsupportedEncodingException{
			
		SignMan signMan = new SignMan();
		boolean t=true;
		signMan.setDid(did);
		if (!"".equals(sid) && sid != null) {
			signMan.setSid(sid);
		} else {
			signMan.setSid(UUID.randomUUID().toString().replaceAll("-", ""));
		}
		signMan.setType(type);
		signMan.setUid(uid);

		try {
			if (sid == null || "".equals(sid)) {
				List<SignMan> lSignMan = signManBIZ.getSianMan("where did='" + did + "'");
				if (lSignMan.size() > 0) {
					t=false;
					result.setMessage("This department has been maintained");
					result.setStatusCode("300");
				} else {
					signManBIZ.save(signMan);
					logUtil.logInfo("新增审核信息，id:" + signMan.getSid());
				}
			} else {
				List<SignMan> lSignMan = signManBIZ.getSianMan("where did='" + did + "' and sid <>'"+sid+"'");
				if (lSignMan.size() > 0) {
					t=false;
					result.setMessage("This department has been maintained");
					result.setStatusCode("300");
				}else{
					signManBIZ.update(signMan);
					logUtil.logInfo("修改审核信息，id:" + signMan.getSid());
				}				
			}
			
		} catch (Exception e1) {
			e1.printStackTrace();
			logUtil.logInfo("修改审核信息，异常:" + e1.getMessage());
		}
		
		if (t) {
			signMan = signManBIZ.getSianMan(" where sid='" + signMan.getSid() + "'").get(0);
			signMan.setUname(signMan.getUser().getName());
			signMan.setDname(signMan.getDepartment().getName());

			String r = callback + "(" + new Gson().toJson(signMan) + ")";
			reslutJson = new ByteArrayInputStream(r.getBytes("UTF-8"));
		}else{
			reslutJson = new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8"));
		}		
		
		
		return SUCCESS;

		
	}
	
	
	@Action(value="querySignMan",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String querySignMan() throws UnsupportedEncodingException{	
		String where="";
		if (!"".equals(type)&&type!=null) {
			where+=" where type = '"+type+"' ";
		}
		
		List<SignMan> list  =signManBIZ.getSianMan(where, Integer.parseInt(pageSize),Integer.parseInt(pageCurrent));
		int total=signManBIZ.getSianMan(where).size();
		
		for (SignMan object : list) {
			object.setDname(object.getDepartment().getName());
			object.setUname(object.getUser().getName());
		}
		
		queryResult.setList(list);
		queryResult.setTotalRow(total);
		queryResult.setFirstPage(Integer.parseInt(pageCurrent)==1?true:false);
		queryResult.setPageNumber(Integer.parseInt(pageCurrent));
		queryResult.setLastPage(total/Integer.parseInt(pageSize) +1==Integer.parseInt(pageCurrent)&&Integer.parseInt(pageCurrent)!=1?true:false);
		queryResult.setTotalPage(total/Integer.parseInt(pageSize) +1);
		queryResult.setPageSize(Integer.parseInt(pageSize));
		String r=callback+"("+new Gson().toJson(queryResult)+")";
		
		reslutJson=new ByteArrayInputStream(r.getBytes("UTF-8"));  
		
		logUtil.logInfo("查询审批人，条件:"+where);
		return SUCCESS;
	}

	@Action(value="deleteSignMan",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String deleteSignMan() throws UnsupportedEncodingException{
		List<SignMan> list=new Gson().fromJson(json, new TypeToken<ArrayList<SignMan>>() {}.getType());
		try {
			for (SignMan object : list) {
				signManBIZ.delete(object);
				logUtil.logInfo("删除收款人:"+object.getSid());
			}
			result.setMessage(Message.DEL_MESSAGE_SUCCESS);
			result.setStatusCode("200");
		} catch (Exception e) {
			logUtil.logInfo("删除收款人:"+e.getMessage());
			result.setMessage(e.getMessage());
			result.setStatusCode("300");
		}

		reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8"));  
		return SUCCESS;
	}
	
}
