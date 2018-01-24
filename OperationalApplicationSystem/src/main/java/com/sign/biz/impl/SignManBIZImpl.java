package com.sign.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sign.biz.SignManBIZ;
import com.sign.dao.SignManDAO;
import com.sign.model.SignMan;

@Service
@Transactional(readOnly=true)
public class SignManBIZImpl implements SignManBIZ {

	@Autowired
	SignManDAO signManDao;
	
	
	
	public SignManDAO getSignManDao() {
		return signManDao;
	}

	public void setSignManDao(SignManDAO signManDao) {
		this.signManDao = signManDao;
	}

	@Override
	public List<SignMan> getSianMan(String where) {
		return signManDao.query(where);
	}

	@Override
	public List<SignMan> getSianMan(String where, Integer pageSize, Integer pageCurrent) {
		return signManDao.query(where, pageSize, pageCurrent);
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void update(SignMan signMan) {
		signManDao.update(signMan);

		
	}

	
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void save(SignMan signMan) {
		signManDao.save(signMan);
		
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void delete(SignMan signMan) {
		signManDao.delete(signMan);
	}

}
