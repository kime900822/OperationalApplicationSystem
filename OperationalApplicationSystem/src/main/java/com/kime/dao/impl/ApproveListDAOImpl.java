package com.kime.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.kime.dao.ApproveListDAO;
import com.kime.model.ApproveList;

@Repository
public class ApproveListDAOImpl extends HibernateDaoSupport implements ApproveListDAO{

    @Autowired
    private SessionFactory sessionFactory;  
    @PostConstruct
    public void setSessionFactory() {  
        super.setSessionFactory(sessionFactory);  
    }  
	
	
	@Override
	public void save(ApproveList approveList) {
		this.getHibernateTemplate().save(approveList);
	}

	@Override
	public List getApproveListByTradeId(String tradeId) {
		Session session=this.getSessionFactory().openSession();
		String hql="FROM ApproveList where tradeId='"+tradeId+"' ORDER BY date ";
		List list = session.createQuery(hql).list();
		session.close();
		return list;
	}

	@Override
	public void update(ApproveList approveList) {
		this.getHibernateTemplate().update(approveList);		
	}

	@Override
	public void delete(ApproveList approveList) {
		this.getHibernateTemplate().delete(approveList);
		
	}

	@Override
	public List query(String where) {
		Session session=this.getSessionFactory().openSession();
		String hql="FROM ApproveList "+where;
		List list = session.createQuery(hql).list();
		session.close();
		return list;
	}

	
	
}
