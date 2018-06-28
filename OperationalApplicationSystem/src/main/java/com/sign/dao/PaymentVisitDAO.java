package com.sign.dao;

import com.sign.model.paymentVisit.PaymentVisit;

public interface PaymentVisitDAO {

	public void save(PaymentVisit paymentVisit);
	
	public void delete(PaymentVisit paymentVisit);
	
	public void update(PaymentVisit paymentVisit);
	
	
}
