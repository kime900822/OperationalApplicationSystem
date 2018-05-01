package com.sign.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kime.base.BizBase;
import com.kime.dao.CommonDAO;
import com.sign.biz.PaymentWeekBIZ;
import com.sign.dao.PaymentWeekDAO;
import com.sign.model.PaymentWeek;
@Service
@Transactional(readOnly=true)
public class PaymentWeekBIZImpl extends BizBase implements PaymentWeekBIZ {

	@Autowired
	private PaymentWeekDAO paymentWeekDAO;
	@Autowired
	private CommonDAO commonDAO;
	
	@Override
	public void save(PaymentWeek paymentWeek) {
		paymentWeekDAO.save(paymentWeek);		
	}

	@Override
	public void update(PaymentWeek paymentWeek) {
		paymentWeekDAO.update(paymentWeek);
	}

	@Override
	public void delete(PaymentWeek paymentWeek) {
		paymentWeekDAO.delete(paymentWeek);
	}

	@Override
	public List<PaymentWeek> getPayment(String where) {
		return paymentWeekDAO.query(where);
	}

	@Override
	public List<PaymentWeek> getPayment(String where, Integer pageSize, Integer pageCurrent) {
		return paymentWeekDAO.query(where, pageSize, pageCurrent);
	}

}
