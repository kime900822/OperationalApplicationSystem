package com.sign.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

@Component
@Entity @Table(name = "t_check")
public class Check {
	@Id
	@GeneratedValue(generator="assigned")
	@GenericGenerator(name = "assigned", strategy = "assigned")
	private String id;
	@Column
	private String type;
	@Column
	private String fistUID;
	@Column
	private String fistUname;
	@Column
	private String secondUID;
	@Column
	private String secondUname;
	@Column
	private String thirdUID;
	@Column
	private String thirdUname;
	@Column
	private String attachment;
	@Transient
	private String addFlag;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFistUID() {
		return fistUID;
	}
	public void setFistUID(String fistUID) {
		this.fistUID = fistUID;
	}
	public String getFistUname() {
		return fistUname;
	}
	public void setFistUname(String fistUname) {
		this.fistUname = fistUname;
	}
	public String getSecondUID() {
		return secondUID;
	}
	public void setSecondUID(String secondUID) {
		this.secondUID = secondUID;
	}
	public String getSecondUname() {
		return secondUname;
	}
	public void setSecondUname(String secondUname) {
		this.secondUname = secondUname;
	}
	public String getThirdUID() {
		return thirdUID;
	}
	public void setThirdUID(String thirdUID) {
		this.thirdUID = thirdUID;
	}
	public String getThirdUname() {
		return thirdUname;
	}
	public void setThirdUname(String thirdUname) {
		this.thirdUname = thirdUname;
	}
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	public String getAddFlag() {
		return addFlag;
	}
	public void setAddFlag(String addFlag) {
		this.addFlag = addFlag;
	}
}
