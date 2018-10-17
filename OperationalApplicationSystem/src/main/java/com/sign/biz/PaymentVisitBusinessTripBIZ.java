package com.sign.biz;

import java.util.List;

import com.sign.model.paymentVisit.PaymentVisitBusinessTrip;

public interface PaymentVisitBusinessTripBIZ {

	
	public void save(PaymentVisitBusinessTrip paymentVisitBusinessTrip);
	
	public void delete(PaymentVisitBusinessTrip paymentVisitBusinessTrip);
	
	public void udate(PaymentVisitBusinessTrip paymentVisitBusinessTrip);

	public List<PaymentVisitBusinessTrip> query(String where);
	
	public List<PaymentVisitBusinessTrip> query(String where,Integer pageSize,Integer pageCurrent);

	void save(List<PaymentVisitBusinessTrip> paymentVisitBusinessTrip);
}
