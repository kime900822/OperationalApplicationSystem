package com.sign.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.sign.dao.BeneficiaryDAO;
import com.sign.model.Beneficiary;

@Repository
public class BeneficiaryDAOImpl extends HibernateDaoSupport implements BeneficiaryDAO {

	
    @Autowired
    private SessionFactory sessionFactory;  
    @PostConstruct
    public void setSessionFactory() {  
        super.setSessionFactory(sessionFactory);  
    }  
    
    
	@Override
	public void save(Beneficiary beneficiary) {
		this.getHibernateTemplate().save(beneficiary);
		
	}

	@Override
	public void update(Beneficiary beneficiary) {
		this.getHibernateTemplate().update(beneficiary);
		
	}

	@Override
	public void delete(Beneficiary beneficiary) {
		this.getHibernateTemplate().delete(beneficiary);
		
	}

	@Override
	public List<Beneficiary> query(String where) {
		Session session=this.getSessionFactory().openSession();
		String hql="FROM Beneficiary "+where;
		return session.createQuery(hql).list();
	}

	@Override
	public List<Beneficiary> query(String where, Integer pageSize, Integer pageCurrent) {
		Session session=this.getSessionFactory().openSession();
		String hql="FROM Beneficiary "+where;
		return session.createQuery(hql).setFirstResult((pageCurrent-1)*pageSize).setMaxResults(pageSize).list();
	}

}
