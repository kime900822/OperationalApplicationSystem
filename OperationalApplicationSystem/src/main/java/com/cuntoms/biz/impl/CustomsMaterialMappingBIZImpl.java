package com.cuntoms.biz.impl;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
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
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public String delete(List<CustomsMaterialMapping> list) {
		try {
			for (CustomsMaterialMapping customsMaterialMapping : list) {
				customsMaterialMappingDAO.delete(customsMaterialMapping);
				logUtil.logInfo(CustomsMaterialMappinglHelp.title,"删除成功：旧料号"+customsMaterialMapping.getOldMaterialNo()+" 新料号："+customsMaterialMapping.getNewMaterialNo());
			}
			return null;
		} catch (Exception e) {
			logUtil.logError(CustomsMaterialMappinglHelp.title,"删除报错："+e.getMessage());
			return "删除报错："+e.getMessage();
		}
		
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public String update(CustomsMaterialMapping cMapping) {
		try {
			customsMaterialMappingDAO.update(cMapping);
			logUtil.logInfo(CustomsMaterialMappinglHelp.title,"更新成功：旧料号"+cMapping.getOldMaterialNo()+" 新料号："+cMapping.getNewMaterialNo());
			return null;
		} catch (Exception e) {
			logUtil.logError(CustomsMaterialMappinglHelp.title,"更新报错："+e.getMessage());
			return "更新报错："+e.getMessage();
		}
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public String save(CustomsMaterialMapping cMapping) {
		try {
			List list=customsMaterialMappingDAO.query(" where oldMaterialNo='"+cMapping.getOldMaterialNo()+"'");
			if (list.size()>0) {
				logUtil.logInfo(CustomsMaterialMappinglHelp.title,"新增失败：旧料号"+cMapping.getOldMaterialNo()+"️已存在");
				return "新增失败：旧料号"+cMapping.getOldMaterialNo()+"️已存在";
			}
			customsMaterialMappingDAO.save(cMapping);
			logUtil.logInfo(CustomsMaterialMappinglHelp.title,"新增成功：旧料号"+cMapping.getOldMaterialNo()+" 新料号："+cMapping.getNewMaterialNo());
			return null;
		} catch (Exception e) {
			logUtil.logError(CustomsMaterialMappinglHelp.title,"新增报错："+e.getMessage());
			return "新增报错："+e.getMessage();
		}
	}

	
}
