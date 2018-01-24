package com.kime.biz;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kime.model.Menu;
import com.kime.model.Role;

public interface MenuBIZ {
	
	
	public void editMenu(Menu menu);
	
	public List getAllMenu();
	
	public List getParentMenu();
	
	public String getChildMenu(String parentID);
	
	public void deleteMenu(Menu menu);
	
	public Menu getMenuById(String id);
	
	public List getParentMenuByRole(String role);
	
	public String getChildMenu_R(String parentID,String roleid);
}
