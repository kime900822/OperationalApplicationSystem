package com.customs.dao;

import java.util.List;

import com.customs.model.CustomsMaterial;

public interface CustomsMaterialDAO {

	
	public void save(CustomsMaterial customsMaterial);
	
	public void update(CustomsMaterial customsMaterial);
	
	public void delete(CustomsMaterial customsMaterial);
	
	public List query(String where);
	
	public List query(String where,Integer pageSize,Integer pageCurrent);
	
}
