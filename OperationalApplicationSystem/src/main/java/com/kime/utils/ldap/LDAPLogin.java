package com.kime.utils.ldap;

import java.util.Hashtable;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

import com.kime.utils.LogUtil;


public class LDAPLogin {

	 public static String  TITLE="ADLogin";
	
	  /**
     * 使用java连接AD域
     * @author Herman.Xiong
     * @date 2014-12-23 下午02:24:04
     * @return void  
     * @throws 异常说明
     * @param username 用户名
     * @param password 密码
     */
    public static String connect(String username,String password) {
    	LogUtil logUtil=new LogUtil();
    	String result=null;
        DirContext ctx=null;
        String ldapURL="ldap://"+LDAPConfig.IP+":"+LDAPConfig.PORT;
        Hashtable<String,String> HashEnv = new Hashtable<String,String>();
        HashEnv.put(Context.SECURITY_AUTHENTICATION, LDAPConfig.LDAP_SECURITY_LEVEL); // LDAP访问安全级别(none,simple,strong)
        HashEnv.put(Context.SECURITY_PRINCIPAL, username); //AD的用户名
        HashEnv.put(Context.SECURITY_CREDENTIALS, password); //AD的密码
        HashEnv.put(Context.INITIAL_CONTEXT_FACTORY,LDAPConfig.LDAP_FACTORY); // LDAP工厂类
        HashEnv.put("com.sun.jndi.ldap.connect.timeout", LDAPConfig.TIME_OUT);//连接超时设置为3秒
        HashEnv.put(Context.PROVIDER_URL,ldapURL);// 默认端口389 
        try {
            ctx = new InitialDirContext(HashEnv);// 初始化上下文
            logUtil.logInfo(TITLE,username+"  登录成功");
            result= null;
        } catch (AuthenticationException e) {
        	logUtil.logError(TITLE,username+"  登录失败："+e.getMessage());
        	e.printStackTrace();
        	result= username+"  登录失败："+e.getMessage();
        } catch (javax.naming.CommunicationException e) {
        	logUtil.logError(TITLE,username+"  AD域连接失败："+e.getMessage());
            e.printStackTrace();
            result= username+"  AD域连接失败："+e.getMessage();
        } catch (Exception e) {
            logUtil.logError(TITLE,username+"  身份验证未知异常："+e.getMessage());
            e.printStackTrace();
            result= username+"  身份验证未知异常："+e.getMessage();
        } finally{
            if(null!=ctx){
                try {
                    ctx.close();
                    ctx=null;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
	
	
}
