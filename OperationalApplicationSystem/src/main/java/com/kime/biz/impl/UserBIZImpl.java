package com.kime.biz.impl;

import java.text.MessageFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.YamlProcessor.ResolutionMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kime.base.BizBase;
import com.kime.biz.UserBIZ;
import com.kime.dao.UserDAO;
import com.kime.infoenum.Config;
import com.kime.infoenum.Message;
import com.kime.model.Role;
import com.kime.model.User;
import com.kime.utils.PropertiesUtil;
import com.kime.utils.ldap.LDAPLogin;
import com.kime.utils.mail.SendMail;

@Service
@Transactional(readOnly=true)
public class UserBIZImpl extends BizBase implements UserBIZ {
	
	@Autowired
	UserDAO userDao;

	public UserDAO getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}

	@Override
	public User login(String uid, String passWord,String domain) throws Exception {
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
			if (Config.ADLOGIN) {
				String result=LDAPLogin.connect(uid+domain, passWord);
				if (result!=null) {
					throw new Exception(result);
				}else {
					List<User> list= userDao.query(" where adName='"+uid+domain+"'");
					if (list.size()==0) {
						throw new Exception(uid+"没有维护到系统对应用户");
					}else{
						return list.get(0);
					}
				}
			}else {
				return userDao.login(uid, passWord);
			}
		}
		
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public String register(User user) {
		String r=checkUser(user);
		if (r==null) {
			userDao.save(user);
			return "1";
		}else{
			return r;
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
		String r=checkUser(user);
		if (r==null) {
			userDao.update(user);
			return "1";
		}else{
			return r;
		}
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void deleteUser(User user) {
		userDao.delete(user);
		
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void importUser(List<User> lUsers) throws Exception {
		try {
			for (User u : lUsers) {
				String r=checkUser(u);
				if (r==null) {
					userDao.save(u);
				}else {
					throw new Exception(r);
				}		
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
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
	public String checkUser(User user){
		if (userDao.query(" where  email='"+user.getEmail()+"' and uid<>'"+user.getUid()+"'").size()>0) {
			return "Email repeat";
		}
		if (userDao.query(" where  adName='"+user.getAdName()+"' and uid<>'"+user.getUid()+"'").size()>0) {
			return "ADName repeat";
		}
		return null;
	}

	
}
