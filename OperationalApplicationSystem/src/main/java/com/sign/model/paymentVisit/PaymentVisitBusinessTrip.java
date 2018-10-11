package com.sign.model.paymentVisit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

@Component
@Entity @Table(name = "t_payment_visit_business_trip")
public class PaymentVisitBusinessTrip {

	@Id
	@GeneratedValue(generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	private String id;
	@Column
	private String visitId;
	@Column
	private int rowNum;
	@Column
	private String date;
	@Column
	private String startFrom;
	@Column
	private String endTo;
	@Column
	private String description;
	@Column
	private String currency;
	@Column
	private double metro;
	@Column
	private double taxi;
	@Column
	private double train;
	@Column
	private double bus;
	@Column
	private double rentalCar;
	@Column
	private double roadToil;
	@Column
	private double roadToilWithoutVAT;
	@Column
	private double roadToilVAT;
	@Column
	private double selfDriver;
	@Column
	private double airTicket;
	@Column
	private double landwayTotal;
	@Column
	private double transportationTotal;
	@Column
	private double hotelWithoutVAT;
	@Column
	private String hotelTaxRate;
	@Column
	private double hotelVAT;
	@Column
	private double hotel;
	@Column
	private double breakfast;
	@Column
	private double lunch;
	@Column
	private double dinner;
	@Column
	private double mealTotal;
	@Column
	private double other;
	@Column
	private double originalCurrencyTotal;
	@Column
	private double RMBExchangeRate;
	@Column
	private double total;

	
	
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStartFrom() {
		return startFrom;
	}

	public void setStartFrom(String startFrom) {
		this.startFrom = startFrom;
	}

	public String getEndTo() {
		return endTo;
	}

	public void setEndTo(String endTo) {
		this.endTo = endTo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getRoadToilWithoutVAT() {
		return roadToilWithoutVAT;
	}

	public void setRoadToilWithoutVAT(double roadToilWithoutVAT) {
		this.roadToilWithoutVAT = roadToilWithoutVAT;
	}

	public double getRoadToilVAT() {
		return roadToilVAT;
	}

	public void setRoadToilVAT(double roadToilVAT) {
		this.roadToilVAT = roadToilVAT;
	}

	public double getLandwayTotal() {
		return landwayTotal;
	}

	public void setLandwayTotal(double landwayTotal) {
		this.landwayTotal = landwayTotal;
	}

	public double getTransportationTotal() {
		return transportationTotal;
	}

	public void setTransportationTotal(double transportationTotal) {
		this.transportationTotal = transportationTotal;
	}

	public double getHotelWithoutVAT() {
		return hotelWithoutVAT;
	}

	public void setHotelWithoutVAT(double hotelWithoutVAT) {
		this.hotelWithoutVAT = hotelWithoutVAT;
	}

	
	public double getHotelVAT() {
		return hotelVAT;
	}

	public void setHotelVAT(double hotelVAT) {
		this.hotelVAT = hotelVAT;
	}

	public double getHotel() {
		return hotel;
	}

	public void setHotel(double hotel) {
		this.hotel = hotel;
	}

	public double getMealTotal() {
		return mealTotal;
	}

	public void setMealTotal(double mealTotal) {
		this.mealTotal = mealTotal;
	}

	public double getOriginalCurrencyTotal() {
		return originalCurrencyTotal;
	}

	public void setOriginalCurrencyTotal(double originalCurrencyTotal) {
		this.originalCurrencyTotal = originalCurrencyTotal;
	}

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

	public int getRowNum() {
		return rowNum;
	}

	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public double getMetro() {
		return metro;
	}

	public void setMetro(double metro) {
		this.metro = metro;
	}

	public double getTaxi() {
		return taxi;
	}

	public void setTaxi(double taxi) {
		this.taxi = taxi;
	}

	public double getTrain() {
		return train;
	}

	public void setTrain(double train) {
		this.train = train;
	}

	public double getBus() {
		return bus;
	}

	public void setBus(double bus) {
		this.bus = bus;
	}

	public double getRentalCar() {
		return rentalCar;
	}

	public void setRentalCar(double rentalCar) {
		this.rentalCar = rentalCar;
	}



	public double getRoadToil() {
		return roadToil;
	}

	public void setRoadToil(double roadToil) {
		this.roadToil = roadToil;
	}

	public double getSelfDriver() {
		return selfDriver;
	}

	public void setSelfDriver(double selfDriver) {
		this.selfDriver = selfDriver;
	}

	public double getAirTicket() {
		return airTicket;
	}

	public void setAirTicket(double airTicket) {
		this.airTicket = airTicket;
	}

	public String getHotelTaxRate() {
		return hotelTaxRate;
	}

	public void setHotelTaxRate(String hotelTaxRate) {
		this.hotelTaxRate = hotelTaxRate;
	}

	public double getBreakfast() {
		return breakfast;
	}

	public void setBreakfast(double breakfast) {
		this.breakfast = breakfast;
	}

	public double getLunch() {
		return lunch;
	}

	public void setLunch(double lunch) {
		this.lunch = lunch;
	}

	public double getDinner() {
		return dinner;
	}

	public void setDinner(double dinner) {
		this.dinner = dinner;
	}

	public double getOther() {
		return other;
	}

	public void setOther(double other) {
		this.other = other;
	}

	public double getRMBExchangeRate() {
		return RMBExchangeRate;
	}

	public void setRMBExchangeRate(double rMBExchangeRate) {
		RMBExchangeRate = rMBExchangeRate;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
	
	
	
}
