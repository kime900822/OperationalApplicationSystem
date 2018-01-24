package com.kime.dao;

import java.util.List;

import com.kime.model.Dict;

public interface DictDAO {
	
	public List<Dict> query(String where);
	
	public List<Dict> query(String where,int pageSize,int pageCurrent);
	
	public void delete(Dict dict);
	
	public void save(Dict dict);
	
	public void update(Dict dict);
	
	public List getType();
}
