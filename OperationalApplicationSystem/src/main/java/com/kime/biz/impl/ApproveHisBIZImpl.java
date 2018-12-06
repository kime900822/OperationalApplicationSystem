package com.kime.biz.impl;

import java.text.MessageFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kime.base.BizBase;
import com.kime.biz.ApproveBIZ;
import com.kime.biz.ApproveHisBIZ;
import com.kime.biz.UserBIZ;
import com.kime.dao.ApproveHisDAO;
import com.kime.dao.CommonDAO;
import com.kime.infoenum.Message;
import com.kime.model.Approve;
import com.kime.model.ApproveHis;
import com.kime.model.User;
import com.kime.utils.CommonUtil;
import com.kime.utils.PropertiesUtil;
import com.kime.utils.mail.SendMail;
import com.sign.biz.StampBIZ;
import com.sign.dao.StampDAO;
import com.sign.model.Stamp;
import com.sign.model.StampApprove;
import com.sign.other.StampHelp;

@Service
@Transactional(readOnly=true)
public class ApproveHisBIZImpl extends BizBase implements ApproveHisBIZ{

	@Autowired
	CommonDAO commonDAO;
	@Autowired
	ApproveBIZ approveBIZ;
	@Autowired
	ApproveHisDAO approveHisDAO;
	@Autowired
	StampBIZ stampBIZ;
	@Autowired
	UserBIZ userBIZ;
	
	@Override
	public List getApproveHisByTradeId(String tradeId) {
		return approveHisDAO.getApproveHisByTradeId(tradeId);
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void delete(ApproveHis approveHis) {
		approveHisDAO.delete(approveHis);		
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void save(ApproveHis approveHis) {
		approveHisDAO.save(approveHis);		
	}






}
