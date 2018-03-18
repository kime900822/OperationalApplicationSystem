package com.kime.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kime.base.BizBase;
import com.kime.biz.ApproveBIZ;
import com.kime.dao.ApproveDAO;
import com.kime.model.Approve;

@Service
@Transactional(readOnly=true)
public class ApproveBIZImpl extends BizBase implements ApproveBIZ {
	
	@Autowired
	ApproveDAO approveDAO;

	public ApproveDAO getApproveDAO() {
		return approveDAO;
	}

	public void setApproveDAO(ApproveDAO approveDAO) {
		this.approveDAO = approveDAO;
	}

	@Override
	public void editApprove(Approve approve) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List getAllApprove() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getParentApprove() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getChildApprove(String parentID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteApprove(Approve approve) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Approve getApproveById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getParentApproveByRole(String role) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
