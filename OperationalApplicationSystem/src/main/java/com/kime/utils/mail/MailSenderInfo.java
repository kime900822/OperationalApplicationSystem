package com.kime.utils.mail;

import java.util.Properties;

import com.kime.infoenum.Message;
import com.kime.utils.PropertiesUtil;

public class MailSenderInfo {
	private String isSSL=PropertiesUtil.ReadProperties(Message.MAIL_PROPERTIES, "isSSl");    
    // 发送邮件的服务器的IP和端口    
    private String mailServerHost=PropertiesUtil.ReadProperties(Message.MAIL_PROPERTIES, "MailServerHost");    
    private String mailServerPort =PropertiesUtil.ReadProperties(Message.MAIL_PROPERTIES, "MailServerPort");    
    // 邮件发送者的地址    
    private String fromAddress=PropertiesUtil.ReadProperties(Message.MAIL_PROPERTIES, "FromAddress");   
    // 邮件接收者的地址    
    private String toAddress;    
    // 登陆邮件发送服务器的用户名和密码    
    private String userName=PropertiesUtil.ReadProperties(Message.MAIL_PROPERTIES, "UserName");     
    private String password=PropertiesUtil.ReadProperties(Message.MAIL_PROPERTIES, "Password");    
    // 是否需要身份验证    
    private boolean validate =Boolean.valueOf(PropertiesUtil.ReadProperties(Message.MAIL_PROPERTIES, "Validate"));    
    // 邮件主题    
    private String subject;    
    // 邮件的文本内容    
    private String content;    
    // 邮件附件的文件名    
    private String[] attachFileNames;      
    
    private String SSLSocketFactory=PropertiesUtil.ReadProperties(Message.MAIL_PROPERTIES, "SSLSocketFactory");    
    
    private String fallback=PropertiesUtil.ReadProperties(Message.MAIL_PROPERTIES, "fallback");  
    
    private String socketFactoryport=PropertiesUtil.ReadProperties(Message.MAIL_PROPERTIES, "socketFactoryport"); 
    
    private String protocol=PropertiesUtil.ReadProperties(Message.MAIL_PROPERTIES, "protocol"); 
    
    /**   
      * 获得邮件会话属性   
      */    
    public Properties getProperties(){    
      Properties p = new Properties();    
      p.put("mail.smtp.host", this.mailServerHost);    
      p.put("mail.smtp.port", this.mailServerPort);    
      p.put("mail.smtp.auth", validate ? "true" : "false");    
      p.put("mail.transport.protocol", this.protocol);
      //ssl连接发送邮件
      if(isSSL.equals("Y")){
          p.put("mail.smtp.socketFactory.class", this.SSLSocketFactory);
          p.put("mail.smtp.socketFactory.fallback", this.fallback);
          p.put("mail.smtp.socketFactory.port", this.socketFactoryport);
      }

      return p;    
    }    
    public String getMailServerHost() {    
      return mailServerHost;    
    }    
    public void setMailServerHost(String mailServerHost) {    
      this.mailServerHost = mailServerHost;    
    }   
    public String getMailServerPort() {    
      return mailServerPort;    
    }   
    public void setMailServerPort(String mailServerPort) {    
      this.mailServerPort = mailServerPort;    
    }   
    public boolean isValidate() {    
      return validate;    
    }   
    public void setValidate(boolean validate) {    
      this.validate = validate;    
    }   
    public String[] getAttachFileNames() {    
      return attachFileNames;    
    }   
    public void setAttachFileNames(String[] fileNames) {    
      this.attachFileNames = fileNames;    
    }   
    public String getFromAddress() {    
      return fromAddress;    
    }    
    public void setFromAddress(String fromAddress) {    
      this.fromAddress = fromAddress;    
    }   
    public String getPassword() {    
      return password;    
    }   
    public void setPassword(String password) {    
      this.password = password;    
    }   
    public String getToAddress() {    
      return toAddress;    
    }    
    public void setToAddress(String toAddress) {    
      this.toAddress = toAddress;    
    }    
    public String getUserName() {    
      return userName;    
    }   
    public void setUserName(String userName) {    
      this.userName = userName;    
    }   
    public String getSubject() {    
      return subject;    
    }   
    public void setSubject(String subject) {    
      this.subject = subject;    
    }   
    public String getContent() {    
      return content;    
    }   
    public void setContent(String textContent) {    
      this.content = textContent;    
    }  
}
