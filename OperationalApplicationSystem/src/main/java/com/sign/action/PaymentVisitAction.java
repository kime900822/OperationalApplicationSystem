package com.sign.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.hibernate.dialect.pagination.LimitHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;
import com.kime.base.ActionBase;
import com.kime.infoenum.Message;
import com.kime.model.Approve;
import com.kime.model.ApproveHis;
import com.kime.model.ApproveList;
import com.kime.model.User;
import com.kime.utils.CommonUtil;
import com.kime.utils.PDFUtil;
import com.opensymphony.xwork2.ActionContext;
import com.sign.biz.BeneficiaryBIZ;
import com.sign.biz.PaymentVisitBIZ;
import com.sign.dao.impl.PyamentVisitDAOImpl;import com.sign.model.Beneficiary;
import com.sign.model.Payment;
import com.sign.model.Stamp;
import com.sign.model.paymentVisit.PaymentVisit;
import com.sign.model.paymentVisit.PaymentVisitBusinessTrip;
import com.sign.model.paymentVisit.PaymentVisitEmployee;
import com.sign.other.PaymentHelp;
import com.sign.other.PaymentVisitHelp;

@Controller
@Scope("prototype")
@ParentPackage("Struts 2")
public class PaymentVisitAction extends ActionBase{

	@Autowired
	PaymentVisitBIZ paymentVisitBIZ;
	@Autowired
	BeneficiaryBIZ beneficiaryBIZ;
	
	String id;
	String state;
	String applicantDate;
	String visitPurpose;
	String referenceNo;
	String projectNo;
	String visitDateFrom;
	String visitDateTo;
	Double totalLevelWorkHours;
	String businessTrip;
	String visitDetailPlace;
	String visitDetailPurpose;
	String dateTmp;
	String uId;
	String uName;
	List<PaymentVisitEmployee> employees;
	List<ApproveList> approveList;
	List<ApproveHis> approveHis;
	String nextApprove;
	double advanceAmount;
	String currency;
	
	String dId;
	String applicationDate_f;
	String applicationDate_t;
	
	
	public double getAdvanceAmount() {
		return advanceAmount;
	}
	public void setAdvanceAmount(double advanceAmount) {
		this.advanceAmount = advanceAmount;
	}
	public String getCurrency() { 
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getdId() {
		return dId;
	}
	public void setdId(String dId) {
		this.dId = dId;
	}
	public String getApplicationDate_f() {
		return applicationDate_f;
	}
	public void setApplicationDate_f(String applicationDate_f) {
		this.applicationDate_f = applicationDate_f;
	}
	public String getApplicationDate_t() {
		return applicationDate_t;
	}
	public void setApplicationDate_t(String applicationDate_t) {
		this.applicationDate_t = applicationDate_t;
	}



	private String tradeId;
	private String comment;
	private String level;
	private String approveState;
	
	private String paymentId;
	
	
	public String getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	public String getTradeId() {
		return tradeId;
	}
	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getApproveState() {
		return approveState;
	}
	public void setApproveState(String approveState) {
		this.approveState = approveState;
	}
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
	public Double getTotalLevelWorkHours() {
		return totalLevelWorkHours;
	}
	public void setTotalLevelWorkHours(Double totalLevelWorkHours) {
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
	public List<ApproveList> getApproveList() {
		return approveList;
	}
	public void setApproveList(List<ApproveList> approveList) {
		this.approveList = approveList;
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
	
	@Action(value="getPaymentVisitByID",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String getPaymentVisitByID() throws UnsupportedEncodingException{

		PaymentVisit paymentVisit=new PaymentVisit();
		try {
			paymentVisit=paymentVisitBIZ.queryById(id);
			reslutJson=new ByteArrayInputStream(new Gson().toJson(paymentVisit).getBytes("UTF-8")); 	
			logUtil.logInfo("查询出差申请单:"+paymentVisit.getReferenceNo());
		} catch (Exception e) {
			logUtil.logInfo("查询出差申请单异常:"+e.getMessage());
			result.setMessage(e.getMessage());
			result.setStatusCode("300");
			reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8")); 	
		}
		
		return SUCCESS;
	}
	
	@Action(value="checkPaymentVisit",results={@org.apache.struts2.convention.annotation.Result(type="stream",
	params={
			"inputName", "reslutJson"
	})})
	public String checkPaymentVisit() throws UnsupportedEncodingException{
	
	
	try {
		List<PaymentVisit> lPaymentVisits= paymentVisitBIZ.query(" where referenceNo='"+referenceNo+"' And state='"+PaymentVisitHelp.COMPLETED+"'");
		if (lPaymentVisits.size()>0) {
			result.setMessage(Message.SUCCESS);
			result.setStatusCode("200");
			Map<String, String>map=new HashMap<>();
			map.put("id", lPaymentVisits.get(0).getId());
			result.setParams(map);
		}else{
			result.setMessage("Reference No ERRPR! ");
			result.setStatusCode("300");
		}
	
	} catch (Exception e) {
		logUtil.logInfo("查询出差申请单异常:"+e.getMessage());
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
			else if (beneficiaryBIZ.queryBeneficiary(" where supplierCode='"+paymentVisit.getuId()+"'").size()==0) {
				result.setMessage("The user is not maintained to the payee.!");
				result.setStatusCode("300");
			}else {
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
					paymentVisit.setState(PaymentVisitHelp.SAVE);
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
	
	
	
	@Action(value="submitPaymentVisit",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String submitPaymentVisit() throws UnsupportedEncodingException{
		try {
			PaymentVisit paymentVisit=paymentVisitBIZ.queryById(id);
			paymentVisit.setState(PaymentVisitHelp.SUBMIT);
			paymentVisitBIZ.update(paymentVisit);
			
			result.setMessage(Message.SAVE_MESSAGE_SUCCESS);
			result.setStatusCode("200");
			logUtil.logInfo("提交付款申请单:"+paymentVisit.getReferenceNo());
		} catch (Exception e) {
			logUtil.logInfo("提交付款申请单异常:"+e.getMessage());
			result.setMessage(e.getMessage());
			result.setStatusCode("300");
		}
		
		reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8")); 	
		return SUCCESS;
	}
	
	
	
	
	@Action(value="getPaymentVisit",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String getPaymentVisit() throws UnsupportedEncodingException{
	
		User user=(User)session.getAttribute("user");
		String hql="";
		String where="";
		
		
		if (!"".equals(applicationDate_f)&&applicationDate_f!=null) {
			where += " AND P.applicantDate>='"+applicationDate_f+"' ";
		}
		if (!"".equals(applicationDate_t)&&applicationDate_t!=null) {
			where += " AND P.applicantDate <= '"+applicationDate_t+"' ";
		}
		if (!"".equals(referenceNo)&&referenceNo!=null) {
			where += " AND P.referenceNo like '%"+referenceNo+"%' ";
		}
		if (!"".equals(state)&&state!=null) {
			where += " AND P.state = '"+state+"' ";
		}
		if (!"".equals(uId)&&uId!=null) {
			where += " AND P.uId = '"+uId+"' ";
		}
		if (!"".equals(dId)&&dId!=null) {
			where += " AND P.uId in( select uid from User where did like '"+dId+"') ";
		}
		
		if(queryType.equals("manager")) {
			hql=" select P from PaymentVisit P where P.nextApprove='"+user.getUid()+"' "+where+" order By P.dateTmp desc";
		}
		if(queryType.equals("admin")) {
			hql=" select P from PaymentVisit P where (P.noticeA = '"+user.getUid()+"' or P.noticeB = '"+user.getUid()+"' or P.noticeC = '"+user.getUid()+"' or P.noticeD = '"+user.getUid()+"' ) "+where+" order By P.dateTmp desc";
		}
		if(queryType.equals("user")) {
			hql=" select P from PaymentVisit P where P.uId='"+user.getUid()+"' "+where+" order By P.dateTmp desc";
		}
		if(queryType.equals("all")) {
			hql=" select P from PaymentVisit P where 1=1 "+where+" order By P.dateTmp desc";
		}

		List<PaymentVisit> list=paymentVisitBIZ.queryByHql(hql,Integer.parseInt(pageSize),Integer.parseInt(pageCurrent));
		int total=paymentVisitBIZ.queryByHql(hql).size();
		
			
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
	
	
	
	@Action(value="approvePaymentVisit",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String approvePaymentVisit() throws UnsupportedEncodingException{	
		ApproveHis approveHis=new ApproveHis();
		try {
			approveHis=paymentVisitBIZ.approve(level, comment, approveState, tradeId);
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
	
	@Action(value="cancelPaymentVisit",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String cancelPaymentVisit() throws UnsupportedEncodingException{	
		try {
			paymentVisitBIZ.cancel(id);
			result.setStatusCode("200");
			result.setMessage(Message.SUCCESS);
		} catch (Exception e) {
			result.setStatusCode("300");
			result.setMessage(e.getMessage());
		}
		
		reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8"));  	
		return SUCCESS;
	}
	
	
	@Action(value="getPaymentVisitStatus",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String getPaymentVisitStatus() throws UnsupportedEncodingException{	
		try {
			Map<String, String> status=new HashMap<>();
			status.put(PaymentVisitHelp.SAVE, PaymentVisitHelp.SAVE);
			status.put(PaymentVisitHelp.SUBMIT, PaymentVisitHelp.SUBMIT);
			List<Approve> list=paymentVisitBIZ.getApprove();
			for (Approve approve : list) {
				status.put(approve.getName()+" Approval", approve.getName()+" Approval");
				status.put(approve.getName()+" Rejected", approve.getName()+" Rejected");
			}
			status.put(PaymentVisitHelp.CANCEL, PaymentVisitHelp.CANCEL);
			status.put(PaymentVisitHelp.INVALID, PaymentVisitHelp.INVALID);
			status.put(PaymentVisitHelp.COMPLETED, PaymentVisitHelp.COMPLETED);
			result.setStatusCode("200");
			result.setParams(status);
		} catch (Exception e) {
			result.setStatusCode("300");
			result.setMessage(e.getMessage());
		}
		
		reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8"));  	
		return SUCCESS;
	}
	
	@Action(value="paymentVisitPrintBusiness",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson",
					"contentType","application/vnd.ms-excel",
					"contentDisposition","attachment;filename=%{fileName}",
					"bufferSize","1024"
			})})
    public String paymentVisitPrintBusiness() throws Exception {
		try {
			
		PaymentVisit paymentVisit=paymentVisitBIZ.queryById(id);
		Map<String, String> map=new HashMap<>();
		
		
		
		String managerCheck="";
		String uNames="";
		String uIds="";
		for (ApproveHis approveHis : paymentVisit.getApproveHis()) {
			if (approveHis.getLevel().equals("0")&&approveHis.getStatus().equals("Approved")) {
				managerCheck=approveHis.getuName()+" "+approveHis.getDate();
			}
		}
		
		for (PaymentVisitEmployee paymentVisitEmployee : paymentVisit.getEmployees()) {
			uNames+=paymentVisitEmployee.getEmployeeName()+",";
			uIds+=paymentVisitEmployee.getEmployeeNo()+",";
		}
		
		if (uNames.length()>0) {
			uNames=uNames.substring(0, uNames.length()-1);
			uIds=uIds.substring(0, uIds.length()-1);
		}
		
		
		map.put("uName", uNames);
		map.put("uId", uIds);
		map.put("visitDateFrom", paymentVisit.getVisitDateFrom());
		map.put("applicantDate", paymentVisit.getApplicantDate());
		map.put("visitDateTo", paymentVisit.getVisitDateTo());
		map.put("hours", String.valueOf(paymentVisit.getTotalLeaveWorkHours()));
		map.put("visitDetailPlace", paymentVisit.getVisitDetailPlace());
		map.put("managerCheck", managerCheck);
		map.put("referenceNo", paymentVisit.getReferenceNo());
		
		ActionContext ac = ActionContext.getContext();   
		ServletContext sc = (ServletContext) ac.get(ServletActionContext.SERVLET_CONTEXT);   
		String path = sc.getRealPath("/");  
		
		ByteArrayOutputStream os=PDFUtil.printPDF(map, path+printUrl);
		
		byte[] fileContent = os.toByteArray();
    	ByteArrayInputStream is = new ByteArrayInputStream(fileContent);
    	
    	HttpServletResponse response = (HttpServletResponse)
    			ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_RESPONSE);
    	response.setHeader("Set-Cookie", "fileDownload=true; path=/");
    	
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");		 
		fileName = "F-HR&ADM-01 Leave Requisition 请假单"+sf.format(new Date()).toString()+ ".pdf";
		fileName= new String(fileName.getBytes(), "ISO8859-1");
		
		reslutJson = is;            
        logUtil.logInfo("打印请假单："+paymentVisit.getReferenceNo());
		}
	    catch(Exception e) {
	    	logUtil.logInfo("打印请假单异常："+e.getMessage());
	        e.printStackTrace();
	    }
		
		
		 return "success";
	}
	
	
	
	@Action(value="paymentVisitPrintTravel",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson",
					"contentType","application/vnd.ms-excel",
					"contentDisposition","attachment;filename=%{fileName}",
					"bufferSize","1024"
			})})
    public String paymentVisitPrintTravel() throws Exception {
		try {
			
		PaymentVisit paymentVisit=paymentVisitBIZ.queryById(id);
		
		ByteArrayOutputStream os=PDFUtil.printPaymentVisitTravelPDF(paymentVisit);
		
		byte[] fileContent = os.toByteArray();
    	ByteArrayInputStream is = new ByteArrayInputStream(fileContent);
    	
    	HttpServletResponse response = (HttpServletResponse)
    			ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_RESPONSE);
    	response.setHeader("Set-Cookie", "fileDownload=true; path=/");
    	
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");		 
		fileName = "出差申请单"+sf.format(new Date()).toString()+ ".pdf";
		fileName= new String(fileName.getBytes(), "ISO8859-1");
		
		reslutJson = is;            
        logUtil.logInfo("打印请假单："+paymentVisit.getReferenceNo());
		}
	    catch(Exception e) {
	    	logUtil.logInfo("打印请假单异常："+e.getMessage());
	        e.printStackTrace();
	    }
		
		
		 return "success";
	}
}
