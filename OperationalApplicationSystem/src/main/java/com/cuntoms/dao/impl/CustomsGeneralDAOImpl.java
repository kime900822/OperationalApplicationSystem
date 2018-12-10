package com.cuntoms.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cuntoms.dao.CustomsGeneralDAO;
import com.cuntoms.model.CustomsGeneral;
import com.kime.base.DaoBase;

@Repository
public class CustomsGeneralDAOImpl extends DaoBase implements CustomsGeneralDAO{

	@Autowired
    private SessionFactory sessionFactory;  
    @PostConstruct
    public void setSessionFactory() {  
        super.setSessionFactory(sessionFactory);  
    }  
    
	@Override
	public void save(CustomsGeneral general) {
		this.getHibernateTemplate().save(general);
	}

	@Override
	public void update(CustomsGeneral general) {
		this.getHibernateTemplate().update(general);
	}

	@Override
	public void delete(CustomsGeneral general) {
		this.getHibernateTemplate().delete(general);
	}

	@Override
	public List query(String where) {
		Session session=this.getSessionFactory().openSession();
		String hql="FROM CustomsGeneral "+where;
		return session.createQuery(hql).list();
	}

	@Override
	public List query(String where, Integer pageSize, Integer pageCurrent) {
		Session session=this.getSessionFactory().openSession();
		String hql="FROM CustomsGeneral "+where;
		return session.createQuery(hql).setFirstResult((pageCurrent-1)*pageSize).setMaxResults(pageSize).list();
	}

	
	
}
