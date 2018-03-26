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
import com.sign.other.StampState;

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
		return approveBIZ.getApproveAndChild(tradeId);
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public ApproveHis save(String approveid, String comment,String status,String tradeId,String type) {
		Approve approve=(Approve)approveBIZ.query(" where id='"+approveid+"'").get(0);
		ApproveHis approveHis=new ApproveHis();
		approveHis.setDate(CommonUtil.getDateTemp());
		approveHis.setLevel(approve.getLevel());
		approveHis.setuName(approve.getUname());
		approveHis.setuId(approve.getUid());
		approveHis.setName(approve.getName());
		approveHis.setStatus(status);
		approveHis.setType(type);
		approveHis.setTradeId(tradeId);
		approveHis.setComment(comment);
		approveHis.setdId(approve.getDid());
		approveHis.setdName(approve.getDname());
		approveHisDAO.save(approveHis);
		if (type.equals("STAMP")) {
			Stamp stamp=stampBIZ.getStampById(tradeId);
			if (stamp.getApprove().size()-1>Integer.parseInt(approve.getLevel())) {
				stamp.setNextApprover(stamp.getApprove().get(Integer.parseInt(approve.getLevel())+1).getUid());				
			}
			else{
				stamp.setState(StampState.SUCCESS);
				User user=userBIZ.getUser(" where uid='"+stamp.getApplicantID()+"'").get(0);
				SendMail.SendMail(user.getEmail(), PropertiesUtil.ReadProperties(Message.MAIL_PROPERTIES, "mailTitleOfStamp"), MessageFormat.format(PropertiesUtil.ReadProperties(Message.MAIL_PROPERTIES, "mailContentOfStamp"),approve.getUname()));
			}
			stampBIZ.saveStamp(stamp);
		}
			
		return approveHis;
	}






}
