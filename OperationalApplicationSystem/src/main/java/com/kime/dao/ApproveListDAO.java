package com.kime.dao;

import java.util.List;

import com.kime.model.ApproveList;

public interface ApproveListDAO {
	
	public void save(ApproveList approveList);
	
	public List getApproveListByTradeId(String tradeId);
	
	public void update(ApproveList approveList);
	
	public void delete(ApproveList approveList);
		
	public List query(String where);
}
