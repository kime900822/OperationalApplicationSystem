package com.sign.biz.impl;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.kime.base.BizBase;
import com.kime.biz.ApproveBIZ;
import com.kime.biz.ApproveHisBIZ;
import com.kime.dao.ApproveListDAO;
import com.kime.dao.CommonDAO;
import com.kime.dao.DepartmentDAO;
import com.kime.dao.DictDAO;
import com.kime.dao.UserDAO;
import com.kime.infoenum.Message;
import com.kime.model.Approve;
import com.kime.model.ApproveHis;
import com.kime.model.ApproveList;
import com.kime.model.Dict;
import com.kime.model.User;
import com.kime.utils.ApproveListUtil;
import com.kime.utils.CommonUtil;
import com.kime.utils.PropertiesUtil;
import com.kime.utils.TypeChangeUtil;
import com.kime.utils.mail.SendMail;
import com.sign.biz.BeneficiaryBIZ;
import com.sign.biz.PaymentBIZ;
import com.sign.biz.PaymentVisitBIZ;
import com.sign.dao.PaymentDAO;
import com.sign.dao.PaymentVisitBusinessTripDAO;
import com.sign.dao.PaymentVisitDAO;
import com.sign.dao.PaymentVisitEmployeeDAO;
import com.sign.model.Beneficiary;
import com.sign.model.Payment;
import com.sign.model.Stamp;
import com.sign.model.StampApprove;
import com.sign.model.paymentVisit.PaymentVisit;
import com.sign.model.paymentVisit.PaymentVisitBusinessTrip;
import com.sign.model.paymentVisit.PaymentVisitEmployee;
import com.sign.other.PaymentHelp;
import com.sign.other.PaymentVisitHelp;

@Service
@Transactional(readOnly=true)
public class PaymentVisitBIZImpl extends BizBase implements PaymentVisitBIZ {

	@Autowired
	PaymentVisitDAO paymentVisitDAO;
	@Autowired
	CommonDAO commonDAO;
	@Autowired
	PaymentVisitEmployeeDAO PaymentVisitEmployeeDAO;
	@Autowired
	DictDAO dictDAO;
	@Autowired
	ApproveBIZ approveBIZ;
	@Autowired
	ApproveListDAO approveListDAO;
	@Autowired
	DepartmentDAO departmentDAO;
	@Autowired
	UserDAO userDAO;
	@Autowired
	ApproveHisBIZ approveHisBIZ;
	@Autowired
	PaymentVisitBusinessTripDAO paymentVisitBusinessTripDAO;
	@Autowired
	PaymentBIZ paymentBIZ;
	@Autowired
	BeneficiaryBIZ beneficiaryBIZ;
	
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void save(PaymentVisit paymentVisit) {
		
		List<PaymentVisitEmployee> lEmployees=PaymentVisitEmployeeDAO.query(" where VisitID='"+paymentVisit.getId()+"'");
		
		for (PaymentVisitEmployee employee : lEmployees) {
			PaymentVisitEmployeeDAO.delete(employee);
		}
		
		for (PaymentVisitEmployee employee : paymentVisit.getEmployees()) {
			employee.setVisitId(paymentVisit.getId());
			PaymentVisitEmployeeDAO.save(employee);
		}
			
		
		paymentVisitDAO.save(paymentVisit);
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void delete(PaymentVisit paymentVisit) {

		paymentVisitDAO.delete(paymentVisit);
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void update(PaymentVisit paymentVisit) throws Exception {
		if (paymentVisit.getState().equals(PaymentVisitHelp.SUBMIT)) {
			
			if (paymentVisit.getNextApprove()==null||paymentVisit.getNextApprove().equals("")) {
				List<ApproveList> lApproveLists=new ArrayList<>();
				String paymentVisitApprove=PropertiesUtil.ReadProperties(Message.SYSTEM_PROPERTIES, "PaymentVisitApprove");
				List<Dict> list=dictDAO.query(" where type='CHECKTYPE' and key='"+paymentVisitApprove+"'");
				if (list.size()==0) {
					throw new Exception("未找到类型为:"+paymentVisitApprove+"的签核");
				}		
				List<Approve> lApproves = approveBIZ.getApproveAndChild(list.get(0).getValueExplain());	
			
				
				
				
				if (lApproves.size()==0) {
					throw new Exception(paymentVisitApprove+"未绑定签核流程");
				}
				
				
				for (int i = 0; i < lApproves.size(); i++) {
					if (lApproves.get(i).getUid().equals("Dept. Head")) {
						if (paymentVisit.getAdvanceAmount()>5000||paymentVisit.getTotalLeaveWorkHours()>40||paymentVisit.getBusinessTrip().equals("Oversea 国外")) {
							try {
								User GM=(User) userDAO.query(" where uid='"+PropertiesUtil.ReadProperties(Message.SYSTEM_PROPERTIES, "GMApprove")+"' ").get(0);
								lApproves.get(0).setUid(GM.getUid());
								lApproves.get(0).setUname(GM.getName());
								lApproves.get(0).setDid(GM.getDid());
								lApproves.get(0).setDname(GM.getDepartment().getName());
								paymentVisit.setNextApprove(GM.getUid());
							} catch (Exception e) {
								logUtil.logInfo("自动付款申异常:总经理信息获取异常:"+paymentVisit.getuName());
								throw new Exception("自动付款申异常:总经理信息获取异常:"+paymentVisit.getuName());
							}
						}else {
							List<Dict> lDicts=commonDAO.queryByHql(" select D from Dict D where D.key='"+paymentVisit.getuId()+"' and D.type='SignMan4Manager' ");
							if (lDicts.size()>0) {
								List<User> lUsers=userDAO.query(" where uid='"+lDicts.get(0).getValue()+"'");
								if (list.size()>0) {
									lApproves.get(0).setUid(lUsers.get(0).getUid());
									lApproves.get(0).setUname(lUsers.get(0).getName());
									lApproves.get(0).setDid(lUsers.get(0).getDid());
									lApproves.get(0).setDname(lUsers.get(0).getDepartment().getName());
									paymentVisit.setNextApprove(lUsers.get(0).getUid());
								}else{
									throw new Exception("对应签核人员信息不存在，提交审批失败");
								}
							}else {
								User user=(User) userDAO.query(" where uid='"+paymentVisit.getuId()+"'").get(0);
								List<User> lUsers =userDAO.query(" where uid='" + user.getDepartment().getUid() + "'");
								if (lUsers.size() > 0) {
									lApproves.get(0).setUid(lUsers.get(0).getUid());
									lApproves.get(0).setUname(lUsers.get(0).getName());
									lApproves.get(0).setDid(lUsers.get(0).getDid());
									lApproves.get(0).setDname(lUsers.get(0).getDepartment().getName());
									paymentVisit.setNextApprove(lUsers.get(0).getUid());
								} else {
									throw new Exception(" Department Manager is null");
								}
							}
							
						}
					}else {
						List<User> lUsers =userDAO.query(" where uid='" + lApproves.get(0).getUid() + "'");
						if (lUsers.size() > 0) {
							lApproves.get(0).setUid(lUsers.get(0).getUid());
							lApproves.get(0).setUname(lUsers.get(0).getName());
							lApproves.get(0).setDid(lUsers.get(0).getDid());
							lApproves.get(0).setDname(lUsers.get(0).getDepartment().getName());
							paymentVisit.setNextApprove(lUsers.get(0).getUid());
						} else {
							throw new Exception(lApproves.get(0).getUid()+" is not exist!");
						}
						
					}
					
					
					
				}		
			
				
				for (Approve approve : lApproves) {
					ApproveList tmp=new ApproveList();
					tmp.setUid(approve.getUid());
					tmp.setDid(approve.getDid());
					tmp.setTradeId(paymentVisit.getId());
					tmp.setName(approve.getName());
					tmp.setUname(approve.getUname());
					tmp.setDname(approve.getDname());
					tmp.setLevel(approve.getLevel());
					approveListDAO.save(tmp);
					lApproveLists.add(tmp);
				}
				
				
				for (ApproveList approveList : paymentVisit.getApproveList()) {
					approveListDAO.delete(approveList);
				}
				
				User user=(User) userDAO.query(" where uid='"+paymentVisit.getNextApprove()+"' ").get(0);
				SendMail.SendMail(user.getEmail(), PropertiesUtil.ReadProperties(Message.MAIL_PROPERTIES, "mailTitleOfPaymentVisit"), MessageFormat.format(PropertiesUtil.ReadProperties(Message.MAIL_PROPERTIES, "mailContentOfPaymentVisitSubmit"),paymentVisit.getReferenceNo(),paymentVisit.getuName(),paymentVisit.getVisitDetailPurpose(),PropertiesUtil.ReadProperties(Message.SYSTEM_PROPERTIES, "website")));
				logUtil.logInfo("出差预申请开始审批 发送邮件  地址："+user.getEmail());
				
			}else {
				paymentVisit.setNextApprove(paymentVisit.getApproveList().get(0).getUid());
			}
			
			
			
		}

		paymentVisitDAO.update(paymentVisit);
	}

	@Override
	public List<PaymentVisit> query(String where) {
		return paymentVisitDAO.query(where);
	}

	@Override
	public List<PaymentVisit> query(String where, Integer pageSize, Integer pageCurrent) {
		return paymentVisitDAO.query(where, pageSize, pageCurrent);
	}

	
	@Override
	public String getMaxCode() {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String hql = "SELECT MAX(P.referenceNo) FROM PaymentVisit P where SUBSTR(P.referenceNo,2,8)='" + sdf.format(d)
				+ "'";
		List list = commonDAO.queryByHql(hql);
		if (list.size() > 0) {
			String mcode = (String) list.get(0);
			if (mcode != null && !mcode.equals("")) {

				return "T" + String.valueOf(Long.valueOf(mcode.replace("T", "")) + 1);
			}
		}
		return "T" + sdf.format(d) + "01";
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public ApproveHis approve(String level, String comment, String approveState, String tradeId) throws Exception {

		ApproveHis approveHis=new ApproveHis();
		try {
				PaymentVisit paymentVisit=queryById(tradeId);
				ApproveList approve= ApproveListUtil.search(paymentVisit.getApproveList(), level);
				approveHis.setDate(CommonUtil.getDateTemp());
				approveHis.setLevel(approve.getLevel());
				approveHis.setuName(approve.getUname());
				approveHis.setuId(approve.getUid());
				approveHis.setName(approve.getName());
				approveHis.setStatus(approveState);
				approveHis.setType("PAYMENT VISIT");
				approveHis.setTradeId(tradeId);
				approveHis.setComment(comment);
				approveHis.setdId(approve.getDid());
				approveHis.setdName(approve.getDname());
				approveHisBIZ.save(approveHis);
				
				if (approveState.equals("Rejected")) {
					paymentVisit.setState(paymentVisit.getApproveList().get(Integer.parseInt(level)).getName()+" Rejected");
					paymentVisit.setNextApprove(paymentVisit.getApproveList().get(0).getUid());
				}else {
					if (Integer.parseInt(level)+2<=paymentVisit.getApproveList().size()) {
						paymentVisit.setState(paymentVisit.getApproveList().get(Integer.parseInt(level+1)).getName()+" Approval");
						paymentVisit.setNextApprove(paymentVisit.getApproveList().get(Integer.parseInt(level+1)).getUid());
						User user=(User) userDAO.query(" where uid='"+paymentVisit.getNextApprove()+"' ").get(0);
						SendMail.SendMail(user.getEmail(), PropertiesUtil.ReadProperties(Message.MAIL_PROPERTIES, "mailTitleOfPaymentVisit"), MessageFormat.format(PropertiesUtil.ReadProperties(Message.MAIL_PROPERTIES, "mailContentOfPaymentVisitSubmit"),paymentVisit.getReferenceNo(),paymentVisit.getuName(),paymentVisit.getVisitDetailPurpose(),PropertiesUtil.ReadProperties(Message.SYSTEM_PROPERTIES, "website")));
						logUtil.logInfo("出差预申请审批下一步审批  发送邮件  地址："+user.getEmail());
						
					}else {
						paymentVisit.setState(PaymentVisitHelp.COMPLETED);
						paymentVisit.setNextApprove("");
						
						
						Boolean[] isNeed= {false,false,false,false};
						for (PaymentVisitEmployee obj : paymentVisit.getEmployees()) {
							if(obj.getHotelBookingByHR().equals("YES")) {
								isNeed[0]=true;
							}
							if(obj.getCarArrangeByHR().equals("YES")) {
								isNeed[1]=true;
							}
							if(obj.getAirTickerBookingByHR().equals("YES")) {
								isNeed[2]=true;
							}
							if(obj.getVisarArrangeByHR().equals("YES")) {
								isNeed[3]=true;
							}
						}
						
						User user=new User();
						if (isNeed[0]) {
							user=(User) userDAO.query(" where uid in (select D.value from Dict D where D.type='PAYMENT_VISIT' and D.key='A') ").get(0);
							paymentVisit.setNoticeA(user.getUid());
							SendMail.SendMail(user.getEmail(),PropertiesUtil.ReadProperties(Message.MAIL_PROPERTIES, "mailTitleOfPaymentVisit") , MessageFormat.format(PropertiesUtil.ReadProperties(Message.MAIL_PROPERTIES, "mailContentOfPaymentVisitNotice"),paymentVisit.getReferenceNo()));
							logUtil.logInfo("出差预申请审批结束   发送邮件  地址："+user.getEmail());	
						}
						if (isNeed[1]) {
							user=(User) userDAO.query(" where uid in (select D.value from Dict D where D.type='PAYMENT_VISIT' and D.key='B') ").get(0);
							paymentVisit.setNoticeB(user.getUid());
							SendMail.SendMail(user.getEmail(),PropertiesUtil.ReadProperties(Message.MAIL_PROPERTIES, "mailTitleOfPaymentVisit") , MessageFormat.format(PropertiesUtil.ReadProperties(Message.MAIL_PROPERTIES, "mailContentOfPaymentVisitNotice"),paymentVisit.getReferenceNo()));
							logUtil.logInfo("出差预申请审批结束  发送邮件  地址："+user.getEmail());	
						}
						if (isNeed[2]) {
							user=(User) userDAO.query(" where uid in (select D.value from Dict D where D.type='PAYMENT_VISIT' and D.key='C') ").get(0);
							paymentVisit.setNoticeC(user.getUid());
							SendMail.SendMail(user.getEmail(),PropertiesUtil.ReadProperties(Message.MAIL_PROPERTIES, "mailTitleOfPaymentVisit") , MessageFormat.format(PropertiesUtil.ReadProperties(Message.MAIL_PROPERTIES, "mailContentOfPaymentVisitNotice"),paymentVisit.getReferenceNo()));
							logUtil.logInfo("出差预申请审批结束  发送邮件  地址："+user.getEmail());	
						}
						if (isNeed[3]) {
							user=(User) userDAO.query(" where uid in (select D.value from Dict D where D.type='PAYMENT_VISIT' and D.key='D') ").get(0);
							paymentVisit.setNoticeD(user.getUid());
							SendMail.SendMail(user.getEmail(),PropertiesUtil.ReadProperties(Message.MAIL_PROPERTIES, "mailTitleOfPaymentVisit") , MessageFormat.format(PropertiesUtil.ReadProperties(Message.MAIL_PROPERTIES, "mailContentOfPaymentVisitNotice"),paymentVisit.getReferenceNo()));
							logUtil.logInfo("出差预申请审批结束  发送邮件  地址："+user.getEmail());	
						}
						
						user=(User) userDAO.query(" where uid='"+paymentVisit.getuId()+"' ").get(0);
						SendMail.SendMail(user.getEmail(), PropertiesUtil.ReadProperties(Message.MAIL_PROPERTIES, "mailTitleOfPaymentVisit"), MessageFormat.format(PropertiesUtil.ReadProperties(Message.MAIL_PROPERTIES, "mailContentOfPaymentVisitApprove"),paymentVisit.getReferenceNo(),paymentVisit.getApproveList().get(paymentVisit.getApproveList().size()-1).getUname()));
						
						for (PaymentVisitEmployee employee : paymentVisit.getEmployees()) {
							user=(User) userDAO.query(" where uid='"+employee.getEmployeeNo()+"' ").get(0);
							SendMail.SendMail(user.getEmail(), PropertiesUtil.ReadProperties(Message.MAIL_PROPERTIES, "mailTitleOfPaymentVisit"), MessageFormat.format(PropertiesUtil.ReadProperties(Message.MAIL_PROPERTIES, "mailContentOfPaymentVisitEmployee"),paymentVisit.getuName(),paymentVisit.getReferenceNo(),paymentVisit.getVisitDateFrom(),paymentVisit.getVisitDateTo(),paymentVisit.getAdvanceAmount()));
						}
						
						logUtil.logInfo("出差预申请审批结束  发送邮件  地址："+user.getEmail());	
						
						if (paymentVisit.getAdvanceAmount()>0) {
							buildPayment(paymentVisit);
						}
						
					}
					
				}
				
				paymentVisitDAO.update(paymentVisit);
				
		}catch (Exception e) {
			logUtil.logError(e.getMessage());
			throw new Exception(e.getMessage());
		}
		
		return approveHis;
		
		
	}

	@Override
	public PaymentVisit queryById(String id) {
		PaymentVisit paymentVisit = paymentVisitDAO.query(" where id='" + id + "' ").get(0);
		paymentVisit.setApproveHis(approveHisBIZ.getApproveHisByTradeId(paymentVisit.getId()));
		paymentVisit.setApproveList(approveListDAO.query("where tradeId='"+id+"' order by level "));
		paymentVisit.setEmployees(PaymentVisitEmployeeDAO.query(" where visitId='"+id+"' order by id "));
		paymentVisit.setBusinessTrips(paymentVisitBusinessTripDAO.query(" where visitId='"+paymentVisit.getId()+"' order by rowNum"));
		
		return paymentVisit;
		
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void cancel(String id) throws Exception {
		PaymentVisit paymentVisit=queryById(id);
		paymentVisit.setState(PaymentVisitHelp.INVALID);
		for (PaymentVisitEmployee paymentVisitEmployee : paymentVisit.getEmployees()) {
			List<User> lUsers=userDAO.query(" where uid='"+paymentVisitEmployee.getEmployeeNo()+"'");
			if (lUsers.size()>0) {
				SendMail.SendMail(lUsers.get(0).getEmail(),PropertiesUtil.ReadProperties(Message.MAIL_PROPERTIES, "mailTitleOfPaymentVisit") , PropertiesUtil.ReadProperties(Message.MAIL_PROPERTIES, "mailContentOfPaymentVisitCancel"));
			}
		}
		User manager=(User) userDAO.query(" where uid='"+paymentVisit.getApproveList().get(0).getUid()+"'").get(0);
		SendMail.SendMail(manager.getEmail(),PropertiesUtil.ReadProperties(Message.MAIL_PROPERTIES, "mailTitleOfPaymentVisit") , PropertiesUtil.ReadProperties(Message.MAIL_PROPERTIES, "mailContentOfPaymentVisitCancel"));		
		update(paymentVisit);
	}

	@Override
	public List<PaymentVisit> queryByHql(String hql) {
		return commonDAO.queryByHql(hql);
	}

	@Override
	public List<PaymentVisit> queryByHql(String hql, Integer pageSize, Integer pageCurrent) {
		return commonDAO.queryByHql(hql,pageSize,pageCurrent);
	}

	@Override
	public void buildPayment(PaymentVisit paymentVisit) throws Exception {
		Payment payment=new Payment();
		List luser=userDAO.query(" where uid='"+paymentVisit.getuId()+"'");
		if (luser.size()==0) {
			logUtil.logInfo("自动付款申异常:出差申请人员未找到:"+paymentVisit.getuName());
			throw new Exception("自动付款申异常:出差申请人员未找到:"+paymentVisit.getuName());
		}
		User user=(User) luser.get(0);
		payment.setApplicationDate(paymentVisit.getApplicantDate());
		payment.setState(PaymentHelp.APPROVEPAYMENT);
		payment.setVisitId(paymentVisit.getId());
		payment.setPaymentSubject(PaymentHelp.TRAVEL);
		payment.setUID(user.getUid());
		payment.setUName(user.getName());
		payment.setDepartmentID(user.getDid());
		payment.setDepartmentName(user.getDepartment().getName());
		if (paymentVisit.getAdvanceAmount()>0) {
			payment.setDeptManagerID(paymentVisit.getApproveList().get(0).getUid());
			payment.setDeptManager(paymentVisit.getApproveList().get(0).getUname());
			payment.setDeptManager2ID(paymentVisit.getApproveList().get(0).getUid());
			payment.setDeptManager2(paymentVisit.getApproveList().get(0).getUname());
		}else {
			payment.setDeptManagerID(user.getDepartment().getUid());
			payment.setDeptManager(user.getDepartment().getName());
			payment.setDeptManager2ID(user.getDepartment().getUid());
			payment.setDeptManager2(user.getDepartment().getName());
		}
		payment.setDeptManagerDate(CommonUtil.getDateTemp());
		payment.setUsageDescription(paymentVisit.getVisitDateFrom()+" "+paymentVisit.getVisitDateTo()+" "+paymentVisit.getVisitDetailPlace()+" "+paymentVisit.getVisitPurpose()+"  单号："+paymentVisit.getReferenceNo());
		payment.setCurrency_1(paymentVisit.getCurrency());
		payment.setCurrency_2(paymentVisit.getCurrency());
		payment.setCurrency_3(paymentVisit.getCurrency());
		payment.setCurrency_4(paymentVisit.getCurrency());
		payment.setCurrency_5(paymentVisit.getCurrency());
		payment.setCurrency_6(paymentVisit.getCurrency());
		payment.setAmount_1(String.valueOf(paymentVisit.getAdvanceAmount()));
		payment.setDateTemp(CommonUtil.getDateTemp());
		payment.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		payment.setPaymentTerm(PaymentHelp.PAYMENT_TERM_ADVANCE);
		payment.setPaymentDays_1("Y");
		payment.setPaymentDays_2("Y");
		payment.setPaymentDays_3("Y");
		payment.setPaymentDays_4("Y");
		payment.setPaymentDays_5("Y");
		payment.setPaymentDays_6("Y");
		payment.setAmountInFigures(String.valueOf(paymentVisit.getAdvanceAmount()));
		if (paymentVisit.getCurrency().equals("RMB")) {
			payment.setPayType(PaymentHelp.PAYTYPE_BANKING);
		}else {
			payment.setPayType(PaymentHelp.PAYTYPE_CASH);
		}
		
		try {
			Beneficiary beneficiary  =beneficiaryBIZ.queryBeneficiary(" where supplierCode='"+paymentVisit.getuId()+"'").get(0);
			payment.setSupplierCode(beneficiary.getSupplierCode());
			payment.setBeneficiary(beneficiary.getName());
			payment.setBeneficiaryE(beneficiary.getEname());
			payment.setBeneficiaryAccountNO(beneficiary.getAccno());
			payment.setBeneficiaryAccountBank(beneficiary.getAccbank());
			payment.setRequestPaymentDate(CommonUtil.getDate());
		} catch (Exception e) {
			logUtil.logInfo(" 收款人信息获取失败 "+e.getMessage());
			throw new Exception(" 收款人信息获取失败 "+e.getMessage());
		}
		
		
		
		
		
		List<Dict> lDicts=dictDAO.query(" where type='PAYMENT' and  key='"+PaymentHelp.TRAVEL+"' ");
		if (!"".equals(lDicts.get(0).getValue())&&lDicts.get(0).getValue()!=null) {
			payment.setDocumentAuditID(lDicts.get(0).getValue());
			paymentBIZ.savePayment(payment);
			logUtil.logInfo("自动生成付款申请单:"+payment.getId());
		}else{
			logUtil.logInfo("自动付款申异常:未维护对应财务人员");
			throw new Exception("自动付款申异常:未维护对应财务人员");
		}

	}
	
	@Override
	public List<Approve> getApprove(){
		String paymentVisitApprove=PropertiesUtil.ReadProperties(Message.SYSTEM_PROPERTIES, "PaymentVisitApprove");
		List<Dict> list=dictDAO.query(" where type='CHECKTYPE' and key='"+paymentVisitApprove+"'");
		List<Approve> lApproves = approveBIZ.getApproveAndChild(list.get(0).getValueExplain());	
		
		return lApproves;
	}

	
}
