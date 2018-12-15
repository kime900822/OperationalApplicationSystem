package com.kime.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.kime.dao.ApproveDAO;
import com.kime.model.Approve;
import com.kime.model.Menu;

@Repository
public class ApproveDAOImpl  extends HibernateDaoSupport implements ApproveDAO {

    @Autowired
    private SessionFactory sessionFactory;  
    @PostConstruct
    public void setSessionFactory() {  
        super.setSessionFactory(sessionFactory);  
    }  
	
	
	@Override
	public void save(Approve approve) {
		this.getHibernateTemplate().save(approve);	
	}

	@Override
	public List getAllApprove() {
		List Approve=this.getHibernateTemplate().find("FROM Approve ORDER BY order ");
		return Approve;
	}

	@Override
	public Approve getApproveByID(String id) {
		List Approve=this.getHibernateTemplate().find("FROM Approve where id=? ", new String[]{id});
		this.getHibernateTemplate().getSessionFactory().getCurrentSession().clear();
		if (Approve.size()>0) {
			return (Approve)Approve.get(0);
		}
		else{
			return null;
		}
	}

	@Override
	public List getApproveByParentID(String parentID) {
		List Approve=this.getHibernateTemplate().find("FROM Approve where parentID=? ORDER BY order ", new String[]{parentID});
		this.getHibernateTemplate().getSessionFactory().getCurrentSession().clear();
		return Approve;
	}

	@Override
	public void update(Approve approve) {
		this.getHibernateTemplate().update(approve);	
		
	}

	@Override
	public void delete(Approve approve) {
		this.getHibernateTemplate().delete(approve);	
		
	}

	@Override
	public List getParentMenu() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List query(String where) {
		Session session=this.getSessionFactory().openSession();
		String hql="FROM Approve "+where;
		List list = session.createQuery(hql).list();
		session.close();
		return list;
	}


	@Override
	public List query(String where, Integer pageSize, Integer pageCurrent) {
		Session session=this.getSessionFactory().openSession();
		String hql="FROM Approve "+where;
		List list = session.createQuery(hql).setFirstResult((pageCurrent-1)*pageSize).setMaxResults(pageSize).list();
		session.close();
		return list;
	}



}
