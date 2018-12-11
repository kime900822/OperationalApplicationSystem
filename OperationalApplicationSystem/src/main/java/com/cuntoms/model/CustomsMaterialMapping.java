package com.cuntoms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

@Component
@Entity @Table(name = "t_customs_material_mapping")
public class CustomsMaterialMapping {

	@Id
	@GeneratedValue(generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	String id;
	@Column
	String oldMaterialNo;
	@Column
	String newMaterialNo;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOldMaterialNo() {
		return oldMaterialNo;
	}
	public void setOldMaterialNo(String oldMaterialNo) {
		this.oldMaterialNo = oldMaterialNo;
	}
	public String getNewMaterialNo() {
		return newMaterialNo;
	}
	public void setNewMaterialNo(String newMaterialNo) {
		this.newMaterialNo = newMaterialNo;
	}
	
}
