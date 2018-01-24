package com.analysis.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.analysis.dao.SalesDAO;
import com.analysis.model.Sales;

@Repository
public class SalesDAOImpl extends HibernateDaoSupport implements SalesDAO {

	@Autowired
    private SessionFactory sessionFactory;  
    @PostConstruct
    public void setSessionFactory() {  
        super.setSessionFactory(sessionFactory);  
    } 
	
	@Override
	public List Query(String where) {
		Session session=this.getSessionFactory().openSession();
		String hql="FROM Sales "+where;
		return session.createQuery(hql).list();
	}

	@Override
	public void Save(Sales sales) {
		this.getHibernateTemplate().save(sales);
	}

	@Override
	public void Delete(Sales sales) {
		this.getHibernateTemplate().delete(sales);
	}

	@Override
	public void Delete(String where) {
		Session session=this.getSessionFactory().openSession();
		Transaction tx=session.getTransaction();
		session.beginTransaction(); 
		String sql="delete from t_sales "+where; 
		session.createSQLQuery(sql).executeUpdate();
		 tx.commit();

		
		
	}

}
