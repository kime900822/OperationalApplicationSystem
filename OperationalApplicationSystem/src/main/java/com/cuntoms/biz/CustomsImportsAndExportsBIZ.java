package com.cuntoms.biz;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.List;

import com.cuntoms.model.CustomsImportsAndExports;
import com.kime.model.HeadColumn;
import com.kime.model.User;

public interface CustomsImportsAndExportsBIZ {

	public void importData(User user,File file, String first, String upfileFileName, int start) throws Exception;
	
	public ByteArrayInputStream exportData(String where,List<HeadColumn> lHeadColumns) throws Exception;
	
	public List<CustomsImportsAndExports> query(String where);
	
	public List<CustomsImportsAndExports> query(String where, int pageSize, int pageCurrent);
	
	public String deleteByBatchNumber(String batchNumber);
	
	public String editNo(String id,String no);

	public CustomsImportsAndExports getById(String id);
}
