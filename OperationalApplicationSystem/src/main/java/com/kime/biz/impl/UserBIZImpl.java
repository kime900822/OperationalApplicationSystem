package com.kime.biz.impl;

import java.text.MessageFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.YamlProcessor.ResolutionMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kime.biz.UserBIZ;
import com.kime.dao.UserDAO;
import com.kime.infoenum.Message;
import com.kime.model.Role;
import com.kime.model.User;
import com.kime.utils.PropertiesUtil;
import com.kime.utils.mail.SendMail;

@Service
@Transactional(readOnly=true)
public class UserBIZImpl implements UserBIZ {
	
	@Autowired
	UserDAO userDao;

	public UserDAO getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}

	@Override
	public User login(String uid, String passWord) {
		if (uid.equals(PropertiesUtil.ReadProperties(Message.SYSTEM_PROPERTIES, "id"))&&passWord.equals(PropertiesUtil.ReadProperties(Message.SYSTEM_PROPERTIES, "password"))) {
			User user=new User();
			user.setName("admin");
			user.setPassword(passWord);
			user.setUid("admin");
			Role role=new Role();
			role.setName("admin");
			user.setRole(role);
			return user;
		}else{
			return userDao.login(uid, passWord);
		}
		
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public String register(User user) {
		if (checkUser(user)) {
			userDao.save(user);
			return "1";
		}else{
			return "email repeat!";
		}
		
	}

	@Override
	public List<User> getUser(String where,Integer pageSize,Integer pageCurrent) {
		return userDao.query(where,pageSize , pageCurrent);
	}

	
	@Override
	public List<User> getUser(String where) {
		return userDao.query(where);
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public String modUser(User user) {
		if (checkUser(user)) {
			userDao.update(user);
			return "1";
		}else{
			return "email repeat!";
		}
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void deleteUser(User user) {
		userDao.delete(user);
		
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void inportUser(List<User> lUsers) {
		for (User u : lUsers) {
			if (checkUser(u)) {
				userDao.save(u);
			}		
		}
		
	}

	@Override
	public String forgetPassword(User user) {
		try {
			SendMail.SendMail(user.getEmail(),PropertiesUtil.ReadProperties(Message.MAIL_PROPERTIES, "mailTitleOfForgetPWD"),MessageFormat.format(PropertiesUtil.ReadProperties(Message.MAIL_PROPERTIES, "mailContentOfForgetPWD"),user.getPassword()));
			return "1";
		} catch (Exception e) {
			return e.getMessage();
		}
		
	}
	
	@Override
	public boolean checkUser(User user){
		if (userDao.query(" where  email='"+user.getEmail()+"' and uid<>'"+user.getUid()+"'").size()>0) {
			return false;
		}
		return true;
	}

	
}
