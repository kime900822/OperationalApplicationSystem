package com.kime.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
		return list;
	}
	
	
	@Override
	public void executeSQL(String sql) {
		Session session=this.getSessionFactory().openSession();
		Transaction trance = session.beginTransaction();
		session.createSQLQuery(sql).executeUpdate();
		trance.commit();
	}
	@Override
	public void executeHQL(String hql) {
		Session session=this.getSessionFactory().openSession();
		Transaction trance = session.beginTransaction();
		session.createQuery(hql).executeUpdate();	
		trance.commit();
	}
	@Override
	public List queryByHql(String hql, Integer pageSize, Integer pageCurrent) {
		Session session=this.getSessionFactory().openSession();
		return session.createQuery(hql).setFirstResult((pageCurrent-1)*pageSize).setMaxResults(pageSize).list();
	}

	@Override
	public List queryBySql(String sql, Class class1) {
		Session session=this.getSessionFactory().openSession();
		List list =session.createSQLQuery(sql).addEntity(class1).list();
		return list;
	}

	@Override
	public List queryBySql(String sql, Integer pageSize, Integer pageCurrent, Class class1) {
		Session session=this.getSessionFactory().openSession();
		List list =session.createSQLQuery(sql).addEntity(class1).setFirstResult((pageCurrent-1)*pageSize).setMaxResults(pageSize).list();
		return list;
	}

	
	
}
