package com.kime.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kime.biz.DictBIZ;
import com.kime.dao.DictDAO;
import com.kime.model.Dict;

@Service
@Transactional(readOnly=true)
public class DictBIZImpl implements DictBIZ {

	@Autowired
	DictDAO dictDao;
	
	public DictDAO getDictDao() {
		return dictDao;
	}

	public void setDictDao(DictDAO dictDao) {
		this.dictDao = dictDao;
	}

	@Override
	public List<Dict> getALLSign() {
		return dictDao.query(" where type='SIGN_TYPE' ");
	}


	
	@Override
	public List<Dict> getAllType() {
		// TODO Auto-generated method stub
		return dictDao.getType();
	}

	@Override
	public List<Dict> getDict(String where) {
		return dictDao.query(where);
	}

	@Override
	public List<Dict> getDict(String where, Integer pageSize, Integer pageCurrent) {
		return dictDao.query(where, pageSize, pageCurrent);
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void save(Dict dict) {
		dictDao.save(dict);
		
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void update(Dict dict) {
		dictDao.update(dict);
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void delete(Dict dict) {
		dictDao.delete(dict);
	}
	
	
	
}
