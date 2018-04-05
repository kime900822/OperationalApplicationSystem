package com.sign.biz;

import java.io.ByteArrayInputStream;
import java.util.List;

import com.kime.model.ApproveHis;
import com.sign.model.Stamp;

public interface StampBIZ {

	public ByteArrayInputStream export();
	
	public void saveStamp(Stamp stamp);
	
	public void submitStamp(Stamp stamp) throws Exception;
	
	public List<Stamp> getStamp(String where);
	
	public Stamp getStampById(String where) throws Exception;
	
	public List<Stamp> getStamp(String where,Integer pageSize,Integer pageCurrent);
	
	public String getMaxCode();

	public List<Stamp> getStampByHql(String hql, Integer pageSize, Integer pageCurrent);

	public List<Stamp> getStampByHql(String hql);

	public void update(Stamp stamp) throws Exception;
		
	public void deleteStamp(Stamp stamp);
	
	public void updateOfApporve(Stamp stamp) throws Exception;

	ApproveHis StampApprove(String level, String comment, String status, String tradeId);
}
