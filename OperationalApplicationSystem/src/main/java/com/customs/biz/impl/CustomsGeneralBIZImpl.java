package com.customs.biz.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.xmlbeans.impl.xb.xsdschema.impl.PublicImpl;
import org.hibernate.annotations.Where;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.customs.biz.CustomsGeneralBIZ;
import com.customs.dao.CustomsGeneralDAO;
import com.customs.model.CustomsGeneral;
import com.customs.model.CustomsGeneralInit;
import com.customs.model.CustomsImportsAndExports;
import com.customs.model.CustomsJDE;
import com.customs.other.CustomsGeneralHelp;
import com.customs.other.CustomsJDEHelp;
import com.kime.base.BizBase;
import com.kime.dao.CommonDAO;
import com.kime.model.HeadColumn;
import com.kime.model.User;
import com.kime.utils.CommonUtil;
import com.kime.utils.ExcelUtil;

@Service
@Transactional(readOnly=true)
public class CustomsGeneralBIZImpl  extends BizBase implements CustomsGeneralBIZ{

	@Autowired
	CustomsGeneralDAO customsGeneralDAO;
	@Autowired
	CommonDAO commonDAO;
	
	@Override
	public List<CustomsGeneral> query(String month,String where) throws Exception {
		
		if (month==null) {
			return new ArrayList<>();
		}
		if (!month.equals(CommonUtil.getMonth())&&checkGeneral(month)) {
			return customsGeneralDAO.query(" where month='"+month+"'");
		}
		String sql=getSQL(month,where);
		
		return commonDAO.queryBySql(sql);
		
		
	}

	@Override
	public List<CustomsGeneral> query(String month,String where, int pageSize, int pageCurrent) throws Exception {
		
		if (month==null) {
			return new ArrayList<>();
		}
		if (!month.equals(CommonUtil.getMonth())&&checkGeneral(month)) {
			return customsGeneralDAO.query(" where month='"+month+"'", pageSize, pageCurrent);
		}
		String sql=getSQL(month,where);
		return dataToEntity(commonDAO.queryBySql(sql, pageSize, pageCurrent));
		
	}

	@Override
	public ByteArrayInputStream exportData(String month,String where, List<HeadColumn> lHeadColumns) throws Exception {
		List list  = new ArrayList<>();
		
		if (!month.equals(CommonUtil.getMonth())&&checkGeneral(month)) {
			list= dataToEntity(customsGeneralDAO.query(" where month='"+month+"'"));
		}else {
			String sql=getSQL(month,where);
			list = dataToEntity(commonDAO.queryBySql(sql));
		}
		
		
    	Class c = (Class) new CustomsGeneral().getClass();  
    	ByteArrayOutputStream os = ExcelUtil.exportExcel("CustomsGeneral", c, list, "yyy-MM-dd",lHeadColumns);
    	byte[] fileContent = os.toByteArray();
    	return new ByteArrayInputStream(fileContent);	
	}
	
	
	

	@Override
	public ByteArrayInputStream exportData4Init(List list, List<HeadColumn> lHeadColumns) throws Exception {
		Class c = (Class) new CustomsGeneralInit().getClass();  
    	ByteArrayOutputStream os = ExcelUtil.exportExcel("CustomsGeneralInit", c, list, "yyy-MM-dd",lHeadColumns);
    	byte[] fileContent = os.toByteArray();
    	return new ByteArrayInputStream(fileContent);	
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public String saveData(String month) throws Exception {
		String sql=getSQL(month,"");                                                                                           
		
		List<CustomsGeneral> list= dataToEntity(commonDAO.queryBySql(sql));
		
		if (list.size()>0) {
			try {
				if (checkGeneral(list.get(0).getMonth())) {
					//commonDAO.executeHQL(" delete from CustomsGeneral where month='"+list.get(0).getMonth()+"'");
					return "数据锁定报错：已有锁定数据，请先解除锁定！";
				}
				for (CustomsGeneral customsGeneral : list) {
					customsGeneral.setEarlyNumber(CommonUtil.spaceToNull(customsGeneral.getEarlyNumber().replaceAll(",", "")));
					customsGeneral.setIncomingVolume(CommonUtil.spaceToNull(customsGeneral.getIncomingVolume().replaceAll(",", "")));
					customsGeneral.setRegulatoryInventory(CommonUtil.spaceToNull(customsGeneral.getRegulatoryInventory().replaceAll(",", "")));
					customsGeneral.setPickingVolume(CommonUtil.spaceToNull(customsGeneral.getPickingVolume().replaceAll(",", "")));
					customsGeneral.setWarehouseVolume(CommonUtil.spaceToNull(customsGeneral.getWarehouseVolume().replaceAll(",", "")));
					customsGeneral.setWriteOffVolume(CommonUtil.spaceToNull(customsGeneral.getWriteOffVolume().replaceAll(",", "")));
					customsGeneral.setAmount(CommonUtil.spaceToNull(customsGeneral.getAmount().replaceAll(",", "")));
					customsGeneral.setPrice(CommonUtil.spaceToNull(customsGeneral.getPrice().replaceAll(",", "")));
					customsGeneralDAO.save(customsGeneral);
				}
				return null;
				
			} catch (Exception e) {
				logUtil.logError(CustomsGeneralHelp.title, "数据锁定报错："+e.getMessage());
				return "数据锁定报错："+e.getMessage();
			}
		}else{
			return month+"没有数据!";
		}
		
		
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public String deleteData(String month) throws Exception {
		String sql=getSQL(month,"");                                                                                           
		List<CustomsGeneral> list= dataToEntity(commonDAO.queryBySql(sql));
		
		if (list.size()>0) {
			try {
				if (checkGeneral(list.get(0).getMonth())) {
					commonDAO.executeHQL(" delete from CustomsGeneral where month='"+list.get(0).getMonth()+"'");
					return null;
				}
				else {
					return "数据解锁报错：没有已锁定数据！";
				}
			} catch (Exception e) {
				logUtil.logError(CustomsGeneralHelp.title, "数据解锁报错："+e.getMessage());
				return "数据解锁报错："+e.getMessage();
			}
		}else{
			return month+"没有数据!";
		}
		
		
	}
	
	@Override
	public List<CustomsGeneralInit> query4init(String where) {
		return customsGeneralDAO.query4init(where);
	}

	@Override
	public List<CustomsGeneralInit> query4init(String where, int pageSize, int pageCurrent) {
		return customsGeneralDAO.query4init(where, pageSize, pageCurrent);
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void initData(User user, File file, String first, String upfileFileName, int start) throws Exception {
		try {
			List<CustomsGeneralInit> list=ExcelUtil.FileToList(new CustomsGeneralInit().getClass(),file,first,upfileFileName,start);
			if (list.size()>0) {
				commonDAO.executeHQL(" delete from CustomsGeneralInit ");
				for (CustomsGeneralInit generalInit : list) {
					if (generalInit.getMonth()==null||generalInit.getMonth().equals("")) {
						logUtil.logError(CustomsGeneralHelp.title4init,"导入错误：月份不能为空：物料编码："+generalInit.getMaterialNo());
						throw new Exception("导入错误：月份不能为空：物料编码："+generalInit.getMaterialNo());
					}
					
					generalInit.setNo(CommonUtil.spaceToNull(generalInit.getNo()));
					generalInit.setEarlyNumber(CommonUtil.spaceToNull(generalInit.getEarlyNumber()));
					generalInit.setIncomingVolume(CommonUtil.spaceToNull(generalInit.getIncomingVolume()));
					generalInit.setWriteOffVolume(CommonUtil.spaceToNull(generalInit.getWriteOffVolume()));
					generalInit.setRegulatoryInventory(CommonUtil.spaceToNull(generalInit.getRegulatoryInventory()));
					generalInit.setPrice(CommonUtil.spaceToNull(generalInit.getPrice()));
					generalInit.setAmount(CommonUtil.spaceToNull(generalInit.getAmount()));
					
					customsGeneralDAO.save(generalInit);
				}
				
			}else{
				logUtil.logError(CustomsGeneralHelp.title4init,"导入文件没数据");
				throw new Exception("No data!");
			}
			
			logUtil.logInfo(CustomsGeneralHelp.title4init,"导入成功！");
		} catch (Exception e) {
			logUtil.logError(CustomsGeneralHelp.title4init,"导入报错："+e.getMessage());
			throw new Exception("导入报错："+e.getMessage());
		}
	}
	
	/**
	 * 上月的报表是否有保存
	 * @return
	 * @throws Exception 
	 */
	public boolean checkGeneral(String month) throws Exception{
		try {
			List  list = commonDAO.queryByHql(" select count(1) from CustomsGeneral where month='"+month+"'");
			if (list.size()>0&&(long)list.get(0)>0) {
				return true;
			}else{
				return false;
			}
		} catch (NumberFormatException e) {
			logUtil.logError(CustomsGeneralHelp.title,"是否存在落地数据查询异常："+e.getMessage());
			throw new Exception("是否存在落地数据查询异常："+e.getMessage());
		}
		
	}

	
	public List<CustomsGeneral> dataToEntity(List list) throws Exception {
		List<CustomsGeneral> lGenerals=new ArrayList<>();
		String maxLockMonth=getMaxLockMonth();
		for (Object object : list) {
			Object[] tmp= (Object[]) object;
			CustomsGeneral cGeneral=new CustomsGeneral();
			cGeneral.setMonth(String.valueOf(tmp[0]));
			cGeneral.setMaterialNo(String.valueOf(tmp[1]));
			cGeneral.setJdeMaterialNo(String.valueOf(tmp[2]));
			cGeneral.setNo(String.valueOf(tmp[3]));
			cGeneral.setProductNo(String.valueOf(tmp[4]));
			cGeneral.setMaterialName(String.valueOf(tmp[5]));
			cGeneral.setMaterialSpecification(String.valueOf(tmp[6]));
			cGeneral.setUnit(String.valueOf(tmp[7]));
			cGeneral.setEarlyNumber(String.valueOf(tmp[8]));
			cGeneral.setIncomingVolume(String.valueOf(tmp[9]));
			cGeneral.setWriteOffVolume(String.valueOf(tmp[10]));
			cGeneral.setRegulatoryInventory(String.valueOf(tmp[11]));
			cGeneral.setPickingVolume(String.valueOf(tmp[12]));
			cGeneral.setWarehouseVolume(String.valueOf(tmp[13]));
			cGeneral.setPrice(String.valueOf(tmp[14]));
			cGeneral.setCurrency((String)(tmp[15]));
			cGeneral.setAmount(String.valueOf(tmp[16]));
			cGeneral.setLockMonth(maxLockMonth);
			lGenerals.add(cGeneral);
					
		}
		return lGenerals;
	}
	
	public String getMaxLockMonth() throws Exception {
		try {
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
			List  list = commonDAO.queryByHql(" select MAX(month) from CustomsGeneral " );
			if (list.size()>0&&list.get(0)!=null) {
				return (String)list.get(0);
			}else{
				return "";
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		
		
	}
	
	@Override
	public String getSQL(String month,String where) throws Exception {
		boolean flag=false;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
		Date date=sdf.parse(month);
		Calendar calendar = Calendar.getInstance(); 
		calendar.setTime(date); 
		calendar.add(Calendar.MONTH, -1);//当前时间前去一个月，即一个月前的时间    
		date = calendar.getTime();//获取一年前的时间，或者一个月前的时间    
		String rmonth=sdf.format(date);
		List  list = commonDAO.queryByHql(" select count(1) from CustomsGeneral where month='"+rmonth+"'");
		if (list.size()>0&&(long)list.get(0)>0) {
			flag=true;
		}else{
			flag=false;
		}
		String earlySQL="";
		String earlyTable="";
		if (flag) {
			earlySQL= 	" UNION all                                                                                                                "
					+" select                                                                                                                  "
					+" t1.`Month`,                                                                                                             "
					+" t1.MaterialNo,                                                                                                          "
					+" IFNULL(t6.NewMaterialNo,t1.JdeMaterialNo) AS JdeMaterialNo,                                                                                                       "
					+" t1.`No`,                                                                                                                "
					+" t1.ProductNo,                                                                                                           "
					+" t1.MaterialName,                                                                                                        "
					+" t1.MaterialSpecification,                                                                                               "
					+" t1.Unit,                                                                                                                "
					+" IFNULL(t1.RegulatoryInventory,0) AS EarlyNumber,          "
					+" t2.ID,"
					+" t2.Quantity,        "
					+ " CASE when t1.Unit='米' "
					+ " and IFNULL(t6.NewMaterialNo,t1.JdeMaterialNo) not like 'FREE0%'"
					+ " and IFNULL(t6.NewMaterialNo,t1.JdeMaterialNo) not like 'FREE1%'"
					+ " and IFNULL(t6.NewMaterialNo,t1.JdeMaterialNo) not like 'FREE2%'"
					+ " and IFNULL(t6.NewMaterialNo,t1.JdeMaterialNo) not like 'FREE3%'"
					+ " and IFNULL(t6.NewMaterialNo,t1.JdeMaterialNo) not like 'FREE4%'"
					+ " and IFNULL(t6.NewMaterialNo,t1.JdeMaterialNo) not like 'FREE5%'"
					+ " and IFNULL(t6.NewMaterialNo,t1.JdeMaterialNo) not like 'FREE6%'"
					+ " and IFNULL(t6.NewMaterialNo,t1.JdeMaterialNo) not like 'FREE7%'"
					+ " and IFNULL(t6.NewMaterialNo,t1.JdeMaterialNo) not like 'FREE8%'"
					+ " and IFNULL(t6.NewMaterialNo,t1.JdeMaterialNo) not like 'FREE9%'"
					+ " and t7.`no` not in ('99991','99992','99993','99994','99995')"
					+" then IFNULL(sum(t4.quantityIssued*1.03/1000.0),0) else IFNULL(sum(t4.quantityIssued),0) end as WriteOffVolume,                                                                               "
					+ " null as pickingVolume,"
					+ " null as warehouseVolume,"
					+ " IFNULL(MAX(t2.unitPriceUSD),t1.price) as price, "
					+ " t1.currency,  "
					+ " t7.`no` AS prodectNo   "
					+" from CustomsGeneral t1                                                                                          "
					+" left join t_customs_importsandexports t2                                                                                "
					+" on t1.`No`=t2.`No` and t1.`Month`=substr(t2.EntryDate,1,7)                                                          "
					+" left join t_customs_material_mapping t6 on t1.JdeMaterialNo=t6.OldMaterialNo                         "
					+" left join (                                                                                                             "
					+" select IFNULL(tt2.NewMaterialNo,tt1.CimtasLongItemNo) NewMaterialNo,tt1.TransQTY,tt1.OperationDate                      "
					+" from t_customs_jde tt1 left join t_customs_material_mapping tt2 on tt1.CimtasLongItemNo=tt2.OldMaterialNo               "
					+" ) t3 on t3.NewMaterialNo=IFNULL(t6.NewMaterialNo,t1.JdeMaterialNo)  and t1.`Month`=substr(t3.OperationDate,1,7)                                   "
					+" left join t_customs_clearance t4 on t1.`No`=t4.`No`                                                  "
					+" and substr(t4.BOMDate,1,7) = t1.`Month`          "
					+" left join t_customs_material t5 on t1.`No`=t5.`No`                                                                         "
					+" left join t_customs_product t7 " 
					+" on t4.shipmentIems=t7.materialNo "
					+" where t1.`Month` = '"+rmonth+"'                                                                                          "
					+" group by                                                                                                                "
					+" t1.`Month`,                                                                                                             "
					+" t1.MaterialNo,                                                                                                          "
					+" t1.JdeMaterialNo,                                                                                                       "
					+" t1.`No`,                                                                                                                "
					+" t1.ProductNo,                                                                                                           "
					+" t1.MaterialName,                                                                                                        "
					+" t1.MaterialSpecification,                                                                                               "
					+" t1.Unit,                                                                                                                "
					+" t1.EarlyNumber, "
					+ "T2.Id,T2.Quantity ,"
					+ "7.`no`                                                                                                        ";
			earlyTable=" select 1 from t_customs_general t8 where t8.`Month`=substr(t1.EntryDate,1,7) and t8.`No`=t1.`No` ";
		}else {
			
			earlySQL= 	" UNION all                                                                                                                "
					+" select                                                                                                                  "
					+"'"+month +"'AS `Month`,                                                                                                             "
					+" t1.MaterialNo,                                                                                                          "
					+" IFNULL(t6.NewMaterialNo,t1.JdeMaterialNo) as JdeMaterialNo,                                                                                                       "
					+" t1.`No`,                                                                                                                "
					+" t1.ProductNo,                                                                                                           "
					+" t1.MaterialName,                                                                                                        "
					+" t1.MaterialSpecification,                                                                                               "
					+" t1.Unit,                                                                                                                "
					+" t1.EarlyNumber,          "
					+" t2.ID ,   "
					+" t2.Quantity,                       "
					+ " CASE when t1.Unit='米' "
					+ " and IFNULL(t6.NewMaterialNo,t1.JdeMaterialNo) not like 'FREE0%'"
					+ " and IFNULL(t6.NewMaterialNo,t1.JdeMaterialNo) not like 'FREE1%'"
					+ " and IFNULL(t6.NewMaterialNo,t1.JdeMaterialNo) not like 'FREE2%'"
					+ " and IFNULL(t6.NewMaterialNo,t1.JdeMaterialNo) not like 'FREE3%'"
					+ " and IFNULL(t6.NewMaterialNo,t1.JdeMaterialNo) not like 'FREE4%'"
					+ " and IFNULL(t6.NewMaterialNo,t1.JdeMaterialNo) not like 'FREE5%'"
					+ " and IFNULL(t6.NewMaterialNo,t1.JdeMaterialNo) not like 'FREE6%'"
					+ " and IFNULL(t6.NewMaterialNo,t1.JdeMaterialNo) not like 'FREE7%'"
					+ " and IFNULL(t6.NewMaterialNo,t1.JdeMaterialNo) not like 'FREE8%'"
					+ " and IFNULL(t6.NewMaterialNo,t1.JdeMaterialNo) not like 'FREE9%'"
					+ " and t7.`no` not in ('99991','99992','99993','99994','99995')"
					+" then IFNULL(sum(t4.quantityIssued*1.03/1000.0),0) else IFNULL(sum(t4.quantityIssued),0) end as WriteOffVolume,                                                                               "
					+ " null as pickingVolume,"
					+ " null as warehouseVolume,"
					+ " IFNULL(MAX(t2.unitPriceUSD),t1.price) price,"
					+ " t1.currency "
					+" from t_customs_general_init t1                                                                                          "
					+" left join t_customs_importsandexports t2                                                                                "
					+" on t1.`No`=t2.`No`  and   substr(t2.EntryDate,1,7)='"+month+"'       "
					+" left join t_customs_material_mapping t6 on t1.MaterialNo=t6.OldMaterialNo                         "
					+" left join (                                                                                                             "
					+" select IFNULL(tt2.NewMaterialNo,tt1.CimtasLongItemNo) NewMaterialNo,tt1.TransQTY,tt1.OperationDate                    "
					+" from t_customs_jde tt1 left join t_customs_material_mapping tt2 on tt1.CimtasLongItemNo=tt2.OldMaterialNo               "
					+" ) t3 on t3.NewMaterialNo=IFNULL(t6.NewMaterialNo,t1.JdeMaterialNo) and substr(t3.OperationDate,1,7)='"+month+"'                                "
					+" left join t_customs_clearance t4 on t1.`No`=t4.`No` and substr(t4.BOMDate,1,7) = '"+month+"' "
					+" left join t_customs_material t5 on t1.`No`=t5.`No`                                                  "
					+" left join t_customs_product t7 " 
					+" on t4.shipmentIems=t7.materialNo "
					//+" where t1.`Month` = '"+month+"'                                                                                          "
					+" group by                                                                                                                "
					+" t1.MaterialNo,                                                                                                          "
					+" t1.JdeMaterialNo,                                                                                                       "
					+" t1.`No`,                                                                                                                "
					+" t1.ProductNo,                                                                                                           "
					+" t1.MaterialName,                                                                                                        "
					+" t1.MaterialSpecification,                                                                                               "
					+" t1.Unit,                                                                                                                "
					+" t1.EarlyNumber,      "
					+ "T2.Id,T2.Quantity                                                                             ";
			earlyTable=" select 1 from t_customs_general_init t8 where  t8.`No`=t1.`No`";
		
		}
		
		
		String sql=" select total.total.`Month`,"
				+ "total.MaterialNo,"
				+ "total.JdeMaterialNo,"
				+ "total.`No`,"
				+ "total.ProductNo,"
				+ "total.MaterialName,"
				+ "total.MaterialSpecification,"
				+ "total.Unit,"
				+ "CASE when total.Unit='米' "
				+ " then FORMAT(total.EarlyNumber,4) else  FORMAT(total.EarlyNumber,2) end as EarlyNumber,"
				+ " CASE when total.Unit='米' "
				+ " then FORMAT(IFNULL(sum(total.Quantity),0),4) else FORMAT(IFNULL(sum(total.Quantity),0),2) end as IncomingVolume,"
				+ " CASE when total.Unit='米' "
				+ " then  FORMAT(total.WriteOffVolume,4) else FORMAT(total.WriteOffVolume,2) end AS WriteOffVolume,"
				+ "CASE when total.Unit='米' "
				+ " then FORMAT(total.EarlyNumber + IFNULL(sum(total.Quantity),0)-total.WriteOffVolume,4) " 
				+ " else FORMAT(total.EarlyNumber + IFNULL(sum(total.Quantity),0)-total.WriteOffVolume,2) end as RegulatoryInventory,"
				+ "total.pickingVolume,"
				+ "total.warehouseVolume,"
				+ "MAX(total.price) AS price,"
				+ "total.currency,"
				+ " round(MAX(total.price)*(total.EarlyNumber + IFNULL(sum(total.Quantity),0)-total.WriteOffVolume),2) AS amount"
				+ " from (         "
				+" select                                                                                                                  "
				+" substr(t1.EntryDate,1,7) AS `Month`,                                                                                "
				+" t3.MaterialNo,                                                                                                          "
				+" IFNULL(t4.NewMaterialNo,t1.CimtasCode) AS JdeMaterialNo,                                                          "
				+" t1.`No`,                                                                                                                "
				+" t3.ProductNo,                                                                                                           "
				+" t3.MaterialName,                                                                                                        "
				+" t3.Specification as MaterialSpecification,                                                                              "
				+" IFNULL(t6.`value`,t3.DeclareUnitCode) as Unit,                                                                          "
				+" 0 as EarlyNumber,                                                                                                       "
				+" t1.Id,"
				+" t1.Quantity ,"
				+ " CASE when IFNULL(t6.`value`,t3.DeclareUnitCode)='米' "
				+ " and IFNULL(t4.NewMaterialNo,t1.CimtasCode) not like 'FREE0%'"
				+ " and IFNULL(t4.NewMaterialNo,t1.CimtasCode) not like 'FREE1%'"
				+ " and IFNULL(t4.NewMaterialNo,t1.CimtasCode) not like 'FREE2%'"
				+ " and IFNULL(t4.NewMaterialNo,t1.CimtasCode) not like 'FREE3%'"
				+ " and IFNULL(t4.NewMaterialNo,t1.CimtasCode) not like 'FREE4%'"
				+ " and IFNULL(t4.NewMaterialNo,t1.CimtasCode) not like 'FREE5%'"
				+ " and IFNULL(t4.NewMaterialNo,t1.CimtasCode) not like 'FREE6%'"
				+ " and IFNULL(t4.NewMaterialNo,t1.CimtasCode) not like 'FREE7%'"
				+ " and IFNULL(t4.NewMaterialNo,t1.CimtasCode) not like 'FREE8%'"
				+ " and IFNULL(t4.NewMaterialNo,t1.CimtasCode) not like 'FREE9%'"
				+ " and t8.`no` not in ('99991','99992','99993','99994','99995')"
				+" then IFNULL(sum(t2.quantityIssued*1.03/1000.0),0) else  IFNULL(sum(t2.quantityIssued),0) end as WriteOffVolume,                                                                               "
				+ " null as pickingVolume,"
				+ " null as warehouseVolume,"
				+ " t1.unitPriceUSD as price,"
				+ " 'USD' AS currency  "
				+" FROM t_customs_importsandexports t1                                                                                     "
				+" left join t_customs_clearance t2 on t1.`No`=t2.`No`                                                                     "
				+" and substr(t1.EntryDate,1,7) = substr(t2.BOMDate,1,7)                                                         "
				+" left join t_customs_material t3 on t1.`No`=t3.`No`                                                                      "
				+" left join (                                                                                                             "
				+" select IFNULL(tt2.NewMaterialNo,tt1.CimtasLongItemNo) as NewMaterialNo,tt1.TransQTY,tt1.OperationDate                      "
				+" from t_customs_jde tt1 left join t_customs_material_mapping tt2 on tt1.CimtasLongItemNo=tt2.OldMaterialNo               "
				+" ) t4 on t4.NewMaterialNo=t1.CimtasCode and substr(t1.EntryDate,1,7)=substr(t4.OperationDate,1,7)                                   "
				+" left join t_dict t6 on t6.type='CUSTOMS_UNIT' and t6.`key`=t3.DeclareUnitCode                                          "
				+" left join t_customs_product t8 " 
				+" on t2.shipmentIems=t8.materialNo "
				+" where substr(t1.EntryDate,1,7)='"+month+"'                                                                          "
				+" and not EXISTS(                                                                                                         "
				+ earlyTable
				+" )                                                                                                                       "
				+" group by                                                                                                                "
				+" substr(t1.EntryDate,1,7) ,                                                                                          "
				+" t3.MaterialNo,                                                                                                          "
				+" IFNULL(t4.NewMaterialNo,t1.CimtasCode) ,                                                                           "
				+" t1.`No`,                                                                                                                "
				+" t3.ProductNo,                                                                                                           "
				+" t3.MaterialName,                                                                                                        "
				+" t3.Specification,                                                                                                       "
				+" IFNULL(t6.`value`,t3.DeclareUnitCode) ,"
				+ "t1.Id, "
				+ "t1.Quantity  "
				+ earlySQL
				+" ) total                                                                                                                 "
				+ where
				+" group by " 
				+"total.`Month`," 
				+"total.MaterialNo," 
				+"total.JdeMaterialNo," 
				+"total.`No`," 
				+"total.ProductNo," 
				+"total.MaterialName," 
				+"total.MaterialSpecification," 
				+"total.Unit," 
				+"total.EarlyNumber," 
				+"total.pickingVolume," 
				+"total.warehouseVolume," 
				+"total.currency "
				+" order by total.`No`                                                                                                     ";
		return sql;
		
	}
}
