package com.cuntoms.biz;

import java.io.File;
import java.util.List;

import com.cuntoms.model.CustomsGeneral;
import com.cuntoms.model.CustomsGeneralInit;
import com.kime.model.User;


public interface CustomsGeneralBIZ {
	public List<CustomsGeneral> query(String where);
	
	public List<CustomsGeneral> query(String where, int pageSize, int pageCurrent);
	
	public String deleteByDate(String date);
	
	public String buildData();
	
	public List<CustomsGeneralInit> query4init(String where);
	
	public List<CustomsGeneralInit> query4init(String where, int pageSize, int pageCurrent);
	
	public void initData(User user,File file, String first, String upfileFileName, int start) throws Exception;

}
