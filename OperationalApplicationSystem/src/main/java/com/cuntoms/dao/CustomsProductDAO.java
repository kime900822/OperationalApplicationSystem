package com.cuntoms.dao;

import java.util.List;

import com.cuntoms.model.CustomsProduct;

public interface CustomsProductDAO {

	
	public void save(CustomsProduct customsProduct);
	
	public void update(CustomsProduct customsProduct);
	
	public void delete(CustomsProduct customsProduct);
	
	public List query(String where);
	
	public List query(String where,Integer pageSize,Integer pageCurrent);
	
}
