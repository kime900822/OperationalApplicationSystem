package com.cuntoms.biz;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.List;

import com.cuntoms.model.CustomsMaterial;
import com.kime.model.HeadColumn;
import com.kime.model.User;

public interface CustomsMaterialBIZ {

	public void importData(User user,File file, String first, String upfileFileName, int start) throws Exception;
	
	public ByteArrayInputStream exportData(String where,List<HeadColumn> lHeadColumns) throws Exception;
	
	public String customsHandingOK(String batchNumber,User user);
	
	public String customsHandingNO(String batchNumber,User user);
	
	public List<CustomsMaterial> query(String where);
	
	public List<CustomsMaterial> query(String where, int pageSize, int pageCurrent);
	
	public String deleteByBatchNumber(String batchNumber);
	
	public List getByMaterialNo(String materialNo);
	
	public CustomsMaterial getByNo(String no);
}
