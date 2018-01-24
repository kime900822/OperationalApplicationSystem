package com.sign.biz;

import java.util.List;

import com.sign.model.SignMan;

public interface SignManBIZ {
	
	public List<SignMan> getSianMan(String where);
	
	public List<SignMan> getSianMan(String where, Integer pageSize, Integer pageCurrent);
	
	public void update(SignMan signMan);
	
	public void save(SignMan signMan);
	
	public void delete(SignMan signMan);
}
