package com.sign.dao;

import java.util.List;

import com.sign.model.SignMan;

public interface SignManDAO {
	
	public List<SignMan> query(String where);
	
	public List<SignMan> query(String where,Integer pageSize,Integer pageCurrent);
	
	public void save(SignMan signMan);
	
	public void delete(SignMan signMan);
	
	public void update(SignMan signMan);
}
