package com.kime.biz;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kime.model.Role;

public interface RoleBIZ {
	public List getRole(String where,int pageSize,int pageCurrent);
	
	public List getRole(String where);
	
	public void update(Role role);
	
	public void delete(Role role);
	
	public void save(Role role);
	
	public void deleteAllOfName(Role role);
	
	public String getRoleName(String rid);
	
	public String getRoleID(String name);
}
