package com.cuntoms.biz;

import java.io.ByteArrayOutputStream;
import java.util.List;

import com.cuntoms.model.CustomsProduct;

public interface CustomsProductBIZ {

	
	public String ImportDate(List<CustomsProduct> list);
	
	public ByteArrayOutputStream ExportDate(String where);
	
	public String CusomsHandingOK(List<CustomsProduct> list);
	
	public String CusomsHandingNO(List<CustomsProduct> list);
}
