package com.kime.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kime.model.Role;


public interface RoleDAO {
	public List query(String where);
	
	public void delete(Role role);
	
	public void save(Role role);
	
	public void update(Role role);
	
	public List query(String where,int pageSize,int pageCurrent);
	
	public void delete(String id);
}
