package com.kime.action;


import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Results;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kime.biz.MenuBIZ;
import com.kime.biz.RoleBIZ;
import com.kime.infoenum.Message;
import com.kime.model.Menu;
import com.kime.model.Result;
import com.kime.model.Role;
import com.kime.model.User;
import com.kime.utils.PropertiesUtil;
import com.mysql.cj.api.x.io.MessageWriter;
import com.opensymphony.xwork2.ActionSupport;


@Controller
@Scope("prototype")
@ParentPackage("Struts 2")
public class MenuAction extends ActionBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private MenuBIZ menuBIZ;
	@Autowired
	private RoleBIZ roleBIZ;
	@Autowired
	private Menu menu;


	
	private String id;
	private String type;
	


	
	
	public MenuBIZ getMenuBIZ() {
		return menuBIZ;
	}

	public void setMenuBIZ(MenuBIZ menuBIZ) {
		this.menuBIZ = menuBIZ;
	}

	public RoleBIZ getRoleBIZ() {
		return roleBIZ;
	}

	public void setRoleBIZ(RoleBIZ roleBIZ) {
		this.roleBIZ = roleBIZ;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Action(value="getAllMenu",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String getAllMenu() throws UnsupportedEncodingException{		
		List lmenu = menuBIZ.getAllMenu();
		reslutJson=new ByteArrayInputStream(new Gson().toJson(lmenu).getBytes("UTF-8"));  
		logUtil.logInfo("获取菜单");
		return SUCCESS;
	}
	
	@Action(value="getFatherMenu",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String getFatherMenu() throws UnsupportedEncodingException{
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		User user=(User)session.getAttribute("user");
		if (PropertiesUtil.ReadProperties(Message.SYSTEM_PROPERTIES, "id").equals(user.getUid())) {
			List lMenus=menuBIZ.getParentMenu();
			session.setAttribute("parentMenu", lMenus); 
			for (Object object : lMenus) {
				Menu m=(Menu)object;
				String string=menuBIZ.getChildMenu(m.getId());
				session.setAttribute(m.getId(), string); 
			}
			
		}else{
			
			List<Menu> lMenus=menuBIZ.getParentMenuByRole(user.getRole().getName());
			session.setAttribute("parentMenu", lMenus); 
			for (Menu m : lMenus) {
				String string=menuBIZ.getChildMenu_R(m.getId(), user.getRole().getName());
				session.setAttribute(m.getId(), string); 
			}
			
		}

		

		return SUCCESS;
	}
	
	@Action(value="getChildMenu",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String getChildMenu() throws UnsupportedEncodingException{
		
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		reslutJson=new ByteArrayInputStream(session.getAttribute(id).toString().getBytes("UTF-8")); 
		
		return SUCCESS;
	}
	
	@Action(value="deleteMenu",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String deleteMenu() throws UnsupportedEncodingException{
		List lmenu=new Gson().fromJson(json, new TypeToken<ArrayList<Menu>>() {}.getType());
		menu=(Menu) lmenu.get(0);
		try {
			menuBIZ.deleteMenu(menu);
			logUtil.logInfo("删除菜单:"+menu.getName());
			result.setMessage(Message.DEL_MESSAGE_SUCCESS);
			result.setStatusCode("200");
		} catch (Exception e) {
			logUtil.logInfo("删除菜单:"+e.getMessage());
			result.setMessage(e.getMessage());
			result.setStatusCode("300");		
		}
		reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8")); 
		return SUCCESS;
	}
	
	@Action(value="editMenu",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String editMenu() throws UnsupportedEncodingException{
		
		List lmenu=new Gson().fromJson(json, new TypeToken<ArrayList<Menu>>() {}.getType());

			
			try {
				for(Object o:lmenu){
					menu=(Menu)o;
					if (menu.getLevel()==null) {
						menu.setLevel("0");
					}
					if (menu.getOrder()==null) {
						menu.setOrder("0");
					}
					menuBIZ.editMenu(menu);
					logUtil.logInfo("编辑菜单:"+menu.getName());
				}
				
				result.setMessage(Message.SAVE_MESSAGE_SUCCESS);
				result.setStatusCode("200");
			} catch (Exception e) {
				logUtil.logInfo("编辑菜单:"+e.getMessage());
				result.setMessage(e.getMessage());
				result.setStatusCode("300");		
			}
			
		
		reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8"));  
		return SUCCESS;
	}
	
	@Action(value="getRoleMenu",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String GetRoleMenu() throws UnsupportedEncodingException{
		
		List<Menu> lmenu = menuBIZ.getAllMenu();
		List<Role> lrole=roleBIZ.getRole(" WHERE NAME='"+type+"' AND menuid is not null");
		
		for (Menu menu : lmenu) {
			menu.setType(type);
		}
		
		for (Role r : lrole) {
				for (Menu m : lmenu) {
					if (r.getMenuid().equals(m.getId())) {
						m.setUsed(true);
					}
			}
			
		}
		
		reslutJson=new ByteArrayInputStream(new Gson().toJson(lmenu).getBytes("UTF-8"));  
		
		return SUCCESS;
	}
	
	@Action(value="editRoleMenu",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String EditRoleMenu() throws UnsupportedEncodingException{
		List lMenu=new Gson().fromJson(json, new TypeToken<ArrayList<Menu>>() {}.getType());
		Menu menu=(Menu)lMenu.get(0);
		
		if ("".equals(menu.getType())||menu.getType()==null) {
			result.setStatusCode("300");
			result.setMessage(Message.MOD_MESSAGE_ERROR_MENU_1);
		}else{
			
			if (menu.isUsed()) {
				try {
					Role role=(Role)roleBIZ.getRole(" WHERE NAME='"+menu.getType()+"' ").get(0);
					role.setMenuid(menu.getId());
					roleBIZ.save(role);
					result.setStatusCode("200");
					result.setMessage(Message.SAVE_MESSAGE_SUCCESS);
					logUtil.logInfo(role.getName()+" 添加权限:"+menu.getName());
				} catch (Exception e) {
					logUtil.logInfo("编辑权限:"+e.getMessage());
					result.setStatusCode("300");
					result.setMessage(e.getMessage());
				}
			}else{
				try {
					Role role=(Role)roleBIZ.getRole(" WHERE NAME='"+menu.getType()+"' AND menuid='"+menu.getId()+"' ").get(0);
					roleBIZ.delete(role);
					logUtil.logInfo(role.getName()+" 移除权限:"+menu.getName());
					result.setStatusCode("200");
					result.setMessage(Message.SAVE_MESSAGE_SUCCESS);
				} catch (Exception e) {
					logUtil.logInfo("编辑权限:"+e.getMessage());
					result.setStatusCode("300");
					result.setMessage(e.getMessage());
				}
				
			}
			
		}

		reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8")); 	
		return SUCCESS;
	}
	
	
	
	
}
