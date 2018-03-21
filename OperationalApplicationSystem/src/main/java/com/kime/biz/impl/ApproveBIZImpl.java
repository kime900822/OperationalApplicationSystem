package com.kime.biz.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kime.base.BizBase;
import com.kime.biz.ApproveBIZ;
import com.kime.dao.ApproveDAO;
import com.kime.dao.CommonDAO;
import com.kime.model.Approve;

@Service
@Transactional(readOnly=true)
public class ApproveBIZImpl extends BizBase implements ApproveBIZ {
	
	@Autowired
	ApproveDAO approveDAO;
	@Autowired
	CommonDAO commonDAO;
	public ApproveDAO getApproveDAO() {
		return approveDAO;
	}

	public void setApproveDAO(ApproveDAO approveDAO) {
		this.approveDAO = approveDAO;
	}


	public CommonDAO getCommonDAO() {
		return commonDAO;
	}

	public void setCommonDAO(CommonDAO commonDAO) {
		this.commonDAO = commonDAO;
	}

	@Override
	public List getAllApprove() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getParentApprove() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getChildApprove(String parentID) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	@Override
	public List getApproveAndChild(String id) {		
		List<Approve> lApproves=new ArrayList<>();
		Approve approve=approveDAO.getApproveByID(id);
		if (approve!=null) {
			lApproves=getChileApprove(approve);
		}
		return lApproves;
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public String delete(List<Approve> approves) {
		String log="";
		try {
			for (Approve approve : approves) {
				List<Approve> list=approveDAO.query(" where ParentID='"+approve.getId()+"' or ID= '"+approve.getId()+"' ");
				for (Approve dApprove : list) {
					approveDAO.delete(dApprove);
					log+=" 删除签核步骤成功:"+dApprove.getType()+" "+dApprove.getUname()+" /r/n";
				}
				
			}
			logUtil.logInfo(log);
		} catch (Exception e) {
			logUtil.logInfo(e.getMessage());
			return e.getMessage();
		}
		
		return null;
		
		
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public String update(List<Approve> approve) {
		String msg="";
		try {
			for (Approve a : approve) {
				if (a.getId()==null||a.getId().equals("")) {
					approveDAO.save(a);
					msg+=" 新增签核人:"+a.getType()+" "+a.getUname()+" /r/n";
				}else{
					approveDAO.update(a);
					msg+=" 更新签核人:"+a.getType()+" "+a.getUname()+" /r/n";
				}
				logUtil.logInfo(msg);
			}
		} catch (Exception e) {
			logUtil.logInfo(e.getMessage());
			return e.getMessage();
		}
		return null;
	}

	@Override
	public Approve getApproveById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List query(String where) {
		return approveDAO.query(where);
	}

	@Override
	public List query(String where, int pageSize, int pageCurrent) {
		return approveDAO.query(where, pageSize, pageCurrent);
	}

	
	@Override
	public List getFirstApproveOfStamp4Select() {
		String hql="select U from User U WHERE U.uid IN(select A.uid from Approve A,Dict D where A.id=D.valueExplain and D.key='STAMP') ) ";
		return commonDAO.queryByHql(hql);
	}

	public List<Approve> getChileApprove(Approve approve){
		List<Approve> lout=new ArrayList<>();
		lout.add(approve);
		List<Approve> lchild=approveDAO.getApproveByParentID(approve.getId());
		if (lchild.size()>0) {
			for (Approve cApprove : lchild) {
				lout.addAll(getChileApprove(cApprove));
			}
		}
		
		return lout;
		
	}
	
}
