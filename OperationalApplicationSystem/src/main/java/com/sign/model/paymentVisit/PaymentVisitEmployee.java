package com.sign.model.paymentVisit;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

@Component
@Entity @Table(name = "t_payment_visit_employee")
public class PaymentVisitEmployee {

	@Id
	@GeneratedValue(generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	String id;
	@Column
	String visitId;
	@Column
	String employeeNo;
	@Column
	String employeeBUNo;
	@Column
	String employeeName;
	@Column
	BigDecimal advanceAmount; 
	@Column
	String hotelBookingByHR;
	@Column
	String hotelName;
	@Column
	String carArrangeByHR;
	@Column
	String carArrangePeriod;
	@Column
	String airTickerBookingByHR;
	@Column
	String flightNO;
	@Column
	String visarArrangeByHR;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getVisitId() {
		return visitId;
	}
	public void setVisitId(String visitId) {
		this.visitId = visitId;
	}
	public String getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}
	public String getEmployeeBUNo() {
		return employeeBUNo;
	}
	public void setEmployeeBUNo(String employeeBUNo) {
		this.employeeBUNo = employeeBUNo;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public BigDecimal getAdvanceAmount() {
		return advanceAmount;
	}
	public void setAdvanceAmount(BigDecimal advanceAmount) {
		this.advanceAmount = advanceAmount;
	}
	public String getHotelBookingByHR() {
		return hotelBookingByHR;
	}
	public void setHotelBookingByHR(String hotelBookingByHR) {
		this.hotelBookingByHR = hotelBookingByHR;
	}
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public String getCarArrangeByHR() {
		return carArrangeByHR;
	}
	public void setCarArrangeByHR(String carArrangeByHR) {
		this.carArrangeByHR = carArrangeByHR;
	}
	public String getCarArrangePeriod() {
		return carArrangePeriod;
	}
	public void setCarArrangePeriod(String carArrangePeriod) {
		this.carArrangePeriod = carArrangePeriod;
	}
	public String getAirTickerBookingByHR() {
		return airTickerBookingByHR;
	}
	public void setAirTickerBookingByHR(String airTickerBookingByHR) {
		this.airTickerBookingByHR = airTickerBookingByHR;
	}
	public String getFlightNO() {
		return flightNO;
	}
	public void setFlightNO(String flightNO) {
		this.flightNO = flightNO;
	}
	public String getVisarArrangeByHR() {
		return visarArrangeByHR;
	}
	public void setVisarArrangeByHR(String visarArrangeByHR) {
		this.visarArrangeByHR = visarArrangeByHR;
	}
	
}
