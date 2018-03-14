package com.sign.dao;

import java.util.List;

import com.sign.model.Check;


public interface CheckDAO {

	public List<?> query(String where);
	
	public void delete(Check check);
	
	public void insert(Check check);
	
	public void update(Check check);
	
	public List<?> query(String where,Integer pageSize,Integer pageCurrent);
	
}
