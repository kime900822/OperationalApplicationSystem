package com.cuntoms.biz;

import java.util.List;


public interface CustomsReport2BIZ {
	
	public List query(String where);
	
	public List query(String where, int pageSize, int pageCurrent);
}
