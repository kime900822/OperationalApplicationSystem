package com.kime.dao;

import java.util.List;

import com.kime.model.Approve;
import com.kime.model.ApproveHis;

public interface ApproveHisDAO {
	
	public void save(ApproveHis approveHis);
	
	public List getApproveHisByTradeId(String tradeId);
	
	public void update(ApproveHis approveHis);
	
	public void delete(ApproveHis approveHis);
		
	public List query(String where);
	
	
}
