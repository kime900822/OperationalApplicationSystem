package com.cuntoms.biz.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cuntoms.biz.CustomsMaterialBIZ;
import com.cuntoms.dao.CustomsMaterialDAO;
import com.cuntoms.model.CustomsMaterial;
import com.cuntoms.model.CustomsProduct;
import com.kime.base.BizBase;
import com.kime.dao.CommonDAO;
import com.kime.model.HeadColumn;
import com.kime.model.User;
import com.kime.utils.CommonUtil;
import com.kime.utils.ExcelUtil;

@Service
@Transactional(readOnly=true)
public class CustomsMaterialBIZImpl extends BizBase implements CustomsMaterialBIZ{

	@Autowired
	CustomsMaterialDAO customsMaterialDAO;
	@Autowired
	CommonDAO commonDAO;
	
	
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void importData(User user, File file, String first, String upfileFileName, int start) throws Exception {
		try {
			List<CustomsMaterial> lCustomsMaterials=ExcelUtil.FileToList(new CustomsMaterial().getClass(),file,first,upfileFileName,start);
			if (lCustomsMaterials.size()>0) {
				int max=getMaxNO();
				String batchNumber=getMaxBatchNumber();
				String date=CommonUtil.getDate();
				for (CustomsMaterial customsMaterial : lCustomsMaterials) {
					customsMaterial.setBatchNumber(batchNumber);
					customsMaterial.setNo(String.valueOf(max++));
					customsMaterial.setUploadDate(date);
					customsMaterial.setUploadOperator(user.getUid());
					if (checkMaterialNo(customsMaterial.getMaterialNo())) {
						customsMaterialDAO.save(customsMaterial);
					}else{
						logUtil.logError("海关文件导入：料号重复："+customsMaterial.getMaterialNo());
						throw new Exception("料号重复:"+customsMaterial.getMaterialNo());
					}
				}
				
				
			}else{
				logUtil.logError("海关文件导入：文件没数据");
				throw new Exception("No data!");
			}
			
		} catch (Exception e) {
			logUtil.logError("海关文件导入报错："+e.getMessage());
			throw new Exception("海关文件导入报错："+e.getMessage());
		}		
	}

	@Override
	public ByteArrayInputStream exportData(String where, List<HeadColumn> lHeadColumns) throws Exception {
		List list  =customsMaterialDAO.query(where);
    	Class c = (Class) new CustomsMaterial().getClass();  
    	ByteArrayOutputStream os = ExcelUtil.exportExcel("CustomsMaterial", c, list, "yyy-MM-dd",lHeadColumns);
    	byte[] fileContent = os.toByteArray();
    	return new ByteArrayInputStream(fileContent);	
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public String customsHandingOK(String batchNumber, User user) {
		try {
			
			List<CustomsMaterial> list1=customsMaterialDAO.query(" where batchNumber='"+batchNumber+"' and declaration='Y' ");
			List<CustomsMaterial> list=customsMaterialDAO.query(" where batchNumber='"+batchNumber+"' and declaration='' ");
			if (list1.size()>0) {
				return batchNumber+" 此批号已经进行海关系统操作！";
			}
			if (list.size()==0) {
				return batchNumber+" 此批号不存在！";
			}
			for (CustomsMaterial customsMaterial : list) {
				customsMaterial.setDeclaration("Y");
				customsMaterial.setDeclarationDate(CommonUtil.getDate());
				customsMaterial.setDeclarationOperatior(user.getUid());
				customsMaterialDAO.update(customsMaterial);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logUtil.logError("海关操作报错：批次号："+batchNumber+" 错误信息："+e.getMessage());
			return e.getMessage();
		}
		logUtil.logInfo("海关取消操作成功！批次号："+batchNumber);
		return null;
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public String customsHandingNO(String batchNumber, User user) {
		try {
			List<CustomsMaterial> list=customsMaterialDAO.query(" where batchNumber='"+batchNumber+"' and declaration='Y' ");
			List<CustomsMaterial> list1=customsMaterialDAO.query(" where batchNumber='"+batchNumber+"' and declaration='' ");
			if (list1.size()>0) {
				return batchNumber+" 此批号还未进行海关系统操作，不用取消！";
			}
			if (list.size()==0) {
				return batchNumber+" 此批号不存在！";
			}
			for (CustomsMaterial customsMaterial : list) {
				customsMaterial.setDeclaration("");
				customsMaterial.setDeclarationDate("");
				customsMaterial.setDeclarationOperatior("");
				customsMaterialDAO.update(customsMaterial);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logUtil.logError("海关取消操作报错：批次号："+batchNumber+" 错误信息："+e.getMessage());
			return e.getMessage();
		}
		logUtil.logInfo("海关取消操作成功！批次号："+batchNumber);
		return null;
	}

	@Override
	public List<CustomsMaterial> query(String where) {
		return  customsMaterialDAO.query(where);
	}

	@Override
	public List<CustomsMaterial> query(String where, int pageSize, int pageCurrent) {
		return customsMaterialDAO.query(where, pageSize, pageCurrent);
	}

	
	public int getMaxNO() throws Exception{
		try {
			List  list = commonDAO.queryByHql(" select MAX(no) from CustomsMaterial");
			if (list.size()>0&&list.get(0)!=null) {
				return Integer.parseInt((String)list.get(0))+1;
			}else{
				return 1;
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		
	}
	public String getMaxBatchNumber() throws Exception{
		try {
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
			List  list = commonDAO.queryByHql(" select MAX(batchNumber) from CustomsMaterial where substr(batchNumber,2,6)='"+sdf.format(d)+"'" );
			if (list.size()>0&&list.get(0)!=null) {
				String max= (String)list.get(0);
				return "B" + String.valueOf(Long.valueOf(max.replace("B", "")) + 1);
			}else{
				return "B"+sdf.format(d)+"01";
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		
		
	}
	
	public boolean checkMaterialNo(String materialNo) throws Exception{
		try {
			List  list = commonDAO.queryByHql(" select count(1) from CustomsMaterial where materialNo='"+materialNo+"'");
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
