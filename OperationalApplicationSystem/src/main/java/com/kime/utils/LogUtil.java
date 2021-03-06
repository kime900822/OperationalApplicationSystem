package com.kime.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.kime.model.User;

@Component
public class LogUtil  {
	
      @Autowired  
      private  HttpSession session;    
      @Autowired  
      private  HttpServletRequest request;  
	
	
	  private static Logger log = Logger.getLogger(LogUtil.class);
	  
	  public void logDebug(String message){
		  User user;
		  String ip;
		if (session==null) {
			user=null;
			ip=null;
		}else {
			user = (User) session.getAttribute("user");
			ip = request.getRemoteAddr();
		}
		  if (user!=null) {
			  log.debug(" IP:"+ip+" 工号:"+user.getUid()+" 操作:" +message);
		  }else{
			  log.error(" IP:"+ip+"  操作: "+message);
		  }
		  
	  }
	  
	  public  void logInfo(String message){
		  User user;
		  String ip;
		if (session==null) {
			user=null;
			ip=null;
		}else {
			user = (User) session.getAttribute("user");
			ip = request.getRemoteAddr();
		}
		  if (user!=null) {
			  log.info(" IP:"+ip+" 工号:"+user.getUid()+" 操作:" +message);
		  }else{
			  log.error(" IP:"+ip+"  操作: "+message);
		  }
	  }
	  
	  public  void logError(String message){
		  User user;
		  String ip;
		if (session==null) {
			user=null;
			ip=null;
		}else {
			user = (User) session.getAttribute("user");
			ip = request.getRemoteAddr();
		}
		  if (user!=null) {
			  log.error(" IP:"+ip+" 工号:"+user.getUid()+" 操作:" +message);
		  }else{
			  log.error(" IP:"+ip+"  操作: "+message);
		  }
	  }
	  
	  public void logDebug(String title,String message){
		  User user;
		  String ip;
		if (session==null) {
			user=null;
			ip=null;
		}else {
			user = (User) session.getAttribute("user");
			ip = request.getRemoteAddr();
		}
		  if (user!=null) {
			  log.debug(" IP:"+ip+" 工号:"+user.getUid()+" 操作:" +title+":"+message);
		  }else{
			  log.error(" IP:"+ip+"  操作:" +title+":"+message);
		  }
		  
	  }
	  
	  public  void logInfo(String title,String message){
		  User user;
		  String ip;
		if (session==null) {
			user=null;
			ip=null;
		}else {
			user = (User) session.getAttribute("user");
			ip = request.getRemoteAddr();
		}
		  if (user!=null) {
			  log.info(" IP:"+ip+" 工号:"+user.getUid()+" 操作:" +title+":"+message);
		  }else{
			  log.error(" IP:"+ip+"  操作:" +title+":"+message);
		  }
	  }
	  
	  public  void logError(String title,String message){
		  User user;
		  String ip;
		if (session==null) {
			user=null;
			ip=null;
		}else {
			user = (User) session.getAttribute("user");
			ip = request.getRemoteAddr();
		}
		
		  if (user!=null) {
			  log.error(" IP:"+ip+" 工号:"+user.getUid()+" 操作:" +title+":"+message);
		  }else{
			  log.error(" IP:"+ip+"  操作:" +title+":"+message);
		  }
	  }
}
