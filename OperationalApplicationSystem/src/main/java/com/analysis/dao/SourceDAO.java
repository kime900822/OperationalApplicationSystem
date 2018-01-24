package com.analysis.dao;

import java.util.List;

import com.analysis.model.Source;

public interface SourceDAO {

	public List Query(String where);
	
	public void Save(Source source);
	
	public void Delete(Source source);
	
	public void Delete(String where);
}
