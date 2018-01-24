package com.analysis.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.analysis.dao.InstructionDAO;
import com.analysis.model.Instruction;

@Repository
public class InstructionDAOImpl extends HibernateDaoSupport implements InstructionDAO {

    @Autowired
    private SessionFactory sessionFactory;  
    @PostConstruct
    public void setSessionFactory() {  
        super.setSessionFactory(sessionFactory);  
    } 
    
	
	@Override
	public List Query(String where) {
		Session session=this.getSessionFactory().openSession();
		String hql="FROM Instruction "+where;
		return session.createQuery(hql).list();
	}

	@Override
	public void Save(Instruction instruction) {
		this.getHibernateTemplate().save(instruction);
		
	}

	@Override
	public void Delete(Instruction instruction) {
		this.getHibernateTemplate().delete(instruction);
		
	}

	@Override
	public void Delete(String where) {
	
		
		Session session=this.getSessionFactory().openSession();
		Transaction tx=session.getTransaction();
		session.beginTransaction();
		String sql="delete from t_instruction "+where; 
		session.createSQLQuery(sql).executeUpdate();
		tx.commit();
		//String hql="DELETE FROM Instruction e "+where;
		//return session.createQuery(hql).executeUpdate();
	}

}
