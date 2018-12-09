package com.cuntoms.biz;

import java.util.List;


public interface CustomsReportBIZ {
	
	public List queryReport1(String where);
	
	public List queryReport1(String where, int pageSize, int pageCurrent);
	
	public List queryReport2(String where);
	
	public List queryReport2(String where, int pageSize, int pageCurrent);
}
