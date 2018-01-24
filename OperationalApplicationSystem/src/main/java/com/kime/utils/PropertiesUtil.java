package com.kime.utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class PropertiesUtil {
	
	
	
	public static String ReadProperties(String file,String name){
	      Properties prop = new Properties();
	      String rString=null;
	        try{
	            //读取属性文件a.properties
	            InputStream in = new BufferedInputStream(new FileInputStream(file)) ;
	            prop.load(in);     ///加载属性列表
	            rString=prop.getProperty(name);
	            in.close();	 
	        }
	        catch(Exception e){
	            System.out.println(e);
	        }
	        return rString;
	}
	
	public static void WriteProperties(String file,String name,String value){
		Properties prop = new Properties();
		try {
	        FileOutputStream oFile = new FileOutputStream(file, true);//true表示追加打开
	        prop.setProperty(name, value);
	        prop.store(oFile, "The New properties file");
	        oFile.close();
		} catch (Exception e) {
			// TODO: handle exception
		}

		
	}
}
