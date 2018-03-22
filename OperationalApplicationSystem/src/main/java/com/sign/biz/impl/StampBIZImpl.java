package com.sign.biz.impl;

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kime.base.BizBase;
import com.kime.dao.CommonDAO;
import com.kime.model.Approve;
import com.sign.biz.StampBIZ;
import com.sign.dao.StampDAO;
import com.sign.model.Stamp;
@Service
@Transactional(readOnly=true)
public class StampBIZImpl extends BizBase implements StampBIZ{

	@Autowired
	private Approve approve;
	@Autowired
	private StampDAO stamDAO;
	@Autowired
	private CommonDAO commonDAO;
	
	public Approve getApprove() {
		return approve;
	}

	public void setApprove(Approve approve) {
		this.approve = approve;
	}

	public StampDAO getStamDAO() {
		return stamDAO;
	}

	public void setStamDAO(StampDAO stamDAO) {
		this.stamDAO = stamDAO;
	}

	public CommonDAO getCommonDAO() {
		return commonDAO;
	}

	public void setCommonDAO(CommonDAO commonDAO) {
		this.commonDAO = commonDAO;
	}

	@Override
	public ByteArrayInputStream export() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void saveStamp(Stamp stamp) {
		stamDAO.save(stamp);
		logUtil.logInfo("用章申请单保存成功："+stamp.getApplicationCode());
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void submitStamp(Stamp stamp) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Stamp> getStamp(String where) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Stamp> getStamp(String where, Integer pageSize, Integer pageCurrent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMaxCode() {
		Date d=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMM");
		String hql="SELECT MAX(P.applicationCode) FROM Stamp P where SUBSTR(P.applicationCode,2,6)='"+sdf.format(d)+"'";
		List list= commonDAO.queryByHql(hql);
		if (list.size()>0) { 
			String mcode=(String)list.get(0);
			if (mcode!=null&&!mcode.equals("")) {
				return  "S"+String.valueOf(Integer.valueOf(mcode.replace("S", "")+1));
			}		
		}
			return "S"+sdf.format(d)+"0001";
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void approveStamp(Stamp stamp) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void rejectStamp(Stamp stamp) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Stamp> getStampByHql(String hql, Integer pageSize, Integer pageCurrent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Stamp> getStampByHql(String hql) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void updateStamp(Stamp stamp) {
		stamDAO.update(stamp);
		logUtil.logInfo("用章申请单更新成功："+stamp.getApplicationCode());
		
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void deleteStamp(Stamp stamp) {
		stamDAO.delete(stamp);
		logUtil.logInfo("用章申请单删除成功："+stamp.getApplicationCode());
		
	}

	
}
