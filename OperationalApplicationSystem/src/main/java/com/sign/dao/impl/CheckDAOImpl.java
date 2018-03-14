package com.sign.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sign.dao.CheckDAO;
import com.sign.model.Check;


@Repository
public class CheckDAOImpl extends HibernateDaoSupport implements CheckDAO {

    @Autowired
    private SessionFactory sessionFactory;  
    @PostConstruct
    public void setSessionFactory() {  
        super.setSessionFactory(sessionFactory);  
    }  
	
	
	@Override
	public List<?> query(String where) {
		Session session=this.getSessionFactory().openSession();
		String hql="FROM Check "+where;
		return session.createQuery(hql).list();
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void delete(Check check) {
		this.getHibernateTemplate().delete(check);
		
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void insert(Check check) {
		this.getHibernateTemplate().save(check);
		
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void update(Check check) {
		this.getHibernateTemplate().update(check);
		
	}

	@Override
	public List<?> query(String where, Integer pageSize, Integer pageCurrent) {
		Session session=this.getSessionFactory().openSession();
		String hql="FROM Check "+where;
		return session.createQuery(hql).setFirstResult((pageCurrent-1)*pageSize).setMaxResults(pageSize).list();
	}

}
