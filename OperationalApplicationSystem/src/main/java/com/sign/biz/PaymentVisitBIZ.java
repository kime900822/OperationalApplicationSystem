package com.sign.biz;

import java.util.List;

import com.sign.model.paymentVisit.PaymentVisit;

public interface PaymentVisitBIZ {

	public void save(PaymentVisit paymentVisit);
	
	public void delete(PaymentVisit paymentVisit);
	
	public void update(PaymentVisit paymentVisit);
	
	public List<PaymentVisit> query(String where);
	
	public List<PaymentVisit> query(String where,Integer pageSize,Integer pageCurrent);
	
	public String getMaxCode();
	
}
