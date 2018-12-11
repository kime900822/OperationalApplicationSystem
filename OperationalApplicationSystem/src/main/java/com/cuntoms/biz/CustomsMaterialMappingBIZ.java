package com.cuntoms.biz;

import java.io.File;
import java.util.List;

import com.cuntoms.model.CustomsMaterialMapping;
import com.kime.model.User;

public interface CustomsMaterialMappingBIZ {
	
	public void importData(User user,File file, String first, String upfileFileName, int start) throws Exception;
	
	public List<CustomsMaterialMapping> query(String where);
	
	public List<CustomsMaterialMapping> query(String where, int pageSize, int pageCurrent);
	
	public String delete(List<CustomsMaterialMapping> list);
	
	public String update(CustomsMaterialMapping cMapping);
	
	public String save(CustomsMaterialMapping cMapping);
}
