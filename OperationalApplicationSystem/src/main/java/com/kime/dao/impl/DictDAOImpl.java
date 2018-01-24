package com.kime.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.kime.dao.DictDAO;
import com.kime.model.Dict;

@Repository
public class DictDAOImpl extends HibernateDaoSupport implements DictDAO {

    @Autowired
    private SessionFactory sessionFactory;  
    @PostConstruct
    public void setSessionFactory() {  
        super.setSessionFactory(sessionFactory);  
    }  
	
	@Override
	public List<Dict> query(String where) {
		Session session=this.getSessionFactory().openSession();
		String hql="FROM Dict "+where;
		return session.createQuery(hql).list();
	}

	@Override
	public List<Dict> query(String where, int pageSize, int pageCurrent) {
		Session session=this.getSessionFactory().openSession();
		String hql="FROM Dict "+where;
		return session.createQuery(hql).setFirstResult((pageCurrent-1)*pageSize).setMaxResults(pageSize).list();
	}

	@Override
	public void delete(Dict dict) {
		this.getHibernateTemplate().delete(dict);
	}

	@Override
	public void save(Dict dict) {
		this.getHibernateTemplate().save(dict);
		
	}

	@Override
	public void update(Dict dict) {
		this.getHibernateTemplate().update(dict);
		
	}

	@Override
	public List getType() {
		Session session=this.getSessionFactory().openSession();
		String hql="select distinct r.type from Dict r";
		return session.createQuery(hql).list();
		
		
	}

}
