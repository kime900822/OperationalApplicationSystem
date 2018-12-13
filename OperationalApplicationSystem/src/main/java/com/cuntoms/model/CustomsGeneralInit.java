package com.cuntoms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

@Component
@Entity @Table(name = "t_customs_general_init")
public class CustomsGeneralInit {

	@Id
	@GeneratedValue(generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	String id;
	@Column
	String month;
	@Column
	String materialNo;
	@Column
	String jdeMaterialNo;
	@Column
	String no;
	@Column
	String productNo;
	@Column
	String materialName;
	@Column
	String materialSpecification;
	@Column
	String unit;
	@Column
	String earlyNumber;
	@Column
	String incomingVolume;
	@Column
	String writeOffVolume;
	@Column
	String regulatoryInventory;
	
	
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
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
	public String getJdeMaterialNo() {
		return jdeMaterialNo;
	}
	public void setJdeMaterialNo(String jdeMaterialNo) {
		this.jdeMaterialNo = jdeMaterialNo;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}

	public String getProductNo() {
		return productNo;
	}
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public String getMaterialSpecification() {
		return materialSpecification;
	}
	public void setMaterialSpecification(String materialSpecification) {
		this.materialSpecification = materialSpecification;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getEarlyNumber() {
		return earlyNumber;
	}
	public void setEarlyNumber(String earlyNumber) {
		this.earlyNumber = earlyNumber;
	}
	public String getIncomingVolume() {
		return incomingVolume;
	}
	public void setIncomingVolume(String incomingVolume) {
		this.incomingVolume = incomingVolume;
	}
	public String getWriteOffVolume() {
		return writeOffVolume;
	}
	public void setWriteOffVolume(String writeOffVolume) {
		this.writeOffVolume = writeOffVolume;
	}
	public String getRegulatoryInventory() {
		return regulatoryInventory;
	}
	public void setRegulatoryInventory(String regulatoryInventory) {
		this.regulatoryInventory = regulatoryInventory;
	}
	
	



	
	
	
}
