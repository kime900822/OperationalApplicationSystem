package com.kime.biz;

import java.util.List;

import com.kime.model.Approve;


public interface ApproveBIZ {
	
	
	public List getAllApprove();
	
	public List getParentApprove();
	
	public String getChildApprove(String parentID);
	
	public String delete(List<Approve> approves);
	
	public String update(List<Approve> approves);
	
	public Approve getApproveById(String id);
	
	public List getApproveAndChild(String id);
	
	public List query(String where);
	
	public List query(String where, int pageSize, int pageCurrent);
	
	public List getFirstApproveOfStamp4Select();
}
