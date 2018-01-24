package com.kime.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kime.biz.RoleBIZ;
import com.kime.dao.RoleDAO;
import com.kime.model.Role;

@Service
@Transactional(readOnly=true)
public class RoleBIZImpl implements RoleBIZ {
	
	@Autowired
	RoleDAO roleDao;
	
	
	public RoleDAO getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(RoleDAO roleDao) {
		this.roleDao = roleDao;
	}

	@Override
	public List getRole(String where,int pageSize,int pageCurrent) {
		return roleDao.query(where,pageSize,pageCurrent);
	}

	@Override
	public List getRole(String where) {
		return roleDao.query(where);
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void update(Role role) {
		Role tmp=(Role)roleDao.query(" where rid='"+role.getRid()+"'").get(0);
		List<Role> lRoles=roleDao.query(" WHERE NAME='"+tmp.getName()+"'");
		for (Role r : lRoles) {
			r.setName(role.getName());
			r.setDescription(role.getDescription());
			roleDao.update(r);
		}
		
		
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void deleteAllOfName(Role role) {
		
		List<Role> lRoles=roleDao.query(" WHERE NAME='"+role.getName()+"'");
		for (Role r : lRoles) {
			roleDao.delete(r);
		}
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void delete(Role role) {
		roleDao.delete(role);
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void save(Role role) {
			roleDao.save(role);		
	}

	@Override
	public String getRoleName(String rid) {
		List<Role> list=roleDao.query(" where rid='"+rid+"'");
		if (list.size()>0) {
			return list.get(0).getName();
		}else{
			return null;
		}		
	}

	@Override
	public String getRoleID(String name) {
		List<Role> list=roleDao.query(" where name='"+name+"'");
		if (list.size()>0) {
			return list.get(0).getRid();
		}else{
			return null;
		}		
	}
	
	

}
