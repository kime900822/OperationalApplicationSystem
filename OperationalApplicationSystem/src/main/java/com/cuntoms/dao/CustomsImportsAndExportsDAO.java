package com.cuntoms.dao;

import java.util.List;

import com.cuntoms.model.CustomsImportsAndExports;

public interface CustomsImportsAndExportsDAO {

	public void save(CustomsImportsAndExports customsImportsAndExports);
	
	public void update(CustomsImportsAndExports customsImportsAndExports);
	
	public void delete(CustomsImportsAndExports customsImportsAndExports);
	
	public List query(String where);
	
	public List query(String where,Integer pageSize,Integer pageCurrent);
}
