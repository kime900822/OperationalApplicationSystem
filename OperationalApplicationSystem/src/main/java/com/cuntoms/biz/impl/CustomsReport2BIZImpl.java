package com.cuntoms.biz.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cuntoms.biz.CustomsReport2BIZ;
import com.cuntoms.model.CustomsReport2;
import com.kime.base.BizBase;
import com.kime.dao.CommonDAO;

@Service
public class CustomsReport2BIZImpl extends BizBase implements CustomsReport2BIZ {

	@Autowired
	CommonDAO commonDAO;
	
	@Override
	public List query(String where) {
		String hql=" select A.orderNumber,A.cimtasLongItemNo,A.quantity,B.transQTY,B.transQTY-A.quantity AS DValue from CustomsImportsAndExports A "
				+ " left join CustomsJDE B "
				+ " on A.orderNumber=B.orderNumber"
				+ " and A.cimtasLongItemNo=B.cimtasLongItemNo ";
		return commonDAO.queryByHql(hql);
	}

	@Override
	public List query(String where, int pageSize, int pageCurrent) {
		String hql=" select A.orderNumber,A.cimtasLongItemNo,A.quantity,B.transQTY,B.transQTY-A.quantity AS DValue from CustomsImportsAndExports A "
				+ " left join CustomsJDE B "
				+ " on A.orderNumber=B.orderNumber"
				+ " and A.cimtasLongItemNo=B.cimtasLongItemNo ";
		List list=commonDAO.queryByHql(hql, pageSize, pageCurrent);
		List<CustomsReport2> lReport2s=new ArrayList<>();
		for (Object object : list) {
			Object[] tmp= (Object[]) object;
			CustomsReport2 customsReport2=new CustomsReport2();
			customsReport2.setOrderNumber((String)tmp[0]);
			customsReport2.setCimtasLongItemNo((String)tmp[1]);
			customsReport2.setQuantity((String)tmp[2]);
			customsReport2.setTransQTY((String)tmp[3]);
			customsReport2.setDvalue((String)tmp[4]);
			lReport2s.add(customsReport2);
		}
		return lReport2s;
	}
}
