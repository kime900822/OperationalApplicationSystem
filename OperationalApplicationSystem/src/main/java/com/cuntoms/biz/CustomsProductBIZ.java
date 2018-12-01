package com.cuntoms.biz;

import java.io.ByteArrayOutputStream;
import java.util.List;

import com.cuntoms.model.CustomsProduct;

public interface CustomsProductBIZ {


	public String CusomsHandingOK(List<CustomsProduct> list);
	
	public String CusomsHandingNO(List<CustomsProduct> list);
	
	public List<CustomsProduct> query(String where);
	
	public List<CustomsProduct> query(String where, int pageSize, int pageCurrent);
	
	
}
