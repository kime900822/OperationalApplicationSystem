package com.kime.biz;

import java.util.List;

import com.kime.model.Approve;


public interface ApproveBIZ {
	
	public void editApprove(Approve approve);
	
	public List getAllApprove();
	
	public List getParentApprove();
	
	public String getChildApprove(String parentID);
	
	public void deleteApprove(Approve approve);
	
	public Approve getApproveById(String id);
	
	public List getParentApproveByRole(String role);
	
}
