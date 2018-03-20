package com.sign.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

@Component
@Entity @Table(name = "t_stamp")
public class Stamp {

	@Id
	@GeneratedValue(generator="assigned")
	@GenericGenerator(name = "assigned", strategy = "assigned")
	private String id;
	@Column
	private String applicationDate;
	@Column
	private String applicationCode;
	@Column
	private String formFillerID;
	@Column
	private String formFiller;
	@Column
	private String departmentOfFormFillerID;
	@Column
	private String departmentOfFormFiller;
	@Column
	private String applicantID;
	@Column
	private String applicant;
	@Column
	private String departmentOfApplicantID;
	@Column
	private String departmentOfApplicant;
	@Column
	private String contactNumber;
	@Column
	private String stampType;
	@Column
	private String documentType;
	@Column
	private String projectResponsible;
	@Column
	private String chopDate;
	@Column
	private String lendDate;
	@Column
	private String giveBackDate;
	@Column
	private String chopQty;
	@Column
	private String chopObject;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getApplicationDate() {
		return applicationDate;
	}
	public void setApplicationDate(String applicationDate) {
		this.applicationDate = applicationDate;
	}
	public String getApplicationCode() {
		return applicationCode;
	}
	public void setApplicationCode(String applicationCode) {
		this.applicationCode = applicationCode;
	}
	public String getFormFiller() {
		return formFiller;
	}
	public void setFormFiller(String formFiller) {
		this.formFiller = formFiller;
	}
	public String getDepartmentOfFormFiller() {
		return departmentOfFormFiller;
	}
	public void setDepartmentOfFormFiller(String departmentOfFormFiller) {
		this.departmentOfFormFiller = departmentOfFormFiller;
	}
	public String getApplicantID() {
		return applicantID;
	}
	public void setApplicantID(String applicantID) {
		this.applicantID = applicantID;
	}
	public String getApplicant() {
		return applicant;
	}
	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}
	public String getDepartmentOfApplicantID() {
		return departmentOfApplicantID;
	}
	public void setDepartmentOfApplicantID(String departmentOfApplicantID) {
		this.departmentOfApplicantID = departmentOfApplicantID;
	}
	public String getDepartmentOfApplicant() {
		return departmentOfApplicant;
	}
	public void setDepartmentOfApplicant(String departmentOfApplicant) {
		this.departmentOfApplicant = departmentOfApplicant;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getStampType() {
		return stampType;
	}
	public void setStampType(String stampType) {
		this.stampType = stampType;
	}
	public String getDocumentType() {
		return documentType;
	}
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
	public String getChopDate() {
		return chopDate;
	}
	public void setChopDate(String chopDate) {
		this.chopDate = chopDate;
	}
	public String getLendDate() {
		return lendDate;
	}
	public void setLendDate(String lendDate) {
		this.lendDate = lendDate;
	}
	public String getGiveBackDate() {
		return giveBackDate;
	}
	public void setGiveBackDate(String giveBackDate) {
		this.giveBackDate = giveBackDate;
	}
	public String getChopQty() {
		return chopQty;
	}
	public void setChopQty(String chopQty) {
		this.chopQty = chopQty;
	}
	public String getChopObject() {
		return chopObject;
	}
	public void setChopObject(String chopObject) {
		this.chopObject = chopObject;
	}
	public String getFormFillerID() {
		return formFillerID;
	}
	public void setFormFillerID(String formFillerID) {
		this.formFillerID = formFillerID;
	}
	public String getDepartmentOfFormFillerID() {
		return departmentOfFormFillerID;
	}
	public void setDepartmentOfFormFillerID(String departmentOfFormFillerID) {
		this.departmentOfFormFillerID = departmentOfFormFillerID;
	}
	public String getProjectResponsible() {
		return projectResponsible;
	}
	public void setProjectResponsible(String projectResponsible) {
		this.projectResponsible = projectResponsible;
	}
	
}
