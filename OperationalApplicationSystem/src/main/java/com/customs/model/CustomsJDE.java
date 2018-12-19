package com.customs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

@Component
@Entity @Table(name = "t_customs_jde")
public class CustomsJDE {

	
	@Id
	@GeneratedValue(generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	String id;
	@Column
	String businessUnit;
	@Column
	String orTy;
	@Column
	String orderNumber;
	@Column
	String orderDate;
	@Column
	String location;
	@Column
	String shortItemNo;
	@Column
	String cimtasLongItemNo;
	@Column
	String doTy;
	@Column
	String transactionExplanation;
	@Column
	String transQTY;
	@Column
	String extendedCostPrice;
	@Column
	String glDate;
	@Column
	String documentNumber;
	@Column
	String lotStatCode;
	@Column
	String longProjectNo;
	@Column
	String lotNumber;
	@Column
	String userID;
	@Column
	String materialLongDescription;
	@Column
	String importNo;
	@Column
	String heatNo;
	@Column
	String NCRNo;
	@Column
	String note;
	@Column
	String weight;
	@Column
	String purchaseOrderLineNumber;
	@Column
	String note1;
	@Column
	String operationDate;
	@Column
	String batchNumber;
	
	
	
	public String getBatchNumber() {
		return batchNumber;
	}
	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBusinessUnit() {
		return businessUnit;
	}
	public void setBusinessUnit(String businessUnit) {
		this.businessUnit = businessUnit;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getShortItemNo() {
		return shortItemNo;
	}
	public void setShortItemNo(String shortItemNo) {
		this.shortItemNo = shortItemNo;
	}
	public String getCimtasLongItemNo() {
		return cimtasLongItemNo;
	}
	public void setCimtasLongItemNo(String cimtasLongItemNo) {
		this.cimtasLongItemNo = cimtasLongItemNo;
	}
	public String getDoTy() {
		return doTy;
	}
	public void setDoTy(String doTy) {
		this.doTy = doTy;
	}
	public String getTransactionExplanation() {
		return transactionExplanation;
	}
	public void setTransactionExplanation(String transactionExplanation) {
		this.transactionExplanation = transactionExplanation;
	}
	public String getTransQTY() {
		return transQTY;
	}
	public void setTransQTY(String transQTY) {
		this.transQTY = transQTY;
	}
	public String getExtendedCostPrice() {
		return extendedCostPrice;
	}
	public void setExtendedCostPrice(String extendedCostPrice) {
		this.extendedCostPrice = extendedCostPrice;
	}
	public String getGlDate() {
		return glDate;
	}
	public void setGlDate(String glDate) {
		this.glDate = glDate;
	}
	public String getDocumentNumber() {
		return documentNumber;
	}
	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}
	public String getLotStatCode() {
		return lotStatCode;
	}
	public void setLotStatCode(String lotStatCode) {
		this.lotStatCode = lotStatCode;
	}
	public String getLongProjectNo() {
		return longProjectNo;
	}
	public void setLongProjectNo(String longProjectNo) {
		this.longProjectNo = longProjectNo;
	}
	public String getLotNumber() {
		return lotNumber;
	}
	public void setLotNumber(String lotNumber) {
		this.lotNumber = lotNumber;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getMaterialLongDescription() {
		return materialLongDescription;
	}
	public void setMaterialLongDescription(String materialLongDescription) {
		this.materialLongDescription = materialLongDescription;
	}
	public String getImportNo() {
		return importNo;
	}
	public void setImportNo(String importNo) {
		this.importNo = importNo;
	}
	public String getHeatNo() {
		return heatNo;
	}
	public void setHeatNo(String heatNo) {
		this.heatNo = heatNo;
	}
	public String getNCRNo() {
		return NCRNo;
	}
	public void setNCRNo(String nCRNo) {
		NCRNo = nCRNo;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getPurchaseOrderLineNumber() {
		return purchaseOrderLineNumber;
	}
	public void setPurchaseOrderLineNumber(String purchaseOrderLineNumber) {
		this.purchaseOrderLineNumber = purchaseOrderLineNumber;
	}
	public String getNote1() {
		return note1;
	}
	public void setNote1(String note1) {
		this.note1 = note1;
	}
	public String getOperationDate() {
		return operationDate;
	}
	public void setOperationDate(String operationDate) {
		this.operationDate = operationDate;
	}
	public String getOrTy() {
		return orTy;
	}
	public void setOrTy(String orTy) {
		this.orTy = orTy;
	}
	
	
	
}
