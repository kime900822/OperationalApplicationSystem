/**
 * 提醒
 */
package com.kime.infoenum;

import antlr.StringUtils;

public class Message {
	
	/**
	 * 上传成功
	 */
	public static String UPLOAD_MESSAGE_SUCCESS="Upload successfully!";
	
	/**
	 * 上传失败
	 */
	public static String UPLOAD_MESSAGE_ERROE="Upload field!";
	
	public static String UPLOAD_MESSAGE_ERROR_NODATA="The Excel no data!";
	
	/**
	 * 修改成功
	 */
	public static String MOD_MESSAGE_SUCCESS="Modify successfully";
	
	/**
	 * 修改失败
	 */
	public static String MOD_MESSAGE_ERROE="Modify field";
	
	/**
	 * 新密码不能与旧密码一致
	 */
	public static String MOD_MESSAGE_ERROR_REGISTER_1="The new password cannot be the same as the old password";
	
	/**
	 * 旧密码错误
	 */
	public static String MOD_MESSAGE_ERROR_REGISTER_2="Old password error";
	
	/**
	 * 请选择类别后再重试
	 */
	public static String MOD_MESSAGE_ERROR_MENU_1="Please select the category and try again";
	
	/**
	 * 保存成功
	 */
	public static String SAVE_MESSAGE_SUCCESS="Save successfully!";
	
	/**
	 * 保存失败
	 */
	public static String SAVE_MESSAGE_ERROR="Save field!";
	
	/**
	 * 用户类别名称不能重复
	 */
	public static String SAVE_MESSAGE_ERROR_ROLE_1="Name cannot be duplicated！";
	
	/**
	 * 字典中同样TYPE同业KEY只能存在一条记录
	 */
	public static String SAVE_MESSAGE_ERROR_DICT="The type of consent can only have the same KEY!";
	
	/**
	 * 维护财务人员时对应ID不存在
	 */
	public static String SAVE_MESSAGE_ERROR_DICT_PAYMENT="The corresponding ID does not exist!";
	
	/**
	 * 保存付款单，没有维护对应财务人员
	 */
	public static String SAVE_MESSAGE_PAYMENT_ERROR="Not maintaining a corresponding financial officer!";
	
	/**
	 * 删除成功
	 */
	public static String DEL_MESSAGE_SUCCESS="Delete successfully!";
	
	/**
	 * 删除失败
	 */
	public static String DEL_MESSAGE_ERROR="Delete field!";
	
	/**
	 * 当前用户类别有对应用户，不可删除
	 */
	public static String DEL_MESSAGE_ERROR_ROLE_1="Users cannot delete if there is a class of users!";
	
	/**
	 * 注册成功
	 */
	public static String REGISTER_MESSAGE_SUCCESS="Register successfully!";
	
	/**
	 * 登录成功
	 */
	public static String LOGIN_MESSAGE_SUCCESS="Login successfully";
	
	/**
	 * 未登录
	 */
	public static String LOGIN_MESSAGE_UNLOGIN="Please login first!";
	
	/**
	 * 默认用户分类
	 */
	public static String NORMAL_USER="普通用户";
	
	
	public static String SYSTEM_PROPERTIES=StringUtils.class.getClassLoader().getResource("/").getPath()+"/conf/system.properties";
	public static String MAIL_PROPERTIES=StringUtils.class.getClassLoader().getResource("/").getPath()+"/conf/mail.properties";
}
