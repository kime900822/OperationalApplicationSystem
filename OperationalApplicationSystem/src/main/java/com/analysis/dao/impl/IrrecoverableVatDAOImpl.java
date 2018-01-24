package com.analysis.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.analysis.dao.IrrecoverableVatDAO;
import com.analysis.model.IrrecoverableVat;

@Repository
public class IrrecoverableVatDAOImpl extends HibernateDaoSupport implements IrrecoverableVatDAO{

	
	@Autowired
    private SessionFactory sessionFactory;  
    @PostConstruct
    public void setSessionFactory() {  
        super.setSessionFactory(sessionFactory);  
    } 
	
	
	@Override
	public List Query(String where) {
		Session session=this.getSessionFactory().openSession();
		String hql="FROM IrrecoverableVat "+where;
		return session.createQuery(hql).list();
	}

	@Override
	public void Save(IrrecoverableVat irrecoverableVat) {
		this.getHibernateTemplate().save(irrecoverableVat);		
	}

	@Override
	public void Delete(IrrecoverableVat irrecoverableVat) {
		this.getHibernateTemplate().delete(irrecoverableVat);
		
	}

	@Override
	public void Delete(String where) {
		Session session=this.getSessionFactory().openSession();
		Transaction tx=session.getTransaction();
		session.beginTransaction(); 
		String sql="delete from t_irrecoverable_vat "+where; 
		session.createSQLQuery(sql).executeUpdate();
		tx.commit();
		
	}

}
