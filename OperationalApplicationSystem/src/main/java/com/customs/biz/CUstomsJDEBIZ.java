package com.customs.biz;

import java.io.File;
import java.util.List;

import com.customs.model.CustomsMaterial;
import com.kime.model.User;

public interface CUstomsJDEBIZ {

	public void importData(User user,File file, String first, String upfileFileName, int start) throws Exception;
	
	public List<CustomsMaterial> query(String where);
	
	public List<CustomsMaterial> query(String where, int pageSize, int pageCurrent);
	
	public String deleteByBatchNumber(String batchNumber);
}
