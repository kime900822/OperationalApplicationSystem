package com.customs.biz;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.List;

import com.customs.model.CustomsGeneral;
import com.customs.model.CustomsGeneralInit;
import com.kime.model.HeadColumn;
import com.kime.model.User;


public interface CustomsGeneralBIZ {
	public List<CustomsGeneral> query(String month,String where) throws Exception;
	
	public List<CustomsGeneral> query(String month,String where, int pageSize, int pageCurrent) throws Exception;
	
	public String saveData(String month) throws Exception;
	
	public List<CustomsGeneralInit> query4init(String where);
	
	public List<CustomsGeneralInit> query4init(String where, int pageSize, int pageCurrent);
	
	public void initData(User user,File file, String first, String upfileFileName, int start) throws Exception;

	String deleteData(String month) throws Exception;

	ByteArrayInputStream exportData(String month, String where, List<HeadColumn> lHeadColumns) throws Exception;
	
	ByteArrayInputStream exportData4Init(List list, List<HeadColumn> lHeadColumns) throws Exception;
	
	String getSQL(String month,String where) throws Exception;

}
