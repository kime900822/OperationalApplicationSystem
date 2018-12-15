package com.cuntoms.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cuntoms.dao.CustomsProductDAO;
import com.cuntoms.model.CustomsProduct;
import com.kime.base.DaoBase;

@Repository
public class CustomsProductDAOImpl extends DaoBase implements CustomsProductDAO {

    @Autowired
    private SessionFactory sessionFactory;  
    @PostConstruct
    public void setSessionFactory() {  
        super.setSessionFactory(sessionFactory);  
    }  
	
	
	@Override
	public void save(CustomsProduct customsProduct) {
		this.getHibernateTemplate().save(customsProduct);
		
	}

	@Override
	public void update(CustomsProduct customsProduct) {
		this.getHibernateTemplate().update(customsProduct);
	}

	@Override
	public void delete(CustomsProduct customsProduct) {
		this.getHibernateTemplate().delete(customsProduct);
	}

	@Override
	public List query(String where) {
		Session session=this.getSessionFactory().openSession();
		String hql="FROM CustomsProduct "+where;
		List list = session.createQuery(hql).list();
		session.close();
		return list;
	}

	@Override
	public List query(String where, Integer pageSize, Integer pageCurrent) {
		Session session=this.getSessionFactory().openSession();
		String hql="FROM CustomsProduct "+where;
		List list = session.createQuery(hql).setFirstResult((pageCurrent-1)*pageSize).setMaxResults(pageSize).list();
		session.close();
		return list;
	}
	
	
}
