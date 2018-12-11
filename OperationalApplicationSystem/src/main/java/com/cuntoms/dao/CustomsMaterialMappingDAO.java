package com.cuntoms.dao;

import java.util.List;

import com.cuntoms.model.CustomsMaterialMapping;


public interface CustomsMaterialMappingDAO {

	public void save(CustomsMaterialMapping cMapping);
	
	public void update(CustomsMaterialMapping cMapping);
	
	public void delete(CustomsMaterialMapping cMapping);
	
	public List query(String where);
	
	public List query(String where,Integer pageSize,Integer pageCurrent);
}
