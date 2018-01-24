package com.analysis.biz;

import java.util.List;

import org.springframework.stereotype.Service;

import com.analysis.model.Sales;

@Service
public interface SalesBIZ {

	public List GetSales(String where);
	
	public void SaveSales(List<Sales> lSales);
	
	public void DeleteSales(Sales sales);
	
	public void ImportSales(List<Sales> lSales);
	
	public void DeleteSales(String where);
}
