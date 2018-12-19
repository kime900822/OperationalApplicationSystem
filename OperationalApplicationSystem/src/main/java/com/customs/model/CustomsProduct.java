package com.customs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

@Component
@Entity @Table(name = "t_customs_product")
public class CustomsProduct {

	@Id
	@GeneratedValue(generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	String id;
	@Column
	String no;
	@Column
	String materialNo;
	@Column
	String productNo;
	@Column
	String productName;
	@Column
	String specification;
	@Column
	String declareUnitCode;
	@Column
	String legalUnitCode1;
	@Column
	String legalUnitCode2;
	@Column
	String declareNumber;
	@Column
	String declarePrice;
	@Column
	String currency;
	@Column
	String exemptedMode;
	@Column
	String executionMode;
	@Column
	String modifyFlag;
	@Column
	String comments;
	@Column
	String uploadOperator;
	@Column
	String uploadDate;
	@Column
	String batchNumber;
	@Column
	String declaration;
	@Column
	String declarationOperatior;
	@Column
	String declarationDate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMaterialNo() {
		return materialNo;
	}
	public void setMaterialNo(String materialNo) {
		this.materialNo = materialNo;
	}
	public String getProductNo() {
		return productNo;
	}
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getSpecification() {
		return specification;
	}
	public void setSpecification(String specification) {
		this.specification = specification;
	}
	public String getDeclareUnitCode() {
		return declareUnitCode;
	}
	public void setDeclareUnitCode(String declareUnitCode) {
		this.declareUnitCode = declareUnitCode;
	}
	public String getLegalUnitCode1() {
		return legalUnitCode1;
	}
	public void setLegalUnitCode1(String legalUnitCode1) {
		this.legalUnitCode1 = legalUnitCode1;
	}
	public String getLegalUnitCode2() {
		return legalUnitCode2;
	}
	public void setLegalUnitCode2(String legalUnitCode2) {
		this.legalUnitCode2 = legalUnitCode2;
	}
	
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getDeclareNumber() {
		return declareNumber;
	}
	public void setDeclareNumber(String declareNumber) {
		this.declareNumber = declareNumber;
	}
	public String getDeclarePrice() {
		return declarePrice;
	}
	public void setDeclarePrice(String declarePrice) {
		this.declarePrice = declarePrice;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getExemptedMode() {
		return exemptedMode;
	}
	public void setExemptedMode(String exemptedMode) {
		this.exemptedMode = exemptedMode;
	}
	public String getExecutionMode() {
		return executionMode;
	}
	public void setExecutionMode(String executionMode) {
		this.executionMode = executionMode;
	}
	public String getModifyFlag() {
		return modifyFlag;
	}
	public void setModifyFlag(String modifyFlag) {
		this.modifyFlag = modifyFlag;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getUploadOperator() {
		return uploadOperator;
	}
	public void setUploadOperator(String uploadOperator) {
		this.uploadOperator = uploadOperator;
	}
	public String getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(String uploadDate) {
		this.uploadDate = uploadDate;
	}
	public String getBatchNumber() {
		return batchNumber;
	}
	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}
	public String getDeclaration() {
		return declaration;
	}
	public void setDeclaration(String declaration) {
		this.declaration = declaration;
	}
	public String getDeclarationOperatior() {
		return declarationOperatior;
	}
	public void setDeclarationOperatior(String declarationOperatior) {
		this.declarationOperatior = declarationOperatior;
	}
	public String getDeclarationDate() {
		return declarationDate;
	}
	public void setDeclarationDate(String declarationDate) {
		this.declarationDate = declarationDate;
	}
	
	
}
