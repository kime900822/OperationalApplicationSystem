package com.sign.biz.impl;

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kime.base.BizBase;
import com.kime.biz.ApproveBIZ;
import com.kime.biz.ApproveHisBIZ;
import com.kime.biz.DepartmentBIZ;
import com.kime.biz.UserBIZ;
import com.kime.dao.ApproveDAO;
import com.kime.dao.ApproveHisDAO;
import com.kime.dao.CommonDAO;
import com.kime.dao.DictDAO;
import com.kime.model.Approve;
import com.kime.model.Department;
import com.kime.model.Dict;
import com.kime.model.User;
import com.sign.biz.StampBIZ;
import com.sign.dao.StampDAO;
import com.sign.model.Stamp;
@Service
@Transactional(readOnly=true)
public class StampBIZImpl extends BizBase implements StampBIZ{

	@Autowired
	private Approve approve;
	@Autowired
	private StampDAO stamDAO;
	@Autowired
	private CommonDAO commonDAO;
	@Autowired
	private DictDAO dictDAO;
	@Autowired
	private ApproveBIZ approveBIZ;
	@Autowired
	private ApproveHisDAO approveHisDAO;
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
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void saveStamp(Stamp stamp) {
		stamDAO.save(stamp);
		logUtil.logInfo("用章申请单保存成功："+stamp.getApplicationCode());
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void submitStamp(Stamp stamp) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Stamp> getStamp(String where) {
		return stamDAO.query(where);
	}

	@Override
	public List<Stamp> getStamp(String where, Integer pageSize, Integer pageCurrent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stamp getStampById(String id) {
		Stamp stamp=stamDAO.query(" where id='"+id+"' ").get(0);
		if (stamp.getProjectResponsible()!=null&&!stamp.getProjectResponsible().equals("")) {
			Dict approveType=dictDAO.query(" where id='"+stamp.getDocumentType()+"'").get(0);	
			List<Approve> lApproves=approveBIZ.getApproveAndChild(approveType.getValueExplain());
			List<User> lUsers=userBIZ.getUser(" where uid='"+stamp.getProjectResponsible()+"'");
			if (lUsers.size()>0) {
				lApproves.get(0).setUid(lUsers.get(0).getUid());
				lApproves.get(0).setUname(lUsers.get(0).getName());
				lApproves.get(0).setDid(lUsers.get(0).getDid());
				lApproves.get(0).setDname(lUsers.get(0).getDepartment().getName());
			}
			
			stamp.setApprove(lApproves);
		}else{
			Dict approveType=dictDAO.query(" where id='"+stamp.getDocumentType()+"'").get(0); 
			List<Approve> lApproves=approveBIZ.getApproveAndChild(approveType.getValueExplain());
			if (lApproves.get(0).getId().equals("Dept. Head")) {
				List<Department> lDepartments=departmentBIZ.queryDepartment(" where did='"+stamp.getDepartmentOfApplicantID()+"'");
				if (lDepartments.size()>0) {
					List<User> lUsers=userBIZ.getUser(" where uid='"+lDepartments.get(0).getUid()+"'");
					if (lUsers.size()>0) {
						lApproves.get(0).setUid(lUsers.get(0).getUid());
						lApproves.get(0).setUname(lUsers.get(0).getName());
						lApproves.get(0).setDid(lUsers.get(0).getDid());
						lApproves.get(0).setDname(lUsers.get(0).getDepartment().getName());
					}					
				}
				
			}
			stamp.setApprove(lApproves);
		}
		stamp.setApproveHis(approveHisDAO.getApproveHisByTradeId(stamp.getId()));
		return stamp;
	}

	@Override
	public String getMaxCode() {
		Date d=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMM");
		String hql="SELECT MAX(P.applicationCode) FROM Stamp P where SUBSTR(P.applicationCode,2,6)='"+sdf.format(d)+"'";
		List list= commonDAO.queryByHql(hql);
		if (list.size()>0) { 
			String mcode=(String)list.get(0);
			if (mcode!=null&&!mcode.equals("")) {
				
				return  "S"+String.valueOf(Long.valueOf(mcode.replace("S", ""))+1);
			}		
		}
			return "S"+sdf.format(d)+"0001";
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void approveStamp(Stamp stamp) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void rejectStamp(Stamp stamp) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Stamp> getStampByHql(String hql, Integer pageSize, Integer pageCurrent) {
		return stamDAO.queryHql(hql, pageSize, pageCurrent);
	}

	@Override
	public List<Stamp> getStampByHql(String hql) {
		return stamDAO.queryHql(hql);
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void updateStamp(Stamp stamp) {
		if (stamp.getState().equals("1")) {
			if (stamp.getProjectResponsible()!=null&&!stamp.getProjectResponsible().equals("")) {
				stamp.setNextApprover(stamp.getProjectResponsible());
			}else{
				Dict approveType=dictDAO.query(" where id='"+stamp.getDocumentType()+"'").get(0);		
				Approve approve=(Approve)approveBIZ.query(" where id='"+approveType.getValueExplain()+"' and level=0 ").get(0);
				stamp.setNextApprover(approve.getUid());
			}
		}
		stamDAO.update(stamp);
		logUtil.logInfo("用章申请单更新成功："+stamp.getApplicationCode());
		
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void deleteStamp(Stamp stamp) {
		stamDAO.delete(stamp);
		logUtil.logInfo("用章申请单删除成功："+stamp.getApplicationCode());
		
	}

	
}
