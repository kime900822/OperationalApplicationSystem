package com.analysis.dao;

import java.util.List;

import com.analysis.model.CustomsRecords;

public interface CustomsRecordsDAO {
	public List Query(String where);
	
	public void Save(CustomsRecords customsRecords);
	
	public void Delete(CustomsRecords customsRecords);
	
	public void Delete(String where);
}
