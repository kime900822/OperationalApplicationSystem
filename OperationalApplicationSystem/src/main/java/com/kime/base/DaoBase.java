package com.kime.base;

import java.util.List;

import javax.annotation.PostConstruct;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

public class DaoBase extends HibernateDaoSupport {

    @Autowired
    protected SessionFactory sessionFactory;  
    @PostConstruct
    public void setSessionFactory() {  
        super.setSessionFactory(sessionFactory);  
    }  
    
    protected HibernateTemplate hibernateTemplate;
	
	public List queryByHql(String hql){		
		Session session=this.getSessionFactory().openSession();
		return session.createQuery(hql).list();
	}
	
	
	public List queryBySql(String sql){		
		Session session=this.getSessionFactory().openSession();
		return session.createSQLQuery(sql).list();
	}
	
	
	public void save(Object object){
		this.getHibernateTemplate().save(object);
	}
	
	public void delete(Object object){
		this.getHibernateTemplate().delete(object);
	}
	
	public void update(Object object){
		this.getHibernateTemplate().update(object);
	}
}
