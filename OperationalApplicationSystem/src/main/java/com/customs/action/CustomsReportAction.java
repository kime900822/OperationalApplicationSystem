package com.customs.action;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import com.customs.biz.CustomsGeneralBIZ;
import com.customs.biz.CustomsReportBIZ;
import com.customs.model.CustomsReport1;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kime.base.ActionBase;
import com.kime.model.HeadColumn;
import com.kime.utils.CommonUtil;
import com.opensymphony.xwork2.ActionContext;

public class CustomsReportAction extends ActionBase {

	@Autowired
	CustomsReportBIZ customsReportBIZ;
	@Autowired
	CustomsGeneralBIZ customsGeneralBIZ;
	
	
	String materialNo;
	String productNo;
	String batchNumber;
	String orderNumber;
	String cimtasLongItemNo;
	String dvalue;
	
	
	public String getOrderNumber() {
		return orderNumber;
	}


	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}


	public String getCimtasLongItemNo() {
		return cimtasLongItemNo;
	}


	public void setCimtasLongItemNo(String cimtasLongItemNo) {
		this.cimtasLongItemNo = cimtasLongItemNo;
	}



	public String getDvalue() {
		return dvalue;
	}


	public void setDvalue(String dvalue) {
		this.dvalue = dvalue;
	}


	public String getMaterialNo() {
		return materialNo;
	}


	public void setMaterialNo(String materialNo) {
		this.materialNo = materialNo;
	}


	public String getProductNo() {
		return productNo;
	}


	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}


	public String getBatchNumber() {
		return batchNumber;
	}


	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}


	@Action(value="queryCustomsReport2",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String queryCustomsReport2(){	
		
		
		try {
			List list  =customsReportBIZ.queryReport(getReport2SQL(), Integer.parseInt(pageSize),Integer.parseInt(pageCurrent),"dataToListR2");
			int total=customsReportBIZ.queryReport(getReport2SQL()).size();
			
			queryResult.setList(list);
			queryResult.setTotalRow(total);
			queryResult.setFirstPage(Integer.parseInt(pageCurrent)==1?true:false);
			queryResult.setPageNumber(Integer.parseInt(pageCurrent));
			queryResult.setLastPage(total/Integer.parseInt(pageSize) +1==Integer.parseInt(pageCurrent)&&Integer.parseInt(pageCurrent)!=1?true:false);
			queryResult.setTotalPage(total/Integer.parseInt(pageSize) +1);
			queryResult.setPageSize(Integer.parseInt(pageSize));
			String r=callback+"("+new Gson().toJson(queryResult)+")";
			
			reslutJson=new ByteArrayInputStream(r.getBytes("UTF-8"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		return SUCCESS;
	}
	
	
	
	
	@Action(value="queryCustomsReport1",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String queryCustomsReport1() throws UnsupportedEncodingException{	
		
		try {
			List list = customsReportBIZ.queryReport(getReport1SQl(), Integer.parseInt(pageSize),Integer.parseInt(pageCurrent),"dataToListR1");
			int total=customsReportBIZ.queryReport(getReport1SQl()).size();
			
			queryResult.setList(list);
			queryResult.setTotalRow(total);
			queryResult.setFirstPage(Integer.parseInt(pageCurrent)==1?true:false);
			queryResult.setPageNumber(Integer.parseInt(pageCurrent));
			queryResult.setLastPage(total/Integer.parseInt(pageSize) +1==Integer.parseInt(pageCurrent)&&Integer.parseInt(pageCurrent)!=1?true:false);
			queryResult.setTotalPage(total/Integer.parseInt(pageSize) +1);
			queryResult.setPageSize(Integer.parseInt(pageSize));
			String r=callback+"("+new Gson().toJson(queryResult)+")";
			
			reslutJson=new ByteArrayInputStream(r.getBytes("UTF-8"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		return SUCCESS;
	}
	
	 /**
     * excel导出
     * @return
     */
	@Action(value="exportCustomsReport1",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson",
					"contentType","application/vnd.ms-excel",
					"contentDisposition","attachment;filename=%{fileName}",
					"bufferSize","1024"
			})})
    public String exportCustomsReport1() {
        try {
        	List<HeadColumn> lHeadColumns=new Gson().fromJson(thead, new TypeToken<ArrayList<HeadColumn>>() {}.getType());
    		
        	ByteArrayInputStream  is = customsReportBIZ.exportData(getReport1SQl(),lHeadColumns,"单耗表",new CustomsReport1().getClass());
        	
        	HttpServletResponse response = (HttpServletResponse)
        			ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_RESPONSE);
        	response.setHeader("Set-Cookie", "fileDownload=true; path=/");
        	
        	
    		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");		 
    		fileName = "单耗表"+sf.format(new Date()).toString()+ ".xls";
    		fileName= new String(fileName.getBytes(), "ISO8859-1");
    		//文件流
            reslutJson = is;            
            logUtil.logInfo("导出单耗表！"+fileName);
        }
        catch(Exception e) {
        	logUtil.logInfo("导出单耗表！"+e.getMessage());
            e.printStackTrace();
        }

        return "success";
    }
	
	public String getReport2SQL() {
		String where=" where 1=1 ";
		if (!"".equals(materialNo)&&materialNo!=null) {
			where+=" and t.cimtasCode = '"+materialNo+"' ";
		}		
		if (!"".equals(orderNumber)&&orderNumber!=null) {
			where+=" and t.orderNumber = '"+orderNumber+"' ";
		}	
		if (!"".equals(cimtasLongItemNo)&&cimtasLongItemNo!=null) {
			where+=" and t.newCimtasCode = '"+cimtasLongItemNo+"' ";
		}	
		if (!"".equals(dvalue)&&dvalue!=null) {
			if (dvalue.equals("0")) {
				where+=" and t.DValue = 0 ";
			}else if (dvalue.equals("1")) {
				where+=" and t.DValue > 0 ";
			}else {
				where+=" and t.DValue < 0 ";
			}
		}
		
		
		String sql=" select T.* from (select A.orderNumber,  "
				+ " A.cimtasCode,"
				+ " coalesce(C.newMaterialNo,A.cimtasCode) as newCimtasCode, "
				+ " sum(A.quantity) AS quantity,"
				+ " sum(D.transQTY) AS transQTY ,"
				+ " sum(coalesce(D.transQTY,0)-A.quantity) AS DValue "
				+ " from t_customs_importsandexports A "
				+ " left join t_customs_material_mapping C on "
				+ " C.oldMaterialNo=A.cimtasCode "
				+ " left join t_customs_jde D "
				+ " on coalesce(C.newMaterialNo,A.cimtasCode)=D.cimtasLongItemNo "
				+ " and A.orderNumber=D.orderNumber " 
				+ " group by A.orderNumber,A.cimtasCode,coalesce(C.newMaterialNo,A.cimtasCode) "
				+ " order by A.orderNumber,A.cimtasCode,coalesce(C.newMaterialNo,A.cimtasCode)) T "+where;
		
		return sql;
	}
	
	public String getReport1SQl() throws Exception {
		
       	String where=" where 1=1 ";
    		if (!"".equals(materialNo)&&materialNo!=null) {
    			where+=" and A.`no` = '"+materialNo+"' ";
    		}		
    		if (!"".equals(productNo)&&productNo!=null) {
    			where+=" AND B.`no` = '"+productNo+"'  ";
    		}	
    		if (!"".equals(batchNumber)&&batchNumber!=null) {
    			where+=" AND A.batchNumber = '"+batchNumber+"'  ";
    		}	
    		
    		String sql=" select B.`no` AS columen1,A.`no` AS columen2 ,'1' AS columen3,"
    				+ " ROUND(sum(A.quantityIssued)/1000.0*(case when C.declareUnitCode='030'  "
    				+ " and A.PoseLongItemNo not like 'FREE0%'"
    				+ " and A.PoseLongItemNo not like 'FREE1%'"
    				+ " and A.PoseLongItemNo not like 'FREE2%'"
    				+ " and A.PoseLongItemNo not like 'FREE3%'"
    				+ " and A.PoseLongItemNo not like 'FREE4%'"
    				+ " and A.PoseLongItemNo not like 'FREE5%'"
    				+ " and A.PoseLongItemNo not like 'FREE6%'"
    				+ " and A.PoseLongItemNo not like 'FREE7%'"
    				+ " and A.PoseLongItemNo not like 'FREE8%'"
    				+ " and A.PoseLongItemNo not like 'FREE9%'"
    				+ " and B.`no` not in ('99991','99992','99993','99994','99995')"
    				+ " then 1 else 1000.0 end),3) AS columen4,"
    				+ " case when C.declareUnitCode='030' "
    				+ " and A.PoseLongItemNo not like 'FREE0%'"
    				+ " and A.PoseLongItemNo not like 'FREE1%'"
    				+ " and A.PoseLongItemNo not like 'FREE2%'"
    				+ " and A.PoseLongItemNo not like 'FREE3%'"
    				+ " and A.PoseLongItemNo not like 'FREE4%'"
    				+ " and A.PoseLongItemNo not like 'FREE5%'"
    				+ " and A.PoseLongItemNo not like 'FREE6%'"
    				+ " and A.PoseLongItemNo not like 'FREE7%'"
    				+ " and A.PoseLongItemNo not like 'FREE8%'"
    				+ " and A.PoseLongItemNo not like 'FREE9%'"
    				+ " and B.`no` not in ('99991','99992','99993','99994','99995')"
    				+ " then '3' else '0' end AS columen5 ,"
    				+ "'0' AS  columen6 ,"
    				+ "'' AS columen7,"
    				+ "'100' AS columen8 ,"
    				+ "'3' AS columen9 ,"
    				+ "'20420701' AS columen10 ,"
    				+ "'' AS columen11,"
    				+ "A.batchNumber AS  columen12,"
    				+ "case when C.declareUnitCode='030' then "
    				+ " case when  A.PoseLongItemNo not like 'FREE0%'"
    				+ " and A.PoseLongItemNo not like 'FREE1%'"
    				+ " and A.PoseLongItemNo not like 'FREE2%'"
    				+ " and A.PoseLongItemNo not like 'FREE3%'"
    				+ " and A.PoseLongItemNo not like 'FREE4%'"
    				+ " and A.PoseLongItemNo not like 'FREE5%'"
    				+ " and A.PoseLongItemNo not like 'FREE6%'"
    				+ " and A.PoseLongItemNo not like 'FREE7%'"
    				+ " and A.PoseLongItemNo not like 'FREE8%'"
    				+ " and A.PoseLongItemNo not like 'FREE9%'"
    				+ " and B.`no` not in ('99991','99992','99993','99994','99995')"
    				+ " then FORMAT(ROUND(sum(A.quantityIssued)*1.03/1000.0,4),4) "
    				+ " else FORMAT(sum(A.quantityIssued),4) end  "
    				+ " else FORMAT(ROUND(sum(A.quantityIssued),2),2) END AS columen13,"
    				+ " (select total.RegulatoryInventory from ("+customsGeneralBIZ.getSQL(CommonUtil.getMonth(), "")+") total where total.`no`=A.`no`) as columen14"
    				+ " from t_customs_clearance A "
    				+ " left join t_customs_product B "
    				+ " on A.shipmentIems=B.materialNo"
    				+ " left join t_customs_material C "
    				+ " on A.`no`=C.`no` "
    				+ where
    				+ " group by B.`no`,A.`no`,C.declareUnitCode,A.batchNumber order by B.`no`,A.`no`";
		
		return sql;
	}
}
