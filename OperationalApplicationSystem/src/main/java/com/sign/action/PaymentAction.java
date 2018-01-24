package com.sign.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.apache.struts2.convention.annotation.Action;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.analysis.model.Source;
import com.google.gson.Gson;
import com.kime.action.ActionBase;
import com.kime.biz.DictBIZ;
import com.kime.infoenum.Message;
import com.kime.model.Dict;
import com.kime.model.User;
import com.kime.utils.CommonUtil;
import com.kime.utils.ExcelUtil;
import com.kime.utils.TypeChangeUtil;
import com.sign.biz.PaymentBIZ;
import com.sign.model.Payment;
import com.sign.other.FileSave;
import com.sign.other.PaymentState;

@Controller
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
	    		fileName=f[f.length-1];
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
			if (payment.getIsPrint()==null) {
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
				payment.setState(PaymentState.ACCPAYMENT);
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
			payment.setDeptManager(user.getUid());
			payment.setDeptManager(user.getName());
			
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
			payment.setState(PaymentState.SAVEPAYMENT);
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
	
	
	@Action(value="getPaymentByID",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String getPaymentByID() throws UnsupportedEncodingException{

		Payment payment=new Payment();		
		try {
			payment=paymentBIZ.getPayment(" where id='"+id+"'").get(0);
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
		if (!"".equals(code)&&code!=null) {
			where += " AND P.code = '"+code+"'";
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
		
			
		if ("all".equals(queryType)) {
			hql="  select P from Payment P WHERE P.departmentID='"+user.getDid()+"' "+where+" order By P.dateTemp desc";
		}
		if ("acc".equals(queryType)) {
			if (state==null||state.equals("")) {
				hql="  select  P from Payment P where P.state in (0,2,4,5) AND P.documentAuditID='"+user.getUid()+"' order By P.dateTemp desc";
			}else if (state.equals("1")) {
				hql="  select  P from Payment P where P.state in (0,5,4) AND P.documentAuditID='"+user.getUid()+"' order By P.dateTemp desc";
			}else if(state.equals("2")) {
				hql="  select  P from Payment P where P.state=2 AND P.documentAuditID='"+user.getUid()+"' order By P.dateTemp desc";
			}
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
		List<Payment> list=paymentBIZ.getPaymentByHql(hql, Integer.parseInt(pageSize),Integer.parseInt(pageCurrent));
		int total=paymentBIZ.getPaymentByHql(hql).size();
		for (Payment payment : list) {
			payment.setAmountInFigures(TypeChangeUtil.formatMoney(payment.getAmountInFigures(),2,""));
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
        	
    		String hql="";
    		
    		String where="";
    		
    		if (!"".equals(applicationDate_f)&&applicationDate_f!=null) {
    			where += " AND P.applicationDate>='"+applicationDate_f+"'";
    		}
    		if (!"".equals(applicationDate_t)&&applicationDate_t!=null) {
    			where += " AND P.applicationDate <= '"+applicationDate_t+"'";
    		}
    		if (!"".equals(code)&&code!=null) {
    			where += " AND P.code = '"+code+"'";
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
    		
    			
    		if ("all".equals(queryType)) {
    			hql="  select P from Payment P WHERE P.departmentID='"+user.getDid()+"' "+where+" order By P.dateTemp desc";
    		}
    		if ("acc".equals(queryType)) {
    			if (state.equals("1")) {
    				hql="  select  P from Payment P where P.state in (0,5,4) AND P.documentAuditID='"+user.getUid()+"' order By P.dateTemp desc";
				}else if(state.equals("2")) {
    				hql="  select  P from Payment P where P.state=2 AND P.documentAuditID='"+user.getUid()+"' order By P.dateTemp desc";
				}else{
					hql="  select  P from Payment P where P.state in (0,2,4,5) AND P.documentAuditID='"+user.getUid()+"' order By P.dateTemp desc";
				}
    			
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
    		List<Payment> lPayments=paymentBIZ.getPaymentByHql(hql);
        	Class c = (Class) new Payment().getClass();  
        	ByteArrayOutputStream os=ExcelUtil.exportExcel("Payment", c, lPayments, "yyy-MM-dd");
        	byte[] fileContent = os.toByteArray();
        	ByteArrayInputStream is = new ByteArrayInputStream(fileContent);
        	
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

        return "success";
    }
	
	
}
