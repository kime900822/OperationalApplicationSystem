package com.kime.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kime.model.Menu;



public interface MenuDAO {

	public void save(Menu menu);
	
	public List getAllMenu();
	
	public Menu getMenuByID(String id);
	
	public List getMenuByParentID(String parentID);
	
	public void update(Menu menu);
	
	public void delete(Menu menu);
	
	public List getParentMenu();
	
	public List getMenu(String level,String order);
	
	public List getFatherMenuByRole(String role);
	
	public List getMenuByParentIDRole(String parentID,String role);
}
