package com.sign.biz;

import java.util.List;

import com.sign.model.Sign4Stamp;

public interface Sign4StampBIZ {

	public String save(Sign4Stamp check);
	
	public String delete(Sign4Stamp check);
	
	public String update(Sign4Stamp check);
	
	public List<?> getDocumentType();
	
	public List<?> query(String where);
	
	public List<?> query(String where, Integer pageSize, Integer pageCurrent);
}
