package com.cuntoms.dao;

import java.util.List;

import com.cuntoms.model.CustomsGeneral;
import com.cuntoms.model.CustomsGeneralInit;


public interface CustomsGeneralDAO {

	public void save(CustomsGeneral general);
	
	public void save(CustomsGeneralInit generalInit);
	
	public void update(CustomsGeneral general);
	
	public void delete(CustomsGeneral general);
	
	public void delete(CustomsGeneralInit generalInit);
	
	public List query(String where);
	
	public List query(String where,Integer pageSize,Integer pageCurrent);
	
	public List query4init(String where);
	
	public List query4init(String where,Integer pageSize,Integer pageCurrent);
}
