package com.cuntoms.biz.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cuntoms.biz.CustomsImportsAndExportsBIZ;
import com.cuntoms.biz.CustomsMaterialBIZ;
import com.cuntoms.dao.CustomsImportsAndExportsDAO;
import com.cuntoms.model.CustomsImportsAndExports;
import com.cuntoms.model.CustomsMaterial;
import com.cuntoms.other.CustomsImportsAndExportsHelp;
import com.kime.base.BizBase;
import com.kime.dao.CommonDAO;
import com.kime.model.HeadColumn;
import com.kime.model.User;
import com.kime.utils.CommonUtil;
import com.kime.utils.ExcelUtil;


@Service
@Transactional(readOnly=true)
public class CustomsImportsAndExportsBIZImpl  extends BizBase implements CustomsImportsAndExportsBIZ{
	
	@Autowired
	CustomsImportsAndExportsDAO customsImportsAndExportsDAO;
	@Autowired
	CommonDAO commonDAO;
	@Autowired
	CustomsMaterialBIZ customsMaterialBIZ;

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void importData(User user, File file, String first, String upfileFileName, int start) throws Exception {
		try {
			List<CustomsImportsAndExports> lCustomsImportsAndExports=ExcelUtil.FileToList(new CustomsImportsAndExports().getClass(),file,first,upfileFileName,start);
			if (lCustomsImportsAndExports.size()>0) {
				for (CustomsImportsAndExports customsImportsAndExports : lCustomsImportsAndExports) {
					if (customsImportsAndExports.getEntryDate().equals("")) {
						logUtil.logError(CustomsImportsAndExportsHelp.title,"导入报错："+customsImportsAndExports.getOrderNumber()+"报关进料日期不能为空。");
						throw new Exception("导入报错："+customsImportsAndExports.getOrderNumber()+"报关进料日期不能为空。");
					}

					CustomsMaterial customsMaterial=customsMaterialBIZ.getByMaterialNo(customsImportsAndExports.getCimtasLongItemNo());
					if(customsMaterial==null){
						logUtil.logError(CustomsImportsAndExportsHelp.title,"导入报错："+customsImportsAndExports.getOrderNumber()+"料件序号未mapping到:"+customsImportsAndExports.getCimtasLongItemNo());
						throw new Exception("导入报错："+customsImportsAndExports.getOrderNumber()+"料件序号未mapping到:"+customsImportsAndExports.getCimtasLongItemNo());
					}
					
					if(checkMaterialNo(customsMaterial.getNo())){
						customsImportsAndExports.setNo(customsMaterial.getNo());
					}
						
					
					
					customsImportsAndExports.setOperationDate(CommonUtil.getDate());
					customsImportsAndExports.setOperator(user.getName());
					
					customsImportsAndExportsDAO.save(customsImportsAndExports);
				}
				
			}else{
				logUtil.logError(CustomsImportsAndExportsHelp.title,"导入报错：文件没数据");
				throw new Exception("No data!");
			}
			
			logUtil.logInfo(CustomsImportsAndExportsHelp.title,"导入成功！");
			
		} catch (Exception e) {
			logUtil.logError(CustomsImportsAndExportsHelp.title,"导入报错："+e.getMessage());
			throw new Exception("进（进出口）导入报错："+e.getMessage());
		}
		
	}

	@Override
	public ByteArrayInputStream exportData(String where, List<HeadColumn> lHeadColumns) throws Exception {
		List list  =customsImportsAndExportsDAO.query(where);
    	Class c = (Class) new CustomsImportsAndExports().getClass();  
    	ByteArrayOutputStream os = ExcelUtil.exportExcel("CustomsImportsAndExports", c, list, "yyy-MM-dd",lHeadColumns);
    	byte[] fileContent = os.toByteArray();
    	return new ByteArrayInputStream(fileContent);	
	}
	

	@Override
	public List<CustomsImportsAndExports> query(String where) {
		return  customsImportsAndExportsDAO.query(where);
	}

	@Override
	public List<CustomsImportsAndExports> query(String where, int pageSize, int pageCurrent) {
		return customsImportsAndExportsDAO.query(where, pageSize, pageCurrent);
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public String editNo(String id, String no) {
		try {
			CustomsMaterial customsMaterial=customsMaterialBIZ.getByNo(no);
			if (customsMaterial==null) {
				logUtil.logError(CustomsImportsAndExportsHelp.title,"料件序号编辑：料件序号不存在："+no);
				return "料件序号编辑：料件序号不存在："+no;
			}
			CustomsImportsAndExports cus=(CustomsImportsAndExports) customsImportsAndExportsDAO.query(" where id='"+id+"'").get(0);
			cus.setNo(no);
			customsImportsAndExportsDAO.update(cus);
			logUtil.logInfo(CustomsImportsAndExportsHelp.title,"料件序号编辑成功：id"+id+" 料件序号："+no);
			return null;
		} catch (Exception e) {
			logUtil.logError(CustomsImportsAndExportsHelp.title,"料件序号编辑异常：料件序号:"+no+" 异常："+e.getMessage());
			return "料件序号编辑异常：料件序号:"+no+" 异常："+e.getMessage();
		}
	}
	
	

	@Override
	public CustomsImportsAndExports getById(String id) {
		return (CustomsImportsAndExports) customsImportsAndExportsDAO.query(" where id='"+id+"'").get(0);
	}

	public boolean checkMaterialNo(String no) throws Exception{
		try { 
			List  list = commonDAO.queryByHql(" select count(1) from CustomsImportsAndExports where no='"+no+"'");
			if (list.size()>0&&(long)list.get(0)>0) {
				return false;
			}else{
				return true;
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		
	}
	

	
	
}
