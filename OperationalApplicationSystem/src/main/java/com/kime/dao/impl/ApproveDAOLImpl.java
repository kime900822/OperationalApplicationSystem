package com.kime.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.kime.dao.ApproveDAO;
import com.kime.model.Approve;
import com.kime.model.Menu;

@Repository
public class ApproveDAOLImpl  extends HibernateDaoSupport implements ApproveDAO {

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Approve getApproveByID(String id) {
		List Approve=this.getHibernateTemplate().find("FROM Approve where id=? ", new String[]{id});
		if (Approve.size()>0) {
			return (Approve)Approve.get(0);
		}
		else{
			return null;
		}
	}

	@Override
	public List getApproveByParentID(String parentID) {
		return null;
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
	public List getApproveByRole(String role) {
		// TODO Auto-generated method stub
		return null;
	}

}
