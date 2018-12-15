package com.sign.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.sign.dao.PaymentVisitDAO;
import com.sign.model.paymentVisit.PaymentVisit;

@Repository
public class PyamentVisitDAOImpl  extends HibernateDaoSupport implements PaymentVisitDAO {

	
    @Autowired
    private SessionFactory sessionFactory;  
    @PostConstruct
    public void setSessionFactory() {  
        super.setSessionFactory(sessionFactory);  
    }  
    
	
	@Override
	public void save(PaymentVisit paymentVisit) {
		this.getHibernateTemplate().save(paymentVisit);
	}

	@Override
	public void delete(PaymentVisit paymentVisit) {
		this.getHibernateTemplate().delete(paymentVisit);
	}

	@Override
	public void update(PaymentVisit paymentVisit) {
		this.getHibernateTemplate().update(paymentVisit);
	}


	@Override
	public List<PaymentVisit> query(String where) {
		Session session=this.getSessionFactory().openSession();
		String hql="FROM PaymentVisit "+where;
		List list = session.createQuery(hql).list();
		session.close();
		return list;
	}


	@Override
	public List<PaymentVisit> query(String where, Integer pageSize, Integer pageCurrent) {
		Session session=this.getSessionFactory().openSession();
		String hql="FROM PaymentVisit "+where;
		List list = session.createQuery(hql).setFirstResult((pageCurrent-1)*pageSize).setMaxResults(pageSize).list();
		session.close();
		return list;
	}

	
}
