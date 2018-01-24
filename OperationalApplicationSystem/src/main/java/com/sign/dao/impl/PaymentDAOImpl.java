package com.sign.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.sign.dao.PaymentDAO;
import com.sign.model.Payment;

@Repository
public class PaymentDAOImpl extends HibernateDaoSupport implements PaymentDAO {

    @Autowired
    private SessionFactory sessionFactory;  
    @PostConstruct
    public void setSessionFactory() {  
        super.setSessionFactory(sessionFactory);  
    }  
	
	@Override
	public void save(Payment payment) {
		this.getHibernateTemplate().save(payment);	
	}

	@Override
	public void delete(Payment payment) {
		this.getHibernateTemplate().delete(payment);
	}

	@Override
	public void update(Payment payment) {
		this.getHibernateTemplate().update(payment);
	}

	@Override
	public List<Payment> query(String where) {
		Session session=this.getSessionFactory().openSession();
		String hql="FROM Payment "+where;
		return session.createQuery(hql).list();
	}

	@Override
	public List<Payment> query(String where, Integer pageSize, Integer pageCurrent) {
		Session session=this.getSessionFactory().openSession();
		String hql="FROM Payment "+where;
		return session.createQuery(hql).setFirstResult((pageCurrent-1)*pageSize).setMaxResults(pageSize).list();

	}

	@Override
	public List<Payment> queryHql(String hql) {
		Session session=this.getSessionFactory().openSession();
		return session.createQuery(hql).list();
	}

	@Override
	public List<Payment> queryHql(String hql, Integer pageSize, Integer pageCurrent) {
		Session session=this.getSessionFactory().openSession();
		return session.createQuery(hql).setFirstResult((pageCurrent-1)*pageSize).setMaxResults(pageSize).list();
	}
	
}
