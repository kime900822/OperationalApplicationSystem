package com.sign.dao;

import java.util.List;

import com.sign.model.Payment;

public interface PaymentDAO {
	public void save(Payment payment);
	
	public void delete(Payment payment);
	
	public void update(Payment payment);
	
	public List<Payment> queryHql(String hql);
	
	public List<Payment> query(String where);

	public List<Payment> query(String where,Integer pageSize,Integer pageCurrent);

	public List<Payment> queryHql(String hql, Integer pageSize, Integer pageCurrent);
	
}
