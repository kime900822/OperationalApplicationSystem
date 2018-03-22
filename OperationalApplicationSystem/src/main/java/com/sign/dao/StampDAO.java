package com.sign.dao;

import java.util.List;

import com.sign.model.Stamp;


public interface StampDAO {
	public void save(Stamp stamp);
	
	public void delete(Stamp stamp);
	
	public void update(Stamp stamp);
	
	public List<Stamp> queryHql(String hql);
	
	public List<Stamp> query(String where);

	public List<Stamp> query(String where,Integer pageSize,Integer pageCurrent);

	public List<Stamp> queryHql(String hql, Integer pageSize, Integer pageCurrent);
}
