package com.kime.infoenum;

import com.kime.utils.PropertiesUtil;

public class Config {
	
	/**
	 * 是否AD域登录
	 */
	public static Boolean ADLOGIN=Boolean.valueOf(PropertiesUtil.ReadProperties(Message.SYSTEM_PROPERTIES, "ADLogin"));
}
