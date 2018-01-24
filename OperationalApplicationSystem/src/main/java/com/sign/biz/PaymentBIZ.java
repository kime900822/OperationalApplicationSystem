package com.sign.biz;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import com.sign.model.Payment;

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
}
