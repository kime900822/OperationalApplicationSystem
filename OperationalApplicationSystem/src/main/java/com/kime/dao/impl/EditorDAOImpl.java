package com.kime.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kime.dao.EditorDAO;
import com.kime.model.Editor;

@Repository
public class EditorDAOImpl extends HibernateDaoSupport implements EditorDAO {


    @Autowired
    private SessionFactory sessionFactory;  
    @PostConstruct
    public void setSessionFactory() {  
        super.setSessionFactory(sessionFactory);  
    }  

	@Override
	public List query(String where) {
		Session session=this.getSessionFactory().openSession();
		String hql="FROM Editor "+where;
		return session.createQuery(hql).list();
	}

	@Override
	public void save(Editor editor) {
		this.getHibernateTemplate().save(editor);
	}

	@Override
	public void delete(Editor editor) {
		this.getHibernateTemplate().delete(editor);
		
	}

	@Override
	public List query(String where, int pageSize, int pageCurrent) {
		Session session=this.getSessionFactory().openSession();
		String hql="FROM Editor "+where;
		return session.createQuery(hql).setFirstResult((pageCurrent-1)*pageSize).setMaxResults(pageSize).list();

	}

	@Override
	public void mod(Editor editor) {
		this.getHibernateTemplate().merge(editor);
		
	}

}
