package com.customs.dao.impl;

import com.customs.dao.CustomsMappingDAO;
import com.customs.model.CustomsMapping;
import com.kime.base.DaoBase;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

public class CustomsMappingDAOImpl  extends DaoBase implements CustomsMappingDAO {


    @Autowired
    private SessionFactory sessionFactory;
    @PostConstruct
    public void setSessionFactory() {
        super.setSessionFactory(sessionFactory);
    }

    @Override
    public void save(CustomsMapping customsMapping) {
        this.getHibernateTemplate().save(customsMapping);
    }

    @Override
    public void update(CustomsMapping customsMapping) {
        this.getHibernateTemplate().update(customsMapping);
    }

    @Override
    public void delete(CustomsMapping customsMapping) {
        this.getHibernateTemplate().delete(customsMapping);
    }

    @Override
    public List query(String where) {
        Session session=this.getSessionFactory().openSession();
        String hql="FROM CustomsMapping "+where;
        List list = session.createQuery(hql).list();
        session.close();
        return list;
    }

    @Override
    public List query(String where, Integer pageSize, Integer pageCurrent) {
        Session session=this.getSessionFactory().openSession();
        String hql="FROM CustomsMapping "+where;
        List list = session.createQuery(hql).setFirstResult((pageCurrent-1)*pageSize).setMaxResults(pageSize).list();
        session.close();
        return list;
    }
}
