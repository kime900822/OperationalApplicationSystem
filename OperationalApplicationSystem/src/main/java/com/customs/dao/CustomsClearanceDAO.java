package com.customs.dao;

import java.util.List;

import com.customs.model.CustomsClearance;

public interface CustomsClearanceDAO {

	public void save(CustomsClearance clearance);
	
	public void update(CustomsClearance clearance);
	
	public void delete(CustomsClearance clearance);
	
	public List query(String where);
	
	public List query(String where,Integer pageSize,Integer pageCurrent);
}
