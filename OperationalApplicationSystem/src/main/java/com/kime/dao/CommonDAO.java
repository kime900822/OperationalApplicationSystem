package com.kime.dao;

import java.util.List;

public interface CommonDAO {
	
	public List queryByHql(String hql);
	
	public List queryBySql(String sql);
}
