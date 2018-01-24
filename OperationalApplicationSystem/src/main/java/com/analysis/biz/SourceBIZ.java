package com.analysis.biz;

import java.util.List;

import com.analysis.model.Source;

public interface SourceBIZ {
	
	public List GetSource(String where);
	
	public void SaveSource(List<Source> lSources);
	
	public void DeleteSource(Source source);
	
	public void DeleteSource(String where);
}
