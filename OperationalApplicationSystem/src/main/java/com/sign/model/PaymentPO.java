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
public class PaymentPO {
	@Id
	@GeneratedValue(generator="assigned")
	@GenericGenerator(name = "assigned", strategy = "assigned")
	private String id;
	@Column
	private String code;
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
	
	
}
