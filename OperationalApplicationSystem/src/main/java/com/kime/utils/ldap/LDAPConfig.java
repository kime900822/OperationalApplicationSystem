package com.kime.utils.ldap;

import com.kime.infoenum.Message;
import com.kime.utils.PropertiesUtil;

public class LDAPConfig {

	public static String IP=PropertiesUtil.ReadProperties(Message.LDAP_CONFIG,"ip");
	public static String LDAP_SECURITY_LEVEL=PropertiesUtil.ReadProperties(Message.LDAP_CONFIG,"LDAPSecurityLevel");
	public static String PORT=PropertiesUtil.ReadProperties(Message.LDAP_CONFIG,"port");
	public static String TIME_OUT=PropertiesUtil.ReadProperties(Message.LDAP_CONFIG,"timeout");
	public static String LDAP_FACTORY=PropertiesUtil.ReadProperties(Message.LDAP_CONFIG,"LDAPFactory");

	

}
