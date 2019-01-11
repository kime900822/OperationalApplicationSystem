package com.customs.biz;

import java.io.ByteArrayInputStream;
import java.util.List;

import com.kime.model.HeadColumn;


public interface CustomsReportBIZ {
	

	public List queryReport(String where);
	
	public List queryReport(String where, int pageSize, int pageCurrent,String method) throws Exception;

	ByteArrayInputStream exportData(String sql, List<HeadColumn> lHeadColumns,String title,Class class1) throws Exception;
}
