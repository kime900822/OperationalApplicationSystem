package com.cuntoms.biz;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.List;

import com.cuntoms.model.CustomsClearance;
import com.kime.model.HeadColumn;
import com.kime.model.User;

public interface CustomsClearanceBIZ {

	public void importData(User user,File file, String first, String upfileFileName, int start) throws Exception;
	
	public ByteArrayInputStream exportData(String where,List<HeadColumn> lHeadColumns) throws Exception;
	
	public List<CustomsClearance> query(String where);
	
	public List<CustomsClearance> query(String where, int pageSize, int pageCurrent);
	
	public String editNo(String id,String no);

	public CustomsClearance getById(String id);
	
	public String deleteByBatchNumber(String batchNumber);
}
