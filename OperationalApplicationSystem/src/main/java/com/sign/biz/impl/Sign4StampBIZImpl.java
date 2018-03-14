package com.sign.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kime.base.BizBase;
import com.sign.biz.Sign4StampBIZ;
import com.sign.dao.CheckDAO;
import com.sign.model.Sign4Stamp;

@Service
@Transactional(readOnly=true)
public class Sign4StampBIZImpl extends BizBase implements Sign4StampBIZ {

	@Autowired
	private CheckDAO checkDAO;
	
	public CheckDAO getCheckDAO() {
		return checkDAO;
	}

	public void setCheckDAO(CheckDAO checkDAO) {
		this.checkDAO = checkDAO;
	}

	@Override
	public String save(Sign4Stamp check) {
		try {
			checkDAO.insert(check);
		} catch (Exception e) {
			logUtil.logError("新增文件类型："+e.getMessage());
			return e.getMessage();
		}
		logUtil.logInfo("新增文件类型：成功。"+check.getDocumentType());
		return null;
	}

	@Override
	public String delete(Sign4Stamp check) {
		try {
			checkDAO.delete(check);
		} catch (Exception e) {
			logUtil.logError("删除文件类型："+e.getMessage());
			return e.getMessage();
		}
		logUtil.logInfo("删除文件类型：成功。"+check.getDocumentType());
		return null;
	}

	@Override
	public String update(Sign4Stamp check) {
		try {
			checkDAO.update(check);
		} catch (Exception e) {
			logUtil.logError("更新文件类型："+e.getMessage());
			return e.getMessage();
		}
		logUtil.logInfo("更新文件类型：成功。"+check.getDocumentType());
		return null;
	}

	@Override
	public List<?> getDocumentType() {
		return checkDAO.query("");
	}

	@Override
	public List<?> query(String where) {
		return checkDAO.query(where);
	}

	@Override
	public List<?> query(String where, Integer pageSize, Integer pageCurrent) {
		return checkDAO.query(where, pageSize, pageCurrent);
	}

}
