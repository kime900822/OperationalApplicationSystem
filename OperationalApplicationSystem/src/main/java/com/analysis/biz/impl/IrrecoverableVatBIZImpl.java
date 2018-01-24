package com.analysis.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.analysis.biz.IrrecoverableVatBIZ;
import com.analysis.dao.IrrecoverableVatDAO;
import com.analysis.model.IrrecoverableVat;

@Service
@Transactional(readOnly=true)
public class IrrecoverableVatBIZImpl implements IrrecoverableVatBIZ {
	
	@Autowired
	IrrecoverableVatDAO irrecoverableVatDao;

	@Override
	public List GetIrrecoverableVat(String where) {
		return irrecoverableVatDao.Query(where);
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void SaveIrrecoverableVat(List<IrrecoverableVat> lIrrecoverableVats) {
		for (IrrecoverableVat irrecoverableVat : lIrrecoverableVats) {
			irrecoverableVatDao.Save(irrecoverableVat);
		}
		
		
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void DeleteIrrecoverableVat(IrrecoverableVat irrecoverableVat) {
		irrecoverableVatDao.Delete(irrecoverableVat);
		
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void ImportIrrecoverableVat(List<IrrecoverableVat> lIrrecoverableVats) {
		for (int i = 0; i < lIrrecoverableVats.size(); i++) {
			irrecoverableVatDao.Save(lIrrecoverableVats.get(i));
		}
			
		
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void DeleteIrrecoverableVat(String where) {
		irrecoverableVatDao.Delete(where);
		
	}


}
