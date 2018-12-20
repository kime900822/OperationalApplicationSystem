package com.customs.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.customs.dao.CustomsMaterialDAO;
import com.customs.model.CustomsMaterial;
import com.kime.base.DaoBase;

@Repository
public class CustomsMaterialDAOImpl extends DaoBase implements CustomsMaterialDAO{

    @Autowired
    private SessionFactory sessionFactory;  
    @PostConstruct
    public void setSessionFactory() {  
        super.setSessionFactory(sessionFactory);  
    }  
	
	
	@Override
	public void save(CustomsMaterial customsMaterial) {
		this.getHibernateTemplate().save(customsMaterial);		
	}

	@Override
	public void update(CustomsMaterial customsMaterial) {
		this.getHibernateTemplate().update(customsMaterial);
		
	}

	@Override
	public void delete(CustomsMaterial customsMaterial) {
		this.getHibernateTemplate().delete(customsMaterial);
		
	}

	@Override
	public List query(String where) {
		Session session=this.getSessionFactory().openSession();
		String hql="FROM CustomsMaterial "+where;
		List list = session.createQuery(hql).list();
		session.close();
		return list;
	}

	@Override
	public List query(String where, Integer pageSize, Integer pageCurrent) {
		Session session=this.getSessionFactory().openSession();
		String hql="FROM CustomsMaterial "+where;
		List list = session.createQuery(hql).setFirstResult((pageCurrent-1)*pageSize).setMaxResults(pageSize).list();
		session.close();
		return list;
	}

	
}
