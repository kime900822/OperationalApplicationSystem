package com.cuntoms.dao;

import java.util.List;

import com.cuntoms.model.CustomsJDE;


public interface CustomsJDEDAO {

	public void save(CustomsJDE customsJDE);
	
	public void update(CustomsJDE customsJDE);
	
	public void delete(CustomsJDE customsJDE);
	
	public List query(String where);
	
	public List query(String where,Integer pageSize,Integer pageCurrent);
}
