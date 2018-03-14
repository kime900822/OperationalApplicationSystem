package com.sign.biz;

import java.util.List;

import com.sign.model.Check;


public interface CheckBIZ {

	public String save(Check check);
	
	public String delete(List check);
	
	public String update(Check check);
	
	public List<?> getDocumentType();
	
	public List<?> query(String where);
	
	public List<?> query(String where, Integer pageSize, Integer pageCurrent);
}
