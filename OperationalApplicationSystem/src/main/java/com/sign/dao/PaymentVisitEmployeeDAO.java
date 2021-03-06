package com.sign.dao;

import java.util.List;

import com.sign.model.paymentVisit.PaymentVisitEmployee;

public interface PaymentVisitEmployeeDAO {

	
	public void save(PaymentVisitEmployee paymentVisitEmployee);
	
	public void delete(PaymentVisitEmployee paymentVisitEmployee);
	
	public void update(PaymentVisitEmployee paymentVisitEmployee);

	public List<PaymentVisitEmployee> query(String where);

	public List<PaymentVisitEmployee> query(String where,Integer pageSize,Integer pageCurrent);
	
}
