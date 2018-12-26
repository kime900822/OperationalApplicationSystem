package com.customs.biz.impl;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.xmlbeans.impl.xb.xsdschema.impl.PublicImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.customs.biz.CustomsGeneralBIZ;
import com.customs.dao.CustomsGeneralDAO;
import com.customs.model.CustomsGeneral;
import com.customs.model.CustomsGeneralInit;
import com.customs.model.CustomsJDE;
import com.customs.other.CustomsGeneralHelp;
import com.customs.other.CustomsJDEHelp;
import com.kime.base.BizBase;
import com.kime.dao.CommonDAO;
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
	public List<CustomsGeneral> query(String month) throws Exception {
		
		if (month==null) {
			return new ArrayList<>();
		}
		if (!month.equals(CommonUtil.getMonth())&&checkGeneral(month)) {
			return customsGeneralDAO.query(" where month='"+month+"'");
		}
		String sql=" select total.*,  '' AS id, '' as pickingVolume,'' as warehouseVolume,'' as price,'' as amount from (                                                                                                   "
				+" select                                                                                                                  "
				+" substr(t1.OperationDate,1,7) AS `Month`,                                                                                "
				+" t3.MaterialNo,                                                                                                          "
				+" IFNULL(t5.NewMaterialNo,t4.CimtasLongItemNo) AS JdeMaterialNo,                                                          "
				+" t1.`No`,                                                                                                                "
				+" t3.ProductNo,                                                                                                           "
				+" t3.MaterialName,                                                                                                        "
				+" t3.Specification as MaterialSpecification,                                                                              "
				+" IFNULL(t6.`value`,t3.DeclareUnitCode) as Unit,                                                                          "
				+" 0 as EarlyNumber,                                                                                                       "
				+" sum(t4.TransQTY) as IncomingVolume,                                                                                     "
				+" sum(t7.QuantityIssued) as WriteOffVolume,                                                                               "
				+" sum(t4.TransQTY)-sum(t7.QuantityIssued) as RegulatoryInventory                                                          "
				+" FROM t_customs_importsandexports t1                                                                                     "
				+" left join t_customs_clearance t2 on t1.`No`=t2.`No`                                                                     "
				+" and substr(t1.OperationDate,1,7) = substr(t2.OperationDate,1,7)                                                         "
				+" left join t_customs_material t3 on t1.`No`=t3.`No`                                                                      "
				+" left join t_customs_jde t4 on t1.CimtasCode=t4.CimtasLongItemNo                                                   "
				+" and substr(t1.OperationDate,1,7) = substr(t4.OperationDate,1,7)                                                         "
				+" left join t_customs_material_mapping t5 on t4.CimtasLongItemNo=t5.OldMaterialNo                                         "
				+" left join t_dict t6 on t6.type='CUSTOMS_UNIT' and t6.`key`=t3.DeclareUnitCode                                           "
				+" left join t_customs_clearance t7 on IFNULL(t5.NewMaterialNo,t4.CimtasLongItemNo)=t7.PoseLongItemNo                      "
				+" and substr(t1.OperationDate,1,7) = substr(t7.OperationDate,1,7)                                                         "
				+" where substr(t1.OperationDate,1,7)='"+month+"'                                                                          "
				+" and not EXISTS(                                                                                                         "
				+" select 1 from t_customs_general_init t8 where  t8.`No`=t1.`No`        "
				+" )                                                                                                                       "
				+" group by                                                                                                                "
				+" substr(t1.OperationDate,1,7) ,                                                                                          "
				+" t3.MaterialNo,                                                                                                          "
				+" IFNULL(t5.NewMaterialNo,t4.CimtasLongItemNo),                                                                           "
				+" t1.`No`,                                                                                                                "
				+" t3.ProductNo,                                                                                                           "
				+" t3.MaterialName,                                                                                                        "
				+" t3.Specification,                                                                                                       "
				+" IFNULL(t6.`value`,t3.DeclareUnitCode)                                                                                   "
				+ getEarlySQL(month)
				+" ) total                                                                                                                 "
				+" order by total.`No`                                                                                                     ";
		return commonDAO.queryBySql(sql);
		
		
	}

	@Override
	public List<CustomsGeneral> query(String month, int pageSize, int pageCurrent) throws Exception {
		
		String hql="";
		if (month==null) {
			return new ArrayList<>();
		}
		if (!month.equals(CommonUtil.getMonth())&&checkGeneral(month)) {
			return customsGeneralDAO.query4init(" where month='"+month+"'", pageSize, pageCurrent);
		}
		String sql=" select total.*, '' AS id, '' as pickingVolume,'' as warehouseVolume,'' as price,'' as amount from (                   "
				+" select                                                                                                                  "
				+" substr(t1.OperationDate,1,7) AS `Month`,                                                                                "
				+" t3.MaterialNo,                                                                                                          "
				+" IFNULL(t5.NewMaterialNo,t4.CimtasLongItemNo) AS JdeMaterialNo,                                                          "
				+" t1.`No`,                                                                                                                "
				+" t3.ProductNo,                                                                                                           "
				+" t3.MaterialName,                                                                                                        "
				+" t3.Specification as MaterialSpecification,                                                                              "
				+" IFNULL(t6.`value`,t3.DeclareUnitCode) as Unit,                                                                          "
				+" 0 as EarlyNumber,                                                                                                       "
				+" sum(t4.TransQTY) as IncomingVolume,                                                                                     "
				+" sum(t7.QuantityIssued) as WriteOffVolume,                                                                               "
				+" sum(t4.TransQTY)-sum(t7.QuantityIssued) as RegulatoryInventory                                                          "
				+" FROM t_customs_importsandexports t1                                                                                     "
				+" left join t_customs_clearance t2 on t1.`No`=t2.`No`                                                                     "
				+" and substr(t1.OperationDate,1,7) = substr(t2.OperationDate,1,7)                                                         "
				+" left join t_customs_material t3 on t1.`No`=t3.`No`                                                                      "
				+" left join t_customs_jde t4 on t1.CimtasCode=t4.CimtasLongItemNo                                                   "
				+" and substr(t1.OperationDate,1,7) = substr(t4.OperationDate,1,7)                                                         "
				+" left join t_customs_material_mapping t5 on t4.CimtasLongItemNo=t5.OldMaterialNo                                         "
				+" left join t_dict t6 on t6.type='CUSTOMS_UNIT' and t6.`key`=t3.DeclareUnitCode                                           "
				+" left join t_customs_clearance t7 on IFNULL(t5.NewMaterialNo,t4.CimtasLongItemNo)=t7.PoseLongItemNo                      "
				+" and substr(t1.OperationDate,1,7) = substr(t7.OperationDate,1,7)                                                         "
				+" where substr(t1.OperationDate,1,7)='"+month+"'                                                                          "
				+" and not EXISTS(                                                                                                         "
				+" select 1 from t_customs_general t8 where t8.`Month`=substr(t1.OperationDate,1,7) and t8.MaterialNo=t3.MaterialNo        "
				+" )                                                                                                                       "
				+" group by                                                                                                                "
				+" substr(t1.OperationDate,1,7) ,                                                                                          "
				+" t3.MaterialNo,                                                                                                          "
				+" IFNULL(t5.NewMaterialNo,t4.CimtasLongItemNo),                                                                           "
				+" t1.`No`,                                                                                                                "
				+" t3.ProductNo,                                                                                                           "
				+" t3.MaterialName,                                                                                                        "
				+" t3.Specification,                                                                                                       "
				+" IFNULL(t6.`value`,t3.DeclareUnitCode)                                                                                   "
				+ getEarlySQL(month)
				+" ) total                                                                                                                 "
				+" order by total.`No`                                                                                                     ";
		return dataToEntity(commonDAO.queryBySql(sql, pageSize, pageCurrent));
		
	}


	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public String saveData(String month) throws Exception {
		String sql=" select total.*, '' AS id, null as pickingVolume,null as warehouseVolume,null as price,null as amount from (         "
				+" select                                                                                                                  "
				+" substr(t1.OperationDate,1,7) AS `Month`,                                                                                "
				+" t3.MaterialNo,                                                                                                          "
				+" IFNULL(t5.NewMaterialNo,t4.CimtasLongItemNo) AS JdeMaterialNo,                                                          "
				+" t1.`No`,                                                                                                                "
				+" t3.ProductNo,                                                                                                           "
				+" t3.MaterialName,                                                                                                        "
				+" t3.Specification as MaterialSpecification,                                                                              "
				+" IFNULL(t6.`value`,t3.DeclareUnitCode) as Unit,                                                                          "
				+" 0 as EarlyNumber,                                                                                                       "
				+" sum(t4.TransQTY) as IncomingVolume,                                                                                     "
				+" sum(t7.QuantityIssued) as WriteOffVolume,                                                                               "
				+" IFNULL(sum(t4.TransQTY),0)-IFNULL(sum(t7.QuantityIssued),0) as RegulatoryInventory                                                          "
				+" FROM t_customs_importsandexports t1                                                                                     "
				+" left join t_customs_clearance t2 on t1.`No`=t2.`No`                                                                     "
				+" and substr(t1.OperationDate,1,7) = substr(t2.OperationDate,1,7)                                                         "
				+" left join t_customs_material t3 on t1.`No`=t3.`No`                                                                      "
				+" left join t_customs_jde t4 on t1.CimtasCode=t4.CimtasLongItemNo                                                   "
				+" and substr(t1.OperationDate,1,7) = substr(t4.OperationDate,1,7)                                                         "
				+" left join t_customs_material_mapping t5 on t4.CimtasLongItemNo=t5.OldMaterialNo                                         "
				+" left join t_dict t6 on t6.type='CUSTOMS_UNIT' and t6.`key`=t3.DeclareUnitCode                                           "
				+" left join t_customs_clearance t7 on IFNULL(t5.NewMaterialNo,t4.CimtasLongItemNo)=t7.PoseLongItemNo                      "
				+" and substr(t1.OperationDate,1,7) = substr(t7.OperationDate,1,7)                                                         "
				+" where substr(t1.OperationDate,1,7)='"+month+"'                                                                          "
				+" and not EXISTS(                                                                                                         "
				+" select 1 from t_customs_general t8 where t8.`Month`=substr(t1.OperationDate,1,7) and t8.MaterialNo=t3.MaterialNo        "
				+" )                                                                                                                       "
				+" group by                                                                                                                "
				+" substr(t1.OperationDate,1,7) ,                                                                                          "
				+" t3.MaterialNo,                                                                                                          "
				+" IFNULL(t5.NewMaterialNo,t4.CimtasLongItemNo),                                                                           "
				+" t1.`No`,                                                                                                                "
				+" t3.ProductNo,                                                                                                           "
				+" t3.MaterialName,                                                                                                        "
				+" t3.Specification,                                                                                                       "
				+" IFNULL(t6.`value`,t3.DeclareUnitCode)                                                                                   "
				+ getEarlySQL(month)
				+" ) total                                                                                                                 "
				+" order by total.`No`                                                                                                     ";
		
		List<CustomsGeneral> list= dataToEntity(commonDAO.queryBySql(sql));
		
		if (list.size()>0) {
			try {
				if (checkGeneral(list.get(0).getMonth())) {
					commonDAO.executeHQL(" delete from CustomsGeneral where month='"+list.get(0).getMonth()+"'");
				}
				for (CustomsGeneral customsGeneral : list) {
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

	public String getEarlySQL(String month) throws Exception {
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
			flag=true;
		}
		if (flag) {
			
			return 	" UNION all                                                                                                                "
					+" select                                                                                                                  "
					+"'"+month +"'AS `Month`,                                                                                                             "
					+" t1.MaterialNo,                                                                                                          "
					+" t1.JdeMaterialNo,                                                                                                       "
					+" t1.`No`,                                                                                                                "
					+" t1.ProductNo,                                                                                                           "
					+" t1.MaterialName,                                                                                                        "
					+" t1.MaterialSpecification,                                                                                               "
					+" t1.Unit,                                                                                                                "
					+" t1.EarlyNumber,                                                                                                         "
					+" sum(t3.TransQTY) as IncomingVolume,                                                                                     "
					+" sum(t4.QuantityIssued) as WriteOffVolume,                                                                               "
					+" t1.EarlyNumber + IFNULL(sum(t3.TransQTY),0)-IFNULL(sum(t4.QuantityIssued),0) as RegulatoryInventory                                                          "
					+" from t_customs_general_init t1                                                                                          "
					+" left join t_customs_importsandexports t2                                                                                "
					+" on t1.`No`=t2.`No`                                "
					+" left join (                                                                                                             "
					+" select IFNULL(tt2.NewMaterialNo,tt1.CimtasLongItemNo) newMarerialNo,tt1.TransQTY,tt1.OperationDate                      "
					+" from t_customs_jde tt1 left join t_customs_material_mapping tt2 on tt1.CimtasLongItemNo=tt2.OldMaterialNo               "
					+" ) t3 on t3.newMarerialNo=t1.JdeMaterialNo                                  "
					+" left join t_customs_clearance t4 on t1.JdeMaterialNo=t4.PoseLongItemNo                                                  "
					//+" where t1.`Month` = '"+month+"'                                                                                          "
					+" group by                                                                                                                "
					+" t1.MaterialNo,                                                                                                          "
					+" t1.JdeMaterialNo,                                                                                                       "
					+" t1.`No`,                                                                                                                "
					+" t1.ProductNo,                                                                                                           "
					+" t1.MaterialName,                                                                                                        "
					+" t1.MaterialSpecification,                                                                                               "
					+" t1.Unit,                                                                                                                "
					+" t1.EarlyNumber                                                                                                          ";
		}else {
			return 	" UNION all                                                                                                                "
					+" select                                                                                                                  "
					+" t1.`Month`,                                                                                                             "
					+" t1.MaterialNo,                                                                                                          "
					+" t1.JdeMaterialNo,                                                                                                       "
					+" t1.`No`,                                                                                                                "
					+" t1.ProductNo,                                                                                                           "
					+" t1.MaterialName,                                                                                                        "
					+" t1.MaterialSpecification,                                                                                               "
					+" t1.Unit,                                                                                                                "
					+" t1.EarlyNumber,                                                                                                         "
					+" sum(t3.TransQTY) as IncomingVolume,                                                                                     "
					+" sum(t4.QuantityIssued) as WriteOffVolume,                                                                               "
					+" t1.EarlyNumber-IFNULL(sum(t3.TransQTY),0)-IFNULL(sum(t4.QuantityIssued),0) as RegulatoryInventory                                                          "
					+" from CustomsGeneral t1                                                                                          "
					+" left join t_customs_importsandexports t2                                                                                "
					+" on t1.`No`=t2.`No` and t1.`Month`=substr(t2.OperationDate,1,7)                                                          "
					+" left join (                                                                                                             "
					+" select IFNULL(tt2.NewMaterialNo,tt1.CimtasLongItemNo) newMarerialNo,tt1.TransQTY,tt1.OperationDate                      "
					+" from t_customs_jde tt1 left join t_customs_material_mapping tt2 on tt1.CimtasLongItemNo=tt2.OldMaterialNo               "
					+" ) t3 on t3.newMarerialNo=t1.JdeMaterialNo and t1.`Month`=substr(t3.OperationDate,1,7)                                   "
					+" left join t_customs_clearance t4 on t1.JdeMaterialNo=t4.PoseLongItemNo                                                  "
					+" and substr(t4.OperationDate,1,7) = t1.`Month`                                                                           "
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
					+" t1.EarlyNumber                                                                                                          ";
		}
		
		
		
	}
	
	
	public List<CustomsGeneral> dataToEntity(List list) {
		List<CustomsGeneral> lGenerals=new ArrayList<>();
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
			cGeneral.setAmount(String.valueOf(tmp[15]));
			lGenerals.add(cGeneral);
					
		}
		return lGenerals;
	}
	
}
