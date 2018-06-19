package com.kime.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	
	/** 
     * 通过正则表达式的方式获取字符串中指定字符的个数 
     * @param text 指定的字符串 
     * @return 指定字符的个数 
     */  
	public static int pattern(String text,String c) {  
		if (text==null) {
			text="";
		}
        // 根据指定的字符构建正则  
        Pattern pattern = Pattern.compile(c);  
        // 构建字符串和正则的匹配  
        Matcher matcher = pattern.matcher(text);  
        int count = 0;  
        // 循环依次往下匹配  
        while (matcher.find()){ // 如果匹配,则数量+1  
            count++;  
        }  
        if (count>0) {
        	count++;
		}
        return  count;  
    }  
}
