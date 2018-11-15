package com.analysis.biz.impl;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.analysis.biz.CustomsRecordsBIZ;
import com.analysis.dao.CustomsRecordsDAO;
import com.analysis.model.CustomsRecords;
import com.analysis.model.Instruction;
import com.kime.base.BizBase;
import com.kime.infoenum.Message;
import com.kime.model.User;
import com.kime.utils.CommonUtil;
import com.kime.utils.ExcelUtil;

@Service
@Transactional(readOnly=true)
public class CustomsRecordsBIZImpl extends BizBase implements CustomsRecordsBIZ {

	@Autowired
	CustomsRecordsDAO customsRecordsDAO;
	
	
	@Override
	public List query(String where) {
		return customsRecordsDAO.Query(where);
	}


	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void delete(String where) {
		customsRecordsDAO.Delete(where);
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void fileToData(User user,File file, String first, String upfileFileName, int start) throws Exception {

		try {
			List<CustomsRecords> lCustomsRecords=ExcelUtil.FileToList(new CustomsRecords().getClass(),file,first,upfileFileName,start);
			if (lCustomsRecords.size()>0) {
				customsRecordsDAO.Delete(" where date='"+lCustomsRecords.get(0).getDate()+"' ");
				for (CustomsRecords customsRecords : lCustomsRecords) {
					customsRecords.setOperator(user.getName());
					customsRecords.setCreateDate(CommonUtil.getDate());
					customsRecordsDAO.Save(customsRecords);
				}
			}else{
				throw new Exception("No data!");
			}
			
		} catch (Exception e) {
			logUtil.logError("海关文件导入报错："+e.getMessage());
			throw new Exception("海关文件导入报错："+e.getMessage());
		}
		
		
		
	}

	
	
}
