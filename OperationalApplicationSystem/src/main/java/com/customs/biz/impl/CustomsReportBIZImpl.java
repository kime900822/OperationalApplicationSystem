package com.customs.biz.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customs.biz.CustomsReportBIZ;
import com.customs.model.CustomsReport1;
import com.customs.model.CustomsReport2;
import com.kime.base.BizBase;
import com.kime.dao.CommonDAO;

@Service
public class CustomsReportBIZImpl extends BizBase implements CustomsReportBIZ {

	@Autowired
	CommonDAO commonDAO;
	
	@Override
	public List queryReport2(String where) {
		String hql=" select A.orderNumber,A.CimtasCode,A.quantity,B.transQTY,B.transQTY-A.quantity AS DValue from CustomsImportsAndExports A "
				+ " left join CustomsJDE B "
				+ " on A.orderNumber=B.orderNumber"
				+ " and A.CimtasCode=B.cimtasLongItemNo ";
		return commonDAO.queryByHql(hql);
	}

	@Override
	public List queryReport2(String where, int pageSize, int pageCurrent) {
		String hql=" select A.orderNumber,A.CimtasCode,A.quantity,B.transQTY,B.transQTY-A.quantity AS DValue from CustomsImportsAndExports A "
				+ " left join CustomsJDE B "
				+ " on A.orderNumber=B.orderNumber"
				+ " and A.CimtasCode=B.cimtasLongItemNo ";
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

	@Override
	public List queryReport1(String where) {
		String hql=" select B.no,A.no,'' ,A.quantityOrdered,case when B.declareUnitCode='030' then 0.03 else 0 end,'','','','','','',A.batchNumber,'' "
				+ " from CustomsClearance A "
				+ " left join CustomsProduct B "
				+ " on A.shipmentIems=B.materialNo";
		return commonDAO.queryByHql(hql);
	}

	@Override
	public List queryReport1(String where, int pageSize, int pageCurrent) {
		String hql=" select B.no,A.no,'' ,A.quantityOrdered,case when B.declareUnitCode='030' then 0.03 else 0 end,'','','','','','',A.batchNumber,'' "
				+ " from CustomsClearance A "
				+ " left join CustomsProduct B "
				+ " on A.shipmentIems=B.materialNo";
		
		List list=commonDAO.queryByHql(hql, pageSize, pageCurrent);
		List<CustomsReport1> lReport1s=new ArrayList<>();
		for (Object object : list) {
			Object[] tmp= (Object[]) object;
			CustomsReport1 customsReport1=new CustomsReport1();
			customsReport1.setColumen1((String)tmp[0]);
			customsReport1.setColumen2((String)tmp[1]);
			customsReport1.setColumen3((String)tmp[2]);
			customsReport1.setColumen4((String)tmp[3]);
			customsReport1.setColumen5(String.valueOf(tmp[4]));
			customsReport1.setColumen6(String.valueOf(tmp[5]));
			customsReport1.setColumen7((String)tmp[6]);
			customsReport1.setColumen8((String)tmp[7]);
			customsReport1.setColumen9((String)tmp[8]);
			customsReport1.setColumen10((String)tmp[9]);
			customsReport1.setColumen11((String)tmp[10]);
			customsReport1.setColumen12((String)tmp[11]);
			customsReport1.setColumen13((String)tmp[12]);
			lReport1s.add(customsReport1);
		}
		return lReport1s;
	}
	
	
	
}
