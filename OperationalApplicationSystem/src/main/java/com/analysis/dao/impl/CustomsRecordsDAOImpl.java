package com.analysis.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.analysis.dao.CustomsRecordsDAO;
import com.analysis.model.CustomsRecords;

@Repository
public class CustomsRecordsDAOImpl  extends HibernateDaoSupport implements CustomsRecordsDAO{

	
	@Autowired
    private SessionFactory sessionFactory;  
    @PostConstruct
    public void setSessionFactory() {  
        super.setSessionFactory(sessionFactory);  
    } 
    
	
	@Override
	public List Query(String where) {
		Session session=this.getSessionFactory().openSession();
		String hql="FROM CustomsRecords "+where;
		return session.createQuery(hql).list();
	}

	@Override
	public void Save(CustomsRecords customsRecords) {
		this.getHibernateTemplate().save(customsRecords);
	}

	@Override
	public void Delete(CustomsRecords customsRecords) {
		this.getHibernateTemplate().delete(customsRecords);
	}

	@Override
	public void Delete(String where) {
		Session session=this.getSessionFactory().openSession();
		String sql="delete from t_instruction "+where; 
		session.createSQLQuery(sql).executeUpdate();
	}

	
}
