package com.cuntoms.biz.impl;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cuntoms.biz.CUstomsJDEBIZ;
import com.cuntoms.dao.CustomsJDEDAO;
import com.cuntoms.model.CustomsJDE;
import com.cuntoms.model.CustomsMaterial;
import com.cuntoms.other.CustomsJDEHelp;
import com.kime.base.BizBase;
import com.kime.model.User;
import com.kime.utils.CommonUtil;
import com.kime.utils.ExcelUtil;

@Service
@Transactional(readOnly=true)
public class CUstomsJDEBIZImpl extends BizBase implements CUstomsJDEBIZ {

	@Autowired
	CustomsJDEDAO customsJDFDAO;
	
	
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void importData(User user, File file, String first, String upfileFileName, int start) throws Exception {
		try {
			List<CustomsJDE> lCustomsJDEs=ExcelUtil.FileToList(new CustomsJDE().getClass(),file,first,upfileFileName,start);
			if (lCustomsJDEs.size()>0) {
				for (CustomsJDE customsJDE : lCustomsJDEs) {
					customsJDE.setOperationDate(CommonUtil.getDate());
					customsJDFDAO.save(customsJDE);
				}
				
			}else{
				logUtil.logError(CustomsJDEHelp.title,"导入文件没数据");
				throw new Exception("No data!");
			}
			
			logUtil.logInfo(CustomsJDEHelp.title,"导入成功！");
		} catch (Exception e) {
			logUtil.logError(CustomsJDEHelp.title,"导入报错："+e.getMessage());
			throw new Exception("导入报错："+e.getMessage());
		}
		
	}

	@Override
	public List<CustomsMaterial> query(String where) {
		return customsJDFDAO.query(where);
	}

	@Override
	public List<CustomsMaterial> query(String where, int pageSize, int pageCurrent) {
		return customsJDFDAO.query(where, pageSize, pageCurrent);
	}
	
	

}
