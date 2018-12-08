package com.cuntoms.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cuntoms.dao.CustomsImportsAndExportsDAO;
import com.cuntoms.model.CustomsImportsAndExports;
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
		return session.createQuery(hql).list();
	}

	@Override
	public List query(String where, Integer pageSize, Integer pageCurrent) {
		Session session=this.getSessionFactory().openSession();
		String hql="FROM CustomsImportsAndExports "+where;
		return session.createQuery(hql).setFirstResult((pageCurrent-1)*pageSize).setMaxResults(pageSize).list();
	}

	
	
}
