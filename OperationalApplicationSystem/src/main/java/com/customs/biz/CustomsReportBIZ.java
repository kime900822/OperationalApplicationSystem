package com.customs.biz;

import java.io.ByteArrayInputStream;
import java.util.List;

import com.kime.model.HeadColumn;


public interface CustomsReportBIZ {
	
	public List queryReport1(String where);
	
	public List queryReport1(String where, int pageSize, int pageCurrent);
	
	public List queryReport2(String where);
	
	public List queryReport2(String where, int pageSize, int pageCurrent);

	ByteArrayInputStream exportData(String hql, List<HeadColumn> lHeadColumns,String title,Class class1) throws Exception;
}
