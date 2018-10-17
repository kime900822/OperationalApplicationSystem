package com.sign.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kime.base.BizBase;
import com.sign.biz.PaymentVisitBusinessTripBIZ;
import com.sign.dao.PaymentVisitBusinessTripDAO;
import com.sign.model.paymentVisit.PaymentVisitBusinessTrip;


@Service
@Transactional(readOnly=true)
public class PaymentVisitBusinessTripBIZImpl extends BizBase implements PaymentVisitBusinessTripBIZ {

	@Autowired
	PaymentVisitBusinessTripDAO paymentVisitBusinessTripDAO;
	
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void save(PaymentVisitBusinessTrip paymentVisitBusinessTrip) {
		paymentVisitBusinessTripDAO.save(paymentVisitBusinessTrip);
	}
	
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void save(List<PaymentVisitBusinessTrip> lPaymentVisitBusinessTrip) {
		
		List<PaymentVisitBusinessTrip> list= paymentVisitBusinessTripDAO.query(" where visitId='"+lPaymentVisitBusinessTrip.get(0).getVisitId()+"'");
		
		for (PaymentVisitBusinessTrip paymentVisitBusinessTrip : list) {
			paymentVisitBusinessTripDAO.delete(paymentVisitBusinessTrip);
		}
		
		for (PaymentVisitBusinessTrip paymentVisitBusinessTrip : lPaymentVisitBusinessTrip) {
			paymentVisitBusinessTripDAO.save(paymentVisitBusinessTrip);
		}
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void delete(PaymentVisitBusinessTrip paymentVisitBusinessTrip) {
		paymentVisitBusinessTripDAO.delete(paymentVisitBusinessTrip);
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void udate(PaymentVisitBusinessTrip paymentVisitBusinessTrip) {
		paymentVisitBusinessTripDAO.update(paymentVisitBusinessTrip);
	}

	@Override
	public List<PaymentVisitBusinessTrip> query(String where) {
		return paymentVisitBusinessTripDAO.query(where);
	}

	@Override
	public List<PaymentVisitBusinessTrip> query(String where, Integer pageSize, Integer pageCurrent) {
		return paymentVisitBusinessTripDAO.query(where, pageSize, pageCurrent);
	}

	
}
