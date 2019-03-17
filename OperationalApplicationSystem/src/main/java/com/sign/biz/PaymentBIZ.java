package com.sign.biz;

import java.io.ByteArrayInputStream;
import java.util.List;

import com.kime.model.User;
import com.sign.model.Payment;
import com.sign.model.PaymentPO;
import com.sign.model.PaymentWeek;

public interface PaymentBIZ {

	public ByteArrayInputStream export();
	
	public void savePayment(Payment payment);
	
	public void submitPayment(Payment payment) throws Exception;
	
	public List<Payment> getPayment(String where);
	
	public List<Payment> getPayment(String where,Integer pageSize,Integer pageCurrent);
	
	public void sign(Payment payment);
	
	public String getMaxCode();

	public void accPayment(Payment payment) throws Exception;

	public void assignPayment(Payment payment) throws Exception;

	public void approvePayment(Payment payment) throws Exception;

	public void invalidPayment(Payment payment) throws Exception;

	public void returnPayment(Payment payment) throws Exception;

	public void rejectPayment(Payment payment) throws Exception;

	public void printPayment(Payment payment) throws Exception;

	public List<Payment> getPaymentByHql(String hql, Integer pageSize, Integer pageCurrent);

	public List<Payment> getPaymentByHql(String hql);

	public void updatePayment(Payment payment);
	
	public void deletePayment(Payment payment);
	
	public void financeRejectPayment(String[] ids,String message,User user) throws Exception;
	
	public List<PaymentPO> getWeeklyPayment(String[] ids);
	
	public List<PaymentPO> getPaymentPO(String sql);
	
	public List<PaymentPO> getPaymentPO(String sql,Integer pageSize, Integer pageCurrent);
	
	void paidResetPayment(String[] ids) throws Exception;

	void mailInformPayment(String[] ids) throws Exception;

	List getPaidWeek(String where);
	
	List getPaidWeek(String where, Integer pageSize, Integer pageCurrent);

	void paidPayment(List<Payment> list) throws Exception;

	void approvePayment2(Payment payment) throws Exception;
}
