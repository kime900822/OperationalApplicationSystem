package com.customs.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.customs.dao.CustomsImportsAndExportsDAO;
import com.customs.model.CustomsImportsAndExports;
import com.kime.base.DaoBase;

@Repository
public class CustomsImportsAndExportsDAOImpl extends DaoBase implements CustomsImportsAndExportsDAO{

    @Autowired
    private SessionFactory sessionFactory;  
    @PostConstruct
    public void setSessionFactory() {  
        super.setSessionFactory(sessionFactory);  
    }  
    
	@Override
	public void save(CustomsImportsAndExports customsImportsAndExports) {
		this.getHibernateTemplate().save(customsImportsAndExports);		
	}

	@Override
	public void update(CustomsImportsAndExports customsImportsAndExports) {
		this.getHibernateTemplate().update(customsImportsAndExports);		
	}

	@Override
	public void delete(CustomsImportsAndExports customsImportsAndExports) {
		this.getHibernateTemplate().delete(customsImportsAndExports);		
	}

	@Override
	public List query(String where) {
		Session session=this.getSessionFactory().openSession();
		String hql="FROM CustomsImportsAndExports "+where;
		List list = session.createQuery(hql).list();
		session.close();
		return list;
	}

	@Override
	public List query(String where, Integer pageSize, Integer pageCurrent) {
		Session session=this.getSessionFactory().openSession();
		String hql="FROM CustomsImportsAndExports "+where;
		List list = session.createQuery(hql).setFirstResult((pageCurrent-1)*pageSize).setMaxResults(pageSize).list();
		session.close();
		return list;
	}

	
	
}
