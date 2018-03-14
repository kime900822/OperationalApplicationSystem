package com.sign.dao;

import java.util.List;

import com.sign.model.Sign4Stamp;

public interface CheckDAO {

	public List<?> query(String where);
	
	public void delete(Sign4Stamp check);
	
	public void insert(Sign4Stamp check);
	
	public void update(Sign4Stamp check);
	
	public List<?> query(String where,Integer pageSize,Integer pageCurrent);
	
}
