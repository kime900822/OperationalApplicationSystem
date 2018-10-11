package com.kime.utils;

import java.util.List;

import com.kime.model.ApproveList;

public class ApproveListUtil {

	
	public static ApproveList search(List<ApproveList>list,String level){
		   for(int i=0; i < list.size(); i++){
		     if (list.get(i).getLevel().equals(level)) {
				return list.get(i);
		     }
		   }
		   return null;
	}
}
