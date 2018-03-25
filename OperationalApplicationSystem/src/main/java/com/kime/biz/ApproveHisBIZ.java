package com.kime.biz;

import java.util.List;

import com.kime.model.ApproveHis;

public interface ApproveHisBIZ {
	
	public List getApproveHisByTradeId(String tradeId);
	
	public ApproveHis save(String approveid,String comment,String status,String tradeId,String type);
	
		
}
