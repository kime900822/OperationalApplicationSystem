package com.cuntoms.biz.impl;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cuntoms.biz.CustomsMaterialMappingBIZ;
import com.cuntoms.dao.CustomsMaterialMappingDAO;
import com.cuntoms.model.CustomsJDE;
import com.cuntoms.model.CustomsMaterialMapping;
import com.cuntoms.other.CustomsJDEHelp;
import com.cuntoms.other.CustomsMaterialMappinglHelp;
import com.kime.base.BizBase;
import com.kime.model.User;
import com.kime.utils.CommonUtil;
import com.kime.utils.ExcelUtil;

@Service
@Transactional(readOnly=true)
public class CustomsMaterialMappingBIZImpl extends BizBase implements CustomsMaterialMappingBIZ {

	@Autowired
	CustomsMaterialMappingDAO customsMaterialMappingDAO;
	
	
	@Override
	public void importData(User user, File file, String first, String upfileFileName, int start) throws Exception {
		try {
			List<CustomsMaterialMapping> list=ExcelUtil.FileToList(new CustomsMaterialMapping().getClass(),file,first,upfileFileName,start);
			if (list.size()>0) {
				for (CustomsMaterialMapping cMapping : list) {
					customsMaterialMappingDAO.save(cMapping);
				}
				
			}else{
				logUtil.logError(CustomsMaterialMappinglHelp.title,"导入文件没数据");
				throw new Exception("No data!");
			}
			
			logUtil.logInfo(CustomsMaterialMappinglHelp.title,"导入成功！");
		} catch (Exception e) {
			logUtil.logError(CustomsMaterialMappinglHelp.title,"导入报错："+e.getMessage());
			throw new Exception("导入报错："+e.getMessage());
		}
		
	}

	@Override
	public List<CustomsMaterialMapping> query(String where) {
		return customsMaterialMappingDAO.query(where);
	}

	@Override
	public List<CustomsMaterialMapping> query(String where, int pageSize, int pageCurrent) {
		return customsMaterialMappingDAO.query(where, pageSize, pageCurrent);
	}

	@Override
	public String delete(CustomsMaterialMapping cMapping) {
		try {
			customsMaterialMappingDAO.delete(cMapping);
			logUtil.logInfo(CustomsMaterialMappinglHelp.title,"删除成功：旧料号"+cMapping.getOldMaterialNo()+" 新料号："+cMapping.getNewMaterialNo());
			return null;
		} catch (Exception e) {
			logUtil.logError(CustomsMaterialMappinglHelp.title,"删除报错："+e.getMessage());
			return "删除报错："+e.getMessage();
		}
		
	}

	@Override
	public String update(CustomsMaterialMapping cMapping) {
		try {
			customsMaterialMappingDAO.delete(cMapping);
			logUtil.logInfo(CustomsMaterialMappinglHelp.title,"更新成功：旧料号"+cMapping.getOldMaterialNo()+" 新料号："+cMapping.getNewMaterialNo());
			return null;
		} catch (Exception e) {
			logUtil.logError(CustomsMaterialMappinglHelp.title,"更新报错："+e.getMessage());
			return "更新报错："+e.getMessage();
		}
	}

	@Override
	public String save(CustomsMaterialMapping cMapping) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
