package com.sign.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kime.base.BizBase;
import com.sign.biz.CheckBIZ;
import com.sign.dao.CheckDAO;
import com.sign.model.Check;

@Service
@Transactional(readOnly=true)
public class CheckBIZImpl extends BizBase implements CheckBIZ {

	@Autowired
	private CheckDAO checkDAO;
	
	public CheckDAO getCheckDAO() {
		return checkDAO;
	}

	public void setCheckDAO(CheckDAO checkDAO) {
		this.checkDAO = checkDAO;
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public String save(Check check) {
		try {
			checkDAO.insert(check);
		} catch (Exception e) {
			logUtil.logError("新增签核流程："+e.getMessage());
			return e.getMessage();
		}
		logUtil.logInfo("新增签核流程：成功。"+check.getType());
		return null;
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public String delete(List check) {
		StringBuilder stringBuilder=new StringBuilder();
		try {
			for (Object object : check) {
				Check tmp=(Check)object;
				checkDAO.delete(tmp);
				stringBuilder.append(tmp.getType());
			}			
		} catch (Exception e) {
			logUtil.logError("删除签核流程："+e.getMessage());
			return e.getMessage();
		}
		logUtil.logInfo("删除签核流程：成功。"+stringBuilder.toString());
		return null;
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public String update(Check check) {
		try {
			checkDAO.update(check);
		} catch (Exception e) {
			logUtil.logError("更新签核流程："+e.getMessage());
			return e.getMessage();
		}
		logUtil.logInfo("更新签核流程：成功。"+check.getType());
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
