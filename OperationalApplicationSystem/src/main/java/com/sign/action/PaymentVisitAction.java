package com.sign.action;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;
import com.kime.base.ActionBase;
import com.kime.infoenum.Message;
import com.kime.model.ApproveHis;
import com.kime.utils.CommonUtil;
import com.sign.biz.PaymentVisitBIZ;
import com.sign.model.approveApplication;
import com.sign.model.paymentVisit.PaymentVisit;
import com.sign.model.paymentVisit.PaymentVisitEmployee;

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
	String projectNO;
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
	List<approveApplication> approveApplications;
	List<ApproveHis> approveHis;
	
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
	public String getProjectNO() {
		return projectNO;
	}
	public void setProjectNO(String projectNO) {
		this.projectNO = projectNO;
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
	public List<approveApplication> getApproveApplications() {
		return approveApplications;
	}
	public void setApproveApplications(List<approveApplication> approveApplications) {
		this.approveApplications = approveApplications;
	}
	public List<ApproveHis> getApproveHis() {
		return approveHis;
	}
	public void setApproveHis(List<ApproveHis> approveHis) {
		this.approveHis = approveHis;
	}
	
	
	@Action(value="savePaymentVisit",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String savePaymentVisit() throws UnsupportedEncodingException{
		try {
			PaymentVisit paymentVisit=new Gson().fromJson(json, PaymentVisit.class);		
		
			if (paymentVisit.getuId()==null||paymentVisit.getuId().equals("")) {
				result.setMessage("User can`t be NULL,Plese reflash page!");
				result.setStatusCode("300");
				reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8")); 	
				return SUCCESS;
			}
			

			
			if (!paymentVisit.getId().equals("")&&paymentVisit.getId()!=null) {

				
				paymentVisit.setDateTmp(CommonUtil.getDateTemp());
				paymentVisitBIZ.update(paymentVisit);				
				result.setMessage(Message.SAVE_MESSAGE_SUCCESS);
				result.setStatusCode("200");
				logUtil.logInfo("更新出差申请单:"+paymentVisit.getId());			
			}else{

				
				String code=paymentVisitBIZ.getMaxCode();
				paymentVisit.setReferenceNo(code);
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

			
		} catch (Exception e) {
			logUtil.logInfo("新增出差申请单异常:"+e.getMessage());
			result.setMessage(e.getMessage());
			result.setStatusCode("300");
		}
		
		reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8")); 	
		return SUCCESS;
	}
	
	
}
