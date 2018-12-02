package com.cuntoms.biz.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.hibernate.annotations.Where;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cuntoms.biz.CustomsProductBIZ;
import com.cuntoms.dao.CustomsProductDAO;
import com.cuntoms.model.CustomsProduct;
import com.kime.base.BizBase;
import com.kime.dao.CommonDAO;
import com.kime.model.HeadColumn;
import com.kime.model.User;
import com.kime.utils.CommonUtil;
import com.kime.utils.ExcelUtil;

@Service
@Transactional(readOnly=true)
public class CustomsProductBIZImpl extends BizBase implements CustomsProductBIZ{

	@Autowired
	CustomsProductDAO customsProductDAO;
	@Autowired
	CommonDAO commonDAO;
	
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public String customsHandingOK(String batchNumber,User user) {
		try {
			List<CustomsProduct> list=customsProductDAO.query(" where batchNumber='"+batchNumber+"'");
			for (CustomsProduct customsProduct : list) {
				customsProduct.setDeclaration("Y");
				customsProduct.setDeclarationDate(CommonUtil.getDate());
				customsProduct.setDeclarationOperatior(user.getUid());
				customsProductDAO.update(customsProduct);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		
		return null;
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public String customsHandingNO(String batchNumber,User user) {
		try {
			List<CustomsProduct> list=customsProductDAO.query(" where batchNumber='"+batchNumber+"'");
			for (CustomsProduct customsProduct : list) {
				customsProduct.setDeclaration("");
				customsProduct.setDeclarationDate("");
				customsProduct.setDeclarationOperatior("");
				customsProductDAO.update(customsProduct);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		
		return null;
	}

	@Override
	public List<CustomsProduct> query(String where) {
		return customsProductDAO.query(where);
	}

	@Override
	public List<CustomsProduct> query(String where, int pageSize, int pageCurrent) {
		return customsProductDAO.query(where, pageSize, pageCurrent);
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void importData(User user, File file, String first, String upfileFileName, int start) throws Exception {
		try {
			List<CustomsProduct> lCustomsProducts=ExcelUtil.FileToList(new CustomsProduct().getClass(),file,first,upfileFileName,start);
			if (lCustomsProducts.size()>0) {
				int max=getMaxNO();
				String batchNumber=getMaxBatchNumber();
				String date=CommonUtil.getDate();
				for (CustomsProduct customsProduct : lCustomsProducts) {
					customsProduct.setBatchNumber(batchNumber);
					customsProduct.setNo(max++);
					customsProduct.setUploadDate(date);
					customsProduct.setUploadOperator(user.getUid());
					if (checkMaterialNo(customsProduct.getMaterialNo())) {
						customsProductDAO.save(customsProduct);
					}else{
						throw new Exception("料号重复:"+customsProduct.getMaterialNo());
					}
				}
				
				
			}else{
				throw new Exception("No data!");
			}
			
		} catch (Exception e) {
			logUtil.logError("海关文件导入报错："+e.getMessage());
			throw new Exception("海关文件导入报错："+e.getMessage());
		}
	}
	
	

	@Override
	public ByteArrayInputStream exportData(String where,List<HeadColumn> lHeadColumns) throws Exception {
		List list  =customsProductDAO.query(where);
    	Class c = (Class) new CustomsProduct().getClass();  
    	ByteArrayOutputStream os = ExcelUtil.exportExcel("CustomsProduct", c, list, "yyy-MM-dd",lHeadColumns);
    	byte[] fileContent = os.toByteArray();
    	return new ByteArrayInputStream(fileContent);		
	}

	public int getMaxNO() throws Exception{
		try {
			List  list = commonDAO.queryByHql(" select MAX(no) from CustomsProduct");
			if (list.size()>0) {
				return Integer.parseInt((String)list.get(0));
			}else{
				return 0;
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
			List  list = commonDAO.queryByHql(" select MAX(batchNumber) from CustomsProduct where substr(batchNumber,2,6)='"+sdf.format(d)+"'" );
			if (list.size()>0) {
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
			List  list = commonDAO.queryByHql(" select count(1) from CustomsProduct where materialNo='"+materialNo+"'");
			if (list.size()>0&&Integer.parseInt((String)list.get(0))>0) {
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
