package com.sign.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

@Component
@Entity@Table(name="t_payment")
public class Payment {
	
	/**
	 * id
	 */
	@Id
	@GeneratedValue(generator="assigned")
	@GenericGenerator(name = "assigned", strategy = "assigned")
	private String id;
	
	/**
	 * 申请日期
	 */
	@Column
	private String applicationDate;
	
	/**
	 * 要求付款日期
	 */
	@Column
	private String requestPaymentDate;
	
	/**
	 * 合同付款日期
	 */
	@Column
	private String contacturalPaymentDate;
	
	/**
	 * 流水码
	 */
	@Column(name="`code`")
	private String code;
	
	/**
	 * urgent
	 */
	@Column
	private String urgent;
	
	/**
	 * 支付类型
	 */
	@Column
	private String payType;
	
	
	/**
	 * 申请人ID
	 */
	@Column
	private String UID;
	
	/**
	 * 申请人姓名
	 */
	@Column
	private String UName;
	
	/**
	 * 申请部门ID
	 */
	@Column
	private String departmentID;
	
	/**
	 * 申请部门名称
	 */
	@Column
	private String departmentName;
	
	/**
	 * 收款人中文
	 */
	@Column
	private String beneficiary;
	
	/**
	 * 账号
	 */
	@Column
	private String beneficiaryAccountNO;
	
	/**
	 * 收款人英文
	 */
	@Column
	private String beneficiaryE;
	
	/**
	 * 银行
	 */
	@Column
	private String beneficiaryAccountBank;
	
	/**
	 * 收款人变更标志
	 */
	@Column
	private String beneficiaryChange;
	
	/**
	 * 银行账号变更标志
	 */
	@Column
	private String beneficiaryAccountNOChange;
	
	/**
	 * 供应商代码
	 */
	@Column
	private String supplierCode;
	
	/**
	 * 银行交易编码
	 */
	@Column
	private String refNoofBank;
	
	/**
	 * 付款项目
	 */
	@Column
	private String paymentSubject;
	
	
	/**
	 * 结算期类型
	 */
	@Column
	private String paymentTerm;
	
	/**
	 * 结算天数-预付款
	 */
	@Column
	private String paymentDays_1;
	
	/**
	 * 收货或验收日期-预付款
	 */
	@Column
	private String receivingOrApprovalDate_1;
	
	/**
	 * 订单号-预付款
	 */	
	@Column
	private String PONo_1;
	
	/**
	 * 币种-预付款
	 */
	@Column
	private String currency_1;
	
	/**
	 * 金额-预付款
	 */
	@Column
	private String amount_1;
	
	/**
	 * 结算天数-见票即付
	 */
	@Column
	private String paymentDays_2;
	
	/**
	 * 收货或验收日期-见票即付
	 */
	@Column
	private String receivingOrApprovalDate_2;
	
	/**
	 * 订单号-见票即付
	 */	
	@Column
	private String PONo_2;
	
	/**
	 * 币种-见票即付
	 */
	@Column
	private String currency_2;
	
	/**
	 * 金额-见票即付
	 */
	@Column
	private String amount_2;
	
	/**
	 * 结算天数-收货后
	 */
	@Column
	private String paymentDays_3;
	
	/**
	 * 收货或验收日期-收货后
	 */
	@Column
	private String receivingOrApprovalDate_3;
	
	/**
	 * 订单号-收货后
	 */	
	@Column
	private String PONo_3;
	
	/**
	 * 币种-收货后
	 */
	@Column
	private String currency_3;
	
	/**
	 * 金额-收货后
	 */
	@Column
	private String amount_3;
	
	/**
	 * 结算天数-验收后
	 */
	@Column
	private String paymentDays_4;
	
	/**
	 * 收货或验收日期-验收后
	 */
	@Column
	private String receivingOrApprovalDate_4;
	
	/**
	 * 订单号-验收后
	 */	
	@Column
	private String PONo_4;
	
	/**
	 * 币种-验收后
	 */
	@Column
	private String currency_4;
	
	/**
	 * 金额-验收后
	 */
	@Column
	private String amount_4;
	
	/**
	 * 结算天数-见票后
	 */
	@Column
	private String paymentDays_5;
	
	/**
	 * 收货或验收日期-见票后
	 */
	@Column
	private String receivingOrApprovalDate_5;
	
	/**
	 * 订单号-见票后
	 */	
	@Column
	private String PONo_5;
	
	/**
	 * 币种-见票后
	 */
	@Column
	private String currency_5;
	
	/**
	 * 金额-见票后
	 */
	@Column
	private String amount_5;
	
	/**
	 * 结算天数-其他
	 */
	@Column
	private String paymentDays_6;
	
	/**
	 * 收货或验收日期-其他
	 */
	@Column
	private String receivingOrApprovalDate_6;
	
	/**
	 * 订单号-其他
	 */	
	@Column
	private String PONo_6;
	
	/**
	 * 币种-其他
	 */
	@Column
	private String currency_6;
	
	/**
	 * 金额-其他
	 */
	@Column
	private String amount_6;
	
	/**
	 * 支付用途
	 */
	@Column
	private String usageDescription;
	
	/**
	 * 金额小写
	 */
	@Column
	private String amountInFigures;
	
	/**
	 * 财务审核人
	 */
	@Column
	private String documentAudit;
	
	/**
	 * 财务审核人ID
	 */
	@Column
	private String documentAuditID;
	
	/**
	 * 单据状态
	 */
	@Column
	private String state;
	
	/**
	 * 发票地址
	 */
	@Column
	private String file_invoice="";
	
	/**
	 * 合同地址
	 */
	@Column
	private String file_contract="";
	
	/**
	 * 其他附件地址
	 */
	@Column
	private String file_other="";
	
	/**
	 * 作废原因
	 */
	@Column
	private String invalidDescription;
	
	/**
	 * 回退原因
	 */
	@Column
	private String returnDescription;
	
	/**
	 * 部门经理
	 */
	@Column
	private String deptManager;
	
	/**
	 * 部门经理ID
	 */
	@Column
	private String deptManagerID;
	
	/**
	 * 财务主管
	 */
	@Column
	private String financeSupervisor;
	
	/**
	 * 财务经理
	 */
	@Column
	private String financeManager;
	
	/**
	 * 总经理
	 */
	@Column
	private String generalManager;
	
	/**
	 * 打印标志
	 */
	@Column
	private String isPrint;

	/**
	 * 创建时间
	 */
	@Column
	private String dateTemp;
	
	
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

	public String getDateTemp() {
		return dateTemp;
	}

	public void setDateTemp(String dateTemp) {
		this.dateTemp = dateTemp;
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

	public String getUName() {
		return UName;
	}

	public void setUName(String uName) {
		UName = uName;
	}

	public String getDepartmentID() {
		return departmentID;
	}

	public void setDepartmentID(String departmentID) {
		this.departmentID = departmentID;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
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

	public String getIsPrint() {
		return isPrint;
	}

	public void setIsPrint(String isPrint) {
		this.isPrint = isPrint;
	}

	public String getDeptManager() {
		return deptManager;
	}

	public String getPaymentTerm() {
		return paymentTerm;
	}

	public void setPaymentTerm(String paymentTerm) {
		this.paymentTerm = paymentTerm;
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




	
}

