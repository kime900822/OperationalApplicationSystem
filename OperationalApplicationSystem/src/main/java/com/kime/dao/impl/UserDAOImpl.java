package com.kime.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
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
		Date d1=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		List user=this.getHibernateTemplate().find("FROM User where uid=? and password=? and (quitDate ='' or quitDate>?)", new String[]{uid,passWord,sdf.format(d1)});
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
		List list = session.createQuery(hql).setFirstResult((pageCurrent-1)*pageSize).setMaxResults(pageSize).list();
		//return this.getHibernateTemplate().find("FROM User "+where);
		session.close();
		return list;
	}
	
	

	@Override
	public List query(String where) {
		Session session=this.getSessionFactory().openSession();
		String hql="FROM User "+where;
		List list = session.createQuery(hql).list();
		session.close();
		return list;
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
		List list = session.createQuery(hql).list();
		session.close();
		return list;
	}


}


