package com.sign.biz.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
import com.kime.utils.CommonUtil;
import com.kime.utils.PropertiesUtil;
import com.sign.biz.PaymentVisitBIZ;
import com.sign.dao.PaymentVisitDAO;
import com.sign.dao.PaymentVisitEmployeeDAO;
import com.sign.model.Stamp;
import com.sign.model.StampApprove;
import com.sign.model.paymentVisit.PaymentVisit;
import com.sign.model.paymentVisit.PaymentVisitEmployee;
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
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String hql = "SELECT MAX(P.referenceNo) FROM PaymentVisit P where SUBSTR(P.referenceNo,2,6)='" + sdf.format(d)
				+ "'";
		List list = commonDAO.queryByHql(hql);
		if (list.size() > 0) {
			String mcode = (String) list.get(0);
			if (mcode != null && !mcode.equals("")) {

				return "T" + String.valueOf(Long.valueOf(mcode.replace("T", "")) + 1);
			}
		}
		return "S" + sdf.format(d) + "01";
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public ApproveHis approve(String level, String comment, String approveState, String tradeId) {

		ApproveHis approveHis=new ApproveHis();
		try {
				PaymentVisit paymentVisit=queryById(tradeId);
				ApproveList approve=paymentVisit.getApproveList().get(Integer.parseInt(level));
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
				}else {
					if (Integer.parseInt(level)+2<=paymentVisit.getApproveList().size()) {
						paymentVisit.setState(paymentVisit.getApproveList().get(Integer.parseInt(level)).getName()+" Approval");
						paymentVisit.setNextApprove(paymentVisit.getApproveList().get(Integer.parseInt(level)).getUid());
					}else {
						paymentVisit.setState(PaymentVisitHelp.COMPLETED);
						paymentVisit.setNextApprove("");
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
		return paymentVisit;
		
	}

	
	
}
