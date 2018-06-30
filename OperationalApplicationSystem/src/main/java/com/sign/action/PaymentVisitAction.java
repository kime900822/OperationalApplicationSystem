package com.sign.action;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;
import com.kime.base.ActionBase;
import com.kime.infoenum.Message;
import com.kime.model.ApproveHis;
import com.kime.utils.CommonUtil;
import com.sign.biz.PaymentVisitBIZ;
import com.sign.model.ApproveApplication;
import com.sign.model.paymentVisit.PaymentVisit;
import com.sign.model.paymentVisit.PaymentVisitEmployee;

import antlr.collections.impl.LList;

@Controller
@Scope("prototype")
@ParentPackage("Struts 2")
public class PaymentVisitAction extends ActionBase{

	@Autowired
	PaymentVisitBIZ paymentVisitBIZ;
	
	String id;
	String state;
	String applicantDate;
	String visitPurpose;
	String referenceNo;
	String projectNo;
	String visitDateFrom;
	String visitDateTo;
	Integer totalLevelWorkHours;
	String businessTrip;
	String visitDetailPlace;
	String visitDetailPurpose;
	String dateTmp;
	String uId;
	String uName;
	List<PaymentVisitEmployee> employees;
	List<ApproveApplication> approveApplications;
	List<ApproveHis> approveHis;
	String nextApprove;
	
	
	public String getNextApprove() {
		return nextApprove;
	}
	public void setNextApprove(String nextApprove) {
		this.nextApprove = nextApprove;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public PaymentVisitBIZ getPaymentVisitBIZ() {
		return paymentVisitBIZ;
	}
	public void setPaymentVisitBIZ(PaymentVisitBIZ paymentVisitBIZ) {
		this.paymentVisitBIZ = paymentVisitBIZ;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getApplicantDate() {
		return applicantDate;
	}
	public void setApplicantDate(String applicantDate) {
		this.applicantDate = applicantDate;
	}
	public String getVisitPurpose() {
		return visitPurpose;
	}
	public void setVisitPurpose(String visitPurpose) {
		this.visitPurpose = visitPurpose;
	}
	public String getReferenceNo() {
		return referenceNo;
	}
	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}
	public String getProjectNo() {
		return projectNo;
	}
	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}
	public String getVisitDateFrom() {
		return visitDateFrom;
	}
	public void setVisitDateFrom(String visitDateFrom) {
		this.visitDateFrom = visitDateFrom;
	}
	public String getVisitDateTo() {
		return visitDateTo;
	}
	public void setVisitDateTo(String visitDateTo) {
		this.visitDateTo = visitDateTo;
	}
	public Integer getTotalLevelWorkHours() {
		return totalLevelWorkHours;
	}
	public void setTotalLevelWorkHours(Integer totalLevelWorkHours) {
		this.totalLevelWorkHours = totalLevelWorkHours;
	}
	public String getBusinessTrip() {
		return businessTrip;
	}
	public void setBusinessTrip(String businessTrip) {
		this.businessTrip = businessTrip;
	}
	public String getVisitDetailPlace() {
		return visitDetailPlace;
	}
	public void setVisitDetailPlace(String visitDetailPlace) {
		this.visitDetailPlace = visitDetailPlace;
	}
	public String getVisitDetailPurpose() {
		return visitDetailPurpose;
	}
	public void setVisitDetailPurpose(String visitDetailPurpose) {
		this.visitDetailPurpose = visitDetailPurpose;
	}
	public String getDateTmp() {
		return dateTmp;
	}
	public void setDateTmp(String dateTmp) {
		this.dateTmp = dateTmp;
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
	public List<PaymentVisitEmployee> getEmployees() {
		return employees;
	}
	public void setEmployees(List<PaymentVisitEmployee> employees) {
		this.employees = employees;
	}
	public List<ApproveApplication> getApproveApplications() {
		return approveApplications;
	}
	public void setApproveApplications(List<ApproveApplication> approveApplications) {
		this.approveApplications = approveApplications;
	}
	public List<ApproveHis> getApproveHis() {
		return approveHis;
	}
	public void setApproveHis(List<ApproveHis> approveHis) {
		this.approveHis = approveHis;
	}
	
	
	
	@Action(value="deletePaymentVisit",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String deletePaymentVisit() throws UnsupportedEncodingException{
		
		try {
			List<PaymentVisit> list=paymentVisitBIZ.query(" where id='"+id+"'");
			if (list.size()>0) {
				paymentVisitBIZ.delete(list.get(0));
				result.setMessage("Delete Success");
				result.setStatusCode("200");	
			}else {
				result.setMessage("This Reference No is not exist!");
				result.setStatusCode("300");	
			}
			
			
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			result.setStatusCode("300");
		}
		
		reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8")); 	
		return SUCCESS;
		
	}
	
	@Action(value="savePaymentVisit",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String savePaymentVisit() throws UnsupportedEncodingException{
		try {
			PaymentVisit paymentVisit=new Gson().fromJson(json, PaymentVisit.class);		
			List<String> lStrings=new ArrayList<String>();
			
			if (paymentVisit.getuId()==null||paymentVisit.getuId().equals("")) {
				result.setMessage("User can`t be NULL,Plese reflash page!");
				result.setStatusCode("300");
				reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8")); 	
				return SUCCESS;
			}
			
			
			for (PaymentVisitEmployee employee : paymentVisit.getEmployees()) {
				lStrings.add(employee.getEmployeeNo());
			}
			boolean isRepeat = lStrings.size() != new HashSet<String>(lStrings).size();
			
			
			if (isRepeat) {
				result.setMessage("Employee repeat!");
				result.setStatusCode("300");
			}
			else {
				if (!paymentVisit.getId().equals("")&&paymentVisit.getId()!=null) {

					
					paymentVisit.setDateTmp(CommonUtil.getDateTemp());
					paymentVisitBIZ.update(paymentVisit);				
					result.setMessage(Message.SAVE_MESSAGE_SUCCESS);
					result.setStatusCode("200");
					logUtil.logInfo("更新出差申请单:"+paymentVisit.getId());			
				}else{

					
					String code=paymentVisitBIZ.getMaxCode();
					paymentVisit.setReferenceNo(code);
					paymentVisit.setId(UUID.randomUUID().toString().replaceAll("-", ""));
					paymentVisit.setDateTmp(CommonUtil.getDateTemp());
					paymentVisit.setState("0");
					paymentVisitBIZ.save(paymentVisit);
					result.setMessage(Message.SAVE_MESSAGE_SUCCESS);
					result.setStatusCode("200");
					Map<String, String> map=new HashMap<>();
					map.put("id", paymentVisit.getId());
					map.put("referenceNO", paymentVisit.getReferenceNo());
					result.setParams(map);
					logUtil.logInfo("新增出差申请单:"+paymentVisit.getReferenceNo());

				}
			}
			
		} catch (Exception e) {
			logUtil.logInfo("新增出差申请单异常:"+e.getMessage());
			result.setMessage(e.getMessage());
			result.setStatusCode("300");
		}
		
		reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8")); 	
		return SUCCESS;
	}
	
	
}
