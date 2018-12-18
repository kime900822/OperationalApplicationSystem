package com.cuntoms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

@Component
@Entity @Table(name = "t_customs_importsandexports")
public class CustomsImportsAndExports {

	
	
	@Id
	@GeneratedValue(generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	String id;
	@Column
	String entryDate;
	@Column
	String orTy;
	@Column
	String orderNumber;
	@Column
	String cimtasCode;
	@Column
	String no;
	@Column
	String name;
	@Column
	String description;
	@Column
	String quantity;
	@Column
	String unit;
	@Column
	String currency;
	@Column
	String unitPrice;
	@Column
	String amount;
	@Column
	String unitPriceUSD;
	@Column
	String amountUSD;
	@Column
	String operator;
	@Column
	String netWeight;
	@Column
	String origin;
	@Column
	String operationDate;
	@Column
	String batchNumber;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public String getOrTy() {
		return orTy;
	}
	public void setOrTy(String orTy) {
		this.orTy = orTy;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getCimtasCode() {
		return cimtasCode;
	}
	public void setCimtasCode(String cimtasCode) {
		this.cimtasCode = cimtasCode;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getUnitPriceUSD() {
		return unitPriceUSD;
	}
	public void setUnitPriceUSD(String unitPriceUSD) {
		this.unitPriceUSD = unitPriceUSD;
	}
	public String getAmountUSD() {
		return amountUSD;
	}
	public void setAmountUSD(String amountUSD) {
		this.amountUSD = amountUSD;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getNetWeight() {
		return netWeight;
	}
	public void setNetWeight(String netWeight) {
		this.netWeight = netWeight;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getOperationDate() {
		return operationDate;
	}
	public void setOperationDate(String operationDate) {
		this.operationDate = operationDate;
	}
	public String getBatchNumber() {
		return batchNumber;
	}
	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}
	
	
	
	
	
	
}
