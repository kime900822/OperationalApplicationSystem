package com.sign.biz.impl;

import java.math.BigDecimal;
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
import com.kime.utils.mail.SendMail;
import com.sign.biz.PaymentVisitBIZ;
import com.sign.dao.PaymentDAO;
import com.sign.dao.PaymentVisitBusinessTripDAO;
import com.sign.dao.PaymentVisitDAO;
import com.sign.dao.PaymentVisitEmployeeDAO;
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
	PaymentDAO patmentDAO;
	
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
				
				Boolean[] isNeed= {true,false,false,false,false};
				for (PaymentVisitEmployee obj : paymentVisit.getEmployees()) {
					if(obj.getHotelBookingByHR().equals("YES")) {
						isNeed[1]=true;
					}
					if(obj.getCarArrangeByHR().equals("YES")) {
						isNeed[2]=true;
					}
					if(obj.getAirTickerBookingByHR().equals("YES")) {
						isNeed[3]=true;
					}
					if(obj.getVisarArrangeByHR().equals("YES")) {
						isNeed[4]=true;
					}
				}
				
				
				
				if (lApproves.size()==0) {
					throw new Exception(paymentVisitApprove+"未绑定签核流程");
				}
				
				
				
				if (lApproves.get(0).getUid().equals("Dept. Head")) {
					
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
				
				for(int i=0;i<lApproves.size();i++) {
					if(isNeed[i]){
						ApproveList tmp=new ApproveList();
						tmp.setUid(lApproves.get(i).getUid());
						tmp.setDid(lApproves.get(i).getDid());
						tmp.setTradeId(paymentVisit.getId());
						tmp.setName(lApproves.get(i).getName());
						tmp.setUname(lApproves.get(i).getUname());
						tmp.setDname(lApproves.get(i).getDname());
						tmp.setLevel(lApproves.get(i).getLevel());
						approveListDAO.save(tmp);
						lApproveLists.add(tmp);
					}
				}
				
//				for (Approve approve : lApproves) {
//					ApproveList tmp=new ApproveList();
//					tmp.setUid(approve.getUid());
//					tmp.setDid(approve.getDid());
//					tmp.setTradeId(paymentVisit.getId());
//					tmp.setName(approve.getName());
//					tmp.setUname(approve.getUname());
//					tmp.setDname(approve.getDname());
//					tmp.setLevel(approve.getLevel());
//					approveListDAO.save(tmp);
//					lApproveLists.add(tmp);
//				}
				
				
				for (ApproveList approveList : paymentVisit.getApproveList()) {
					approveListDAO.delete(approveList);
				}
				
			}else if (paymentVisit.getState().contains("Rejected")) {
				paymentVisit.setState(paymentVisit.getApproveHis().get(paymentVisit.getApproveHis().size()-1).getName()+" Approval");
				approveHisBIZ.delete(paymentVisit.getApproveHis().get(paymentVisit.getApproveHis().size()-1));
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
	public ApproveHis approve(String level, String comment, String approveState, String tradeId) {

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
					}else {
						paymentVisit.setState(PaymentVisitHelp.COMPLETED);
						paymentVisit.setNextApprove("");
						buildPayment(paymentVisit);
					}
					
				}
				
				paymentVisitDAO.update(paymentVisit);
				
		}catch (Exception e) {
			logUtil.logError(e.getMessage());
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
	public void cancel(String id) {
		PaymentVisit paymentVisit=queryById(id);
		paymentVisit.setState(PaymentVisitHelp.CANCEL);
		for (PaymentVisitEmployee paymentVisitEmployee : paymentVisit.getEmployees()) {
			List<User> lUsers=userDAO.query(" where uid='"+paymentVisitEmployee.getEmployeeNo()+"'");
			if (lUsers.size()>0) {
				SendMail.SendMail(lUsers.get(0).getEmail(),PropertiesUtil.ReadProperties(Message.MAIL_PROPERTIES, "mailTitleOfPaymentVisit") , PropertiesUtil.ReadProperties(Message.MAIL_PROPERTIES, "mailContentOfPaymentVisitCancel"));
			}
		}
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
		for (PaymentVisitEmployee employee : paymentVisit.getEmployees()) {
			if (employee.getAdvanceAmount().compareTo(BigDecimal.ZERO)==1) {
				Payment payment=new Payment();
				List luser=userDAO.query(" where uid='"+employee.getEmployeeNo()+"'");
				if (luser.size()==0) {
					logUtil.logInfo("自动付款申异常:出差申请人员未找到:"+employee.getEmployeeNo());
					throw new Exception("自动付款申异常:出差申请人员未找到:"+employee.getEmployeeNo());
				}
				User user=(User) luser.get(0);
				payment.setApplicationDate(paymentVisit.getApplicantDate());
				payment.setState(PaymentHelp.SAVEPAYMENT);
				payment.setVisitId(paymentVisit.getId());
				payment.setPaymentSubject(PaymentHelp.TRAVEL);
				payment.setUID(user.getUid());
				payment.setUName(user.getName());
				payment.setDepartmentID(user.getDid());
				payment.setDepartmentName(user.getDepartment().getName());
				payment.setDeptManagerID(user.getDepartment().getUid());
				payment.setDeptManager(user.getDepartment().getName());
				payment.setUsageDescription(paymentVisit.getVisitDateFrom()+" "+paymentVisit.getVisitDateTo()+" "+paymentVisit.getVisitDetailPlace()+" "+paymentVisit.getVisitPurpose());
				payment.setCurrency_1(paymentVisit.getBusinessTrips().size()==0?paymentVisit.getBusinessTrips().get(0).getCurrency():"");
				payment.setCurrency_2(paymentVisit.getBusinessTrips().size()==0?paymentVisit.getBusinessTrips().get(0).getCurrency():"");
				payment.setCurrency_3(paymentVisit.getBusinessTrips().size()==0?paymentVisit.getBusinessTrips().get(0).getCurrency():"");
				payment.setCurrency_4(paymentVisit.getBusinessTrips().size()==0?paymentVisit.getBusinessTrips().get(0).getCurrency():"");
				payment.setCurrency_5(paymentVisit.getBusinessTrips().size()==0?paymentVisit.getBusinessTrips().get(0).getCurrency():"");
				payment.setCurrency_6(paymentVisit.getBusinessTrips().size()==0?paymentVisit.getBusinessTrips().get(0).getCurrency():"");
				double amount_1=0;
				double amount_2=0;
				double amount_3=0;
				double amount_4=0;
				double amount_5=0;
				double amount_6=0;
				
				for (PaymentVisitBusinessTrip paymentVisitBusinessTrip : paymentVisit.getBusinessTrips()) {
					amount_1+=paymentVisitBusinessTrip.getLandwayTotal();
					amount_2+=paymentVisitBusinessTrip.getRoadToilVAT();
					amount_3+=paymentVisitBusinessTrip.getHotelWithoutVAT();
					amount_4+=paymentVisitBusinessTrip.getHotelVAT();
					amount_5+=paymentVisitBusinessTrip.getMealTotal();
					amount_6+=paymentVisitBusinessTrip.getAirTicket();
				}
				payment.setAmount_1(amount_1==0?"":String.valueOf(amount_1));
				payment.setAmount_2(amount_2==0?"":String.valueOf(amount_2));
				payment.setAmount_3(amount_3==0?"":String.valueOf(amount_3));
				payment.setAmount_4(amount_4==0?"":String.valueOf(amount_4));
				payment.setAmount_5(amount_5==0?"":String.valueOf(amount_5));
				payment.setAmount_1(amount_6==0?"":String.valueOf(amount_6));
				
				payment.setDateTemp(CommonUtil.getDateTemp());
				payment.setId(UUID.randomUUID().toString().replaceAll("-", ""));
				
				List<Dict> lDicts=dictDAO.query(" where key='"+payment.getPaymentSubject()+"'");
				if (!"".equals(lDicts.get(0).getValue())&&lDicts.get(0).getValue()!=null) {
					patmentDAO.save(payment);
					logUtil.logInfo("自动生成付款申请单:"+payment.getId());
				}else{
					logUtil.logInfo("自动付款申异常:未维护对应财务人员");
					throw new Exception("自动付款申异常:未维护对应财务人员");
				}
			}
		}
	}

	
}
