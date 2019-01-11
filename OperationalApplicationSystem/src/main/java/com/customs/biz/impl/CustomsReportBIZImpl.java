package com.customs.biz.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Method;
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
	public List queryReport(String sql) {
		return commonDAO.queryBySql(sql);
	}

	@Override
	public List queryReport(String sql, int pageSize, int pageCurrent,String method) throws Exception {
		List list=commonDAO.queryBySql(sql, pageSize, pageCurrent);
        Method getMethod = this.getClass().getMethod(method, List.class);
		return (List) getMethod.invoke(this.getClass(), list);
	}
	
	public static List<CustomsReport1> dataToListR1(List list){
		List<CustomsReport1> lReport1s=new ArrayList<>();
		for (Object object : list) {
			Object[] tmp= (Object[]) object;
			CustomsReport1 customsReport1=new CustomsReport1();
			customsReport1.setColumen1(String.valueOf(tmp[0]));
			customsReport1.setColumen2(String.valueOf(tmp[1]));
			customsReport1.setColumen3((String)tmp[2]);
			customsReport1.setColumen4(String.valueOf(tmp[3]));
			customsReport1.setColumen5((String)tmp[4]);
			customsReport1.setColumen6((String)tmp[5]);
			customsReport1.setColumen7((String)tmp[6]);
			customsReport1.setColumen8((String)tmp[7]);
			customsReport1.setColumen9((String)tmp[8]);
			customsReport1.setColumen10((String)tmp[9]);
			customsReport1.setColumen11((String)tmp[10]);
			customsReport1.setColumen12((String)tmp[11]);
			customsReport1.setColumen13(String.valueOf(tmp[12]));
			customsReport1.setColumen14((String)tmp[13]);
			lReport1s.add(customsReport1);
		}
		return lReport1s;
		
		
	}
	
	public static List<CustomsReport2> dataToListR2(List list){
		List<CustomsReport2> lReport2s=new ArrayList<>();
		for (Object object : list) {
			Object[] tmp= (Object[]) object;
			CustomsReport2 customsReport2=new CustomsReport2();
			customsReport2.setOrderNumber((String)tmp[0]);
			customsReport2.setCimtasLongItemNo((String)tmp[1]);
			customsReport2.setNewCimtasLongItemNo((String)tmp[2]);
			customsReport2.setCustomsQuality(String.valueOf(tmp[3]));
			customsReport2.setJEDTransQty(String.valueOf(tmp[4]));
			customsReport2.setDvalue(String.valueOf(tmp[5]));
			lReport2s.add(customsReport2);
		}
		return lReport2s;
	}
	
	
	@Override
	public ByteArrayInputStream exportData(String sql, List<HeadColumn> lHeadColumns,String title, Class class1) throws Exception {
		
		List list  = commonDAO.queryBySql(sql);
    	ByteArrayOutputStream os = ExcelUtil.exportExcel(title, class1, dataToListR1(list), "yyy-MM-dd",lHeadColumns);
    	byte[] fileContent = os.toByteArray();
    	return new ByteArrayInputStream(fileContent);	
	}
	
}
