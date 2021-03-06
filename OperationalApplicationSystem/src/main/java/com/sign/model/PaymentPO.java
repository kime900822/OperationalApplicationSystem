package com.sign.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.springframework.stereotype.Component;

@Component
@Entity @Table(name = "v_po")
public class PaymentPO implements Comparable<PaymentPO> {
	@Id
	@GeneratedValue(generator="assigned")
	@GenericGenerator(name = "assigned", strategy = "assigned")
	private String id;
	@Column
	private String applicationDate;
	@Column
	private String contacturalPaymentDate;
	@Column
	private String paidDate;
	@Column
	private String requestPaymentDate;
	@Column
	private String code;
	@Column
	private String state;
	@Column
	private String applicant;
	@Column
	private String did;
	@Column
	private String amountInFigures;
	@Column
	private String usageDescription;
	@Column
	private String supplierCode;
	@Column
	private String beneficiaryE;
	@Column
	private String paymentDays;
	@Column
	private String receivingOrApprovalDate;
	@Column
	private String PONo;
	@Column
	private String currency;
	@Column
	private String amount;
	
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getApplicant() {
		return applicant;
	}
	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}
	public String getDid() {
		return did;
	}
	public void setDid(String did) {
		this.did = did;
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
	public String getSupplierCode() {
		return supplierCode;
	}
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}
	public String getBeneficiaryE() {
		return beneficiaryE;
	}
	public void setBeneficiaryE(String beneficiaryE) {
		this.beneficiaryE = beneficiaryE;
	}
	public String getPaymentDays() {
		return paymentDays;
	}
	public void setPaymentDays(String paymentDays) {
		this.paymentDays = paymentDays;
	}
	public String getReceivingOrApprovalDate() {
		return receivingOrApprovalDate;
	}
	public void setReceivingOrApprovalDate(String receivingOrApprovalDate) {
		this.receivingOrApprovalDate = receivingOrApprovalDate;
	}
	public String getPONo() {
		return PONo;
	}
	public void setPONo(String pONo) {
		PONo = pONo;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getApplicationDate() {
		return applicationDate;
	}
	public void setApplicationDate(String applicationDate) {
		this.applicationDate = applicationDate;
	}
	public String getContacturalPaymentDate() {
		return contacturalPaymentDate;
	}
	public void setContacturalPaymentDate(String contacturalPaymentDate) {
		this.contacturalPaymentDate = contacturalPaymentDate;
	}
	public String getPaidDate() {
		return paidDate;
	}
	public void setPaidDate(String paidDate) {
		this.paidDate = paidDate;
	}
	public String getRequestPaymentDate() {
		return requestPaymentDate;
	}
	public void setRequestPaymentDate(String requestPaymentDate) {
		this.requestPaymentDate = requestPaymentDate;
	}
	
	@Override
	public int compareTo(PaymentPO o) {
		int code1=0;
		int code2=0;
		
		if (this.getCode()!=null && !this.getCode().equals("")) {
			code1=Integer.parseInt(this.getCode());
		}
		if (o.getCode()!=null && !o.getCode().equals("")) {
			code2=Integer.parseInt(o.getCode());
		}
		
		int i=code1-code2;
		return i;
	}
	
	
}
