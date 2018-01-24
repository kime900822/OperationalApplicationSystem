package com.sign.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.sign.dao.SignManDAO;
import com.sign.model.SignMan;

@Repository
public class SignManDAOImpl extends HibernateDaoSupport implements SignManDAO {

    @Autowired
    private SessionFactory sessionFactory;  
    @PostConstruct
    public void setSessionFactory() {  
        super.setSessionFactory(sessionFactory);  
    }  
    
	@Override
	public List<SignMan> query(String where) {
		Session session=this.getSessionFactory().openSession();
		String hql="FROM SignMan "+where;
		return session.createQuery(hql).list();
	}

	@Override
	public List<SignMan> query(String where, Integer pageSize, Integer pageCurrent) {
		Session session=this.getSessionFactory().openSession();
		String hql="FROM SignMan "+where;
		return session.createQuery(hql).setFirstResult((pageCurrent-1)*pageSize).setMaxResults(pageSize).list();

	}

	@Override
	public void save(SignMan signMan) {
		this.getHibernateTemplate().save(signMan);
	}

	@Override
	public void delete(SignMan signMan) {
		this.getHibernateTemplate().delete(signMan);
	}

	@Override
	public void update(SignMan signMan) {
		this.getHibernateTemplate().update(signMan);
	}

}
