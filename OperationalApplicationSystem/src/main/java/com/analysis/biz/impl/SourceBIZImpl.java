package com.analysis.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.analysis.biz.SourceBIZ;
import com.analysis.dao.SourceDAO;
import com.analysis.model.Source;

@Service
@Transactional(readOnly=true)
public class SourceBIZImpl implements SourceBIZ {

	@Autowired
	SourceDAO sourceDao;
	
	
	public SourceDAO getSourceDao() {
		return sourceDao;
	}

	public void setSourceDao(SourceDAO sourceDao) {
		this.sourceDao = sourceDao;
	}

	@Override
	public List GetSource(String where) {
		return sourceDao.Query(where);
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void SaveSource(List<Source> lSources) {
		for (Source source : lSources) {
			sourceDao.Save(source);
		}
		
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void DeleteSource(Source source) {
		sourceDao.Delete(source);
		
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void DeleteSource(String where) {
		sourceDao.Delete(where);
		
	}

}
