package com.sign.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kime.base.BizBase;
import com.sign.biz.PaymentVisitEmployeeBIZ;
import com.sign.dao.PaymentVisitEmployeeDAO;
import com.sign.model.paymentVisit.PaymentVisitEmployee;

@Service
@Transactional(readOnly=true)
public class PaymentVisitEmployeeBIZImpl extends BizBase implements PaymentVisitEmployeeBIZ {

	@Autowired
	PaymentVisitEmployeeDAO paymentVisitEmployeeDAO;
	
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void save(PaymentVisitEmployee paymentVisitEmployee) {
		paymentVisitEmployeeDAO.save(paymentVisitEmployee);
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void delete(PaymentVisitEmployee paymentVisitEmployee) {
		paymentVisitEmployeeDAO.delete(paymentVisitEmployee);
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void udate(PaymentVisitEmployee paymentVisitEmployee) {
		paymentVisitEmployeeDAO.update(paymentVisitEmployee);
	}

	@Override
	public List<PaymentVisitEmployee> query(String where) {
		return paymentVisitEmployeeDAO.query(where);
	}

	@Override
	public List<PaymentVisitEmployee> query(String where, Integer pageSize, Integer pageCurrent) {
		return paymentVisitEmployeeDAO.query(where, pageSize, pageCurrent);
	}
	
	

}
