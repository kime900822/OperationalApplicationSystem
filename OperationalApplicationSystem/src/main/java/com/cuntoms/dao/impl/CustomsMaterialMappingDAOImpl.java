package com.cuntoms.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cuntoms.dao.CustomsMaterialMappingDAO;
import com.cuntoms.model.CustomsMaterialMapping;
import com.kime.base.DaoBase;

@Repository
public class CustomsMaterialMappingDAOImpl  extends DaoBase implements CustomsMaterialMappingDAO{
	
	@Autowired
    private SessionFactory sessionFactory;  
    @PostConstruct
    public void setSessionFactory() {  
        super.setSessionFactory(sessionFactory);  
    }  
	
	@Override
	public void save(CustomsMaterialMapping cMapping) {
		this.getHibernateTemplate().save(cMapping);		
	}

	@Override
	public void update(CustomsMaterialMapping cMapping) {
		this.getHibernateTemplate().update(cMapping);				
	}

	@Override
	public void delete(CustomsMaterialMapping cMapping) {
		this.getHibernateTemplate().delete(cMapping);				
	}

	@Override
	public List query(String where) {
		Session session=this.getSessionFactory().openSession();
		String hql="FROM CustomsMaterialMapping "+where;
		@SuppressWarnings("rawtypes")
		List list = session.createQuery(hql).list();
		session.close();
		return list;
	}

	@Override
	public List query(String where, Integer pageSize, Integer pageCurrent) {
		Session session=this.getSessionFactory().openSession();
		String hql="FROM CustomsMaterialMapping "+where;
		List list = session.createQuery(hql).setFirstResult((pageCurrent-1)*pageSize).setMaxResults(pageSize).list();
		session.close();
		return list;
	}

}
