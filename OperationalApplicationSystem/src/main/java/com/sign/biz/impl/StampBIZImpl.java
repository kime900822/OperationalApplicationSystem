package com.sign.biz.impl;

import java.io.ByteArrayInputStream;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.itextpdf.text.Element;
import com.kime.base.BizBase;
import com.kime.biz.ApproveBIZ;
import com.kime.biz.ApproveHisBIZ;
import com.kime.biz.DepartmentBIZ;
import com.kime.biz.UserBIZ;
import com.kime.dao.ApproveDAO;
import com.kime.dao.ApproveHisDAO;
import com.kime.dao.CommonDAO;
import com.kime.dao.DictDAO;
import com.kime.infoenum.Message;
import com.kime.model.Approve;
import com.kime.model.ApproveHis;
import com.kime.model.Department;
import com.kime.model.Dict;
import com.kime.model.User;
import com.kime.utils.CommonUtil;
import com.kime.utils.PropertiesUtil;
import com.kime.utils.mail.SendMail;
import com.sign.biz.StampBIZ;
import com.sign.dao.StampDAO;
import com.sign.model.Stamp;
import com.sign.model.StampApprove;
import com.sign.other.StampState;

@Service
@Transactional(readOnly = true)
public class StampBIZImpl extends BizBase implements StampBIZ {

	@Autowired
	private StampDAO stampDAO;
	@Autowired
	private CommonDAO commonDAO;
	@Autowired
	private DictDAO dictDAO;
	@Autowired
	private ApproveBIZ approveBIZ;
	@Autowired
	private ApproveHisBIZ approveHisBIZ;
	@Autowired
	private UserBIZ userBIZ;
	@Autowired
	DepartmentBIZ departmentBIZ;

	@Override
	public ByteArrayInputStream export() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void saveStamp(Stamp stamp) {
		stampDAO.save(stamp);
		logUtil.logInfo("用章申请单保存成功：" + stamp.getApplicationCode());
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void submitStamp(Stamp stamp) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Stamp> getStamp(String where) {
		return stampDAO.query(where);
	}

	@Override
	public List<Stamp> getStamp(String where, Integer pageSize, Integer pageCurrent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stamp getStampById(String id) throws Exception {
		Stamp stamp = stampDAO.query(" where id='" + id + "' ").get(0);
		stamp.setApproveHis(approveHisBIZ.getApproveHisByTradeId(stamp.getId()));
		stamp.setStampApprove(stampDAO.queryStampApprove(stamp.getId()));
		return stamp;
	}

	@Override
	public String getMaxCode() {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String hql = "SELECT MAX(P.applicationCode) FROM Stamp P where SUBSTR(P.applicationCode,2,6)='" + sdf.format(d)
				+ "'";
		List list = commonDAO.queryByHql(hql);
		if (list.size() > 0) {
			String mcode = (String) list.get(0);
			if (mcode != null && !mcode.equals("")) {

				return "S" + String.valueOf(Long.valueOf(mcode.replace("S", "")) + 1);
			}
		}
		return "S" + sdf.format(d) + "0001";
	}

	@Override
	public List<Stamp> getStampByHql(String hql, Integer pageSize, Integer pageCurrent) {
		return stampDAO.queryHql(hql, pageSize, pageCurrent);
	}

	@Override
	public List<Stamp> getStampByHql(String hql) {
		return stampDAO.queryHql(hql);
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public ApproveHis StampApprove(String level, String comment,String approveState,String tradeId) {
		ApproveHis approveHis=new ApproveHis();
		try {
				Stamp stamp=getStampById(tradeId);
				StampApprove approve=stamp.getStampApprove().get(Integer.parseInt(level));
				approveHis.setDate(CommonUtil.getDateTemp());
				approveHis.setLevel(approve.getLevel());
				approveHis.setuName(approve.getUname());
				approveHis.setuId(approve.getUid());
				approveHis.setName(approve.getName());
				approveHis.setStatus(approveState);
				approveHis.setType("STAMP");
				approveHis.setTradeId(tradeId);
				approveHis.setComment(comment);
				approveHis.setdId(approve.getDid());
				approveHis.setdName(approve.getDname());
				approveHisBIZ.save(approveHis);
				//stamp.getApproveHis().add(approveHis);
				if (stamp.getStampApprove().size()-1>Integer.parseInt(approve.getLevel())) {
					if (approveState.equals("Rejected")) {
						stamp.setNextApprover("");
						stamp.setState(approveHis.getName()+" Rejected");
					}else{				
						stamp.setNextApprover(stamp.getStampApprove().get(Integer.parseInt(approve.getLevel())+1).getUid());
						stamp.setState(stamp.getStampApprove().get(Integer.parseInt(approve.getLevel())+1).getName()+" Approval");
						User user=userBIZ.getUser(" where uid='"+stamp.getApplicantID()+"'").get(0);
						SendMail.SendMail(user.getEmail(), PropertiesUtil.ReadProperties(Message.MAIL_PROPERTIES, "mailTitleOfStampApprove"), MessageFormat.format(PropertiesUtil.ReadProperties(Message.MAIL_PROPERTIES, "mailContentOfStampApprove"),stamp.getApplicationCode(),stamp.getApplicant(),stamp.getUrgentReason()));
	
					}
//					if (stamp.getStampApprove().size()-2==Integer.parseInt(approve.getLevel())) {
//						stamp.setState(getStampState(2, approveState));
//					}else {
//						stamp.setState(getStampState(Integer.parseInt(level), approveState));
//					}
					
					
					

				}else{
					if (!approveState.equals("Rejected")) {
						User user=userBIZ.getUser(" where uid='"+stamp.getApplicantID()+"'").get(0);
						stamp.setState(StampState.INFORM);
						SendMail.SendMail(user.getEmail(), PropertiesUtil.ReadProperties(Message.MAIL_PROPERTIES, "mailTitleOfStamp"), PropertiesUtil.ReadProperties(Message.MAIL_PROPERTIES, "mailContentOfStamp"));						
					}else {
						stamp.setState(approveHis.getName()+" Rejected");
					}
					stamp.setNextApprover("");
					//stamp.setState(getStampState(3, approveState));
				}

				updateOfApporve(stamp);
				
		} catch (Exception e) {
			logUtil.logError(e.getMessage());
		}
			
		return approveHis;
	}
	

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void update(Stamp stamp) throws Exception {
		if (stamp.getState().equals(StampState.SUBMIT)) {
			List<StampApprove> lStampApprove = new ArrayList<>();
			if (stamp.getProjectResponsible() != null && !stamp.getProjectResponsible().equals("")) {
				Dict approveType = dictDAO.query(" where id='" + stamp.getDocumentType() + "'").get(0);
				List<Approve> lApproves = approveBIZ.getApproveAndChild(approveType.getValueExplain());			
				for (Approve approve : lApproves) {
					StampApprove tmp = new StampApprove();
					tmp.setUid(approve.getUid());
					tmp.setDid(approve.getDid());
					tmp.setTradeId(stamp.getId());
					tmp.setName(approve.getName());
					tmp.setUname(approve.getUname());
					tmp.setDname(approve.getDname());
					tmp.setLevel(approve.getLevel());
					lStampApprove.add(tmp);
				}

				List<User> lUsers = userBIZ.getUser(" where uid='" + stamp.getProjectResponsible() + "'");
				if (lUsers.size() > 0) {
					lStampApprove.get(0).setUid(lUsers.get(0).getUid());
					lStampApprove.get(0).setUname(lUsers.get(0).getName());
					lStampApprove.get(0).setDid(lUsers.get(0).getDid());
					lStampApprove.get(0).setDname(lUsers.get(0).getDepartment().getName());
					stamp.setNextApprover(lUsers.get(0).getUid());
				}

			} else {
				Dict approveType = dictDAO.query(" where id='" + stamp.getDocumentType() + "'").get(0);
				List<Approve> lApproves = approveBIZ.getApproveAndChild(approveType.getValueExplain());
				if (lApproves.get(0).getUid().equals("Dept. Head")) {
					List<Department> lDepartments = departmentBIZ
							.queryDepartment(" where did='" + stamp.getDepartmentOfApplicantID() + "'");
					if (lDepartments.size() > 0) {
						List<User> lUsers = userBIZ.getUser(" where uid='" + lDepartments.get(0).getUid() + "'");
						if (lUsers.size() > 0) {
							lApproves.get(0).setUid(lUsers.get(0).getUid());
							lApproves.get(0).setUname(lUsers.get(0).getName());
							lApproves.get(0).setDid(lUsers.get(0).getDid());
							lApproves.get(0).setDname(lUsers.get(0).getDepartment().getName());
							stamp.setNextApprover(lUsers.get(0).getUid());
						} else {
							throw new Exception(" Department Manager is null");
						}
					} else {
						throw new Exception(" Department is null");
					}

				}else{
					stamp.setNextApprover(lApproves.get(0).getUid());
				}
				for (Approve approve : lApproves) {
					StampApprove tmp = new StampApprove();
					tmp.setUid(approve.getUid());
					tmp.setName(approve.getName());
					tmp.setDid(approve.getDid());
					tmp.setTradeId(stamp.getId());
					tmp.setUname(approve.getUname());
					tmp.setDname(approve.getDname());
					tmp.setLevel(approve.getLevel());
					lStampApprove.add(tmp);
				}

			}
			stamp.setStampApprove(lStampApprove);
			
			//删除原有审批流程
			List<StampApprove> lApproves=(List<StampApprove>) commonDAO.queryByHql("From StampApprove where tradeId='"+stamp.getId()+"'");
			if (lApproves.size()>0) {
				for (StampApprove stampApprove :lApproves) {
					stampDAO.delete(stampApprove);				
				}
			}
			
			
			//保存新审批流程
			for (StampApprove approve : lStampApprove) {
				stampDAO.save(approve);
			}
			
			stamp.setState(stamp.getStampApprove().get(0).getName()+" Approval");
		}

		stampDAO.update(stamp);
		logUtil.logInfo("用章申请单更新成功：" + stamp.getApplicationCode());

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteStamp(Stamp stamp) {
		for (StampApprove stampApprove :(List<StampApprove>)commonDAO.queryByHql(" From StampApprove where tradeId='"+stamp.getId()+"'")) {
			stampDAO.delete(stampApprove);
		}
		for (ApproveHis approveHis :(List<ApproveHis>)commonDAO.queryByHql(" From ApproveHis where tradeId='"+stamp.getId()+"'")) {
			approveHisBIZ.delete(approveHis);
		}	
		stampDAO.delete(stamp);
		logUtil.logInfo("用章申请单删除成功：" + stamp.getApplicationCode());

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateOfApporve(Stamp stamp) throws Exception {
		stampDAO.update(stamp);	
	}
	
	public String getStampState(Integer level,String status){
		if (status.equals("Rejected")) {
			if (level==0) {
				return StampState.LEVEL1_REJECT;
			}else if (level==1) {
				return StampState.LEVEL2_REJECT;
			}else if (level==2) {
				return StampState.LEVEL3_REJECT;
			}else if (level==3) {
				return StampState.INFORM_REJECT;
			}
		}else{
			if (level==0) {
				return StampState.LEVEL1;
			}else if (level==1) {
				return StampState.LEVEL2;
			}else if (level==2) {
				return StampState.LEVEL3;
			}else if (level==3) {
				return StampState.INFORM;
			}
		}
		return "";
		
	}

}
