package com.customs.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.customs.dao.CustomsClearanceDAO;
import com.customs.model.CustomsClearance;
import com.kime.base.DaoBase;

@Repository
public class CustomsClearanceDAOImpl  extends DaoBase implements CustomsClearanceDAO{
	
	
	@Autowired
    private SessionFactory sessionFactory;  
    @PostConstruct
    public void setSessionFactory() {  
        super.setSessionFactory(sessionFactory);  
    }  

	@Override
	public void save(CustomsClearance clearance) {
		this.getHibernateTemplate().save(clearance);
	}

	@Override
	public void update(CustomsClearance clearance) {
		this.getHibernateTemplate().update(clearance);
	}

	@Override
	public void delete(CustomsClearance clearance) {
		this.getHibernateTemplate().delete(clearance);
	}

	@Override
	public List query(String where) {
		Session session=this.getSessionFactory().openSession();
		String hql="FROM CustomsClearance "+where;
		List list = session.createQuery(hql).list();
		session.close();
		return list;
	}

	@Override
	public List query(String where, Integer pageSize, Integer pageCurrent) {
		Session session=this.getSessionFactory().openSession();
		String hql="FROM CustomsClearance "+where;
		List list = session.createQuery(hql).setFirstResult((pageCurrent-1)*pageSize).setMaxResults(pageSize).list();
		session.close();
		return list;
	}

}
