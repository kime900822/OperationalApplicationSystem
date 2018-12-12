package com.cuntoms.biz.impl;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cuntoms.biz.CustomsGeneralBIZ;
import com.cuntoms.dao.CustomsGeneralDAO;
import com.cuntoms.model.CustomsGeneral;
import com.cuntoms.model.CustomsGeneralInit;
import com.cuntoms.model.CustomsJDE;
import com.cuntoms.other.CustomsGeneralHelp;
import com.cuntoms.other.CustomsJDEHelp;
import com.kime.base.BizBase;
import com.kime.dao.CommonDAO;
import com.kime.model.User;
import com.kime.utils.CommonUtil;
import com.kime.utils.ExcelUtil;

@Service
@Transactional(readOnly=true)
public class CustomsGeneralBIZImpl  extends BizBase implements CustomsGeneralBIZ{

	@Autowired
	CustomsGeneralDAO customsGeneralDAO;
	@Autowired
	CommonDAO commonDAO;
	
	@Override
	public List<CustomsGeneral> query(String where) {
		return customsGeneralDAO.query(where);
	}

	@Override
	public List<CustomsGeneral> query(String where, int pageSize, int pageCurrent) {
		return customsGeneralDAO.query4init(where, pageSize, pageCurrent);
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
	

	@Override
	public List<CustomsGeneralInit> query4init(String where) {
		return customsGeneralDAO.query4init(where);
	}

	@Override
	public List<CustomsGeneralInit> query4init(String where, int pageSize, int pageCurrent) {
		return customsGeneralDAO.query4init(where, pageSize, pageCurrent);
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void initData(User user, File file, String first, String upfileFileName, int start) throws Exception {
		try {
			List<CustomsGeneralInit> list=ExcelUtil.FileToList(new CustomsGeneralInit().getClass(),file,first,upfileFileName,start);
			if (list.size()>0) {
				commonDAO.executeHQL(" delete from CustomsGeneralInit ");
				for (CustomsGeneralInit generalInit : list) {
					generalInit.setNo(CommonUtil.spaceToNull(generalInit.getNo()));
					generalInit.setEarlyNumber(CommonUtil.spaceToNull(generalInit.getEarlyNumber()));
					generalInit.setIncomingVolume(CommonUtil.spaceToNull(generalInit.getIncomingVolume()));
					generalInit.setWriteOffVolume(CommonUtil.spaceToNull(generalInit.getWriteOffVolume()));
					generalInit.setRegulatoryInventory(CommonUtil.spaceToNull(generalInit.getRegulatoryInventory()));
					
					customsGeneralDAO.save(generalInit);
				}
				
			}else{
				logUtil.logError(CustomsGeneralHelp.title4init,"导入文件没数据");
				throw new Exception("No data!");
			}
			
			logUtil.logInfo(CustomsGeneralHelp.title4init,"导入成功！");
		} catch (Exception e) {
			logUtil.logError(CustomsGeneralHelp.title4init,"导入报错："+e.getMessage());
			throw new Exception("导入报错："+e.getMessage());
		}
	}

	
}
