package com.cuntoms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

@Component
@Entity @Table(name = "t_customs_clearance")
public class CustomsClearance {

	
	@Id
	@GeneratedValue(generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	String id;
	@Column
	String no;
	@Column
	String deliveryDate;
	@Column
	String shipmentIems;
	@Column
	String longProjectNo;
	@Column
	String cimtasNo;
	@Column
	String poseLongItemNo;
	@Column
	String oldItemNo;
	@Column
	String materialDescription;
	@Column
	String dia;
	@Column
	String sch;
	@Column
	String quantityOrdered;
	@Column
	String quantityIssued;
	@Column
	String weight;
	@Column
	String scn;
	@Column
	String orTy;
	@Column
	String orderNumber;
	@Column
	String manufacName;
	@Column
	String lotSerialNumber;
	@Column
	String batchNumber;
	@Column
	String operator;
	@Column
	String operationDate;
	@Column
	String BOMDate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public String getShipmentIems() {
		return shipmentIems;
	}
	public void setShipmentIems(String shipmentIems) {
		this.shipmentIems = shipmentIems;
	}
	public String getLongProjectNo() {
		return longProjectNo;
	}
	public void setLongProjectNo(String longProjectNo) {
		this.longProjectNo = longProjectNo;
	}
	public String getCimtasNo() {
		return cimtasNo;
	}
	public void setCimtasNo(String cimtasNo) {
		this.cimtasNo = cimtasNo;
	}
	public String getPoseLongItemNo() {
		return poseLongItemNo;
	}
	public void setPoseLongItemNo(String poseLongItemNo) {
		this.poseLongItemNo = poseLongItemNo;
	}
	public String getOldItemNo() {
		return oldItemNo;
	}
	public void setOldItemNo(String oldItemNo) {
		this.oldItemNo = oldItemNo;
	}
	public String getMaterialDescription() {
		return materialDescription;
	}
	public void setMaterialDescription(String materialDescription) {
		this.materialDescription = materialDescription;
	}
	public String getDia() {
		return dia;
	}
	public void setDia(String dia) {
		this.dia = dia;
	}
	public String getSch() {
		return sch;
	}
	public void setSch(String sch) {
		this.sch = sch;
	}
	public String getQuantityOrdered() {
		return quantityOrdered;
	}
	public void setQuantityOrdered(String quantityOrdered) {
		this.quantityOrdered = quantityOrdered;
	}
	public String getQuantityIssued() {
		return quantityIssued;
	}
	public void setQuantityIssued(String quantityIssued) {
		this.quantityIssued = quantityIssued;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getScn() {
		return scn;
	}
	public void setScn(String scn) {
		this.scn = scn;
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
	public String getManufacName() {
		return manufacName;
	}
	public void setManufacName(String manufacName) {
		this.manufacName = manufacName;
	}
	public String getLotSerialNumber() {
		return lotSerialNumber;
	}
	public void setLotSerialNumber(String lotSerialNumber) {
		this.lotSerialNumber = lotSerialNumber;
	}
	public String getBatchNumber() {
		return batchNumber;
	}
	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getOperationDate() {
		return operationDate;
	}
	public void setOperationDate(String operationDate) {
		this.operationDate = operationDate;
	}
	public String getBOMDate() {
		return BOMDate;
	}
	public void setBOMDate(String bOMDate) {
		BOMDate = bOMDate;
	}
	
	
}
