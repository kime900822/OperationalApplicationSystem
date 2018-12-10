package com.cuntoms.dao;

import java.util.List;

import com.cuntoms.model.CustomsGeneral;


public interface CustomsGeneralDAO {

	public void save(CustomsGeneral general);
	
	public void update(CustomsGeneral general);
	
	public void delete(CustomsGeneral general);
	
	public List query(String where);
	
	public List query(String where,Integer pageSize,Integer pageCurrent);
}
