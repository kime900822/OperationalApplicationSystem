package com.customs.biz.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.customs.biz.CustomsImportsAndExportsBIZ;
import com.customs.biz.CustomsMaterialBIZ;
import com.customs.dao.CustomsImportsAndExportsDAO;
import com.customs.model.CustomsImportsAndExports;
import com.customs.model.CustomsMaterial;
import com.customs.other.CustomsImportsAndExportsHelp;
import com.customs.other.CustomsMaterialHelp;
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
				String batchNumber=getMaxBatchNumber();
				String date=CommonUtil.getDate();
				for (CustomsImportsAndExports customsImportsAndExports : lCustomsImportsAndExports) {
					if (customsImportsAndExports.getEntryDate().equals("")) {
						logUtil.logError(CustomsImportsAndExportsHelp.title,"导入报错："+customsImportsAndExports.getOrderNumber()+"报关进料日期不能为空。");
						throw new Exception("导入报错："+customsImportsAndExports.getOrderNumber()+"报关进料日期不能为空。");
					}

					List<CustomsMaterial> list=customsMaterialBIZ.getByMaterialNo(customsImportsAndExports.getCimtasCode());
					if (list.size()>1) {
						logUtil.logError(CustomsImportsAndExportsHelp.title,"导入报错："+customsImportsAndExports.getOrderNumber()+"料号维护表存在2个相同的料号:"+customsImportsAndExports.getCimtasCode());
						throw new Exception("导入报错："+customsImportsAndExports.getOrderNumber()+"料号维护表存在2个相同的料号:"+customsImportsAndExports.getCimtasCode());
					}else if (list.size()==0) {
						logUtil.logError(CustomsImportsAndExportsHelp.title,"导入报错："+customsImportsAndExports.getOrderNumber()+"料件序号未mapping到:"+customsImportsAndExports.getCimtasCode());
						throw new Exception("导入报错："+customsImportsAndExports.getOrderNumber()+"料件序号未mapping到:"+customsImportsAndExports.getCimtasCode());

					}
					
					
					if (checkImport(customsImportsAndExports)) {
						customsImportsAndExports.setNo(list.get(0).getNo());
						customsImportsAndExports.setName(list.get(0).getMaterialName());
						customsImportsAndExports.setDescription(list.get(0).getSpecification());
					}else {
						logUtil.logError(CustomsImportsAndExportsHelp.title,"导入报错："+customsImportsAndExports.getOrderNumber()+"料件序号已存在:"+customsImportsAndExports.getCimtasCode());
						throw new Exception("导入报错："+customsImportsAndExports.getOrderNumber()+"料件序号已存在:"+customsImportsAndExports.getCimtasCode());
					}
					

						
					customsImportsAndExports.setNo(CommonUtil.spaceToNull(customsImportsAndExports.getNo().trim()));
					customsImportsAndExports.setQuantity(CommonUtil.spaceToNull(customsImportsAndExports.getQuantity().trim()));
					customsImportsAndExports.setUnitPrice(CommonUtil.spaceToNull(customsImportsAndExports.getUnitPrice().trim()));
					customsImportsAndExports.setAmount(CommonUtil.spaceToNull(customsImportsAndExports.getAmount().trim()));
					customsImportsAndExports.setUnitPriceUSD(CommonUtil.spaceToNull(customsImportsAndExports.getUnitPriceUSD().trim()));
					customsImportsAndExports.setAmountUSD(CommonUtil.spaceToNull(customsImportsAndExports.getAmountUSD().trim()));
					customsImportsAndExports.setNetWeight(CommonUtil.spaceToNull(customsImportsAndExports.getNetWeight().trim()));
					
					customsImportsAndExports.setOperationDate(date);
					customsImportsAndExports.setBatchNumber(batchNumber);
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

	public boolean checkImport(CustomsImportsAndExports customsImportsAndExports) throws Exception{
		try { 
			List  list = commonDAO.queryByHql(" select count(1) from CustomsImportsAndExports where  "
					+ " and cimtasCode='"+customsImportsAndExports.getCimtasCode()+"' amd orderNumber='"+customsImportsAndExports.getOrderNumber()+"' and entryNo='"+customsImportsAndExports.getEntryNo()+"' ");
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

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public String deleteByBatchNumber(String batchNumber) {
		try {
			List  list = commonDAO.queryByHql(" select count(1) from CustomsImportsAndExports where batchNumber='"+batchNumber+"'");
			if (list.size()>0&&(long)list.get(0)>0) {
				String hql="delete from CustomsImportsAndExports where batchNumber='"+batchNumber+"'";
				commonDAO.executeHQL(hql);
			}else{
				return "数据删除报错：此批次号没有数据";
			}
			
		} catch (Exception e) {
			logUtil.logError(CustomsImportsAndExportsHelp.title,"数据删除报错："+e.getMessage());
			return "数据删除报错："+e.getMessage();
		}
		return null;
	}
	
	
	public String getMaxBatchNumber() throws Exception{
		try {
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
			List  list = commonDAO.queryByHql(" select MAX(batchNumber) from CustomsImportsAndExports where substr(batchNumber,2,6)='"+sdf.format(d)+"'" );
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
	
	
}
