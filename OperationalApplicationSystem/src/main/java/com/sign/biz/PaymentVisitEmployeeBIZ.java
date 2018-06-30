package com.sign.biz;

import java.util.List;

import com.sign.model.paymentVisit.PaymentVisitEmployee;

public interface PaymentVisitEmployeeBIZ {

	public void save(PaymentVisitEmployee paymentVisitEmployee);
	
	public void delete(PaymentVisitEmployee paymentVisitEmployee);
	
	public void udate(PaymentVisitEmployee paymentVisitEmployee);

	public List<PaymentVisitEmployee> query(String where);
	
	public List<PaymentVisitEmployee> query(String where,Integer pageSize,Integer pageCurrent);
}
