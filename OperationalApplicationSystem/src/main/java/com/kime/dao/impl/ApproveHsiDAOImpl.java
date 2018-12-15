package com.kime.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.kime.dao.ApproveHisDAO;
import com.kime.model.ApproveHis;

@Repository
public class ApproveHsiDAOImpl extends HibernateDaoSupport implements ApproveHisDAO {

    @Autowired
    private SessionFactory sessionFactory;  
    @PostConstruct
    public void setSessionFactory() {  
        super.setSessionFactory(sessionFactory);  
    }  
	
	@Override
	public void save(ApproveHis approveHis) {
		this.getHibernateTemplate().save(approveHis);		
	}

	@Override
	public List getApproveHisByTradeId(String tradeId) {
		Session session=this.getSessionFactory().openSession();
		String hql="FROM ApproveHis where tradeId='"+tradeId+"' ORDER BY date ";
		List list = session.createQuery(hql).list();
		session.close();
		return list;
	}

	@Override
	public void update(ApproveHis approveHis) {
		this.getHibernateTemplate().update(approveHis);		
	}

	@Override
	public void delete(ApproveHis approveHis) {
		this.getHibernateTemplate().delete(approveHis);
	}

	@Override
	public List query(String where) {
		Session session=this.getSessionFactory().openSession();
		String hql="FROM ApproveHis "+where;
		List list = session.createQuery(hql).list();
		session.close();
		return list;
	}

}
