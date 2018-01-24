package com.sign.biz;

import java.util.List;

import com.sign.model.Beneficiary;

public interface BeneficiaryBIZ {
	public void saveBeneficiary(Beneficiary beneficiary);
	
	public void deleteBeneficiary(Beneficiary beneficiary);
	
	public void updateBeneficiary(Beneficiary beneficiary);
	
	public List<Beneficiary> queryBeneficiary(String where);
	
	public List<Beneficiary> queryBeneficiary(String where, int pageSize, int pageCurrent);
}
