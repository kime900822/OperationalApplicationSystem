package com.sign.biz.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kime.base.BizBase;
import com.kime.biz.ApproveBIZ;
import com.kime.dao.ApproveDAO;
import com.kime.dao.CommonDAO;
import com.kime.dao.DictDAO;
import com.kime.infoenum.Message;
import com.kime.model.Approve;
import com.kime.model.Dict;
import com.kime.utils.CommonUtil;
import com.kime.utils.PropertiesUtil;
import com.sign.biz.PaymentVisitBIZ;
import com.sign.dao.PaymentVisitDAO;
import com.sign.dao.PaymentVisitEmployeeDAO;
import com.sign.model.paymentVisit.PaymentVisit;
import com.sign.model.paymentVisit.PaymentVisitEmployee;
import com.sign.other.PaymentVisitHelp;

@Service
@Transactional(readOnly=true)
public class PaymentVisitBIZImpl extends BizBase implements PaymentVisitBIZ {

	@Autowired
	PaymentVisitDAO paymentVisitDAO;
	@Autowired
	CommonDAO commonDAO;
	@Autowired
	PaymentVisitEmployeeDAO PaymentVisitEmployeeDAO;
	@Autowired
	DictDAO dictDAO;
	@Autowired
	ApproveBIZ approveBIZ;
	
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void save(PaymentVisit paymentVisit) {
		
		List<PaymentVisitEmployee> lEmployees=PaymentVisitEmployeeDAO.query(" where VisitID='"+paymentVisit.getId()+"'");
		
		for (PaymentVisitEmployee employee : lEmployees) {
			PaymentVisitEmployeeDAO.delete(employee);
		}
		
		for (PaymentVisitEmployee employee : paymentVisit.getEmployees()) {
			employee.setVisitId(paymentVisit.getId());
			PaymentVisitEmployeeDAO.save(employee);
		}
			
		
		paymentVisitDAO.save(paymentVisit);
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void delete(PaymentVisit paymentVisit) {

		paymentVisitDAO.delete(paymentVisit);
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void update(PaymentVisit paymentVisit) throws Exception {
		if (paymentVisit.getState().equals(PaymentVisitHelp.SUBMIT)) {
			String paymentVisitApprove=PropertiesUtil.ReadProperties(Message.SYSTEM_PROPERTIES, "PaymentVisitApprove");
			List<Dict> list=dictDAO.query(" where type='CHECKTYPE' and key='"+paymentVisitApprove+"'");
			if (list.size()==0) {
				throw new Exception("未找到类型为:"+paymentVisitApprove+"的签核类型");
			}		
			List<Approve> lApproves = approveBIZ.getApproveAndChild(list.get(0).getValueExplain());		
		}

		
		
		paymentVisitDAO.update(paymentVisit);
	}

	@Override
	public List<PaymentVisit> query(String where) {
		return paymentVisitDAO.query(where);
	}

	@Override
	public List<PaymentVisit> query(String where, Integer pageSize, Integer pageCurrent) {
		return paymentVisitDAO.query(where, pageSize, pageCurrent);
	}

	@Override
	public String getMaxCode() {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String hql = "SELECT MAX(P.referenceNo) FROM PaymentVisit P where SUBSTR(P.referenceNo,2,6)='" + sdf.format(d)
				+ "'";
		List list = commonDAO.queryByHql(hql);
		if (list.size() > 0) {
			String mcode = (String) list.get(0);
			if (mcode != null && !mcode.equals("")) {

				return "T" + String.valueOf(Long.valueOf(mcode.replace("T", "")) + 1);
			}
		}
		return "S" + sdf.format(d) + "01";
	}

	
}
