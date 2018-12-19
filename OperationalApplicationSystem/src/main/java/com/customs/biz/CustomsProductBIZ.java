package com.customs.biz;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.List;

import com.customs.model.CustomsProduct;
import com.kime.model.HeadColumn;
import com.kime.model.User;

public interface CustomsProductBIZ {

	public void importData(User user,File file, String first, String upfileFileName, int start) throws Exception;
	
	public ByteArrayInputStream exportData(String where,List<HeadColumn> lHeadColumns) throws Exception;
	
	public String customsHandingOK(String batchNumber,User user);
	
	public String customsHandingNO(String batchNumber,User user);
	
	public String deleteByBatchNumber(String batchNumber);
	
	public List<CustomsProduct> query(String where);
	
	public List<CustomsProduct> query(String where, int pageSize, int pageCurrent);
	
	
}
