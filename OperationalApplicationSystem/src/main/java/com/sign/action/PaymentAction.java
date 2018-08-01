package com.sign.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.persistence.Column;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ObjectUtils.Null;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kime.base.ActionBase;
import com.kime.biz.DictBIZ;
import com.kime.infoenum.Message;
import com.kime.model.Dict;
import com.kime.model.HeadColumn;
import com.kime.model.User;
import com.kime.utils.CommonUtil;
import com.kime.utils.ExcelUtil;
import com.kime.utils.PDFUtil;
import com.kime.utils.TypeChangeUtil;
import com.opensymphony.xwork2.ActionContext;
import com.sign.biz.PaymentBIZ;
import com.sign.biz.PaymentWeekBIZ;
import com.sign.model.Payment;
import com.sign.model.PaymentPO;
import com.sign.model.PaymentWeek;
import com.sign.other.FileSave;
import com.sign.other.PaymentState;

@Controller
@Scope("prototype")
@ParentPackage("Struts 2")
public class PaymentAction extends ActionBase {

	@Autowired
	private PaymentBIZ paymentBIZ;
	@Autowired
	private FileSave fileSave;
    @Autowired
    private DictBIZ dictBIZ;

	private File[] file;
	private String[] fileFileName;
	private String dfile;

	private String message;

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDfile() {
		return dfile;
	}
	public void setDfile(String dfile) {
		this.dfile = dfile;
	}
	public String[] getFileFileName() {
		return fileFileName;
	}
	public void setFileFileName(String[] fileFileName) {
		this.fileFileName = fileFileName;
	}
	public void setFile(File[] file) {
		this.file = file;
	}
	public DictBIZ getDictBIZ() {
		return dictBIZ;
	}
	public void setDictBIZ(DictBIZ dictBIZ) {
		this.dictBIZ = dictBIZ;
	}
	public FileSave getFileSave() {
		return fileSave;
	}
	public void setFileSave(FileSave fileSave) {
		this.fileSave = fileSave;
	}


	private String queryType;
	private String id;
	private String applicationDate;
	//用于查询
	private String applicationDate_f;
	private String applicationDate_t;
	private String amount;
	
	
	private String requestPaymentDate;
	private String contacturalPaymentDate;
	private String code;
	private String urgent;
	private String payType;
	private String UID;
	private String departmentID;
	private String beneficiary;
	private String beneficiaryAccountNO;
	private String beneficiaryE;
	private String beneficiaryAccountBank;
	private String beneficiaryChange;
	private String beneficiaryAccountNOChange;
	private String supplierCode;
	private String refNoofBank;
	private String paymentSubject;
	private String paymentTerm;
	
	private String paymentDays_1;
	private String receivingOrApprovalDate_1;
	private String PONo_1;
	private String currency_1;
	private String amount_1;
	
	private String paymentDays_2;
	private String receivingOrApprovalDate_2;
	private String PONo_2;
	private String currency_2;
	private String amount_2;
	
	private String paymentDays_3;
	private String receivingOrApprovalDate_3;
	private String PONo_3;
	private String currency_3;
	private String amount_3;
	
	private String paymentDays_4;
	private String receivingOrApprovalDate_4;
	private String PONo_4;
	private String currency_4;
	private String amount_4;
	
	private String paymentDays_5;
	private String receivingOrApprovalDate_5;
	private String PONo_5;
	private String currency_5;
	private String amount_5;
	
	private String paymentDays_6;
	private String receivingOrApprovalDate_6;
	private String PONo_6;
	private String currency_6;
	private String amount_6;
	
	private String originalApplicationCode;
	private String AdvanceWriteoffWay;
	private String AdvanceWriteOffCurrency;
	private String AdvanceWriteOffAmount;
	private String deptManaferDate;
	private String GMApproveDate;
	
	private String usageDescription;
	private String amountInFigures;
	private String documentAudit;
	private String documentAuditID;
	private String state;
	private String file_invoice;
	private String file_contract;
	private String file_other;
	private String invalidDescription;
	private String returnDescription;
	private String deptManager;
	private String deptManagerID;
	private String financeSupervisor;
	private String financeManager;
	private String generalManager;
	private String handingFee;
	private String paidDate;
	private String paidDate_f;
	private String paidDate_t;
	private String gmDate_f;
	private String gmDate_t;
	private String downloadType;
	private String poNo;
	private String visitId;
	
	
	public String getVisitId() {
		return visitId;
	}
	public void setVisitId(String visitId) {
		this.visitId = visitId;
	}
	public String getPoNo() {
		return poNo;
	}
	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}
	public String getGmDate_f() {
		return gmDate_f;
	}
	public void setGmDate_f(String gmDate_f) {
		this.gmDate_f = gmDate_f;
	}
	public String getGmDate_t() {
		return gmDate_t;
	}
	public void setGmDate_t(String gmDate_t) {
		this.gmDate_t = gmDate_t;
	}
	public String getDeptManaferDate() {
		return deptManaferDate;
	}
	public void setDeptManaferDate(String deptManaferDate) {
		this.deptManaferDate = deptManaferDate;
	}
	public String getGMApproveDate() {
		return GMApproveDate;
	}
	public void setGMApproveDate(String gMApproveDate) {
		GMApproveDate = gMApproveDate;
	}
	public String getDownloadType() {
		return downloadType;
	}
	public void setDownloadType(String downloadType) {
		this.downloadType = downloadType;
	}
	public String getPaidDate_f() {
		return paidDate_f;
	}
	public void setPaidDate_f(String paidDate_f) {
		this.paidDate_f = paidDate_f;
	}
	public String getPaidDate_t() {
		return paidDate_t;
	}
	public void setPaidDate_t(String paidDate_t) {
		this.paidDate_t = paidDate_t;
	}


	private String week;
	
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
	public String getPaidDate() {
		return paidDate;
	}
	public void setPaidDate(String paidDate) {
		this.paidDate = paidDate;
	}
	public String getHandingFee() {
		return handingFee;
	}
	public void setHandingFee(String handingFee) {
		this.handingFee = handingFee;
	}
	public String getOriginalApplicationCode() {
		return originalApplicationCode;
	}
	public void setOriginalApplicationCode(String originalApplicationCode) {
		this.originalApplicationCode = originalApplicationCode;
	}
	public String getAdvanceWriteoffWay() {
		return AdvanceWriteoffWay;
	}
	public void setAdvanceWriteoffWay(String advanceWriteoffWay) {
		AdvanceWriteoffWay = advanceWriteoffWay;
	}
	public String getAdvanceWriteOffCurrency() {
		return AdvanceWriteOffCurrency;
	}
	public void setAdvanceWriteOffCurrency(String advanceWriteOffCurrency) {
		AdvanceWriteOffCurrency = advanceWriteOffCurrency;
	}
	public String getAdvanceWriteOffAmount() {
		return AdvanceWriteOffAmount;
	}
	public void setAdvanceWriteOffAmount(String advanceWriteOffAmount) {
		AdvanceWriteOffAmount = advanceWriteOffAmount;
	}
	public String getBeneficiaryE() {
		return beneficiaryE;
	}
	public void setBeneficiaryE(String beneficiaryE) {
		this.beneficiaryE = beneficiaryE;
	}
	public String getBeneficiaryAccountBank() {
		return beneficiaryAccountBank;
	}
	public void setBeneficiaryAccountBank(String beneficiaryAccountBank) {
		this.beneficiaryAccountBank = beneficiaryAccountBank;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
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
	public String getDocumentAuditID() {
		return documentAuditID;
	}
	public void setDocumentAuditID(String documentAuditID) {
		this.documentAuditID = documentAuditID;
	}
	public String getDeptManagerID() {
		return deptManagerID;
	}
	public void setDeptManagerID(String deptManagerID) {
		this.deptManagerID = deptManagerID;
	}
	public String getPaymentTerm() {
		return paymentTerm;
	}
	public void setPaymentTerm(String paymentTerm) {
		this.paymentTerm = paymentTerm;
	}
	public PaymentBIZ getPaymentBIZ() {
		return paymentBIZ;
	}
	public void setPaymentBIZ(PaymentBIZ paymentBIZ) {
		this.paymentBIZ = paymentBIZ;
	}
	
	public String getQueryType() {
		return queryType;
	}
	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getApplicationDate() {
		return applicationDate;
	}
	public void setApplicationDate(String applicationDate) {
		this.applicationDate = applicationDate;
	}
	public String getRequestPaymentDate() {
		return requestPaymentDate;
	}
	public void setRequestPaymentDate(String requestPaymentDate) {
		this.requestPaymentDate = requestPaymentDate;
	}
	public String getContacturalPaymentDate() {
		return contacturalPaymentDate;
	}
	public void setContacturalPaymentDate(String contacturalPaymentDate) {
		this.contacturalPaymentDate = contacturalPaymentDate;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getUrgent() {
		return urgent;
	}
	public void setUrgent(String urgent) {
		this.urgent = urgent;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getUID() {
		return UID;
	}
	public void setUID(String uID) {
		UID = uID;
	}
	public String getDepartmentID() {
		return departmentID;
	}
	public void setDepartmentID(String departmentID) {
		this.departmentID = departmentID;
	}
	public String getBeneficiary() {
		return beneficiary;
	}
	public void setBeneficiary(String beneficiary) {
		this.beneficiary = beneficiary;
	}
	public String getBeneficiaryAccountNO() {
		return beneficiaryAccountNO;
	}
	public void setBeneficiaryAccountNO(String beneficiaryAccountNO) {
		this.beneficiaryAccountNO = beneficiaryAccountNO;
	}
	public String getBeneficiaryChange() {
		return beneficiaryChange;
	}
	public void setBeneficiaryChange(String beneficiaryChange) {
		this.beneficiaryChange = beneficiaryChange;
	}
	public String getBeneficiaryAccountNOChange() {
		return beneficiaryAccountNOChange;
	}
	public void setBeneficiaryAccountNOChange(String beneficiaryAccountNOChange) {
		this.beneficiaryAccountNOChange = beneficiaryAccountNOChange;
	}
	public String getSupplierCode() {
		return supplierCode;
	}
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}
	public String getRefNoofBank() {
		return refNoofBank;
	}
	public void setRefNoofBank(String refNoofBank) {
		this.refNoofBank = refNoofBank;
	}
	public String getPaymentSubject() {
		return paymentSubject;
	}
	public void setPaymentSubject(String paymentSubject) {
		this.paymentSubject = paymentSubject;
	}

	public String getPaymentDays_1() {
		return paymentDays_1;
	}
	public void setPaymentDays_1(String paymentDays_1) {
		this.paymentDays_1 = paymentDays_1;
	}
	public String getReceivingOrApprovalDate_1() {
		return receivingOrApprovalDate_1;
	}
	public void setReceivingOrApprovalDate_1(String receivingOrApprovalDate_1) {
		this.receivingOrApprovalDate_1 = receivingOrApprovalDate_1;
	}
	public String getPONo_1() {
		return PONo_1;
	}
	public void setPONo_1(String pONo_1) {
		PONo_1 = pONo_1;
	}
	public String getCurrency_1() {
		return currency_1;
	}
	public void setCurrency_1(String currency_1) {
		this.currency_1 = currency_1;
	}
	public String getPaymentDays_2() {
		return paymentDays_2;
	}
	public void setPaymentDays_2(String paymentDays_2) {
		this.paymentDays_2 = paymentDays_2;
	}
	public String getReceivingOrApprovalDate_2() {
		return receivingOrApprovalDate_2;
	}
	public void setReceivingOrApprovalDate_2(String receivingOrApprovalDate_2) {
		this.receivingOrApprovalDate_2 = receivingOrApprovalDate_2;
	}
	public String getPONo_2() {
		return PONo_2;
	}
	public void setPONo_2(String pONo_2) {
		PONo_2 = pONo_2;
	}
	public String getCurrency_2() {
		return currency_2;
	}
	public void setCurrency_2(String currency_2) {
		this.currency_2 = currency_2;
	}
	public String getPaymentDays_3() {
		return paymentDays_3;
	}
	public void setPaymentDays_3(String paymentDays_3) {
		this.paymentDays_3 = paymentDays_3;
	}
	public String getReceivingOrApprovalDate_3() {
		return receivingOrApprovalDate_3;
	}
	public void setReceivingOrApprovalDate_3(String receivingOrApprovalDate_3) {
		this.receivingOrApprovalDate_3 = receivingOrApprovalDate_3;
	}
	public String getPONo_3() {
		return PONo_3;
	}
	public void setPONo_3(String pONo_3) {
		PONo_3 = pONo_3;
	}
	public String getCurrency_3() {
		return currency_3;
	}
	public void setCurrency_3(String currency_3) {
		this.currency_3 = currency_3;
	}
	public String getPaymentDays_4() {
		return paymentDays_4;
	}
	public void setPaymentDays_4(String paymentDays_4) {
		this.paymentDays_4 = paymentDays_4;
	}
	public String getReceivingOrApprovalDate_4() {
		return receivingOrApprovalDate_4;
	}
	public void setReceivingOrApprovalDate_4(String receivingOrApprovalDate_4) {
		this.receivingOrApprovalDate_4 = receivingOrApprovalDate_4;
	}
	public String getPONo_4() {
		return PONo_4;
	}
	public void setPONo_4(String pONo_4) {
		PONo_4 = pONo_4;
	}
	public String getCurrency_4() {
		return currency_4;
	}
	public void setCurrency_4(String currency_4) {
		this.currency_4 = currency_4;
	}
	public String getPaymentDays_5() {
		return paymentDays_5;
	}
	public void setPaymentDays_5(String paymentDays_5) {
		this.paymentDays_5 = paymentDays_5;
	}
	public String getReceivingOrApprovalDate_5() {
		return receivingOrApprovalDate_5;
	}
	public void setReceivingOrApprovalDate_5(String receivingOrApprovalDate_5) {
		this.receivingOrApprovalDate_5 = receivingOrApprovalDate_5;
	}
	public String getPONo_5() {
		return PONo_5;
	}
	public void setPONo_5(String pONo_5) {
		PONo_5 = pONo_5;
	}
	public String getCurrency_5() {
		return currency_5;
	}
	public void setCurrency_5(String currency_5) {
		this.currency_5 = currency_5;
	}
	public String getPaymentDays_6() {
		return paymentDays_6;
	}
	public void setPaymentDays_6(String paymentDays_6) {
		this.paymentDays_6 = paymentDays_6;
	}
	public String getReceivingOrApprovalDate_6() {
		return receivingOrApprovalDate_6;
	}
	public void setReceivingOrApprovalDate_6(String receivingOrApprovalDate_6) {
		this.receivingOrApprovalDate_6 = receivingOrApprovalDate_6;
	}
	public String getPONo_6() {
		return PONo_6;
	}
	public void setPONo_6(String pONo_6) {
		PONo_6 = pONo_6;
	}
	public String getCurrency_6() {
		return currency_6;
	}
	public void setCurrency_6(String currency_6) {
		this.currency_6 = currency_6;
	}

 
	public String getAmount_1() {
		return amount_1;
	}
	public void setAmount_1(String amount_1) {
		this.amount_1 = amount_1;
	}
	public String getAmount_2() {
		return amount_2;
	}
	public void setAmount_2(String amount_2) {
		this.amount_2 = amount_2;
	}
	public String getAmount_3() {
		return amount_3;
	}
	public void setAmount_3(String amount_3) {
		this.amount_3 = amount_3;
	}
	public String getAmount_4() {
		return amount_4;
	}
	public void setAmount_4(String amount_4) {
		this.amount_4 = amount_4;
	}
	public String getAmount_5() {
		return amount_5;
	}
	public void setAmount_5(String amount_5) {
		this.amount_5 = amount_5;
	}
	public String getAmount_6() {
		return amount_6;
	}
	public void setAmount_6(String amount_6) {
		this.amount_6 = amount_6;
	}
	public String getAmountInFigures() {
		return amountInFigures;
	}
	public void setAmountInFigures(String amountInFigures) {
		this.amountInFigures = amountInFigures;
	}
	public String getUsageDescription() {
		return usageDescription;
	}
	public void setUsageDescription(String usageDescription) {
		this.usageDescription = usageDescription;
	}
	
	public String getDocumentAudit() {
		return documentAudit;
	}
	public void setDocumentAudit(String documentAudit) {
		this.documentAudit = documentAudit;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	public String getFile_invoice() {
		return file_invoice;
	}
	public void setFile_invoice(String file_invoice) {
		this.file_invoice = file_invoice;
	}
	public String getFile_contract() {
		return file_contract;
	}
	public void setFile_contract(String file_contract) {
		this.file_contract = file_contract;
	}
	public String getFile_other() {
		return file_other;
	}
	public void setFile_other(String file_other) {
		this.file_other = file_other;
	}
	public String getInvalidDescription() {
		return invalidDescription;
	}
	public void setInvalidDescription(String invalidDescription) {
		this.invalidDescription = invalidDescription;
	}

	public String getReturnDescription() {
		return returnDescription;
	}
	public void setReturnDescription(String returnDescription) {
		this.returnDescription = returnDescription;
	}
	public String getDeptManager() {
		return deptManager;
	}
	public void setDeptManager(String deptManager) {
		this.deptManager = deptManager;
	}
	public String getFinanceSupervisor() {
		return financeSupervisor;
	}
	public void setFinanceSupervisor(String financeSupervisor) {
		this.financeSupervisor = financeSupervisor;
	}
	public String getFinanceManager() {
		return financeManager;
	}
	public void setFinanceManager(String financeManager) {
		this.financeManager = financeManager;
	}
	public String getGeneralManager() {
		return generalManager;
	}
	public void setGeneralManager(String generalManager) {
		this.generalManager = generalManager;
	}
	
	
	
	@Action(value="savefile",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
    public String savefile() throws FileNotFoundException, IOException{
        try {
        	List<String> list=new ArrayList<>();
	    	if (file!=null) {
	    		list=fileSave.fileSave(file, fileFileName);
	            if (!list.get(0).equals("File Exists")&&!list.get(0).equals("Filepath Error")) {
	            	result.setMessage("upload Success!");
					result.setStatusCode("200");
					Map<String, List<String>> params=new HashMap<>();
					params.put("url", list);
					result.setParams(params);
				}else{			
					result.setMessage(list.get(0));
					result.setStatusCode("300");	
				}
			}else{
				result.setMessage("No File upload");
				result.setStatusCode("300");
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			result.setStatusCode("300");
		}
        reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8"));  
    	return SUCCESS;
    }
	
	
	@Action(value="getFile",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson",
					"contentType","application/octet-stream",
					"contentDisposition","attachment;filename=%{fileName}",
					"bufferSize","1024"
			})})
    public String getFile() throws FileNotFoundException, IOException{
        try {
	    	if (dfile!=null) {   
	    		String[] f=dfile.split("/");
	    		fileName= new String(f[f.length-1].getBytes(), "ISO8859-1");
	    		byte[] file=fileSave.getFile(dfile);    		
	    		ByteArrayInputStream is = new ByteArrayInputStream(file);    		
	    		reslutJson = is; 	
			}else{
				result.setMessage("No File download");
				result.setStatusCode("300");
		        reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8"));  
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			result.setStatusCode("300");
	        reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8"));  
		}
    	return SUCCESS;
    }
	
	
	
	@Action(value="deleteFile",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson",
					"contentType","application/octet-stream",
					"contentDisposition","attachment;filename=%{fileName}",
					"bufferSize","1024"
			})})
    public String deleteFile() throws FileNotFoundException, IOException{
        try {
	    	if (dfile!=null) {   
	    		String r=fileSave.fileDelete(dfile);	
	    		if (r.equals("1")) {
		    		if (id!=null&&!id.equals("")) {
						Payment t=paymentBIZ.getPayment(" where id='"+id+"'").get(0);
						t.setFile_contract(t.getFile_contract().replaceAll(dfile+"|", ""));
						t.setFile_invoice(t.getFile_invoice().replaceAll(dfile+"|", ""));
						t.setFile_other(t.getFile_other().replaceAll(dfile+"|", ""));
						paymentBIZ.updatePayment(t);
					}
	    			result.setMessage("Delete Success!");
					result.setStatusCode("200");
				}else{
					result.setMessage(r);
					result.setStatusCode("300");
				}
			}else{
				result.setMessage("No File");
				result.setStatusCode("300");
		        
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			result.setStatusCode("300");
	        reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8"));  
		}
        reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8"));  
    	return SUCCESS;
    }
	
	@Action(value="savePayment",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String savePayment() throws UnsupportedEncodingException{
		try {
			Payment payment=new Gson().fromJson(json, Payment.class);		
		
			if (payment.getUID()==null||payment.getUID().equals("")) {
				result.setMessage("User can`t be NULL,Plese reflash page!");
				result.setStatusCode("300");
				reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8")); 	
				return SUCCESS;
			}
			
			if (!payment.getId().equals("")&&payment.getId()!=null) {
/*				if (file_invoice!=null&&!"".equals(file_invoice)) {
					String filenames="";
					String[] files=file_invoice.split("\\|");
					for (String string : files) {
						filenames+=fileSave.getFilePath(string)+"|";
					}
					payment.setFile_invoice(filenames);
				}
				if (file_contract!=null&&!"".equals(file_contract)) {
					String filenames="";
					String[] files=file_contract.split("\\|");
					for (String string : files) {
						filenames+=fileSave.getFilePath(string)+"|";
					}
					payment.setFile_contract(filenames);
				}
				if (file_other!=null&&!"".equals(file_other)) {
					String filenames="";
					String[] files=file_other.split("\\|");
					for (String string : files) {
						filenames+=fileSave.getFilePath(string)+"|";
					}
					payment.setFile_other(filenames);
				}*/
				if (file_invoice!=null&&!"".equals(file_invoice)) {
					payment.setFile_invoice(file_invoice);
				}
				if (file_contract!=null&&!"".equals(file_contract)) {
					payment.setFile_contract(file_contract);
				}
				if (file_other!=null&&!"".equals(file_other)) {
					payment.setFile_other(file_other);
				}
				if(payment.getUrgent()==null||payment.getUrgent().equals("")){
					payment.setUrgent("0");
				}
				payment.setDateTemp(CommonUtil.getDateTemp());
				paymentBIZ.updatePayment(payment);				
				result.setMessage(Message.SAVE_MESSAGE_SUCCESS);
				result.setStatusCode("200");
				Map<String, String> map=new HashMap<>();
				map.put("id", payment.getId());
				result.setParams(map);
				logUtil.logInfo("更新付款申请单:"+payment.getId());			
			}else{
				if (file_invoice!=null&&!"".equals(file_invoice)) {
					payment.setFile_invoice(file_invoice);
				}
				if (file_contract!=null&&!"".equals(file_contract)) {
					payment.setFile_contract(file_contract);
				}
				if (file_other!=null&&!"".equals(file_other)) {
					payment.setFile_other(file_other);
				}
				payment.setState(PaymentState.SAVEPAYMENT);
				payment.setId(UUID.randomUUID().toString().replaceAll("-", ""));
				payment.setDateTemp(CommonUtil.getDateTemp());
				List<Dict> lDicts=dictBIZ.getDict(" where key='"+payment.getPaymentSubject()+"'");
				if (!"".equals(lDicts.get(0).getValue())&&lDicts.get(0).getValue()!=null) {
					//payment.setDocumentAudit(lDicts.get(0).getValue());
					paymentBIZ.savePayment(payment);

					
					result.setMessage(Message.SAVE_MESSAGE_SUCCESS);
					result.setStatusCode("200");
					Map<String, String> map=new HashMap<>();
					map.put("id", payment.getId());
					result.setParams(map);
					logUtil.logInfo("新增付款申请单:"+payment.getId());
				}else{
					result.setMessage(Message.SAVE_MESSAGE_PAYMENT_ERROR);
					result.setStatusCode("300");
					logUtil.logInfo("新增付款申异常:为维护对应财务人员");
				}
			}

			
		} catch (Exception e) {
			logUtil.logInfo("新增付款申请单异常:"+e.getMessage());
			result.setMessage(e.getMessage());
			result.setStatusCode("300");
		}
		
		reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8")); 	
		return SUCCESS;
	}
	
	
	
	@Action(value="printPayment",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String printPayment() throws UnsupportedEncodingException{
		try {
			Payment payment=paymentBIZ.getPayment(" where id='"+id+"'").get(0);
			if (payment.getIsPrint()==null||payment.getIsPrint().equals("")) {
				payment.setIsPrint("1");
				payment.setCode(paymentBIZ.getMaxCode());		
				paymentBIZ.printPayment(payment);
			}
			result.setMessage(Message.SAVE_MESSAGE_SUCCESS);
			result.setStatusCode("200");
			Map<String, String> map=new HashMap<>();
			map.put("code", payment.getCode());
			result.setParams(map);
			logUtil.logInfo("打印付款申请单:"+payment.getId());
		} catch (Exception e) {
			logUtil.logInfo("打印付款申请单异常:"+e.getMessage());
			result.setMessage(e.getMessage());
			result.setStatusCode("300");
		}
		
		reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8")); 	
		return SUCCESS;
	}
	
	@Action(value="accPayment",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String accPayment() throws UnsupportedEncodingException{
			try {
				Payment payment=paymentBIZ.getPayment(" where id='"+id+"'").get(0);
				payment.setState(PaymentState.FINACEPAYMENT);
				payment.setDocumentAudit(documentAudit);
				
				paymentBIZ.accPayment(payment);
				
				result.setMessage(Message.SAVE_MESSAGE_SUCCESS);
				result.setStatusCode("200");
				logUtil.logInfo("财务处理付款申请单:"+payment.getId());
			} catch (Exception e) {
				logUtil.logInfo("财务处理付款申请单异常:"+e.getMessage());
				result.setMessage(e.getMessage());
				result.setStatusCode("300");
			}

		
		reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8")); 	
		return SUCCESS;
	}
	
	
	@Action(value="deletePayment",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String deletePayment() throws UnsupportedEncodingException{
			try {
				Payment payment=paymentBIZ.getPayment(" where id='"+id+"'").get(0);
				paymentBIZ.deletePayment(payment);
				
				result.setMessage(Message.DEL_MESSAGE_SUCCESS);
				result.setStatusCode("200");
				logUtil.logInfo("删除付款申请单:"+payment.getId());
			} catch (Exception e) {
				logUtil.logInfo("删除付款申请单异常:"+e.getMessage());
				result.setMessage(e.getMessage());
				result.setStatusCode("300");
			}
	
		reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8")); 	
		return SUCCESS;
	}
	
	@Action(value="assignPayment",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String assignPayment() throws UnsupportedEncodingException{
		try {
			Payment payment=paymentBIZ.getPayment(" where id='"+id+"'").get(0);
			payment.setDocumentAuditID(documentAuditID);
			
			paymentBIZ.assignPayment(payment);
			
			result.setMessage(Message.SAVE_MESSAGE_SUCCESS);
			result.setStatusCode("200");
			logUtil.logInfo("付款申请单财务转人:"+payment.getId());
		} catch (Exception e) {
			logUtil.logInfo("付款申请单财务转人异常:"+e.getMessage());
			result.setMessage(e.getMessage());
			result.setStatusCode("300");
		}
		
		reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8")); 	
		return SUCCESS;
	}
	
	
	@Action(value="submitPayment",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String submitPayment() throws UnsupportedEncodingException{
		try {
			Payment payment=paymentBIZ.getPayment(" where id='"+id+"'").get(0);
			payment.setState(PaymentState.SUBPAYMENT);
			paymentBIZ.submitPayment(payment);
			
			result.setMessage(Message.SAVE_MESSAGE_SUCCESS);
			result.setStatusCode("200");
			logUtil.logInfo("提交付款申请单:"+payment.getId());
		} catch (Exception e) {
			logUtil.logInfo("提交付款申请单异常:"+e.getMessage());
			result.setMessage(e.getMessage());
			result.setStatusCode("300");
		}
		
		reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8")); 	
		return SUCCESS;
	}
	
	@Action(value="approvePayment",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String approvePayment() throws UnsupportedEncodingException{
		try {
			User user=(User)session.getAttribute("user");
			Payment payment=paymentBIZ.getPayment(" where id='"+id+"'").get(0);
			payment.setState(PaymentState.APPROVEPAYMENT);
			//如果是代理审批
			if (!user.getUid().equals(payment.getDeptManagerID())) {
				List<Dict> ldict=dictBIZ.getDict(" where type='AGENTEMPLOYEE' and key='"+payment.getDeptManagerID()+"'");
				if (ldict.size()>0) {
					payment.setDeptManager(ldict.get(0).getValueName()+"(On behalf of "+ldict.get(0).getKeyName()+", "+ldict.get(0).getKeyExplain()+" to "+ldict.get(0).getValueExplain()+")");
				}else {
					result.setMessage("Agent Error!");
					result.setStatusCode("300");
					reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8")); 	
					return SUCCESS;
				}
				
			}else {
				payment.setDeptManager(user.getName());	
			}
			payment.setDeptManagerDate(CommonUtil.getDateTemp());
			
			paymentBIZ.approvePayment(payment);
			
			result.setMessage(Message.SAVE_MESSAGE_SUCCESS);
			result.setStatusCode("200");
			logUtil.logInfo("付款申请单审批通过:"+payment.getId());
		} catch (Exception e) {
			logUtil.logInfo("付款申请单审批异常:"+e.getMessage());
			result.setMessage(e.getMessage());
			result.setStatusCode("300");
		}
		
		reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8")); 	
		return SUCCESS;
	}
	
	@Action(value="invalidPayment",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String invalidPayment() throws UnsupportedEncodingException{
		try {
			Payment payment=paymentBIZ.getPayment(" where id='"+id+"'").get(0);
			payment.setState(PaymentState.INVALIDPAYMENT);
			payment.setInvalidDescription(invalidDescription);
			
			paymentBIZ.invalidPayment(payment);
			
			result.setMessage(Message.SAVE_MESSAGE_SUCCESS);
			result.setStatusCode("200");
			logUtil.logInfo("作废申请单:"+payment.getId());
		} catch (Exception e) {
			logUtil.logInfo("作废申请单异常:"+e.getMessage());
			result.setMessage(e.getMessage());
			result.setStatusCode("300");
		}
		
		reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8")); 	
		return SUCCESS;
	}
	
	@Action(value="returnPayment",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String returnPayment() throws UnsupportedEncodingException{
		try {
			Payment payment=paymentBIZ.getPayment(" where id='"+id+"'").get(0);
			payment.setState(PaymentState.RETURNPAYMENT);
			payment.setReturnDescription(returnDescription);
			payment.setDeptManager("");
			payment.setDocumentAudit("");
			
			paymentBIZ.returnPayment(payment);
			
			result.setMessage(Message.SAVE_MESSAGE_SUCCESS);
			result.setStatusCode("200");
			logUtil.logInfo("退回申请单:"+payment.getId());
		} catch (Exception e) {
			logUtil.logInfo("退回申请单异常:"+e.getMessage());
			result.setMessage(e.getMessage());
			result.setStatusCode("300");
		}
		
		reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8")); 	
		return SUCCESS;
	}
	
	@Action(value="rejectPayment",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String rejectPayment() throws UnsupportedEncodingException{
		try {
			Payment payment=paymentBIZ.getPayment(" where id='"+id+"'").get(0);
			payment.setState(PaymentState.REJECTPAYMENT);
			payment.setDeptManager("");
			payment.setDocumentAudit("");
			
			paymentBIZ.rejectPayment(payment);
			
			result.setMessage(Message.SAVE_MESSAGE_SUCCESS);
			result.setStatusCode("200");
			logUtil.logInfo("付款申请单拒绝:"+payment.getId());
		} catch (Exception e) {
			logUtil.logInfo("付款申请单拒绝异常:"+e.getMessage());
			result.setMessage(e.getMessage());
			result.setStatusCode("300");
		}
		
		reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8")); 	
		return SUCCESS;
	}
	
	
	@Action(value="financeRejectPayment",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String financeRejectPayment() throws UnsupportedEncodingException{
		try {
			User user=(User) session.getAttribute("user");
			String[] ids=new Gson().fromJson(json, String[].class);
			paymentBIZ.financeRejectPayment(ids, message,user);
			result.setMessage("Finance reject success");
			logUtil.logInfo("付款申请单 Finance reject success");
			result.setStatusCode("200");
		} catch (Exception e) {
			logUtil.logInfo("付款申请单 Finance reject 异常:"+e.getMessage());
			result.setMessage(e.getMessage());
			result.setStatusCode("300");
		}
		
		reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8")); 	
		return SUCCESS;
	}
	
	
	@Action(value="paidPayment",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String paidPayment() throws UnsupportedEncodingException{
		try {
			List<Payment> lPayments=new Gson().fromJson(json, new TypeToken<ArrayList<Payment>>() {}.getType());
			paymentBIZ.paidPayment(lPayments);
			result.setMessage("Paid success");
			logUtil.logInfo("付款申请单  Paid success");
			result.setStatusCode("200");
		} catch (Exception e) {
			logUtil.logInfo("付款申请单 Paid 异常:"+e.getMessage());
			result.setMessage(e.getMessage());
			result.setStatusCode("300");
		}
		
		reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8")); 	
		return SUCCESS;
	}
	
	@Action(value = "mailInformPayment", results = {
			@org.apache.struts2.convention.annotation.Result(type = "stream", params = { "inputName", "reslutJson" }) })
	public String mailInformPayment() throws UnsupportedEncodingException {
		try {

			String[] ids = new Gson().fromJson(json, String[].class);
			paymentBIZ.mailInformPayment(ids);
			result.setMessage("Mail Inform success");
			result.setStatusCode("200");
		} catch (Exception e) {
			logUtil.logInfo("付款申请单拒绝异常:" + e.getMessage());
			result.setMessage(e.getMessage());
			result.setStatusCode("300");
		}

	reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8")); 	
	return SUCCESS;
	}
	
	@Action(value = "paidResetPayment", results = {
			@org.apache.struts2.convention.annotation.Result(type = "stream", params = { "inputName", "reslutJson" }) })
	public String paidResetPayment() throws UnsupportedEncodingException {
		try {

			String[] ids = new Gson().fromJson(json, String[].class);
			paymentBIZ.paidResetPayment(ids);
			result.setMessage("Paid Reset success");
			logUtil.logInfo("付款申请单 Paid Reset success");
			result.setStatusCode("200");
		} catch (Exception e) {
			logUtil.logInfo("付款申请单 Paid Reset异常:" + e.getMessage());
			result.setMessage(e.getMessage());
			result.setStatusCode("300");
		}

	reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8")); 	
	return SUCCESS;
	}
	
	@Action(value = "getPaidWeek", results = {
			@org.apache.struts2.convention.annotation.Result(type = "stream", params = { "inputName", "reslutJson" }) })
	public String getPaidWeek() throws UnsupportedEncodingException {
		
		
		
		int total=paymentBIZ.getPaidWeek("").size();
		List<String> list=paymentBIZ.getPaidWeek("",Integer.valueOf(pageSize),Integer.valueOf(pageCurrent));
		List<PaymentWeek> lPaymentWeeks=new ArrayList<>();
		
		for (String tmp : list) {
			PaymentWeek paymentWeek=new PaymentWeek();
			paymentWeek.setWeek(tmp);
			lPaymentWeeks.add(paymentWeek);
		}
		
		
		queryResult.setList(lPaymentWeeks);
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
	
	
	
	@Action(value = "getPaymentWeek", results = {
			@org.apache.struts2.convention.annotation.Result(type = "stream", params = { "inputName", "reslutJson" }) })
	public String getPaymentWeek() throws UnsupportedEncodingException {
		if (week==null||week.equals("")) {
			reslutJson=new ByteArrayInputStream(new Gson().toJson(new ArrayList<>()).getBytes("UTF-8")); 				
			return SUCCESS;
		}

		List<PaymentPO> lPaymentPOs=paymentBIZ.getPaymentPO(" select * from v_po where id in( select pid from t_payment_week where week='"+week+"')");
		reslutJson=new ByteArrayInputStream(new Gson().toJson(lPaymentPOs).getBytes("UTF-8"));  
		return SUCCESS;
	}
	
	
	
	
	
	
	
	@Action(value="exportPaymentWeekExcel",results={@org.apache.struts2.convention.annotation.Result(type="stream",
	params={
			"inputName", "reslutJson",
			"contentType","application/vnd.ms-excel",
			"contentDisposition","attachment;filename=%{fileName}",
			"bufferSize","1024"
	})})
	public String exportPaymentWeekExcel() throws UnsupportedEncodingException{
		try {

			List<HeadColumn> lHeadColumns=new ArrayList<>();
			lHeadColumns.add(new HeadColumn("paidDate", "80", "right", "Actual paid Date"));
			lHeadColumns.add(new HeadColumn("amount", "80", "right", "PO Amount"));
			lHeadColumns.add(new HeadColumn("beneficiaryE", "80", "right", "Ename"));
			lHeadColumns.add(new HeadColumn("usageDescription", "80", "right", "Usage Description"));
			lHeadColumns.add(new HeadColumn("applicant", "80", "right", "Applicant"));
			lHeadColumns.add(new HeadColumn("PONo", "80", "right", "PO"));
			lHeadColumns.add(new HeadColumn("supplierCode", "80", "right", "Supplier Code"));
			lHeadColumns.add(new HeadColumn("code", "80", "right", "Sequence"));
			lHeadColumns.add(new HeadColumn("currency", "80", "right", "Currency"));
			
			
			//List<HeadColumn> lHeadColumns=new Gson().fromJson(thead, new TypeToken<ArrayList<HeadColumn>>() {}.getType());        	
			List<PaymentPO> lPaymentPOs=paymentBIZ.getPaymentPO(" select * from v_po where id in( select pid from t_payment_week where week='"+week+"')");

        	Class c = (Class) new PaymentPO().getClass();  
        	ByteArrayOutputStream os=ExcelUtil.exportExcel("Paid Report", c, lPaymentPOs, "yyy-MM-dd",lHeadColumns);
        	byte[] fileContent = os.toByteArray();
        	ByteArrayInputStream is = new ByteArrayInputStream(fileContent);
        	   	
        	HttpServletResponse response = (HttpServletResponse)
        			ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_RESPONSE);
        	response.setHeader("Set-Cookie", "fileDownload=true; path=/");
        	
    		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");		 
    		fileName = "Payment_"+week+ ".xls";
    		fileName= new String(fileName.getBytes(), "ISO8859-1");
    		//文件流
            reslutJson = is;            
            logUtil.logInfo("导出PaidReport！"+fileName);
        }
        catch(Exception e) {
        	logUtil.logInfo("导出PaidReport！"+e.getMessage());
			result.setMessage(e.getMessage());
			result.setStatusCode("300");
			reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8")); 	
			return SUCCESS;
        }

        return SUCCESS;
	}
	
	@Action(value = "checkWeeklyReportPayment", results = {
			@org.apache.struts2.convention.annotation.Result(type = "stream", params = { "inputName", "reslutJson" }) })
	public String checkWeeklyReportPayment() throws UnsupportedEncodingException {
		String[] ids=new Gson().fromJson(json, String[].class);
		StringBuffer sb = new StringBuffer();  
	    for (int i = 0; i < ids.length; i++) {  
	        sb.append("'").append(ids[i]).append("'").append(",");  
	    }  
	    if (paymentBIZ.getPayment(" where id in ("+sb.toString().substring(0, sb.length() - 1)+") and state not in ('4','7') ").size()>0) {
			result.setMessage("Not all Status is Finance Approval OR GM Approval");
			result.setStatusCode("300");
	    }else {
			result.setStatusCode("200");
	    }
	    reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8")); 	
		return SUCCESS;
	}
	
	
	@Action(value="weeklyReportPayment",results={@org.apache.struts2.convention.annotation.Result(type="stream",
	params={
			"inputName", "reslutJson",
			"contentType","application/vnd.ms-excel",
			"contentDisposition","attachment;filename=%{fileName}",
			"bufferSize","1024"
	})})
	public String weeklyReportPayment() throws UnsupportedEncodingException{
		try {
			List<HeadColumn> lColumns=new ArrayList<>();
			lColumns.add(new HeadColumn("code", "80", "right", "Sequence No."));
			lColumns.add(new HeadColumn("applicant", "150", "right", "Applicant"));
			lColumns.add(new HeadColumn("did", "80", "right", "Business Unit"));
			lColumns.add(new HeadColumn("amountInFigures", "80", "right", "Total Amount"));
			lColumns.add(new HeadColumn("usageDescription", "400", "right", "Usage Description"));
			lColumns.add(new HeadColumn("amount", "80", "right", "PO Amount"));
			lColumns.add(new HeadColumn("supplierCode", "80", "right", "Supplier Code"));
			lColumns.add(new HeadColumn("beneficiaryE", "300", "right", "Ename"));

			String[] ids=new Gson().fromJson(json, String[].class);
			StringBuffer sb = new StringBuffer();  
		    for (int i = 0; i < ids.length; i++) {  
		        sb.append("'").append(ids[i]).append("'").append(",");  
		    }  
		    
		    if (paymentBIZ.getPayment(" where id in ("+sb.toString().substring(0, sb.length() - 1)+") and state not in('4','7') ").size()>0) {
				//result.setMessage("Not all Finance Approval");
				//result.setStatusCode("300");
				//reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8")); 	
	        	HttpServletResponse response = (HttpServletResponse)
	        			ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_RESPONSE);
	        	response.setHeader("Set-Cookie", "fileDownload=true; path=/");
				return SUCCESS;
			}

		    
		    List<PaymentPO> lPaymentPOs=paymentBIZ.getWeeklyPayment(ids);
        	Class c = (Class) new PaymentPO().getClass();  
        	ByteArrayOutputStream os=ExcelUtil.exportExcel("Weekly Report", c, lPaymentPOs, "yyy-MM-dd",lColumns);
        	byte[] fileContent = os.toByteArray();
        	ByteArrayInputStream is = new ByteArrayInputStream(fileContent);
        	   	
        	HttpServletResponse response = (HttpServletResponse)
        			ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_RESPONSE);
        	response.setHeader("Set-Cookie", "fileDownload=true; path=/");
        	
    		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");		 
    		fileName = "Payment"+sf.format(new Date()).toString()+ ".xls";
    		fileName= new String(fileName.getBytes(), "ISO8859-1");
    		//文件流
            reslutJson = is;            
            logUtil.logInfo("导出weeklyReport！"+fileName);
        }
        catch(Exception e) {
        	logUtil.logInfo("导出weeklyReport！"+e.getMessage());
//			result.setMessage(e.getMessage());
//			result.setStatusCode("300");
//			reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8")); 	
			return SUCCESS;
        }

        return SUCCESS;
	}
	@Action(value="getPaymentPD",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String getPaymentPD() throws UnsupportedEncodingException {
		
		User user=(User)session.getAttribute("user");
		String where="";
		
		if (!"".equals(applicationDate_f)&&applicationDate_f!=null) {
			where += " AND P.applicationDate>='"+applicationDate_f+"'";
		}
		if (!"".equals(applicationDate_t)&&applicationDate_t!=null) {
			where += " AND P.applicationDate <= '"+applicationDate_t+"'";
		}
		if (!"".equals(paidDate_f)&&paidDate_f!=null) {
			where += " AND P.paidDate>='"+paidDate_f+"'";
		}
		if (!"".equals(paidDate_t)&&paidDate_t!=null) {
			where += " AND P.paidDate <= '"+paidDate_t+"'";
		}
		if (!"".equals(code)&&code!=null) {
			where += " AND P.code = '"+code+"'";
		}
		if (!"".equals(state)&&state!=null) {			
			where += " AND P.state = '"+state+"'";
		}else {
			if ("cashier".equals(queryType)) {
				where+=" AND P.state in ('4','7') ";
			}		
		}
		if (!"".equals(urgent)&&urgent!=null) {
			where += " AND P.urgent = '"+urgent+"'";
		}
		if (!"".equals(UID)&&UID!=null) {
			where += " AND P.UID='"+UID+"'";
		}
		if (!"".equals(departmentID)&&departmentID!=null) {
			where += " AND P.departmentID='"+departmentID+"'";
		}
		if (!"".equals(paymentSubject)&&paymentSubject!=null) {
			where += " AND P.paymentSubject='"+paymentSubject+"'";
		}
		if (!"".equals(paymentTerm)&&paymentTerm!=null) {
			where += " AND P.paymentTerm='"+paymentTerm+"'";
		}
		if (!"".equals(supplierCode)&&supplierCode!=null) {
			where += " AND P.supplierCode like '%"+supplierCode+"%' ";
		}

		String sqlId="  select P.id from t_Payment P WHERE P.UID IN(SELECT u.uid from t_user u where u.did in ( SELECT u1.did from t_user u1 where u1.uid= '"+user.getUid()+"') ) "+where+" ";    		
		
		String sqlw="";
		
		if (!"".equals(poNo)&&poNo!=null) {
			sqlw += " AND PONo like '%"+poNo+"%' ";
		}
		
		
		String sql=" select * from v_po where id in("+sqlId+") "+sqlw;
		
//		if (downloadType!=null&&!downloadType.equals("")) {
//			if (downloadType.equals("1")) {
//				sql+=" order by PONo ";
//			}else {
//				sql+=" order by supplierCode ";
//			}
//		}
		if (orders!=null&&!orders.equals("")) {
			sql+=" order by "+orders;
		}

		
		int total=paymentBIZ.getPaymentPO(sql).size();
		List<PaymentPO> list=paymentBIZ.getPaymentPO(sql, Integer.parseInt(pageSize), Integer.parseInt(pageCurrent));
		
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
	
	
	
	@Action(value="downloadOfPD",results={@org.apache.struts2.convention.annotation.Result(type="stream",
	params={
			"inputName", "reslutJson",
			"contentType","application/vnd.ms-excel",
			"contentDisposition","attachment;filename=%{fileName}",
			"bufferSize","1024"
	})})
	public String downloadOfPD() throws UnsupportedEncodingException{
		try {
//			List<HeadColumn> lColumns=new ArrayList<>();
//			lColumns.add(new HeadColumn("PONo", "80", "right", "PO"));
//			lColumns.add(new HeadColumn("amountInFigures", "80", "right", "Total Amounts"));
//			lColumns.add(new HeadColumn("amount", "80", "right", "Amounts"));
//			lColumns.add(new HeadColumn("currency", "80", "right", "Currency"));
//			lColumns.add(new HeadColumn("code", "80", "right", "Sequence"));
//			lColumns.add(new HeadColumn("applicant", "80", "right", "Applicant"));
//			lColumns.add(new HeadColumn("supplierCode", "80", "right", "Supplier Code"));
//			lColumns.add(new HeadColumn("beneficiaryE", "150", "right", "Company Name-English"));
//			lColumns.add(new HeadColumn("applicationDate", "80", "right", "Application Date"));
//			lColumns.add(new HeadColumn("requestPaymentDate", "80", "right", "Required Payment Date"));
//			lColumns.add(new HeadColumn("contacturalPaymentDate", "80", "right", "Contractual Payment Date"));
//			lColumns.add(new HeadColumn("paidDate", "80", "right", "Actual Paid Date"));

			List<HeadColumn> lColumns=new Gson().fromJson(thead, new TypeToken<ArrayList<HeadColumn>>() {}.getType());

			
			String where="";
			
			if (!"".equals(applicationDate_f)&&applicationDate_f!=null) {
				where += " AND P.applicationDate>='"+applicationDate_f+"'";
			}
			if (!"".equals(applicationDate_t)&&applicationDate_t!=null) {
				where += " AND P.applicationDate <= '"+applicationDate_t+"'";
			}
			if (!"".equals(paidDate_f)&&paidDate_f!=null) {
				where += " AND P.paidDate>='"+paidDate_f+"'";
			}
			if (!"".equals(paidDate_t)&&paidDate_t!=null) {
				where += " AND P.paidDate <= '"+paidDate_t+"'";
			}
			if (!"".equals(code)&&code!=null) {
				where += " AND P.code = '"+code+"'";
			}
			if (!"".equals(state)&&state!=null) {			
				where += " AND P.state = '"+state+"'";
			}else {
				if ("cashier".equals(queryType)) {
					where+=" AND P.state in ('4','7') ";
				}		
			}
			if (!"".equals(urgent)&&urgent!=null) {
				where += " AND P.urgent = '"+urgent+"'";
			}
			if (!"".equals(UID)&&UID!=null) {
				where += " AND P.UID='"+UID+"'";
			}
			if (!"".equals(departmentID)&&departmentID!=null) {
				where += " AND P.departmentID='"+departmentID+"'";
			}
			if (!"".equals(paymentSubject)&&paymentSubject!=null) {
				where += " AND P.paymentSubject='"+paymentSubject+"'";
			}
			if (!"".equals(paymentTerm)&&paymentTerm!=null) {
				where += " AND P.paymentTerm='"+paymentTerm+"'";
			}
			if (!"".equals(supplierCode)&&supplierCode!=null) {
				where += " AND P.supplierCode like '%"+supplierCode+"%' ";
			}


			String sqlId="  select P.id from t_Payment P WHERE 1=1 "+where+" ";    		 		
			
//			List<Payment> lPayments=paymentBIZ.getPaymentByHql(hql);	
//			StringBuilder sb=new StringBuilder();
//			for (Payment payment : lPayments) {
//				 sb.append("'").append(payment.getId()).append("'").append(",");  
//			}
			
			String sqlw="";
			
			if (!"".equals(poNo)&&poNo!=null) {
				sqlw += " AND PONo like '%"+poNo+"%' ";
			}
			
			String sql=" select * from v_po where id in("+sqlId+") "+sqlw;
//			if (downloadType!=null&&!downloadType.equals("")) {
//				if (downloadType.equals("1")) {
//					sql+=" order by PONo ";
//				}else {
//					sql+=" order by supplierCode ";
//				}
//			}
			if (orders!=null&&!orders.equals("")) {
				sql+=" order by "+orders;
			}
			
			List<PaymentPO> lPaymentPOs=paymentBIZ.getPaymentPO(sql);
			
			
		    
        	Class c = (Class) new PaymentPO().getClass();  
        	ByteArrayOutputStream os=PDFUtil.exportPDF("PD Report", c, lPaymentPOs, "yyy-MM-dd",lColumns);
        	byte[] fileContent = os.toByteArray();
        	ByteArrayInputStream is = new ByteArrayInputStream(fileContent);
        	   	
        	HttpServletResponse response = (HttpServletResponse)
        			ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_RESPONSE);
        	response.setHeader("Set-Cookie", "fileDownload=true; path=/");
        	
    		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");		 
    		fileName = "Payment"+sf.format(new Date()).toString()+ ".pdf";
    		fileName= new String(fileName.getBytes(), "ISO8859-1");
    		//文件流
            reslutJson = is;            
            logUtil.logInfo("导出PDReport！"+fileName);
        }
        catch(Exception e) {
        	logUtil.logInfo("导出PDReport！"+e.getMessage());
			return SUCCESS;
        }

        return SUCCESS;
	}
	
	
	
	@Action(value="getPaymentByID",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String getPaymentByID() throws UnsupportedEncodingException{

		Payment payment=new Payment();		
		try {
			List<Payment> list=paymentBIZ.getPayment(" where id='"+id+"'");
			if (list.size()>0) {
				payment=list.get(0);
			}else {
				result.setMessage("No payment");
				result.setStatusCode("300");
				return SUCCESS;
			}
			String string=new Gson().toJson(payment);
			reslutJson=new ByteArrayInputStream(new Gson().toJson(payment).getBytes("UTF-8")); 	
			logUtil.logInfo("查询付款申请单:"+payment.getId());
		} catch (Exception e) {
			logUtil.logInfo("查询付款申请单异常:"+e.getMessage());
			result.setMessage(e.getMessage());
			result.setStatusCode("300");
			reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8")); 	
		}
		
		return SUCCESS;
	}
	
	@Action(value="getPaymentByCode",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String getPaymentByCode() throws UnsupportedEncodingException{

		Payment payment=new Payment();		
		try {
			List<Payment> list=paymentBIZ.getPayment(" where code='"+code+"'");
			if (list.size()>0) {
				payment=list.get(0);
			}else {
				result.setMessage("No payment");
				result.setStatusCode("300");
				reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8")); 	
				return SUCCESS;
			}
			String string=new Gson().toJson(payment);
			reslutJson=new ByteArrayInputStream(new Gson().toJson(payment).getBytes("UTF-8")); 	
			logUtil.logInfo("查询付款申请单:"+payment.getId());
		} catch (Exception e) {
			logUtil.logInfo("查询付款申请单异常:"+e.getMessage());
			result.setMessage(e.getMessage());
			result.setStatusCode("300");
			reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8")); 	
		}
		
		return SUCCESS;
	}
	
	
	@Action(value="getPayment",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String getPayment() throws UnsupportedEncodingException{
	
		User user=(User)session.getAttribute("user");
		String hql="";
		
		String where="";
		
		if (!"".equals(applicationDate_f)&&applicationDate_f!=null) {
			where += " AND P.applicationDate>='"+applicationDate_f+"'";
		}
		if (!"".equals(applicationDate_t)&&applicationDate_t!=null) {
			where += " AND P.applicationDate <= '"+applicationDate_t+"'";
		}
		if (!"".equals(paidDate_f)&&paidDate_f!=null) {
			where += " AND P.paidDate >='"+paidDate_f+"' ";
		}
		if (!"".equals(paidDate_t)&&paidDate_t!=null) {
			where += " AND P.paidDate <= '"+paidDate_t+"' ";
		}
		if (!"".equals(gmDate_f)&&gmDate_f!=null) {
			where += " AND substr(P.GMApproveDate,0,10)>='"+gmDate_f+"'";
		}
		if (!"".equals(gmDate_t)&&gmDate_t!=null) {
			where += " AND substr(P.GMApproveDate,0,10) <= '"+gmDate_t+"'";
		}
		if (!"".equals(code)&&code!=null) {
			where += " AND P.code = '"+code+"'";
		}
		if (!"".equals(state)&&state!=null) {			
			where += " AND P.state = '"+state+"'";
		}else {
			if ("cashier".equals(queryType)) {
				where+=" AND P.state in ('4','7') ";
			}		
		}
		if (!"".equals(urgent)&&urgent!=null) {
			where += " AND P.urgent = '"+urgent+"'";
		}
		if (!"".equals(UID)&&UID!=null) {
			where += " AND P.UID='"+UID+"'";
		}
		if (!"".equals(departmentID)&&departmentID!=null) {
			where += " AND P.departmentID='"+departmentID+"'";
		}
		if (!"".equals(paymentSubject)&&paymentSubject!=null) {
			where += " AND P.paymentSubject='"+paymentSubject+"'";
		}
		if (!"".equals(paymentTerm)&&paymentTerm!=null) {
			where += " AND P.paymentTerm='"+paymentTerm+"'";
		}
				
			
		if ("all".equals(queryType)) {
			hql="  select P from Payment P WHERE P.deptManagerID='"+user.getUid()+"' "+where+" order By P.dateTemp desc";    		
		}
		if ("acc".equals(queryType)) {
			hql="  select  P from Payment P where P.documentAuditID='"+user.getUid()+"' "+where+" order By P.dateTemp desc";  			
		}
		if ("sign".equals(queryType)) {
			hql="  select  P from Payment P left join Dict D ON P.deptManagerID=D.key And D.type='AGENTEMPLOYEE' And '"+CommonUtil.getDate()+"' >=D.keyExplain And '"+CommonUtil.getDate()+"' <=D.valueExplain  where P.state='1' and (P.deptManagerID='"+user.getUid()+"' OR D.value='"+user.getUid()+"')order By P.dateTemp desc";
		}
		if ("user".equals(queryType)) {
			hql=" select P from Payment P where P.UID='"+user.getUid()+"' "+where+" order By P.dateTemp desc";
		}
		if ("admin".equals(queryType)) {
			hql=" select P from Payment P where 1=1 "+where+" order By P.dateTemp desc";
		}
		if ("cashier".equals(queryType)) {
			hql=" select P from Payment P where 1=1 "+where+" order By P.dateTemp desc";
		}
		List<Payment> list=paymentBIZ.getPaymentByHql(hql, Integer.parseInt(pageSize),Integer.parseInt(pageCurrent));
		int total=paymentBIZ.getPaymentByHql(hql).size();
		for (Payment payment : list) {
			payment.setAmountInFigures(TypeChangeUtil.formatMoney(payment.getAmountInFigures().replace(",", ""),2,""));
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
		logUtil.logInfo("查询付款申请单:"+hql);
		return SUCCESS;
	}
	
	 /**
     * excel导出
     * @return
     */
	@Action(value="exportPaymentExcel",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson",
					"contentType","application/vnd.ms-excel",
					"contentDisposition","attachment;filename=%{fileName}",
					"bufferSize","1024"
			})})
    public String exportPaymentExcel() {
        try {
        	User user=(User)session.getAttribute("user");
        	//List<HeadColumn> lHeadColumns=new Gson().fromJson(thead, new TypeToken<ArrayList<HeadColumn>>() {}.getType());        	
        	List<HeadColumn> lHeadColumns = new ArrayList<>();
        	lHeadColumns.add(new HeadColumn("code", "100", "center", "Sequential Code"));
        	lHeadColumns.add(new HeadColumn("refNoofBank", "100", "center", "Ref.No. of Bank"));
        	lHeadColumns.add(new HeadColumn("applicationDate", "100", "center", "Application Date"));
        	lHeadColumns.add(new HeadColumn("state", "100", "center", "Approval Status"));
        	lHeadColumns.add(new HeadColumn("uID", "100", "center", "Cimtas ID"));
        	lHeadColumns.add(new HeadColumn("uName", "100", "center", "User Name"));
        	lHeadColumns.add(new HeadColumn("departmentID", "100", "center", "Manager Cimtas ID"));
        	lHeadColumns.add(new HeadColumn("departmentName", "100", "center", "Manager Name"));
        	lHeadColumns.add(new HeadColumn("supplierCode", "100", "center", "Supplier Code"));
        	lHeadColumns.add(new HeadColumn("beneficiary", "100", "center", "Supplier Name"));
        	lHeadColumns.add(new HeadColumn("contacturalPaymentDate", "100", "center", "Contractural Payment Date"));
        	lHeadColumns.add(new HeadColumn("paidDate", "100", "center", "Actual Paid Date"));
        	lHeadColumns.add(new HeadColumn("urgent", "100", "center", "Urgent"));
        	lHeadColumns.add(new HeadColumn("paymentSubject", "100", "center", "Payment Subject"));
        	lHeadColumns.add(new HeadColumn("paymentTerm", "100", "center", "Payment By"));
        	lHeadColumns.add(new HeadColumn("currency_1", "100", "center", "Currency"));
        	lHeadColumns.add(new HeadColumn("amountInFigures", "100", "center", "Amount"));
        	lHeadColumns.add(new HeadColumn("usageDescription", "100", "center", "Usage Description"));
        	lHeadColumns.add(new HeadColumn("PONo_1", "100", "center", "PO 1"));
        	lHeadColumns.add(new HeadColumn("amount_1", "100", "center", "Amount 1"));
        	lHeadColumns.add(new HeadColumn("PONo_2", "100", "center", "PO 2"));
        	lHeadColumns.add(new HeadColumn("amount_2", "100", "center", "Amount 2"));
        	lHeadColumns.add(new HeadColumn("PONo_3", "100", "center", "PO 3"));
        	lHeadColumns.add(new HeadColumn("amount_3", "100", "center", "Amount 3"));
        	lHeadColumns.add(new HeadColumn("PONo_4", "100", "center", "PO 4"));
        	lHeadColumns.add(new HeadColumn("amount_4", "100", "center", "Amount 4"));
        	lHeadColumns.add(new HeadColumn("PONo_5", "100", "center", "PO 5"));
        	lHeadColumns.add(new HeadColumn("amount_5", "100", "center", "Amount 5"));
        	lHeadColumns.add(new HeadColumn("PONo_6", "100", "center", "PO 6"));
        	lHeadColumns.add(new HeadColumn("amount_6", "100", "center", "Amount 6"));
        	
        	
    		String hql="";
    		String where="";
    		
    		if (!"".equals(applicationDate_f)&&applicationDate_f!=null) {
    			where += " AND P.applicationDate>='"+applicationDate_f+"'";
    		}
    		if (!"".equals(applicationDate_t)&&applicationDate_t!=null) {
    			where += " AND P.applicationDate <= '"+applicationDate_t+"'";
    		}
    		if (!"".equals(gmDate_f)&&gmDate_f!=null) {
    			where += " AND substr(P.GMApproveDate,0,10)>='"+gmDate_f+"'";
    		}
    		if (!"".equals(gmDate_t)&&gmDate_t!=null) {
    			where += " AND substr(P.GMApproveDate,0,10) <= '"+gmDate_t+"'";
    		}
    		if (!"".equals(paidDate_f)&&paidDate_f!=null) {
    			where += " AND P.paidDate >='"+paidDate_f+"'";
    		}
    		if (!"".equals(paidDate_t)&&paidDate_t!=null) {
    			where += " AND P.paidDate <= '"+paidDate_t+"'";
    		}
    		if (!"".equals(code)&&code!=null) {
    			where += " AND P.code = '"+code+"'";
    		}
    		if (!"".equals(state)&&state!=null) {			
    			where += " AND P.state = '"+state+"'";
    		}else {
    			if ("cashier".equals(queryType)) {
    				where+=" AND P.state in ('4','7') ";
    			}		
    		}
    		if (!"".equals(urgent)&&urgent!=null) {
    				where += " AND P.urgent = '"+urgent+"'";		
    		}
    		if (!"".equals(UID)&&UID!=null) {
    			where += " AND P.UID='"+UID+"'";
    		}
    		if (!"".equals(departmentID)&&departmentID!=null) {
    			where += " AND P.departmentID='"+departmentID+"'";
    		}
    		if (!"".equals(paymentSubject)&&paymentSubject!=null) {
    			where += " AND P.paymentSubject='"+paymentSubject+"'";
    		}
    		if (!"".equals(paymentTerm)&&paymentTerm!=null) {
    			where += " AND P.paymentTerm='"+paymentTerm+"'";
    		}
    				
    		
    		if ("all".equals(queryType)) {
    			hql="  select P from Payment P WHERE P.deptManagerID='"+user.getUid()+"' "+where+" order By P.dateTemp desc";    		
    		}
    		if ("acc".equals(queryType)) {
    			hql="  select  P from Payment P where P.documentAuditID='"+user.getUid()+"' "+where+" order By P.dateTemp desc";  			
    		}
    		if ("sign".equals(queryType)) {
    			hql="  select  P from Payment P where P.state='1' and P.deptManagerID='"+user.getUid()+"' order By P.dateTemp desc";
    		}
    		if ("user".equals(queryType)) {
    			hql=" select P from Payment P where P.UID='"+user.getUid()+"' "+where+" order By P.dateTemp desc";
    		}
    		if ("admin".equals(queryType)) {
    			hql=" select P from Payment P where 1=1 "+where+" order By P.dateTemp desc";
    		}
    		if ("cashier".equals(queryType)) {
    			hql=" select P from Payment P where 1=1 "+where+" order By P.dateTemp desc";
    		}
    		List<Payment> lPayments=paymentBIZ.getPaymentByHql(hql);
    		for (Payment payment : lPayments) {
				payment.setState(PaymentState.getState(payment.getState()));
				payment.setPaymentSubject(PaymentState.getPaymentSubject(payment.getPaymentSubject()));
    			if (payment.getUrgent()==null||payment.getUrgent().equals("")) {
    				payment.setUrgent("N");
				}else {
					payment.setUrgent("Y");
				}
    			
			}
    		Class c = (Class) new Payment().getClass();  
        	ByteArrayOutputStream os=ExcelUtil.exportExcel("Payment", c, lPayments, "yyy-MM-dd",lHeadColumns);
        	byte[] fileContent = os.toByteArray();
        	ByteArrayInputStream is = new ByteArrayInputStream(fileContent);
        	   	
        	HttpServletResponse response = (HttpServletResponse)
        			ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_RESPONSE);
        	response.setHeader("Set-Cookie", "fileDownload=true; path=/");
        	
    		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");		 
    		fileName = "Payment"+sf.format(new Date()).toString()+ ".xls";
    		fileName= new String(fileName.getBytes(), "ISO8859-1");
    		//文件流
            reslutJson = is;            
            logUtil.logInfo("导出Payment！"+fileName);
        }
        catch(Exception e) {
        	logUtil.logInfo("导出Payment！"+e.getMessage());
            e.printStackTrace();
        }

        return SUCCESS;
    }
	
	
	
	
	
}
