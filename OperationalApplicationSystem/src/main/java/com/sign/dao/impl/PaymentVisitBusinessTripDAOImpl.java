package com.sign.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.sign.dao.PaymentVisitBusinessTripDAO;
import com.sign.model.paymentVisit.PaymentVisitBusinessTrip;

@Repository
public class PaymentVisitBusinessTripDAOImpl  extends HibernateDaoSupport implements PaymentVisitBusinessTripDAO{

	@Autowired
    private SessionFactory sessionFactory;  
    @PostConstruct
    public void setSessionFactory() {  
        super.setSessionFactory(sessionFactory);  
    }  
	
	
	@Override
	public void save(PaymentVisitBusinessTrip paymentVisitBusinessTrip) {
		this.getHibernateTemplate().save(paymentVisitBusinessTrip);
	}

	@Override
	public void delete(PaymentVisitBusinessTrip paymentVisitBusinessTrip) {
		this.getHibernateTemplate().delete(paymentVisitBusinessTrip);
	}

	@Override
	public void update(PaymentVisitBusinessTrip paymentVisitBusinessTrip) {
		this.getHibernateTemplate().update(paymentVisitBusinessTrip);
	}

	@Override
	public List<PaymentVisitBusinessTrip> query(String where) {
		Session session=this.getSessionFactory().openSession();
		String hql="FROM PaymentVisitBusinessTrip "+where;
		return session.createQuery(hql).list();
	}

	@Override
	public List<PaymentVisitBusinessTrip> query(String where, Integer pageSize, Integer pageCurrent) {
		Session session=this.getSessionFactory().openSession();
		String hql="FROM PaymentVisitBusinessTrip "+where;
		return session.createQuery(hql).setFirstResult((pageCurrent-1)*pageSize).setMaxResults(pageSize).list();
	}

	
	
	
}
