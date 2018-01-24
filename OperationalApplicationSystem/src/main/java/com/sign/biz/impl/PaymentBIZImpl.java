package com.sign.biz.impl;

import java.io.ByteArrayInputStream;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kime.biz.DictBIZ;
import com.kime.biz.UserBIZ;
import com.kime.dao.CommonDAO;
import com.kime.dao.DictDAO;
import com.kime.dao.UserDAO;
import com.kime.infoenum.Message;
import com.kime.model.Dict;
import com.kime.model.User;
import com.kime.utils.PropertiesUtil;
import com.kime.utils.TypeChangeUtil;
import com.kime.utils.mail.SendMail;
import com.sign.biz.PaymentBIZ;
import com.sign.dao.PaymentDAO;
import com.sign.model.Payment;

@Service
@Transactional(readOnly=true)
public class PaymentBIZImpl implements PaymentBIZ {
	
	@Autowired
	private PaymentDAO paymentDao;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private DictDAO dictDAO;
	@Autowired
	private CommonDAO commonDAO;
	
	
	public CommonDAO getCommonDAO() {
		return commonDAO;
	}

	public void setCommonDAO(CommonDAO commonDAO) {
		this.commonDAO = commonDAO;
	}

	public DictDAO getDictDAO() {
		return dictDAO;
	}

	public void setDictDAO(DictDAO dictDAO) {
		this.dictDAO = dictDAO;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public PaymentDAO getPaymentDao() {
		return paymentDao;
	}

	public void setPaymentDao(PaymentDAO paymentDao) {
		this.paymentDao = paymentDao;
	}

	@Override
	public ByteArrayInputStream export() {
		return null;
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void savePayment(Payment payment){
		paymentDao.save(payment);
	}
	
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void updatePayment(Payment payment){
		paymentDao.update(payment);
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void submitPayment(Payment payment) throws Exception {
		
		
		List<Dict> lDicts=commonDAO.queryByHql(" select D from Dict D where D.key='"+payment.getUID()+"'");
		if (lDicts.size()>0) {
			List<User> list=userDAO.query(" where uid='"+lDicts.get(0).getValue()+"'");
			if (list.size()>0) {
				payment.setDeptManagerID(list.get(0).getUid());
				payment.setDeptManager(list.get(0).getName());
				paymentDao.update(payment);
				//SendMail.SendMail(lUsers.get(0).getEmail(), "Payment application system inform", "Dear sir,<br><br> You have got a payment approval request from <u><b>\""+payment.getUName()+"\"</b></u> . <br><br>Approval Website:<a href='"+PropertiesUtil.ReadProperties(Message.SYSTEM_PROPERTIES, "website")+"'>Analysis</a>");	
				SendMail.SendMail(list.get(0).getEmail(), PropertiesUtil.ReadProperties(Message.MAIL_PROPERTIES, "mailTitleOfSubmit"), MessageFormat.format(PropertiesUtil.ReadProperties(Message.MAIL_PROPERTIES, "mailContentOfSubmit"),payment.getUName(),TypeChangeUtil.formatMoney(payment.getAmountInFigures(), 2, payment.getCurrency_1()),PropertiesUtil.ReadProperties(Message.SYSTEM_PROPERTIES, "website")));	
			}else{
				throw new Exception("对应签核人员信息不存在，提交审批失败");
			}
		}
			else{
				List<User> lUsers=userDAO.queryByHql(" select U from User U,SignMan S where U.uid=S.uid AND S.did='"+payment.getDepartmentID()+"'");
				if (lUsers.size()>0) {
					if (!lUsers.get(0).getUid().equals(payment.getUID())) {
						payment.setDeptManagerID(lUsers.get(0).getUid());
						paymentDao.update(payment);
						//SendMail.SendMail(lUsers.get(0).getEmail(), "Payment application system inform", "Dear sir,<br><br> You have got a payment approval request from <u><b>\""+payment.getUName()+"\"</b></u> . <br><br>Approval Website:<a href='"+PropertiesUtil.ReadProperties(Message.SYSTEM_PROPERTIES, "website")+"'>Analysis</a>");	
						SendMail.SendMail(lUsers.get(0).getEmail(), PropertiesUtil.ReadProperties(Message.MAIL_PROPERTIES, "mailTitleOfSubmit"), MessageFormat.format(PropertiesUtil.ReadProperties(Message.MAIL_PROPERTIES, "mailContentOfSubmit"),payment.getUName(),TypeChangeUtil.formatMoney(payment.getAmountInFigures(), 2, payment.getCurrency_1()),PropertiesUtil.ReadProperties(Message.SYSTEM_PROPERTIES, "website")));	

					}else{
						throw new Exception("对应特殊签核人员未维护，提交审批失败");
					}
				}else{
					throw new Exception("对应签核人员信息不存在，提交审批失败");
				}
			}

	
	}
	
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void accPayment(Payment payment) {
		paymentDao.update(payment);
		List<User> lUsers=userDAO.query(" where id='"+payment.getUID()+"'");
		SendMail.SendMail(lUsers.get(0).getEmail(), PropertiesUtil.ReadProperties(Message.MAIL_PROPERTIES, "mailTitleOfAcc"), MessageFormat.format(PropertiesUtil.ReadProperties(Message.MAIL_PROPERTIES, "mailContentOfAcc"), payment.getCode(),TypeChangeUtil.formatMoney(payment.getAmountInFigures(), 2, payment.getCurrency_1()),payment.getDocumentAudit()));	
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void assignPayment(Payment payment) throws Exception {
		paymentDao.update(payment);
	}
	
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void approvePayment(Payment payment) throws Exception {
		List<Dict> lDicts=dictDAO.query(" where key='"+payment.getPaymentSubject()+"'");
		if (!lDicts.get(0).getValue().equals("")&&lDicts.get(0).getValue()!=null) {
			payment.setDocumentAuditID(lDicts.get(0).getValue());
			paymentDao.update(payment);
			User user=(User)userDAO.query(" where id='"+payment.getUID()+"'").get(0);
			//SendMail.SendMail(user.getEmail(), "Payment application system inform", "Dear sir,<br><br>Your application form which amount is <u><b>"+TypeChangeUtil.formatMoney(payment.getAmountInFigures(),2,payment.getCurrency_1())+"</b></u> have been approved by <u><b>"+payment.getDeptManager()+"</b></u>");	
			SendMail.SendMail(user.getEmail(), PropertiesUtil.ReadProperties(Message.MAIL_PROPERTIES, "mailTitleOfApprove"), MessageFormat.format(PropertiesUtil.ReadProperties(Message.MAIL_PROPERTIES, "mailContentOfApprove"),TypeChangeUtil.formatMoney(payment.getAmountInFigures(),2,payment.getCurrency_1()),payment.getDeptManager()));	
		}
	}
	
	
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void invalidPayment(Payment payment) throws Exception {
		paymentDao.update(payment);
		User user=(User)userDAO.query(" where id='"+payment.getUID()+"'").get(0);
		User iuser=(User)userDAO.query(" where id='"+payment.getDocumentAuditID()+"'").get(0);
		SendMail.SendMail(user.getEmail(), PropertiesUtil.ReadProperties(Message.MAIL_PROPERTIES, "mailTitleOfInvaild"), MessageFormat.format(PropertiesUtil.ReadProperties(Message.MAIL_PROPERTIES, "mailContentOfInvaild"),payment.getCode(), TypeChangeUtil.formatMoney(payment.getAmountInFigures(),2,payment.getCurrency_1()),iuser.getName(),payment.getInvalidDescription()));	
	}
	
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void printPayment(Payment payment) throws Exception {
		paymentDao.update(payment);
	}
	
	
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void returnPayment(Payment payment) throws Exception {
		paymentDao.update(payment);
		User user=(User)userDAO.query(" where id='"+payment.getUID()+"'").get(0);
		User iuser=(User)userDAO.query(" where id='"+payment.getDocumentAuditID()+"'").get(0);
		SendMail.SendMail(user.getEmail(), PropertiesUtil.ReadProperties(Message.MAIL_PROPERTIES, "mailTitleOfReturn"), MessageFormat.format(PropertiesUtil.ReadProperties(Message.MAIL_PROPERTIES, "mailContentOfReturn"),payment.getCode(), TypeChangeUtil.formatMoney(payment.getAmountInFigures(),2,payment.getCurrency_1()),iuser.getName(),payment.getReturnDescription()));	

	}
	
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void rejectPayment(Payment payment) throws Exception {
		paymentDao.update(payment);
		User user=(User)userDAO.query(" where id='"+payment.getUID()+"'").get(0);
		User ruser=(User)userDAO.query(" where id='"+payment.getDeptManagerID()+"'").get(0);
		SendMail.SendMail(user.getEmail(), PropertiesUtil.ReadProperties(Message.MAIL_PROPERTIES, "mailTitleOfReject"), MessageFormat.format(PropertiesUtil.ReadProperties(Message.MAIL_PROPERTIES, "mailContentOfReject"),TypeChangeUtil.formatMoney(payment.getAmountInFigures(),2,payment.getCurrency_1()),ruser.getName()));	

	}
	
	@Override
	public List<Payment> getPaymentByHql(String hql) {
		return paymentDao.queryHql(hql);
	}

	@Override
	public List<Payment> getPaymentByHql(String hql, Integer pageSize, Integer pageCurrent) {
		return paymentDao.queryHql(hql, pageSize, pageCurrent);
	}

	
	@Override
	public List<Payment> getPayment(String where) {
		return paymentDao.query(where);
	}

	@Override
	public List<Payment> getPayment(String where, Integer pageSize, Integer pageCurrent) {
		return paymentDao.query(where, pageSize, pageCurrent);
	}
	
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void sign(Payment payment) {
		paymentDao.update(payment);
		
	}

	@Override
	public String getMaxCode() {
		Date d=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMM");
		String hql="SELECT MAX(P.code) FROM Payment P";
		List list= commonDAO.queryByHql(hql);
		if (list.size()>0) { 
			String mcode=(String)list.get(0);
			if (!mcode.equals("")&&mcode!=null) {
				return  String.valueOf(Integer.valueOf(mcode)+1);
			}		
		}
			return sdf.format(d)+"0001";
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void deletePayment(Payment payment) {
		paymentDao.delete(payment);
		
	}

	
	
}
