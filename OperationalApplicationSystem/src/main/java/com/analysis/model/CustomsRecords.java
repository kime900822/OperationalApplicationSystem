package com.analysis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

@Component
@Entity @Table(name = "t_report_customsrecords")
public class CustomsRecords {
	
	@Id
	@GeneratedValue(generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	String id;
	@Column
	String date;
	@Column
	String shipments;
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
	double dia;
	@Column
	double sch;
	@Column
	int quantityOrdered;
	@Column
	int quantityIssued;
	@Column
	double weight;
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
	String operator;
	@Column
	String createDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getShipments() {
		return shipments;
	}

	public void setShipments(String shipments) {
		this.shipments = shipments;
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

	public double getDia() {
		return dia;
	}

	public void setDia(double dia) {
		this.dia = dia;
	}



	public double getSch() {
		return sch;
	}

	public void setSch(double sch) {
		this.sch = sch;
	}

	public int getQuantityOrdered() {
		return quantityOrdered;
	}

	public void setQuantityOrdered(int quantityOrdered) {
		this.quantityOrdered = quantityOrdered;
	}

	public int getQuantityIssued() {
		return quantityIssued;
	}

	public void setQuantityIssued(int quantityIssued) {
		this.quantityIssued = quantityIssued;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
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

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	
	
}
