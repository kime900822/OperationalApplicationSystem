package com.analysis.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.analysis.dao.SourceDAO;
import com.analysis.model.Source;

@Repository
public class SourceDAOImpl extends HibernateDaoSupport implements SourceDAO{

	@Autowired
    private SessionFactory sessionFactory;  
    @PostConstruct
    public void setSessionFactory() {  
        super.setSessionFactory(sessionFactory);  
    } 
	
	@Override
	public List Query(String where) {
		Session session=this.getSessionFactory().openSession();
		String hql="FROM Source "+where;
		return session.createQuery(hql).list();
	}

	@Override
	public void Save(Source source) {
		this.getHibernateTemplate().save(source);		
	}

	@Override
	public void Delete(Source source) {
		this.getHibernateTemplate().delete(source);
		
	}

	@Override
	public void Delete(String where) {
		Session session=this.getSessionFactory().openSession();
		Transaction tx=session.getTransaction();
		session.beginTransaction(); 
		String sql="delete from t_source "+where; 
		session.createSQLQuery(sql).executeUpdate();
		tx.commit();
		
	}

}
