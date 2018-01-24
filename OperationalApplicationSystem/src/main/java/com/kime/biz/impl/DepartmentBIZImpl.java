package com.kime.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kime.biz.DepartmentBIZ;
import com.kime.dao.DepartmentDAO;
import com.kime.model.Department;

@Service
@Transactional(readOnly=true)
public class DepartmentBIZImpl implements DepartmentBIZ {

	@Autowired
	DepartmentDAO departmentDAO;
	
	public DepartmentDAO getDepartmentDAO() {
		return departmentDAO;
	}

	public void setDepartmentDAO(DepartmentDAO departmentDAO) {
		this.departmentDAO = departmentDAO;
	}

	
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void saveDepartment(Department department) {
		departmentDAO.save(department);
		
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void deleteDepartment(Department department) {
		departmentDAO.delete(department);
		
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void updateDepartment(Department department) {
		departmentDAO.update(department);
		
	}

	@Override
	public List<Department> queryDepartment(String where) {
		return departmentDAO.query(where);
	}

	@Override
	public List<Department> queryDepartment(String where, int pageSize, int pageCurrent) {
		return departmentDAO.query(where, pageSize, pageCurrent);
	}

}
