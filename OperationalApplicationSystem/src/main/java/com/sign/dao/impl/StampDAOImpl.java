package com.sign.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.sign.dao.PaymentDAO;
import com.sign.dao.StampDAO;
import com.sign.model.Payment;
import com.sign.model.Stamp;
import com.sign.model.StampApprove;

@Repository
public class StampDAOImpl extends HibernateDaoSupport implements StampDAO{

    @Autowired
    private SessionFactory sessionFactory;  
    @PostConstruct
    public void setSessionFactory() {  
        super.setSessionFactory(sessionFactory);  
    }
	@Override
	public void save(Stamp stamp) {
		this.getHibernateTemplate().save(stamp);		
	}
	@Override
	public void delete(Stamp stamp) {
		this.getHibernateTemplate().delete(stamp);
	}
	@Override
	public void update(Stamp stamp) {
		this.getHibernateTemplate().update(stamp);
		
	}
	@Override
	public List<Stamp> queryHql(String hql) {
		Session session=this.getSessionFactory().openSession();
		return session.createQuery(hql).list();
	}
	@Override
	public List<Stamp> query(String where) {
		Session session=this.getSessionFactory().openSession();
		String hql="FROM Stamp "+where;
		return session.createQuery(hql).list();
	}
	@Override
	public List<Stamp> query(String where, Integer pageSize, Integer pageCurrent) {
		Session session=this.getSessionFactory().openSession();
		String hql="FROM Stamp "+where;
		return session.createQuery(hql).setFirstResult((pageCurrent-1)*pageSize).setMaxResults(pageSize).list();
	}
	@Override
	public List<Stamp> queryHql(String hql, Integer pageSize, Integer pageCurrent) {
		Session session=this.getSessionFactory().openSession();
		return session.createQuery(hql).setFirstResult((pageCurrent-1)*pageSize).setMaxResults(pageSize).list();
	}
	@Override
	public void save(StampApprove stampApprove) {
		this.getHibernateTemplate().save(stampApprove);
	}




}
