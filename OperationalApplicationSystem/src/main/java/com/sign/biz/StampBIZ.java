package com.sign.biz;

import java.io.ByteArrayInputStream;
import java.util.List;

import com.sign.model.Stamp;

public interface StampBIZ {

	public ByteArrayInputStream export();
	
	public void saveStamp(Stamp stamp);
	
	public void submitStamp(Stamp stamp) throws Exception;
	
	public List<Stamp> getStamp(String where);
	
	public Stamp getStampById(String where) throws Exception;
	
	public List<Stamp> getStamp(String where,Integer pageSize,Integer pageCurrent);
	
	public String getMaxCode();

	public void approveStamp(Stamp stamp) throws Exception;

	public void rejectStamp(Stamp stamp) throws Exception;

	public List<Stamp> getStampByHql(String hql, Integer pageSize, Integer pageCurrent);

	public List<Stamp> getStampByHql(String hql);

	public void updateStamp(Stamp stamp) throws Exception;
	
	public void deleteStamp(Stamp stamp);
}
