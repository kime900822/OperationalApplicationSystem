package com.sign.biz.impl;

import java.io.ByteArrayInputStream;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kime.base.BizBase;
import com.kime.biz.DictBIZ;
import com.kime.biz.UserBIZ;
import com.kime.dao.CommonDAO;
import com.kime.dao.DictDAO;
import com.kime.dao.UserDAO;
import com.kime.infoenum.Message;
import com.kime.model.Dict;
import com.kime.model.User;
import com.kime.utils.CommonUtil;
import com.kime.utils.PropertiesUtil;
import com.kime.utils.TypeChangeUtil;
import com.kime.utils.mail.SendMail;
import com.sign.biz.PaymentBIZ;
import com.sign.dao.PaymentDAO;
import com.sign.dao.PaymentWeekDAO;
import com.sign.model.Payment;
import com.sign.model.PaymentPO;
import com.sign.model.PaymentWeek;
import com.sign.other.PaymentHelp;

import javafx.print.Collation;

@Service
@Transactional(readOnly=true)
public class PaymentBIZImpl extends BizBase implements PaymentBIZ {
	
	@Autowired
	private PaymentDAO paymentDAO;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private DictDAO dictDAO;
	@Autowired
	private CommonDAO commonDAO;
	@Autowired
	private PaymentWeekDAO paymentWeekDAO;
	


	@Override
	public ByteArrayInputStream export() {
		return null;
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void savePayment(Payment payment){
		paymentDAO.save(payment);
	}
	
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void updatePayment(Payment payment){
		paymentDAO.update(payment);
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void submitPayment(Payment payment) throws Exception {
		
		
		List<Dict> lDicts=commonDAO.queryByHql(" select D from Dict D where D.key='"+payment.getUID()+"' and D.type='SignMan4Manager' ");
		if (lDicts.size()>0) {
			List<User> list=userDAO.query(" where uid='"+lDicts.get(0).getValue()+"'");
			if (list.size()>0) {
				payment.setDeptManagerID(list.get(0).getUid());
				payment.setDeptManager(list.get(0).getName());
				paymentDAO.update(payment);
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
						paymentDAO.update(payment);
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
		paymentDAO.update(payment);
		List<User> lUsers=userDAO.query(" where id='"+payment.getUID()+"'");
		//SendMail.SendMail(lUsers.get(0).getEmail(), PropertiesUtil.ReadProperties(Message.MAIL_PROPERTIES, "mailTitleOfAcc"), MessageFormat.format(PropertiesUtil.ReadProperties(Message.MAIL_PROPERTIES, "mailContentOfAcc"), payment.getCode(),TypeChangeUtil.formatMoney(payment.getAmountInFigures(), 2, payment.getCurrency_1()),payment.getDocumentAudit()));	
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void assignPayment(Payment payment) throws Exception {
		paymentDAO.update(payment);
	}
	
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void approvePayment(Payment payment) throws Exception {
		try {
			List<Dict> lDicts=dictDAO.query(" where key='"+payment.getPaymentSubject()+"'");
			if (!lDicts.get(0).getValue().equals("")&&lDicts.get(0).getValue()!=null) {
				payment.setDocumentAuditID(lDicts.get(0).getValue());
				paymentDAO.update(payment);
				User user=(User)userDAO.query(" where id='"+payment.getUID()+"'").get(0);
				//SendMail.SendMail(user.getEmail(), "Payment application system inform", "Dear sir,<br><br>Your application form which amount is <u><b>"+TypeChangeUtil.formatMoney(payment.getAmountInFigures(),2,payment.getCurrency_1())+"</b></u> have been approved by <u><b>"+payment.getDeptManager()+"</b></u>");	
				SendMail.SendMail(user.getEmail(), PropertiesUtil.ReadProperties(Message.MAIL_PROPERTIES, "mailTitleOfApprove"), MessageFormat.format(PropertiesUtil.ReadProperties(Message.MAIL_PROPERTIES, "mailContentOfApprove"),TypeChangeUtil.formatMoney(payment.getAmountInFigures(),2,payment.getCurrency_1()),payment.getDeptManager()));	
				logUtil.logInfo("发送邮件：接受地址 "+user.getEmail() + " 发送内容："+ PropertiesUtil.ReadProperties(Message.MAIL_PROPERTIES, "mailTitleOfApprove"));
			}
		} catch (Exception e) {
			logUtil.logInfo(e.getMessage());
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}
	
	
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void invalidPayment(Payment payment) throws Exception {
		paymentDAO.update(payment);
		User user=(User)userDAO.query(" where id='"+payment.getUID()+"'").get(0);
		User iuser=(User)userDAO.query(" where id='"+payment.getDocumentAuditID()+"'").get(0);
		SendMail.SendMail(user.getEmail(), PropertiesUtil.ReadProperties(Message.MAIL_PROPERTIES, "mailTitleOfInvaild"), MessageFormat.format(PropertiesUtil.ReadProperties(Message.MAIL_PROPERTIES, "mailContentOfInvaild"),payment.getCode(), TypeChangeUtil.formatMoney(payment.getAmountInFigures(),2,payment.getCurrency_1()),iuser.getName(),payment.getInvalidDescription()));	
	}
	
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void printPayment(Payment payment) throws Exception {
		paymentDAO.update(payment);
	}
	
	
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void returnPayment(Payment payment) throws Exception {
		paymentDAO.update(payment);
		User user=(User)userDAO.query(" where id='"+payment.getUID()+"'").get(0);
		User iuser=(User)userDAO.query(" where id='"+payment.getDocumentAuditID()+"'").get(0);
		SendMail.SendMail(user.getEmail(), PropertiesUtil.ReadProperties(Message.MAIL_PROPERTIES, "mailTitleOfReturn"), MessageFormat.format(PropertiesUtil.ReadProperties(Message.MAIL_PROPERTIES, "mailContentOfReturn"),payment.getCode(), TypeChangeUtil.formatMoney(payment.getAmountInFigures(),2,payment.getCurrency_1()),iuser.getName(),payment.getReturnDescription()));	

	}
	
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void rejectPayment(Payment payment) throws Exception {
		paymentDAO.update(payment);
		User user=(User)userDAO.query(" where id='"+payment.getUID()+"'").get(0);
		User ruser=(User)userDAO.query(" where id='"+payment.getDeptManagerID()+"'").get(0);
		SendMail.SendMail(user.getEmail(), PropertiesUtil.ReadProperties(Message.MAIL_PROPERTIES, "mailTitleOfReject"), MessageFormat.format(PropertiesUtil.ReadProperties(Message.MAIL_PROPERTIES, "mailContentOfReject"),TypeChangeUtil.formatMoney(payment.getAmountInFigures(),2,payment.getCurrency_1()),ruser.getName()));	

	}
	
	@Override
	public List<Payment> getPaymentByHql(String hql) {
		return paymentDAO.queryHql(hql);
	}

	@Override
	public List<Payment> getPaymentByHql(String hql, Integer pageSize, Integer pageCurrent) {
		return paymentDAO.queryHql(hql, pageSize, pageCurrent);
	}

	
	@Override
	public List<Payment> getPayment(String where) {
		return paymentDAO.query(where);
	}

	@Override
	public List<Payment> getPayment(String where, Integer pageSize, Integer pageCurrent) {
		return paymentDAO.query(where, pageSize, pageCurrent);
	}
	
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void sign(Payment payment) {
		paymentDAO.update(payment);
		
	}

	@Override
	public String getMaxCode() {
		Date d=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMM");
		String hql="SELECT MAX(P.code) FROM Payment P where SUBSTR(P.code,1,6)='"+sdf.format(d)+"'";
		List list= commonDAO.queryByHql(hql);
		if (list.size()>0) { 
			String mcode=(String)list.get(0);
			if (mcode!=null&&!mcode.equals("")) {
				return  String.valueOf(Integer.valueOf(mcode)+1);
			}		
		}
			return sdf.format(d)+"0001";
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void deletePayment(Payment payment) {
		paymentDAO.delete(payment);
		
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void financeRejectPayment(String[] ids, String message,User user) throws Exception {
		for (String id : ids) {
			Payment payment=paymentDAO.query(" where id='"+id+"'").get(0);
			if (!payment.getState().equals(PaymentHelp.FINACEPAYMENT)) {
				throw new Exception(payment.getCode()+"单子的状态不为Finance,请重新选择");
			}
			payment.setFinanceRejectMessage(message);
			payment.setState(PaymentHelp.FINANCEREJECTED);
			List<User> lUsers=userDAO.query(" where uid='"+payment.getUID()+"'");
			if (lUsers.size()>0) {		
				SendMail.SendMail(lUsers.get(0).getEmail(), PropertiesUtil.ReadProperties(Message.MAIL_PROPERTIES, "mailTitleOfFinanceReject"), MessageFormat.format(PropertiesUtil.ReadProperties(Message.MAIL_PROPERTIES, "mailContentOfFinanceReject"),payment.getUName(),TypeChangeUtil.formatMoney(payment.getAmountInFigures(), 2, payment.getCurrency_1()),user.getName(),message));									
			}else {
				throw new Exception(payment.getCode()+"单子的用户获取失败");				
			}
			
			paymentDAO.update(payment);
		}
		
	}
	
	
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void paidResetPayment(String[] ids) throws Exception {
		
		
		//List<PaymentWeek> lPaymentWeeks=paymentWeekDAO.query(" where ids like '%"+ids[0]+"%'");
		for (String id : ids) {
			Payment payment=paymentDAO.query(" where id='"+id+"'").get(0);
			if (!payment.getState().equals(PaymentHelp.PAYMENTCOMPLETED)) {
				throw new Exception(payment.getCode()+"单子的状态不为Payment Completed,请重新选择");
			}
			payment.setRefNoofBank("");
			payment.setPaidDate("");
			payment.setState(PaymentHelp.GMAPPROVE);
			paymentDAO.update(payment);
			List<PaymentWeek> lPaymentWeeks=paymentWeekDAO.query(" where pid= '"+payment.getId()+"'");
			if (lPaymentWeeks.size()>0) {
				paymentWeekDAO.delete(lPaymentWeeks.get(0));
			}
			
			
//			if (lPaymentWeeks.size()>0) {
//				String tmp=lPaymentWeeks.get(0).getIds();
//				if (lPaymentWeeks.get(0).getIds().equals("\\'"+id+"\\'")) {
//					lPaymentWeeks.get(0).setIds(tmp.replace("\\'"+id+"\\'","" ));
//				}else {
//					if (lPaymentWeeks.get(0).getIds().startsWith("\\'"+id+"\\'")) {
//						lPaymentWeeks.get(0).setIds(tmp.replace("\\'"+id+"\\',","" ));
//					}else {
//						lPaymentWeeks.get(0).setIds(tmp.replace(",\\'"+id+"\\'","" ));
//					}
//					
//				}
//			}

			
			
		}
		//paymentWeekDAO.update(lPaymentWeeks.get(0));

	}
	
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void mailInformPayment(String[] ids) throws Exception {
		StringBuffer sb = new StringBuffer();  
	    for (int i = 0; i < ids.length; i++) {  
	        sb.append("'").append(ids[i]).append("'").append(",");  
	    }  
	    
	    List<Payment> list=paymentDAO.query(" where id in ("+sb.toString().substring(0, sb.length() - 1)+") ");
		for (Payment payment : list) {
			List<User> lUsers=userDAO.query(" where uid='"+payment.getUID()+"'");
			if (lUsers.size()>0) {		
				SendMail.SendMail(lUsers.get(0).getEmail(), PropertiesUtil.ReadProperties(Message.MAIL_PROPERTIES, "mailTitleOfMailInform"), MessageFormat.format(PropertiesUtil.ReadProperties(Message.MAIL_PROPERTIES, "mailContentOfMailInform"),payment.getUName(),TypeChangeUtil.formatMoney(payment.getAmountInFigures(), 2, payment.getCurrency_1())));									
			}else {
				throw new Exception(payment.getCode()+"单子的用户获取失败");				
			}
		}
	}
	
	
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void paidPayment(List<Payment> list) throws Exception {
		StringBuffer sb = new StringBuffer();  
		for (Payment payment : list) {
			if (!payment.getState().equals(PaymentHelp.GMAPPROVE)) {
				throw new Exception(payment.getCode()+"单子的状态不为GM Approval,请重新选择");
			}
			if (payment.getState().equals(PaymentHelp.PAYMENTCOMPLETED)) {
				throw new Exception(payment.getCode()+"单子已经处理完毕,请重新选择");
			}
			sb.append("'").append(payment.getId()).append("'").append(","); 
			payment.setState(PaymentHelp.PAYMENTCOMPLETED);
			payment.setPaidDate(CommonUtil.getDate());
			paymentDAO.update(payment);
			PaymentWeek paymentWeek=new PaymentWeek();
			paymentWeek.setPid(payment.getId());
			paymentWeek.setWeek(CommonUtil.getWeek());
			paymentWeekDAO.save(paymentWeek);
			
		}

		
	}
	
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public List<PaymentPO> getWeeklyPayment(String[] ids) {
		
	    List<PaymentPO> lPaymentPOs=new ArrayList<>();
		StringBuffer sb = new StringBuffer();  
	    for (int i = 0; i < ids.length; i++) {  
	        sb.append("'").append(ids[i]).append("'").append(",");  
	    }  
	    
	    List<Payment> lPayments=paymentDAO.query(" where id in ("+sb.toString().substring(0, sb.length() - 1)+") order by Code ");
	    
	    
	    for (Payment payment : lPayments) {
		       payment.setState(PaymentHelp.GMAPPROVE);
			   payment.setGMApproveDate(CommonUtil.getDateTemp());
			   paymentDAO.update(payment);
		       PaymentPO paymentPO=new PaymentPO();
		       paymentPO.setCode(payment.getCode());
		       paymentPO.setApplicant(payment.getUID()+"-"+payment.getUName());
		       paymentPO.setDid(payment.getDepartmentID());
		       paymentPO.setAmountInFigures(payment.getAmountInFigures());
		       paymentPO.setUsageDescription(payment.getUsageDescription());
		       paymentPO.setAmount("");
		       paymentPO.setSupplierCode(payment.getSupplierCode());
		       paymentPO.setBeneficiaryE(payment.getBeneficiaryE());
		       lPaymentPOs.add(paymentPO);
		       List<PaymentPO> list=getPaymentPO(" select * From v_po where id ='"+payment.getId()+"' ");
		       
		       for (int j = 0; j < list.size(); j++) {
		    	   list.get(j).setUsageDescription(list.get(j).getPONo()+" "+list.get(j).getUsageDescription());
		    	   list.get(j).setCode("");
		    	   list.get(j).setApplicant("");
		    	   list.get(j).setDid("");
		    	   list.get(j).setAmountInFigures("");
		       }
		       lPaymentPOs.addAll(list);
		}

		
		return lPaymentPOs;

		
		
	}

	@Override
	public List getPaidWeek(String where){		
		return paymentWeekDAO.queryWeek(where);
		
	}

	@Override
	public List getPaidWeek(String where, Integer pageSize, Integer pageCurrent) {
		return paymentWeekDAO.queryWeek(where, pageSize, pageCurrent);
	}

	@Override
	public List<PaymentPO> getPaymentPO(String sql) {
		return paymentDAO.queryPaymentPOSql(sql);
	}

	@Override
	public List<PaymentPO> getPaymentPO(String sql, Integer pageSize, Integer pageCurrent) {
		return paymentDAO.queryPaymentPOSql(sql, pageSize, pageCurrent);
	}
	
	
}
