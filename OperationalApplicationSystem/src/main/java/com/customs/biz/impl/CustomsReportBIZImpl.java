package com.customs.biz.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customs.biz.CustomsReportBIZ;
import com.customs.model.CustomsMaterial;
import com.customs.model.CustomsReport1;
import com.customs.model.CustomsReport2;
import com.kime.base.BizBase;
import com.kime.dao.CommonDAO;
import com.kime.model.HeadColumn;
import com.kime.utils.ExcelUtil;

@Service
public class CustomsReportBIZImpl extends BizBase implements CustomsReportBIZ {

	@Autowired
	CommonDAO commonDAO;
	
	@Override
	public List queryReport2(String where) {
		String hql=" select A.orderNumber, "
				+ " A.cimtasCode,"
				+ " coalesce(C.newMaterialNo,A.cimtasCode) as cimtasCode, "
				+ " A.quantity,"
				+ " B.transQTY,"
				+ " D.transQTY,"
				+ " coalesce(D.transQTY,0)-A.quantity AS DValue "
				+ " from CustomsImportsAndExports A "
				+ " left join CustomsJDE B "
				+ " on A.cimtasCode=B.cimtasLongItemNo "
				+ " left join CustomsMaterialMapping C on "
				+ " C.oldMaterialNo=A.cimtasCode "
				+ " left join CustomsJDE D "
				+ " on coalesce(C.newMaterialNo,A.cimtasCode)=D.cimtasLongItemNo "+where;
		return commonDAO.queryByHql(hql);
	}

	@Override
	public List queryReport2(String where, int pageSize, int pageCurrent) {
		String hql=" select A.orderNumber, "
				+ " A.cimtasCode,"
				+ " coalesce(C.newMaterialNo,A.cimtasCode) as cimtasCode, "
				+ " A.quantity,"
				+ " B.transQTY,"
				+ " D.transQTY,"
				+ " coalesce(D.transQTY,0)-A.quantity AS DValue "
				+ " from CustomsImportsAndExports A "
				+ " left join CustomsJDE B "
				+ " on A.cimtasCode=B.cimtasLongItemNo "
				+ " left join CustomsMaterialMapping C on "
				+ " C.oldMaterialNo=A.cimtasCode "
				+ " left join CustomsJDE D "
				+ " on coalesce(C.newMaterialNo,A.cimtasCode)=D.cimtasLongItemNo "+ where;
		List list=commonDAO.queryByHql(hql, pageSize, pageCurrent);
		List<CustomsReport2> lReport2s=new ArrayList<>();
		for (Object object : list) {
			Object[] tmp= (Object[]) object;
			CustomsReport2 customsReport2=new CustomsReport2();
			customsReport2.setOrderNumber((String)tmp[0]);
			customsReport2.setCimtasLongItemNo((String)tmp[1]);
			customsReport2.setNewCimtasLongItemNo((String)tmp[2]);
			customsReport2.setCustomsQuality((String)tmp[3]);
			customsReport2.setJEDTransQty((String)tmp[4]);
			customsReport2.setTransQTY((String)tmp[5]);
			customsReport2.setDvalue((String)tmp[6]);
			lReport2s.add(customsReport2);
		}
		return lReport2s;
	}

	@Override
	public List queryReport1(String where) {
		String hql=" select B.no,A.no,'1' ,A.quantityOrdered,"
				+ " case when B.declareUnitCode='030' then 0.03 else 0 end,"
				+ "'0',"
				+ "'',"
				+ "'100',"
				+ "'3',"
				+ "'20420701',"
				+ "'',"
				+ "A.batchNumber,"
				+ "A.quantityOrdered*(1+(case when B.declareUnitCode='030' then 0.03 else 0 end))/1000, "
				+ "'' "
				+ " from CustomsClearance A "
				+ " left join CustomsProduct B "
				+ " on A.shipmentIems=B.materialNo"
				+ where;
		return commonDAO.queryByHql(hql);
	}

	@Override
	public List queryReport1(String where, int pageSize, int pageCurrent) {
		String hql=" select B.no,A.no,'1' ,A.quantityOrdered,"
				+ " case when B.declareUnitCode='030' then 0.03 else 0 end,"
				+ "'0',"
				+ "'',"
				+ "'100',"
				+ "'3',"
				+ "'20420701',"
				+ "'',"
				+ "A.batchNumber,"
				+ "A.quantityOrdered*(1+(case when B.declareUnitCode='030' then 0.03 else 0 end))/1000, "
				+ "'' "
				+ " from CustomsClearance A "
				+ " left join CustomsProduct B "
				+ " on A.shipmentIems=B.materialNo"
				+ where;
		
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
			customsReport1.setColumen13(String.valueOf((Double)tmp[12]));
			customsReport1.setColumen14((String)tmp[13]);
			lReport1s.add(customsReport1);
		}
		return lReport1s;
	}
	
	@Override
	public ByteArrayInputStream exportData(String hql, List<HeadColumn> lHeadColumns,String title) throws Exception {
		
		List list  = commonDAO.queryByHql(hql);
    	Class c = (Class) new CustomsMaterial().getClass();  
    	ByteArrayOutputStream os = ExcelUtil.exportExcel(title, c, list, "yyy-MM-dd",lHeadColumns);
    	byte[] fileContent = os.toByteArray();
    	return new ByteArrayInputStream(fileContent);	
	}
	
}
