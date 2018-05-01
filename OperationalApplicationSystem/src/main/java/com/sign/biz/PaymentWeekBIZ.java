package com.sign.biz;

import java.util.List;

import com.sign.model.PaymentWeek;

public interface PaymentWeekBIZ {

	public void save(PaymentWeek paymentWeek);
	
	public void update(PaymentWeek paymentWeek);
	
	public void delete(PaymentWeek paymentWeek);
	
	public List<PaymentWeek> getPayment(String where);
	
	public List<PaymentWeek> getPayment(String where,Integer pageSize,Integer pageCurrent);
}
