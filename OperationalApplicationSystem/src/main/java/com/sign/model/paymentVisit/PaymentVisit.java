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

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

import com.kime.model.ApproveHis;
import com.sign.model.approveApplication;

@Component
@Entity @Table(name = "t_payment_visit")
public class PaymentVisit {

	
	@Id
	@GeneratedValue(generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	String id;
	@Column
	String ApplicantDate;
	@Column
	String referenceNO;
	@Column
	String projectNO;
	@Column
	String visitDateFrom;
	@Column
	String visitDateTo;
	@Column
	BigDecimal totalLevelWorkHours;
	@Column
	String businessTrip;
	@Column
	String visitDetailPlace;
	@Column
	String visitDetailPurpose;
	@Column
	String dateTmp;
	@OneToMany
	@JoinTable(
		     name="employees",
		     joinColumns = @JoinColumn(name="id"),
		     inverseJoinColumns = @JoinColumn(name="visitId")
		  )
	List<PaymentVisitEmployee> employees;
	@OneToMany
	@JoinTable(
		     name="approveApplications",
		     joinColumns = @JoinColumn(name="id"),
		     inverseJoinColumns = @JoinColumn(name="tradeId")
		  )
	List<approveApplication> approveApplications;
	@OneToMany
	@JoinTable(
		     name="approveHis",
		     joinColumns = @JoinColumn(name="id"),
		     inverseJoinColumns = @JoinColumn(name="tradeId")
		  )
	List<ApproveHis> approveHis;
	
	
	public String getApplicantDate() {
		return ApplicantDate;
	}
	public void setApplicantDate(String applicantDate) {
		ApplicantDate = applicantDate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getReferenceNO() {
		return referenceNO;
	}
	public void setReferenceNO(String referenceNO) {
		this.referenceNO = referenceNO;
	}
	public String getProjectNO() {
		return projectNO;
	}
	public void setProjectNO(String projectNO) {
		this.projectNO = projectNO;
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
	public BigDecimal getTotalLevelWorkHours() {
		return totalLevelWorkHours;
	}
	public void setTotalLevelWorkHours(BigDecimal totalLevelWorkHours) {
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
	public List<approveApplication> getApproveApplications() {
		return approveApplications;
	}
	public void setApproveApplications(List<approveApplication> approveApplications) {
		this.approveApplications = approveApplications;
	}
	public List<ApproveHis> getApproveHis() {
		return approveHis;
	}
	public void setApproveHis(List<ApproveHis> approveHis) {
		this.approveHis = approveHis;
	}
	
	
	
}
