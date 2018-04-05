package com.kime.biz;

import java.util.List;

import com.kime.model.ApproveHis;

public interface ApproveHisBIZ {
	
	public List getApproveHisByTradeId(String tradeId);
	
	public void delete(ApproveHis approveHis);
	
	public void save(ApproveHis approveHis);
		
}
