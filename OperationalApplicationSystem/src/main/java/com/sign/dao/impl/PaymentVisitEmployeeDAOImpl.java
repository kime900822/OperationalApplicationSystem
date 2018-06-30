package com.sign.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.sign.dao.PaymentVisitEmployeeDAO;
import com.sign.model.paymentVisit.PaymentVisitEmployee;

@Repository
public class PaymentVisitEmployeeDAOImpl extends HibernateDaoSupport implements PaymentVisitEmployeeDAO {

    @Autowired
    private SessionFactory sessionFactory;  
    @PostConstruct
    public void setSessionFactory() {  
        super.setSessionFactory(sessionFactory);  
    }  
	
	@Override
	public void save(PaymentVisitEmployee paymentVisitEmployee) {
		this.getHibernateTemplate().save(paymentVisitEmployee);
	}

	@Override
	public void delete(PaymentVisitEmployee paymentVisitEmployee) {
		this.getHibernateTemplate().delete(paymentVisitEmployee);
	}

	@Override
	public void update(PaymentVisitEmployee paymentVisitEmployee) {
		this.getHibernateTemplate().update(paymentVisitEmployee);
	}

	@Override
	public List<PaymentVisitEmployee> query(String where) {
		Session session=this.getSessionFactory().openSession();
		String hql="FROM PaymentVisitEmployee "+where;
		return session.createQuery(hql).list();
	}

	@Override
	public List<PaymentVisitEmployee> query(String where, Integer pageSize, Integer pageCurrent) {
		Session session=this.getSessionFactory().openSession();
		String hql="FROM PaymentVisitEmployee "+where;
		return session.createQuery(hql).setFirstResult((pageCurrent-1)*pageSize).setMaxResults(pageSize).list();
	}

	
}
