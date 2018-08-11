package com.sign.model.paymentVisit;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

import com.kime.model.ApproveHis;
import com.kime.model.ApproveList;

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
	Integer totalLeaveWorkHours;
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
	List<PaymentVisitBusinessTrip> businessTrips;
	@Transient
	List<ApproveList> approveList;
	@Transient
	List<ApproveHis> approveHis;
	
	
	public List<PaymentVisitBusinessTrip> getBusinessTrips() {
		return businessTrips;
	}
	public void setBusinessTrips(List<PaymentVisitBusinessTrip> businessTrips) {
		this.businessTrips = businessTrips;
	}
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

	public Integer getTotalLeaveWorkHours() {
		return totalLeaveWorkHours;
	}
	public void setTotalLeaveWorkHours(Integer totalLeaveWorkHours) {
		this.totalLeaveWorkHours = totalLeaveWorkHours;
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
	public String getNextApprove() {
		return nextApprove;
	}
	public void setNextApprove(String nextApprove) {
		this.nextApprove = nextApprove;
	}
	public List<ApproveList> getApproveList() {
		return approveList;
	}
	public void setApproveList(List<ApproveList> approveList) {
		this.approveList = approveList;
	}
	public List<ApproveHis> getApproveHis() {
		return approveHis;
	}
	public void setApproveHis(List<ApproveHis> approveHis) {
		this.approveHis = approveHis;
	}
	
	
	
}
