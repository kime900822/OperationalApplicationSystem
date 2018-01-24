package com.analysis.biz.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.analysis.biz.CogsBIZ;
import com.analysis.dao.CogsDAO;
import com.analysis.model.COGS_Details;
import com.kime.utils.TypeChangeUtil;

@Service
@Transactional(readOnly=true)
public class CogsBIZImpl implements CogsBIZ {
	
	@Autowired
	CogsDAO cogsDao;
	
	public CogsDAO getCogsDao() {
		return cogsDao;
	}

	public void setCogsDao(CogsDAO cogsDao) {
		this.cogsDao = cogsDao;
	}
	

	@Override
	public List getCogs(String where,String rate) {
		List<COGS_Details> lCogs_Details=cogsDao.Query(where);
		if (!"".equals(rate)&&rate!=null) {
			for (int i = 0; i < lCogs_Details.size(); i++) {
				lCogs_Details.get(i).setConsumption_50(TypeChangeUtil.division( lCogs_Details.get(i).getConsumption_50(),rate));
				lCogs_Details.get(i).setFactory_Amortization_50(TypeChangeUtil.division(lCogs_Details.get(i).getFactory_Amortization_50(), rate));
				lCogs_Details.get(i).setGeneral_Administration_Cost_50(TypeChangeUtil.division(lCogs_Details.get(i).getGeneral_Administration_Cost_50(), rate));
				lCogs_Details.get(i).setGeneral_MFG_Expenses_50(TypeChangeUtil.division(lCogs_Details.get(i).getGeneral_MFG_Expenses_50(), rate));
				lCogs_Details.get(i).setGoods_Transport_50(TypeChangeUtil.division(lCogs_Details.get(i).getGoods_Transport_50(), rate));
				lCogs_Details.get(i).setIndirect_Labour_Cost_50(TypeChangeUtil.division(lCogs_Details.get(i).getIndirect_Labour_Cost_50(), rate));
				lCogs_Details.get(i).setLabour_Cost_50(TypeChangeUtil.division(lCogs_Details.get(i).getLabour_Cost_50(), rate));
				lCogs_Details.get(i).setMaterial_50(TypeChangeUtil.division(lCogs_Details.get(i).getMaterial_50(), rate));
				lCogs_Details.get(i).setOther_50(TypeChangeUtil.division(lCogs_Details.get(i).getOther_50(), rate));
				lCogs_Details.get(i).setResearch_Development_50(TypeChangeUtil.division(lCogs_Details.get(i).getResearch_Development_50(), rate));
				lCogs_Details.get(i).setSales_Marketing_50(TypeChangeUtil.division(lCogs_Details.get(i).getSales_Marketing_50(), rate));
				
				lCogs_Details.get(i).setConsumption_60(TypeChangeUtil.division( lCogs_Details.get(i).getConsumption_60(),rate));
				lCogs_Details.get(i).setFactory_Amortization_60(TypeChangeUtil.division(lCogs_Details.get(i).getFactory_Amortization_60(), rate));
				lCogs_Details.get(i).setGeneral_Administration_Cost_60(TypeChangeUtil.division(lCogs_Details.get(i).getGeneral_Administration_Cost_60(), rate));
				lCogs_Details.get(i).setGeneral_MFG_Expenses_60(TypeChangeUtil.division(lCogs_Details.get(i).getGeneral_MFG_Expenses_60(), rate));
				lCogs_Details.get(i).setGoods_Transport_60(TypeChangeUtil.division(lCogs_Details.get(i).getGoods_Transport_60(), rate));
				lCogs_Details.get(i).setIndirect_Labour_Cost_60(TypeChangeUtil.division(lCogs_Details.get(i).getIndirect_Labour_Cost_60(), rate));
				lCogs_Details.get(i).setLabour_Cost_60(TypeChangeUtil.division(lCogs_Details.get(i).getLabour_Cost_60(), rate));
				lCogs_Details.get(i).setMaterial_60(TypeChangeUtil.division(lCogs_Details.get(i).getMaterial_60(), rate));
				lCogs_Details.get(i).setOther_60(TypeChangeUtil.division(lCogs_Details.get(i).getOther_60(), rate));
				lCogs_Details.get(i).setResearch_Development_60(TypeChangeUtil.division(lCogs_Details.get(i).getResearch_Development_60(), rate));
				lCogs_Details.get(i).setSales_Marketing_60(TypeChangeUtil.division(lCogs_Details.get(i).getSales_Marketing_60(), rate));
				
				lCogs_Details.get(i).setConsumption_60(TypeChangeUtil.division( lCogs_Details.get(i).getConsumption_60(),rate));
				lCogs_Details.get(i).setFactory_Amortization_60(TypeChangeUtil.division(lCogs_Details.get(i).getFactory_Amortization_60(), rate));
				lCogs_Details.get(i).setGeneral_Administration_Cost_60(TypeChangeUtil.division(lCogs_Details.get(i).getGeneral_Administration_Cost_60(), rate));
				lCogs_Details.get(i).setGeneral_MFG_Expenses_60(TypeChangeUtil.division(lCogs_Details.get(i).getGeneral_MFG_Expenses_60(), rate));
				lCogs_Details.get(i).setGoods_Transport_60(TypeChangeUtil.division(lCogs_Details.get(i).getGoods_Transport_60(), rate));
				lCogs_Details.get(i).setIndirect_Labour_Cost_60(TypeChangeUtil.division(lCogs_Details.get(i).getIndirect_Labour_Cost_60(), rate));
				lCogs_Details.get(i).setLabour_Cost_60(TypeChangeUtil.division(lCogs_Details.get(i).getLabour_Cost_60(), rate));
				lCogs_Details.get(i).setMaterial_60(TypeChangeUtil.division(lCogs_Details.get(i).getMaterial_60(), rate));
				lCogs_Details.get(i).setOther_60(TypeChangeUtil.division(lCogs_Details.get(i).getOther_60(), rate));
				lCogs_Details.get(i).setResearch_Development_60(TypeChangeUtil.division(lCogs_Details.get(i).getResearch_Development_60(), rate));
				lCogs_Details.get(i).setSales_Marketing_60(TypeChangeUtil.division(lCogs_Details.get(i).getSales_Marketing_60(), rate));
				
			}
		}
		return lCogs_Details;
	}
	
}
