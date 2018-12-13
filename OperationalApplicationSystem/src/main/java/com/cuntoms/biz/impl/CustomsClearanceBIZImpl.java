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

import com.cuntoms.biz.CustomsClearanceBIZ;
import com.cuntoms.biz.CustomsMaterialBIZ;
import com.cuntoms.dao.CustomsClearanceDAO;
import com.cuntoms.model.CustomsClearance;
import com.cuntoms.model.CustomsImportsAndExports;
import com.cuntoms.model.CustomsMaterial;
import com.cuntoms.other.CustomsClearanceHelp;
import com.cuntoms.other.CustomsImportsAndExportsHelp;
import com.kime.base.BizBase;
import com.kime.dao.CommonDAO;
import com.kime.model.HeadColumn;
import com.kime.model.User;
import com.kime.utils.CommonUtil;
import com.kime.utils.ExcelUtil;

@Service
@Transactional(readOnly=true)
public class CustomsClearanceBIZImpl  extends BizBase implements CustomsClearanceBIZ{

	@Autowired
	CustomsClearanceDAO customsCleanceDAO;
	@Autowired
	CustomsMaterialBIZ customsMaterialBIZ;
	@Autowired
	CommonDAO commonDAO;
	
	
	
	@Override
	public ByteArrayInputStream exportData(String where, List<HeadColumn> lHeadColumns) throws Exception {
		List list  =customsCleanceDAO.query(where);
    	Class c = (Class) new CustomsClearance().getClass();  
    	ByteArrayOutputStream os = ExcelUtil.exportExcel("CustomsClearance", c, list, "yyy-MM-dd",lHeadColumns);
    	byte[] fileContent = os.toByteArray();
    	return new ByteArrayInputStream(fileContent);	
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void importData(User user, File file, String first, String upfileFileName, int start) throws Exception {
		try {
			List<CustomsClearance> lClearances=ExcelUtil.FileToList(new CustomsClearance().getClass(),file,first,upfileFileName,start);
			String batchNumber=getMaxBatchNumber();
			String date=CommonUtil.getDate();
			if (lClearances.size()>0) {
				for (CustomsClearance clearance : lClearances) {

					CustomsMaterial customsMaterial=customsMaterialBIZ.getByMaterialNo(clearance.getPoseLongItemNo());
					if(customsMaterial==null){
						logUtil.logError(CustomsClearanceHelp.title,"导入报错："+clearance.getCimtasNo()+"料件序号未mapping到:"+clearance.getPoseLongItemNo());
						throw new Exception("导入报错："+clearance.getCimtasNo()+"料件序号未mapping到:"+clearance.getPoseLongItemNo());
					}
					
					if(checkMaterialNo(customsMaterial.getNo())){
						clearance.setNo(customsMaterial.getNo());
					}
					
					clearance.setNo(CommonUtil.spaceToNull(clearance.getNo()));
					clearance.setDia(CommonUtil.spaceToNull(clearance.getDia()));
					clearance.setSch(CommonUtil.spaceToNull(clearance.getSch()));
					clearance.setQuantityOrdered(CommonUtil.spaceToNull(clearance.getQuantityOrdered()));
					clearance.setQuantityIssued(CommonUtil.spaceToNull(clearance.getQuantityIssued()));
					clearance.setWeight(CommonUtil.spaceToNull(clearance.getWeight()));
					clearance.setOperationDate(date);
					clearance.setOperator(user.getName());
					clearance.setBatchNumber(batchNumber);
					
					customsCleanceDAO.save(clearance);
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
	public List<CustomsClearance> query(String where) {
		return customsCleanceDAO.query(where);
	}

	@Override
	public List<CustomsClearance> query(String where, int pageSize, int pageCurrent) {
		return customsCleanceDAO.query(where, pageSize, pageCurrent);
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
			CustomsClearance clearance=(CustomsClearance) customsCleanceDAO.query(" where id='"+id+"'").get(0);
			clearance.setNo(no);
			customsCleanceDAO.update(clearance);
			logUtil.logInfo(CustomsImportsAndExportsHelp.title,"料件序号编辑成功：id"+id+" 料件序号："+no);
			return null;
		} catch (Exception e) {
			logUtil.logError(CustomsImportsAndExportsHelp.title,"料件序号编辑异常：料件序号:"+no+" 异常："+e.getMessage());
			return "料件序号编辑异常：料件序号:"+no+" 异常："+e.getMessage();
		}
	}

	@Override
	public CustomsClearance getById(String id) {
		return (CustomsClearance) customsCleanceDAO.query(" where id='"+id+"'").get(0);
	}
	
	
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public String deleteByBatchNumber(String batchNumber) {
		List<CustomsClearance> lClearances=customsCleanceDAO.query(" where batchNumber='"+batchNumber+"'");
		if (lClearances.size()==0) {
			return "数据删除报错：此批次号没有数据";
		}
		for (CustomsClearance customsClearance : lClearances) {
			
			if (customsClearance.getBOMDate()==null||customsClearance.getBOMDate().equals("")) {
				return "此批次号已经进行BOM日期申报，不能删除！";
			}
			customsCleanceDAO.delete(customsClearance);
		}
		return null;
		
	}

	public String getMaxBatchNumber() throws Exception{
		try {
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
			List  list = commonDAO.queryByHql(" select MAX(batchNumber) from CustomsClearance where substr(batchNumber,2,6)='"+sdf.format(d)+"'" );
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
	
	public boolean checkMaterialNo(String no) throws Exception{
		try { 
			List  list = commonDAO.queryByHql(" select count(1) from CustomsClearance where no='"+no+"'");
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