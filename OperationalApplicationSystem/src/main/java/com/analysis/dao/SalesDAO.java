package com.analysis.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.analysis.model.Sales;

@Repository
public interface SalesDAO {

	public List Query(String where);
	
	public void Save(Sales sales);
	
	public void Delete(Sales sales);
	
	public void Delete(String where);
}
