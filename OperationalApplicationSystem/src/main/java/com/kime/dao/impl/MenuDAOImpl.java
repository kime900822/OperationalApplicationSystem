package com.kime.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kime.dao.MenuDAO;
import com.kime.model.Menu;

@Repository
public class MenuDAOImpl extends HibernateDaoSupport implements MenuDAO {

    @Autowired
    private SessionFactory sessionFactory;  
    @PostConstruct
    public void setSessionFactory() {  
        super.setSessionFactory(sessionFactory);  
    }  
	
	@Override
	public void save(Menu menu) {
		this.getHibernateTemplate().save(menu);		
	}


	@Override
	public List getAllMenu() {
		return this.getHibernateTemplate().find("FROM Menu ");
	}

	@Override
	public Menu getMenuByID(String id) {
		List menu=this.getHibernateTemplate().find("FROM Menu where id=? ", new String[]{id});
		if (menu.size()>0) {
			return (Menu)menu.get(0);
		}
		else{
			return null;
		}
	}

	@Override
	public List getMenuByParentID(String parentID) {
		return this.getHibernateTemplate().find("FROM Menu where parentid=? ", new String[]{parentID});
	}

	
	
	
	@Override
	public List getMenuByParentIDRole(String parentID, String role) {
		return this.getHibernateTemplate().find("SELECT M FROM Menu M, Role R where M.parentid=? AND M.id=R.menuid AND R.name=?  ORDER BY M.order", new String[]{parentID,role});
	}

	@Override
	public void update(Menu menu) {
		this.getHibernateTemplate().merge(menu);
		
	}


	@Override
	public void delete(Menu menu) {
		this.getHibernateTemplate().delete(menu);
		
	}


	@Override
	public List getParentMenu() {
		return this.getHibernateTemplate().find("FROM Menu where level=? ORDER BY order", new String[]{"1"});
	}


	@Override
	public List getMenu(String level,String order) {
		return this.getHibernateTemplate().find("FROM Menu  where level=? AND order=? ", new String[]{level,order});
	}

	@Override
	public List getFatherMenuByRole(String role) {
		return this.getHibernateTemplate().find("select M FROM Menu M, Role R  where M.level=1 AND M.id=R.menuid AND R.name=? ", new String[]{role});
	}

	
	
}
