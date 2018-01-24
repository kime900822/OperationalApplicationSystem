package com.kime.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.kime.dao.CommonDAO;


@Repository
public class CommonDAOImpl extends HibernateDaoSupport implements CommonDAO{

	
    @Autowired
    private SessionFactory sessionFactory;  
    @PostConstruct
    public void setSessionFactory() {  
        super.setSessionFactory(sessionFactory);  
    }  
	@Override
	public List queryByHql(String hql) {
		Session session=this.getSessionFactory().openSession();
		Query query = session.createQuery(hql);     
		List<Object[]> list =query.list(); 
		
		return list;
	}
	
	
	@Override
	public List queryBySql(String sql) {
		Session session=this.getSessionFactory().openSession();
		List<Object[]> list =session.createSQLQuery(sql).list();
		return null;
	}

}
