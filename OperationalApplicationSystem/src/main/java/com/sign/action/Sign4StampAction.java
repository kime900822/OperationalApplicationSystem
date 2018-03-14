package com.sign.action;

import javax.persistence.Column;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.kime.base.ActionBase;
import com.sign.biz.Sign4StampBIZ;

@Controller

public class Sign4StampAction extends ActionBase{

	@Autowired
	private Sign4StampBIZ sign4StampBIZ;
	@Autowired
	private String id;
	@Autowired
	private String documentType;
	@Autowired
	private String fistUID;
	@Autowired
	private String fistUname;
	@Autowired
	private String uName;
	@Autowired
	private String secondUID;
	@Autowired
	private String secondUname;
	@Autowired
	private String thirdUID;
	@Autowired
	private String thirdUname;
	@Autowired
	private String attachment;
	public Sign4StampBIZ getSign4StampBIZ() {
		return sign4StampBIZ;
	}
	public void setSign4StampBIZ(Sign4StampBIZ sign4StampBIZ) {
		this.sign4StampBIZ = sign4StampBIZ;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDocumentType() {
		return documentType;
	}
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
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
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
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
	
	
	
	
	
	
}
