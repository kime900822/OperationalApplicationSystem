package com.kime.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kime.utils.LogUtil;

@Service
public class BizBase {
	@Autowired
	protected LogUtil logUtil;

	public LogUtil getLogUtil() {
		return logUtil;
	}

	public void setLogUtil(LogUtil logUtil) {
		this.logUtil = logUtil;
	}
	
}
