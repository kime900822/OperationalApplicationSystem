package com.sign.biz;

import java.util.List;

import com.kime.model.Approve;
import com.kime.model.ApproveHis;
import com.kime.model.ApproveList;
import com.sign.model.paymentVisit.PaymentVisit;
import com.sign.model.paymentVisit.PaymentVisitBusinessTrip;

public interface PaymentVisitBIZ {

	public void save(PaymentVisit paymentVisit);
	
	public void delete(PaymentVisit paymentVisit);
	
	public void update(PaymentVisit paymentVisit) throws Exception;
	
	public List<PaymentVisit> query(String where);
	
	public List<PaymentVisit> query(String where,Integer pageSize,Integer pageCurrent);
	
	public List<PaymentVisit> queryByHql(String hql);
	
	public List<PaymentVisit> queryByHql(String hql,Integer pageSize,Integer pageCurrent);
	
	public String getMaxCode();
	
	ApproveHis approve(String level, String comment, String approveState, String tradeId) throws Exception;
	
	public PaymentVisit queryById(String id);
	
	public void cancel(String id) throws Exception;
	
	public void buildPayment(PaymentVisit paymentVisit) throws Exception;

	List<Approve> getApprove();
}
