package com.sign.other;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kime.infoenum.Message;
import com.kime.utils.LogUtil;
import com.kime.utils.PropertiesUtil;

@Component
public class FileSave {
	
	@Autowired
	LogUtil logUtil;
	
	
	public LogUtil getLogUtil() {
		return logUtil;
	}
	public void setLogUtil(LogUtil logUtil) {
		this.logUtil = logUtil;
	}



	public List<String> fileSave(File[] upfile,String[] filename){
		String filepath=judeDirExists();
		List<String> list=new ArrayList<>();
		for (int i = 0; i < upfile.length; i++) {
			if (filepath!=null) {
				File file=new File(filepath+"/"+filename[i]);
		        if (file.exists()) {
		        	list.add("File Exists");
		        } else {
		        	if (upfile[i].renameTo(file)) {
		        		logUtil.logInfo("上传文件:"+file.getPath());
		        		list.add(getFilePath(file.getName()));
					}
		        	
		        }
			}else{
				list.add("Filepath Error");
			}
		}
		return list;

	
        
		
		
	}
	
	public String fileDelete(String fileName){
		try {
			File file=new File(getFilePathDown(fileName));
			if (file.delete()) {
				return "1";
			}else{
				return "Delete field";
			}
		} catch (Exception e) {
			return e.getMessage();
		}
		
	}
	
	
	public byte[] getFile(String fileName){
		return getBytes(getFilePathDown(fileName));		

	}
	
    private byte[] getBytes(String filePath){  
        byte[] buffer = null;  
        try {  
            File file = new File(filePath);  
            FileInputStream fis = new FileInputStream(file);  
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);  
            byte[] b = new byte[1000];  
            int n;  
            while ((n = fis.read(b)) != -1) {  
                bos.write(b, 0, n);  
            }  
            fis.close();  
            bos.close();  
            buffer = bos.toByteArray();  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return buffer;  
    }
	
    //保存时使用
	public String getFilePath(String filename){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    	Date date=new Date();
		return sdf.format(date)+"/"+filename;		
	}
	
	//下载时使用
	public String getFilePathDown(String filename){
		return PropertiesUtil.ReadProperties(Message.SYSTEM_PROPERTIES, "filepath")+filename;	
	}
    
    
    // 判断文件夹是否存在
    public String judeDirExists() {
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    	Date date=new Date();
    	File dir=new File(PropertiesUtil.ReadProperties(Message.SYSTEM_PROPERTIES, "filepath")+sdf.format(date));
        if (!dir.exists()||!dir.isDirectory()) {
            	if (dir.mkdirs()) {
            		logUtil.logInfo("文件夹不存在，新建文件夹："+dir.getPath());
            		return dir.getPath();
    			}else{
    				return null;
    			}
        		
     
        }else{
        	return dir.getPath();        	
        }
        
    }
}
