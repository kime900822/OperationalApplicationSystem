package com.sign.model.paymentVisit;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

import com.kime.model.ApproveHis;
import com.sign.model.ApproveApplication;

@Component
@Entity @Table(name = "t_payment_visit")
public class PaymentVisit {

	
	@Id
	@GeneratedValue(generator="assigned")
	@GenericGenerator(name = "assigned", strategy = "assigned")
	String id;
	@Column
	String applicantDate;
	@Column
	String state;
	@Column
	String visitPurpose;
	@Column
	String referenceNo;
	@Column
	String projectNo;
	@Column
	String visitDateFrom;
	@Column
	String visitDateTo;
	@Column
	Integer totalLevelWorkHours;
	@Column
	String businessTrip;
	@Column
	String visitDetailPlace;
	@Column
	String visitDetailPurpose;
	@Column
	String dateTmp;
	@Column
	String uId;
	@Column
	String uName;
	@Column
	String nextApprove;
	@Transient
	List<PaymentVisitEmployee> employees;
	@Transient
	List<ApproveApplication> approveApplications;
	@Transient
	List<ApproveHis> approveHis;
	
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public String getApplicantDate() {
		return applicantDate;
	}
	public void setApplicantDate(String applicantDate) {
		this.applicantDate = applicantDate;
	}
	public String getVisitPurpose() {
		return visitPurpose;
	}
	public void setVisitPurpose(String visitPurpose) {
		this.visitPurpose = visitPurpose;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getReferenceNo() {
		return referenceNo;
	}
	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}
	public String getProjectNo() {
		return projectNo;
	}
	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}
	public String getVisitDateFrom() {
		return visitDateFrom;
	}
	public void setVisitDateFrom(String visitDateFrom) {
		this.visitDateFrom = visitDateFrom;
	}
	public String getVisitDateTo() {
		return visitDateTo;
	}
	public void setVisitDateTo(String visitDateTo) {
		this.visitDateTo = visitDateTo;
	}
	public Integer getTotalLevelWorkHours() {
		return totalLevelWorkHours;
	}
	public void setTotalLevelWorkHours(Integer totalLevelWorkHours) {
		this.totalLevelWorkHours = totalLevelWorkHours;
	}
	public String getBusinessTrip() {
		return businessTrip;
	}
	public void setBusinessTrip(String businessTrip) {
		this.businessTrip = businessTrip;
	}
	public String getVisitDetailPlace() {
		return visitDetailPlace;
	}
	public void setVisitDetailPlace(String visitDetailPlace) {
		this.visitDetailPlace = visitDetailPlace;
	}
	public String getVisitDetailPurpose() {
		return visitDetailPurpose;
	}
	public void setVisitDetailPurpose(String visitDetailPurpose) {
		this.visitDetailPurpose = visitDetailPurpose;
	}
	public String getDateTmp() {
		return dateTmp;
	}
	public void setDateTmp(String dateTmp) {
		this.dateTmp = dateTmp;
	}
	public List<PaymentVisitEmployee> getEmployees() {
		return employees;
	}
	public void setEmployees(List<PaymentVisitEmployee> employees) {
		this.employees = employees;
	}
	public List<ApproveApplication> getApproveApplications() {
		return approveApplications;
	}
	public void setApproveApplications(List<ApproveApplication> approveApplications) {
		this.approveApplications = approveApplications;
	}
	public List<ApproveHis> getApproveHis() {
		return approveHis;
	}
	public void setApproveHis(List<ApproveHis> approveHis) {
		this.approveHis = approveHis;
	}
	
	
	
}
