package com.cuntoms.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cuntoms.dao.CustomsJDEDAO;
import com.cuntoms.model.CustomsJDE;
import com.kime.base.DaoBase;

@Repository
public class CustomsJDEDAOImpl  extends DaoBase implements CustomsJDEDAO{

	
	@Autowired
    private SessionFactory sessionFactory;  
    @PostConstruct
    public void setSessionFactory() {  
        super.setSessionFactory(sessionFactory);  
    }  
    
    
	@Override
	public void save(CustomsJDE customsJDE) {
		this.getHibernateTemplate().save(customsJDE);
	}

	@Override
	public void update(CustomsJDE customsJDE) {
		this.getHibernateTemplate().update(customsJDE);		
	}

	@Override
	public void delete(CustomsJDE customsJDE) {
		this.getHibernateTemplate().delete(customsJDE);		
	}

	@Override
	public List query(String where) {
		Session session=this.getSessionFactory().openSession();
		String hql="FROM CustomsJDE "+where;
		List list = session.createQuery(hql).list();
		session.close();
		return list;
	}

	@Override
	public List query(String where, Integer pageSize, Integer pageCurrent) {
		Session session=this.getSessionFactory().openSession();
		String hql="FROM CustomsJDE "+where;
		List list = session.createQuery(hql).setFirstResult((pageCurrent-1)*pageSize).setMaxResults(pageSize).list();
		session.close();
		return list;
	}
	
	

}
