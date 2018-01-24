package com.kime.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtil {
	
	public static String getDateTemp(){
		java.util.Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH24:mm:ss");
		return sdf.format(date);
		
	}
}
