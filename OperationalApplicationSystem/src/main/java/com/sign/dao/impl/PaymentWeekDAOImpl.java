package com.sign.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.sign.dao.PaymentWeekDAO;
import com.sign.model.PaymentWeek;

@Repository
public class PaymentWeekDAOImpl extends HibernateDaoSupport implements PaymentWeekDAO{

    @Autowired
    private SessionFactory sessionFactory;  
    @PostConstruct
    public void setSessionFactory() {  
        super.setSessionFactory(sessionFactory);  
    }  
    
	@Override
	public void save(PaymentWeek paymentWeek) {
		this.getHibernateTemplate().save(paymentWeek);
		
	}

	@Override
	public void delete(PaymentWeek paymentWeek) {
		this.getHibernateTemplate().delete(paymentWeek);
	}

	@Override
	public void update(PaymentWeek paymentWeek) {
		this.getHibernateTemplate().update(paymentWeek);		
	}

	@Override
	public List<PaymentWeek> queryHql(String hql) {
		Session session=this.getSessionFactory().openSession();
		return session.createQuery(hql).list();
	}

	@Override
	public List queryWeek(String where) {
		Session session=this.getSessionFactory().openSession();
		String hql="select distinct week FROM PaymentWeek "+where;
		return session.createQuery(hql).list();
	}

	@Override
	public List queryWeek(String where, Integer pageSize, Integer pageCurrent) {
		Session session=this.getSessionFactory().openSession();
		String hql="select distinct week FROM PaymentWeek "+where;
		return session.createQuery(hql).setFirstResult((pageCurrent-1)*pageSize).setMaxResults(pageSize).list();
	}
	
	@Override
	public List<PaymentWeek> query(String where) {
		Session session=this.getSessionFactory().openSession();
		String hql="select distinct week FROM PaymentWeek "+where;
		return session.createQuery(hql).list();
	}

	@Override
	public List<PaymentWeek> query(String where, Integer pageSize, Integer pageCurrent) {
		Session session=this.getSessionFactory().openSession();
		String hql="select distinct week FROM PaymentWeek "+where;
		return session.createQuery(hql).setFirstResult((pageCurrent-1)*pageSize).setMaxResults(pageSize).list();
	}

}
