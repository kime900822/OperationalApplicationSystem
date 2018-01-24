package com.kime.biz;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kime.dao.UserDAO;
import com.kime.model.User;

public interface UserBIZ {
	
	/**
	 * 登录
	 * @param jobnumber
	 * @param passWord
	 * @return
	 */
	public User login(String uid,String passWord);
	
	/**
	 * 注册
	 * @param user
	 * @return
	 */
	public String register(User user);
	
	/**
	 * 查询
	 * @param where
	 * @return
	 */
	public List<User> getUser(String where,Integer pageSize,Integer pageCurrent);
	
	public List<User> getUser(String where);
	
	/**
	 * 修改
	 * @param user
	 * @return
	 */
	public String modUser(User user);
	
	public void deleteUser(User user);
	
	public void inportUser(List<User> lUsers);
	
	public String forgetPassword(User user);

	public boolean checkUser(User user);
}
