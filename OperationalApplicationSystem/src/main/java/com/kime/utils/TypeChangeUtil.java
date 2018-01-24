package com.kime.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class TypeChangeUtil {
	
	/**
	 * 四舍五入取2位小数
	 * @param tmp1 除数
	 * @param tmp2 被除数
	 * @return
	 */
	public static String division(String tmp1,String tmp2){	
		DecimalFormat   fnum   =   new   DecimalFormat("##0.00");  
		double value=Double.valueOf(tmp1)/Double.valueOf(tmp2);
		String   dd=fnum.format(value);      
		return dd;		
	}
	
	
	/**
	 * 格式化金额		
	 * @param s
	 * @param len
	 * @return
	 */
	public static String formatMoney(String s, int len,String type) 
	{
		if (s == null || s.length() < 1) {
			return "";
		}
		NumberFormat formater = null;
		double num = Double.parseDouble(s);
		
		NumberFormat nf = NumberFormat.getNumberInstance();
		nf.setMaximumFractionDigits(len);
		nf.setGroupingUsed(true);
		String result=nf.format(num);
		
		if(result.indexOf(".") == -1)
		{
			result = type +" "+ result + ".00";
		}
		else
		{
			String[] strings=result.split("\\.");
			if (strings[1].length()==1) {
				result = type +" "+ result+"0";
			}else{
				result = type +" "+ result;
			}			
		}
		return result;
	}

}
