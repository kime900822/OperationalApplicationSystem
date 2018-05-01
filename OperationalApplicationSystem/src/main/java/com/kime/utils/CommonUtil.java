package com.kime.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CommonUtil {
	
	public static String getDateTemp(){
		java.util.Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
		
	}
	
	public static String getDate(){
		java.util.Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
		
	}
	
	public static String getWeek(){
		java.util.Date date=new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.setTime(date);		
		return "FW"+calendar.get(Calendar.WEEK_OF_YEAR);
		
	}
}
