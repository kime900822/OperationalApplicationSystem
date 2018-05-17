package com.sign.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.sign.dao.PaymentDAO;
import com.sign.model.Payment;
import com.sign.model.PaymentPO;

@Repository
public class PaymentDAOImpl extends HibernateDaoSupport implements PaymentDAO {

    @Autowired
    private SessionFactory sessionFactory;  
    @PostConstruct
    public void setSessionFactory() {  
        super.setSessionFactory(sessionFactory);  
    }  
	
	@Override
	public void save(Payment payment) {
		this.getHibernateTemplate().save(payment);	
	}

	@Override
	public void delete(Payment payment) {
		this.getHibernateTemplate().delete(payment);
	}

	@Override
	public void update(Payment payment) {
		this.getHibernateTemplate().update(payment);
	}

	@Override
	public List<Payment> query(String where) {
		Session session=this.getSessionFactory().openSession();
		String hql="FROM Payment "+where;
		return session.createQuery(hql).list();
	}

	@Override
	public List<Payment> query(String where, Integer pageSize, Integer pageCurrent) {
		Session session=this.getSessionFactory().openSession();
		String hql="FROM Payment "+where;
		return session.createQuery(hql).setFirstResult((pageCurrent-1)*pageSize).setMaxResults(pageSize).list();

	}

	@Override
	public List<Payment> queryHql(String hql) {
		Session session=this.getSessionFactory().openSession();
		return session.createQuery(hql).list();
	}

	@Override
	public List<Payment> queryHql(String hql, Integer pageSize, Integer pageCurrent) {
		Session session=this.getSessionFactory().openSession();
		return session.createQuery(hql).setFirstResult((pageCurrent-1)*pageSize).setMaxResults(pageSize).list();
	}

	@Override
	public List<PaymentPO> queryPaymentPOSql(String sql) {
		Session session=this.getSessionFactory().openSession();	
        List list = session.createSQLQuery(sql).list();  
		return dataTopaymentPo(list);
	}

	
	@Override
	public List<PaymentPO> queryPaymentPOSql(String sql, Integer pageSize, Integer pageCurrent) {
		Session session=this.getSessionFactory().openSession();	
        List list = session.createSQLQuery(sql).setFirstResult((pageCurrent-1)*pageSize).setMaxResults(pageSize).list();  
		return dataTopaymentPo(list);
	}

	@Override
	public List<PaymentPO> queryPaymentPO(String where) {
		Session session=this.getSessionFactory().openSession();
		String hql="FROM v_po "+where;
		return session.createQuery(hql).list();
	}
	
	
	public List<PaymentPO> dataTopaymentPo(List list){
		List<PaymentPO> lPaymentPOs=new ArrayList<>();
	    for(Iterator iterator = list.iterator();iterator.hasNext();){  
	        Object[] objects = (Object[]) iterator.next();  
	        PaymentPO paymentPO=new PaymentPO();
	        paymentPO.setId(objects[0]==null?"":objects[0].toString());
	        paymentPO.setApplicationDate(objects[1]==null?"":objects[1].toString());
	        paymentPO.setContacturalPaymentDate(objects[2]==null?"":objects[2].toString());
	        paymentPO.setPaidDate(objects[3]==null?"":objects[3].toString());
	        paymentPO.setRequestPaymentDate(objects[4]==null?"":objects[4].toString());
	        paymentPO.setCode(objects[5]==null?"":objects[5].toString());
	        paymentPO.setApplicant(objects[6]==null?"":objects[6].toString());
	        paymentPO.setDid(objects[7]==null?"":objects[7].toString());
	        paymentPO.setAmountInFigures(objects[8]==null?"":objects[8].toString());
	        paymentPO.setUsageDescription(objects[9]==null?"":objects[9].toString());
	        paymentPO.setSupplierCode(objects[10]==null?"":objects[10].toString());
	        paymentPO.setBeneficiaryE(objects[11]==null?"":objects[11].toString());
	        paymentPO.setPaymentDays(objects[12]==null?"":objects[12].toString());
	        paymentPO.setReceivingOrApprovalDate(objects[13]==null?"":objects[13].toString());
	        paymentPO.setPONo(objects[14]==null?"":objects[14].toString());
	        paymentPO.setCurrency(objects[15]==null?"":objects[15].toString());
	        paymentPO.setAmount(objects[16]==null?"":objects[16].toString());
	        lPaymentPOs.add(paymentPO);
	    }  
	    return lPaymentPOs;
	}

	
}
