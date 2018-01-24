package com.analysis.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.analysis.biz.SalesBIZ;
import com.analysis.dao.SalesDAO;
import com.analysis.model.Sales;

@Service
@Transactional(readOnly=true)
public class SalesBIZImpl implements SalesBIZ {
	
	@Autowired
	SalesDAO salesDao;

	@Override
	public List GetSales(String where) {
		return salesDao.Query(where);
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void SaveSales(List<Sales> lSales) {
		for (Sales sales : lSales) {
			salesDao.Save(sales);
		}
		
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void DeleteSales(Sales sales) {
		salesDao.Delete(sales);
		
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void ImportSales(List<Sales> lSales) { 
			for (int i = 0; i < lSales.size(); i++) {
					salesDao.Save(lSales.get(i));
			
			}

	
		
	}

	

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void DeleteSales(String where) {
		// TODO Auto-generated method stub
		salesDao.Delete(where);
	}
	
	
	
}
