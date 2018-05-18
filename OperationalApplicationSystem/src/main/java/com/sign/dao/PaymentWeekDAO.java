package com.sign.dao;

import java.util.List;

import com.sign.model.PaymentWeek;

public interface PaymentWeekDAO {

	public void save(PaymentWeek paymentWeek);
	
	public void delete(PaymentWeek paymentWeek);
	
	public void update(PaymentWeek paymentWeek);
	
	public List<PaymentWeek> queryHql(String hql);
	
	public List<PaymentWeek> query(String where);

	public List<PaymentWeek> query(String where,Integer pageSize,Integer pageCurrent);

	List queryWeek(String where);

	List queryWeek(String where, Integer pageSize, Integer pageCurrent);
	
}
