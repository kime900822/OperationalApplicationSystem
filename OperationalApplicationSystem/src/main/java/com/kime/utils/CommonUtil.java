package com.kime.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.language.bm.Lang;
import org.hibernate.dialect.identity.SybaseAnywhereIdentityColumnSupport;

import com.kime.infoenum.Message;
import com.kime.model.User;

public class CommonUtil {
	
	public static boolean isAdmin(User user){
		String name=PropertiesUtil.ReadProperties(Message.SYSTEM_PROPERTIES, "id");
		if (name.equals(user.getUid())) {
			return true;
		}
		return false;
	}
	
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
	
	public static String getMonth(){
		java.util.Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
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
	
	   /** 
     * 数字金额大写转换，思想先写个完整的然后将如零拾替换成零 要用到正则表达式 
     */ 
    public static String digitUppercase(double n) {  
        String fraction[] = { "角", "分"};  
        String digit[] = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};  
        String unit[][] = { { "元", "万", "亿"}, { "", "拾", "佰", "仟"}};  
           
        String head = n < 0 ? "负" : "";  
        n = Math.abs(n);  
           
        String s = "";  
        for (int i = 0; i < fraction.length; i++) {  
            s += (digit[(int) (Math.floor(n * 10 * Math.pow(10, i)) % 10)] + fraction[i]).replaceAll("(零.)+", "");  
        }  
        if (s.length() < 1) {  
            s = "整";  
        }  
        int integerPart = (int) Math.floor(n);  
           
        for (int i = 0; i < unit[0].length && integerPart > 0; i++) {  
            String p = "";  
            for (int j = 0; j < unit[1].length && n > 0; j++) {  
                p = digit[integerPart % 10] + unit[1][j] + p;  
                integerPart = integerPart / 10;  
            }  
            s = p.replaceAll("(零.)*零$", "").replaceAll("^$", "零") + unit[0][i] + s;  
        }  
        return head + s.replaceAll("(零.)*零元", "元").replaceFirst("(零.)+", "").replaceAll("(零.)+", "零").replaceAll("^整$", "零元整");  
    }  
    
    
    
    public static String formatAmount(String s) {
    	
        Double a=Double.valueOf(s);
        DecimalFormat df=new DecimalFormat("###,##0.00"); //保留一位小数  
    	return String.valueOf(df.format(a));
    }
    
    /**
     * 将空值转换成NULL
     * @param tmp
     * @return
     */
    public static String spaceToNull(String tmp){
    	if (tmp!=null&&tmp.equals("")) {
			return null;
		}else {
			return tmp;
		}
    	
    }
}
