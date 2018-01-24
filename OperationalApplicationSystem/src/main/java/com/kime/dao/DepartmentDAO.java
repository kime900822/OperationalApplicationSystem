package com.kime.dao;

import java.util.List;

import com.kime.model.Department;

public interface DepartmentDAO {
	
	/**
	 * 保存
	 * @param department
	 */
	public void save(Department department);
	
	/**
	 * 分页查询
	 * @param where
	 * @param pageSize
	 * @param pageCurrent
	 * @return
	 */
	public List query(String where,Integer pageSize,Integer pageCurrent);
	
	/**
	 * 不分页查询
	 * @param where
	 * @return
	 */
	public List query(String where);
	
	/**
	 * 修改
	 * @param department
	 */
	public void update(Department department);
	
	/**
	 * 删除
	 * @param department
	 */
	public void delete(Department department);
}
