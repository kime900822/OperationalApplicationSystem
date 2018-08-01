package com.sign.dao;

import java.util.List;

import com.sign.model.paymentVisit.PaymentVisitBusinessTrip;


public interface PaymentVisitBusinessTripDAO {

	public void save(PaymentVisitBusinessTrip paymentVisitBusinessTrip);
	
	public void delete(PaymentVisitBusinessTrip paymentVisitBusinessTrip);
	
	public void update(PaymentVisitBusinessTrip paymentVisitBusinessTrip);

	public List<PaymentVisitBusinessTrip> query(String where);

	public List<PaymentVisitBusinessTrip> query(String where,Integer pageSize,Integer pageCurrent);
}
