package com.analysis.dao;

import java.util.List;

import com.analysis.model.IrrecoverableVat;

public interface IrrecoverableVatDAO {
	public List Query(String where);
	
	public void Save(IrrecoverableVat irrecoverableVat);
	
	public void Delete(IrrecoverableVat irrecoverableVat);
	
	public void Delete(String where);
}
