package com.kime.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kime.dao.UserDAO;
import com.kime.model.User;

@Repository
public class UserDAOImpl extends HibernateDaoSupport implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;  
    @PostConstruct
    public void setSessionFactory() {  
        super.setSessionFactory(sessionFactory);  
    }  
    
	@Override
	public User login(String uid, String passWord) {
		List user=this.getHibernateTemplate().find("FROM User where uid=? and password=? ", new String[]{uid,passWord});
		if (user.size()>0) {
			return (User)user.get(0);
		}
		else{
			return null;
		}
	}

	@Override
	public void save(User user) {
		this.getHibernateTemplate().save(user);

	}

	@Override
	public List query(String where,Integer pageSize,Integer pageCurrent) {
		// TODO Auto-generated method stub
		Session session=this.getSessionFactory().openSession();
		String hql="FROM User "+where;
		return session.createQuery(hql).setFirstResult((pageCurrent-1)*pageSize).setMaxResults(pageSize).list();
		//return this.getHibernateTemplate().find("FROM User "+where);
	}
	
	

	@Override
	public List query(String where) {
		Session session=this.getSessionFactory().openSession();
		String hql="FROM User "+where;
		return session.createQuery(hql).list();
	}

	@Override
	public void update(User user) {
		this.getHibernateTemplate().merge(user);
		
	}

	@Override
	public void delete(User user) {
		this.getHibernateTemplate().delete(user);
		
	}

	@Override
	public List queryByHql(String hql) {
		Session session=this.getSessionFactory().openSession();
		return session.createQuery(hql).list();
	
	}


}


