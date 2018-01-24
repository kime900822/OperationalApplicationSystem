package com.analysis.biz;

import java.util.List;

import com.analysis.model.IrrecoverableVat;

public interface IrrecoverableVatBIZ {

	public List GetIrrecoverableVat(String where);
	
	public void SaveIrrecoverableVat(List<IrrecoverableVat> lIrrecoverableVats);
	
	public void DeleteIrrecoverableVat(IrrecoverableVat irrecoverableVat);
	
	public void ImportIrrecoverableVat(List<IrrecoverableVat> lIrrecoverableVats);
	
	public void DeleteIrrecoverableVat(String where);
}
