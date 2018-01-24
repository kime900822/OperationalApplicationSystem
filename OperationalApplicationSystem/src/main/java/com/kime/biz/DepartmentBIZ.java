package com.kime.biz;

import java.util.List;

import com.kime.model.Department;

public interface DepartmentBIZ {
	public void saveDepartment(Department department);
	
	public void deleteDepartment(Department department);
	
	public void updateDepartment(Department department);
	
	public List<Department> queryDepartment(String where);
	
	public List<Department> queryDepartment(String where, int pageSize, int pageCurrent);
	
}
