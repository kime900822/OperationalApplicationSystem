package com.kime.dao;

import java.util.List;

public interface CommonDAO {
	
	public List queryByHql(String hql);
	
	public List queryByHql(String hql,Integer pageSize,Integer pageCurrent);
	
	public List queryBySql(String sql);
	
	public void executeSQL(String sql);
	
	public void executeHQL(String hql);
	
	public List queryBySql(String sql,Integer pageSize,Integer pageCurrent);

	
}
