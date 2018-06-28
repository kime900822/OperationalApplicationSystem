package com.sign.dao;

import com.sign.model.paymentVisit.PaymentVisitEmployee;

public interface PaymentVisitEmployeeDAO {

	
	public void save(PaymentVisitEmployee paymentVisitEmployee);
	
	public void delete(PaymentVisitEmployee paymentVisitEmployee);
	
	public void update(PaymentVisitEmployee paymentVisitEmployee);
	
}
