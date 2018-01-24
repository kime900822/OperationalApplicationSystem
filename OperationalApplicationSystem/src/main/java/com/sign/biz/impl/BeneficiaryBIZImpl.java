package com.sign.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sign.biz.BeneficiaryBIZ;
import com.sign.dao.BeneficiaryDAO;
import com.sign.model.Beneficiary;

@Service
@Transactional(readOnly=true)
public class BeneficiaryBIZImpl implements BeneficiaryBIZ {
	
	@Autowired
	private BeneficiaryDAO beneficiaryDAO;
	
	
	
	public BeneficiaryDAO getBeneficiaryDAO() {
		return beneficiaryDAO;
	}

	public void setBeneficiaryDAO(BeneficiaryDAO beneficiaryDAO) {
		this.beneficiaryDAO = beneficiaryDAO;
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void saveBeneficiary(Beneficiary beneficiary) {
		beneficiaryDAO.save(beneficiary);
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void deleteBeneficiary(Beneficiary beneficiary) {
		beneficiaryDAO.delete(beneficiary);
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void updateBeneficiary(Beneficiary beneficiary) {
		beneficiaryDAO.update(beneficiary);
	}

	@Override
	public List<Beneficiary> queryBeneficiary(String where) {
		return beneficiaryDAO.query(where);
	}

	@Override
	public List<Beneficiary> queryBeneficiary(String where, int pageSize, int pageCurrent) {
		return beneficiaryDAO.query(where, pageSize, pageCurrent);
	}

}
