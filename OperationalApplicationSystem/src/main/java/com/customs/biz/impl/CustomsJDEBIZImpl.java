package com.customs.biz.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.customs.biz.CUstomsJDEBIZ;
import com.customs.dao.CustomsJDEDAO;
import com.customs.model.CustomsJDE;
import com.customs.model.CustomsMaterial;
import com.customs.other.CustomsJDEHelp;
import com.customs.other.CustomsMaterialHelp;
import com.customs.other.CustomsProductHelp;
import com.kime.base.BizBase;
import com.kime.dao.CommonDAO;
import com.kime.model.User;
import com.kime.utils.CommonUtil;
import com.kime.utils.ExcelUtil;

@Service
@Transactional(readOnly=true)
public class CustomsJDEBIZImpl extends BizBase implements CUstomsJDEBIZ {

	@Autowired
	CustomsJDEDAO customsJDFDAO;
	@Autowired
	CommonDAO commonDAO;
	
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void importData(User user, File file, String first, String upfileFileName, int start) throws Exception {
		try {
			List<CustomsJDE> lCustomsJDEs=ExcelUtil.FileToList(new CustomsJDE().getClass(),file,first,upfileFileName,start);
			if (lCustomsJDEs.size()>0) {
				String batchNumber=getMaxBatchNumber();
				String date=CommonUtil.getDate();
				for (CustomsJDE customsJDE : lCustomsJDEs) {
					if (!checkLotNumber(customsJDE)) {
						logUtil.logError(CustomsJDEHelp.title,"导入报错，Lot Number重复："+customsJDE.getLotNumber());
						throw new Exception("导入报错，Lot Number重复："+customsJDE.getLotNumber());
					}
					customsJDE.setTransQTY(CommonUtil.spaceToNull(customsJDE.getTransQTY()));
					customsJDE.setExtendedCostPrice(CommonUtil.spaceToNull(customsJDE.getExtendedCostPrice()));
					customsJDE.setDocumentNumber(CommonUtil.spaceToNull(customsJDE.getDocumentNumber()));
					customsJDE.setWeight(CommonUtil.spaceToNull(customsJDE.getWeight()));
					customsJDE.setPurchaseOrderLineNumber(CommonUtil.spaceToNull(customsJDE.getPurchaseOrderLineNumber()));
					customsJDE.setOperationDate(date);
					customsJDE.setBatchNumber(batchNumber);
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

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public String deleteByBatchNumber(String batchNumber) {
		try {
			List  list = commonDAO.queryByHql(" select count(1) from CustomsJDE where batchNumber='"+batchNumber+"'");
			if (list.size()>0&&(long)list.get(0)>0) {
				String hql="delete from CustomsJDE where batchNumber='"+batchNumber+"'";
				commonDAO.executeHQL(hql);
			}else{
				return "数据删除报错：此批次号没有数据";
			}
		} catch (Exception e) {
			logUtil.logError(CustomsJDEHelp.title,"数据删除报错："+e.getMessage());
			return "数据删除报错："+e.getMessage();
		}
		return null;
	}
	
	public String getMaxBatchNumber() throws Exception{
		try {
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
			List  list = commonDAO.queryByHql(" select MAX(batchNumber) from CustomsJDE where substr(batchNumber,2,6)='"+sdf.format(d)+"'" );
			if (list.size()>0&&list.get(0)!=null) {
				String max= (String)list.get(0);
				return "A" + String.valueOf(Long.valueOf(max.replace("A", "")) + 1);
			}else{
				return "A"+sdf.format(d)+"01";
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		
		
	}
	
	public boolean checkLotNumber(CustomsJDE customsJDE)  throws Exception{
		
		try {
			List  list = commonDAO.queryByHql(" select count(1) from CustomsJDE where lotNumber='"+customsJDE.getLotNumber()+"'");
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
