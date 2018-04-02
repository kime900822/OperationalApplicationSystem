package com.kime.action;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kime.base.ActionBase;
import com.kime.biz.ApproveBIZ;
import com.kime.biz.ApproveHisBIZ;
import com.kime.infoenum.Message;
import com.kime.model.Approve;
import com.kime.model.ApproveHis;

@Controller
@Scope("prototype")
@ParentPackage("Struts 2")
public class ApproveAction extends ActionBase{
	
	@Autowired
	private ApproveBIZ approveBIZ;
	@Autowired
	private ApproveHisBIZ approveHisBIZ;
	
	private String id;
	private String type;
	private String name;
	private String uid;
	private String uname;
	private String description;
	private String parentid;
	private String order;
	private String level;
	private boolean tmp1;
	private String tmp2;
	private String tmp3;
	
	
	private String tradeId;
	private String uId;
	private String uName;
	private String dId;
	private String dName;
	private String comment;
	private String status;
	private String date;

	
	
	public ApproveHisBIZ getApproveHisBIZ() {
		return approveHisBIZ;
	}
	public void setApproveHisBIZ(ApproveHisBIZ approveHisBIZ) {
		this.approveHisBIZ = approveHisBIZ;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ApproveBIZ getApproveBIZ() {
		return approveBIZ;
	}
	public void setApproveBIZ(ApproveBIZ approveBIZ) {
		this.approveBIZ = approveBIZ;
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
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public boolean getTmp1() {
		return tmp1;
	}
	public void setTmp1(boolean tmp1) {
		this.tmp1 = tmp1;
	}
	public String getTmp2() {
		return tmp2;
	}
	public void setTmp2(String tmp2) {
		this.tmp2 = tmp2;
	}
	public String getTmp3() {
		return tmp3;
	}
	public void setTmp3(String tmp3) {
		this.tmp3 = tmp3;
	}

	
	
	public String getTradeId() {
		return tradeId;
	}
	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public String getdId() {
		return dId;
	}
	public void setdId(String dId) {
		this.dId = dId;
	}
	public String getdName() {
		return dName;
	}
	public void setdName(String dName) {
		this.dName = dName;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Action(value="getApproveType4Select",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String getApproveType4Select() throws UnsupportedEncodingException{	
		String where=" WHERE LEVEL=0 ";

		List<Approve> lApproves=approveBIZ.query(where);
		reslutJson=new ByteArrayInputStream(new Gson().toJson(lApproves).getBytes("UTF-8"));  	
		return SUCCESS;
	}
	
	@Action(value="getFirstApproveOfStamp4Select",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String getFirstApproveOfStamp4Select() throws UnsupportedEncodingException{	

		List<Approve> lApproves=approveBIZ.getFirstApproveOfStamp4Select(type);
		reslutJson=new ByteArrayInputStream(new Gson().toJson(lApproves).getBytes("UTF-8"));  	
		return SUCCESS;
	}
	
	
	@Action(value="queryApproveType",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String queryApproveType() throws UnsupportedEncodingException{	
		String where=" WHERE LEVEL=0 ";

		if (type!=null&&!type.equals("")) {
			where+=" AND type like '%"+type+"%' ";
		}
		if (description!=null&&!description.equals("")) {
			where+=" AND description like '%"+description+"%' ";
		}
		
		List list  =approveBIZ.query(where, Integer.parseInt(pageSize),Integer.parseInt(pageCurrent));
		int total=approveBIZ.query(where).size();
		
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
	
	
	@Action(value="deleteAppprvoe",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String deleteAppprvoe() throws UnsupportedEncodingException{
		List<Approve> lApproves=new Gson().fromJson(json, new TypeToken<ArrayList<Approve>>() {}.getType());
		String err=approveBIZ.delete(lApproves);
		if (err==null) {
			result.setMessage(Message.DEL_MESSAGE_SUCCESS);
			result.setStatusCode("200");
		}else{
			result.setMessage(err);
			result.setStatusCode("300");			
		}

		reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8"));  
		return SUCCESS;
	}
	
	
	@Action(value="editApproveType",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String editApproveType() throws UnsupportedEncodingException{
		
		List<Approve> lapprove=new Gson().fromJson(json, new TypeToken<ArrayList<Approve>>() {}.getType());
		for (int i = 0; i < lapprove.size(); i++) {
			lapprove.get(i).setLevel("0");
			lapprove.get(i).setOrder("0");
		}
		String err=approveBIZ.update(lapprove);
			
		if (err==null) {
			result.setMessage(Message.MOD_MESSAGE_SUCCESS);
			result.setStatusCode("200");
		}else{
			result.setMessage(err);
			result.setStatusCode("300");			
		}

		reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8"));  
		return SUCCESS;
	}
	
	@Action(value="editApprove",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String editApprove() throws UnsupportedEncodingException{
		
		List<Approve> lapprove=new Gson().fromJson(json, new TypeToken<ArrayList<Approve>>() {}.getType());

		String err=approveBIZ.update(lapprove);
			
		if (err==null) {
			result.setMessage(Message.MOD_MESSAGE_SUCCESS);
			result.setStatusCode("200");
		}else{
			result.setMessage(err);
			result.setStatusCode("300");			
		}

		reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8"));  
		return SUCCESS;
	}
	
	
	@Action(value="queryApproveList",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String queryApproveList() throws UnsupportedEncodingException{	
		List<Approve> lApproves=approveBIZ.getApproveAndChild(id);		
		reslutJson=new ByteArrayInputStream(new Gson().toJson(lApproves).getBytes("UTF-8"));  
		
		return SUCCESS;
	}
	
	@Action(value="submitApprove",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String submitApprove() throws UnsupportedEncodingException{	
		ApproveHis approveHis=new ApproveHis();
		try {
			approveHis=approveHisBIZ.save(level, comment, status, tradeId,type);
			result.setStatusCode("200");
			result.setMessage("Success");
		} catch (Exception e) {
			result.setStatusCode("300");
			result.setMessage(e.getMessage());
		}
		Map<String, Object> params=new HashMap<>();
		params.put("data", approveHis);		
		result.setParams(params);
		
		reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8"));  	
		return SUCCESS;
	}

}
