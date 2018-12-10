package com.cuntoms.biz;

import java.util.List;

import com.cuntoms.model.CustomsGeneral;


public interface CustomsGeneralBIZ {
	public List<CustomsGeneral> query(String where);
	
	public List<CustomsGeneral> query(String where, int pageSize, int pageCurrent);
	
	public String deleteByDate(String date);
	
	public String buildData();
}
