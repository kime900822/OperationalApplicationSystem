package com.cuntoms.biz.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cuntoms.biz.CustomsGeneralBIZ;
import com.cuntoms.model.CustomsGeneral;
import com.kime.base.BizBase;

@Service
@Transactional(readOnly=true)
public class CustomsGeneralBIZImpl  extends BizBase implements CustomsGeneralBIZ{

	@Override
	public List<CustomsGeneral> query(String where) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CustomsGeneral> query(String where, int pageSize, int pageCurrent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public String deleteByDate(String date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public String buildData() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
