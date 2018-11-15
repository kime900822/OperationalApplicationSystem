package com.analysis.biz;

import java.io.File;
import java.util.List;

import com.analysis.model.CustomsRecords;
import com.kime.model.User;


public interface CustomsRecordsBIZ {
	
	public List query(String where);
	
	public void fileToData(User user,File file,String first,String upfileFileName,int start) throws Exception;
	
	public void delete(String where);
	
}
