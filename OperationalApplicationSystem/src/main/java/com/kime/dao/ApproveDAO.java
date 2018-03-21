package com.kime.dao;

import java.util.List;

import com.kime.model.Approve;

public interface ApproveDAO {
	public void save(Approve approve);
	
	public List getAllApprove();
	
	public Approve getApproveByID(String id);
	
	public List getApproveByParentID(String parentID);
	
	public void update(Approve approve);
	
	public void delete(Approve approve);
	
	public List getParentMenu();
	
	public List query(String where);
	
	public List query(String where,Integer pageSize,Integer pageCurrent);
	
}
