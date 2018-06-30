package com.sign.action;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;
import com.kime.base.ActionBase;
import com.kime.model.User;
import com.kime.utils.CommonUtil;
import com.kime.utils.TypeChangeUtil;
import com.sign.biz.PaymentVisitEmployeeBIZ;
import com.sign.model.Payment;
import com.sign.model.paymentVisit.PaymentVisitEmployee;

@Controller
@Scope("prototype")
@ParentPackage("Struts 2")
public class PaymentVisitEmployeeAction extends ActionBase {

	@Autowired
	PaymentVisitEmployeeBIZ paymentVisitEmployeeBIZ;
	
	String id;
	String visitId;
	String employeeNo;
	String employeeBUNo;
	String employeeName;
	BigDecimal advanceAmount; 
	String hotelBookingByHR;
	String hotelName;
	String carArrangeByHR;
	String carArrangePeriod;
	String airTickerBookingByHR;
	String flightNO;
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
	
	
	/**
	 * 出差人员编辑（删除，保存）
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@Action(value="modPaymentVisitEmployee",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String modPaymentVisitEmployee() throws UnsupportedEncodingException{
		
		PaymentVisitEmployee employee=new PaymentVisitEmployee();
		employee.setEmployeeNo(employeeNo);
		employee.setEmployeeBUNo(employeeBUNo);
		employee.setEmployeeName(employeeName);
		employee.setAdvanceAmount(advanceAmount);
		employee.setAirTickerBookingByHR(airTickerBookingByHR);
		employee.setCarArrangeByHR(carArrangeByHR);
		employee.setCarArrangePeriod(carArrangePeriod);
		employee.setFlightNO(flightNO);
		employee.setHotelBookingByHR(hotelBookingByHR);
		employee.setHotelName(hotelName);
		employee.setVisarArrangeByHR(visarArrangeByHR);
	
		
		String r=callback+"("+new Gson().toJson(employee)+")";
		reslutJson=new ByteArrayInputStream(r.getBytes("UTF-8"));  

		
		return SUCCESS;
		
		
	}
	
	@Action(value="getPaymentVisitEmployee",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String getPaymentVisitEmployee() throws UnsupportedEncodingException{
	
		List<PaymentVisitEmployee> list=paymentVisitEmployeeBIZ.query(" where visitId='"+visitId+"' ");
		reslutJson=new ByteArrayInputStream(new Gson().toJson(list).getBytes("UTF-8"));  
		
		return SUCCESS;
	}
	
	
}
