package com.kime.utils.mail;

import com.kime.infoenum.Message;
import com.kime.utils.PropertiesUtil;

public class SendMail {

	
	public static void SendMail(String toAddress,String subject,String content){
		if (PropertiesUtil.ReadProperties(Message.MAIL_PROPERTIES, "SendMail").equals("false")) {
			return;
		}
		
		MailSenderInfo mailInfo = new MailSenderInfo();      
		mailInfo.setContent(content);
		mailInfo.setToAddress(toAddress);
		mailInfo.setSubject(subject);
		
		SimpleMailSender sms = new SimpleMailSender();     
		//sms.sendTextMail(mailInfo);//发送文体格式      
		sms.sendHtmlMail(mailInfo);//发送html格式 

	}
}
