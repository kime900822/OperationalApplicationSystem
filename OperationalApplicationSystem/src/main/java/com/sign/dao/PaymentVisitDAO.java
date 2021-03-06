package com.sign.dao;

import java.util.List;

import com.sign.model.paymentVisit.PaymentVisit;

public interface PaymentVisitDAO {

	public void save(PaymentVisit paymentVisit);
	
	public void delete(PaymentVisit paymentVisit);
	
	public void update(PaymentVisit paymentVisit);
	
	public List<PaymentVisit> query(String where);

	public List<PaymentVisit> query(String where,Integer pageSize,Integer pageCurrent);
}
