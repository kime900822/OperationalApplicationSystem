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

import com.customs.biz.CustomsReportBIZ;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kime.base.ActionBase;
import com.kime.model.HeadColumn;
import com.opensymphony.xwork2.ActionContext;

public class CustomsReportAction extends ActionBase {

	@Autowired
	CustomsReportBIZ customsReportBIZ;
	
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
	public String queryCustomsReport2() throws UnsupportedEncodingException{	
		
		String where=" where 1=1 ";
		if (!"".equals(materialNo)&&materialNo!=null) {
			where+=" and A.cimtasCode = '"+materialNo+"' ";
		}		
		if (!"".equals(orderNumber)&&orderNumber!=null) {
			where+=" and A.orderNumber = '"+orderNumber+"' ";
		}	
		if (!"".equals(cimtasLongItemNo)&&cimtasLongItemNo!=null) {
			where+=" and coalesce(C.newMaterialNo,A.cimtasCode) = '"+cimtasLongItemNo+"' ";
		}	
		if (!"".equals(dvalue)&&dvalue!=null) {
			if (dvalue=="0") {
				where+=" and coalesce(D.transQTY,0)-A.quantity = 0 ";
			}else if (dvalue=="1") {
				where+=" and coalesce(D.transQTY,0)-A.quantity > 0 ";
			}else {
				where+=" and coalesce(D.transQTY,0)-A.quantity < 0 ";
			}
		}	
		
		List list  =customsReportBIZ.queryReport2(where, Integer.parseInt(pageSize),Integer.parseInt(pageCurrent));
		int total=customsReportBIZ.queryReport2(where).size();
		
		queryResult.setList(list);
		queryResult.setTotalRow(total);
		queryResult.setFirstPage(Integer.parseInt(pageCurrent)==1?true:false);
		queryResult.setPageNumber(Integer.parseInt(pageCurrent));
		queryResult.setLastPage(total/Integer.parseInt(pageSize) +1==Integer.parseInt(pageCurrent)&&Integer.parseInt(pageCurrent)!=1?true:false);
		queryResult.setTotalPage(total/Integer.parseInt(pageSize) +1);
		queryResult.setPageSize(Integer.parseInt(pageSize));
		String r=callback+"("+new Gson().toJson(queryResult)+")";
		
		reslutJson=new ByteArrayInputStream(r.getBytes("UTF-8"));  
		
		logUtil.logInfo("查询生成表2");
		return SUCCESS;
	}
	
	
	@Action(value="queryCustomsReport1",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String queryCustomsReport1() throws UnsupportedEncodingException{	
		
		String where=" where 1=1 ";
		if (!"".equals(materialNo)&&materialNo!=null) {
			where+=" and A.no = '"+materialNo+"' ";
		}		
		if (!"".equals(productNo)&&productNo!=null) {
			where+=" AND B.no = '"+productNo+"'  ";
		}	
		if (!"".equals(batchNumber)&&batchNumber!=null) {
			where+=" AND A.batchNumber = '"+batchNumber+"'  ";
		}	
		
		where+=" order by A.no desc";
		
		List list  =customsReportBIZ.queryReport1(where, Integer.parseInt(pageSize),Integer.parseInt(pageCurrent));
		int total=customsReportBIZ.queryReport1(where).size();
		
		queryResult.setList(list);
		queryResult.setTotalRow(total);
		queryResult.setFirstPage(Integer.parseInt(pageCurrent)==1?true:false);
		queryResult.setPageNumber(Integer.parseInt(pageCurrent));
		queryResult.setLastPage(total/Integer.parseInt(pageSize) +1==Integer.parseInt(pageCurrent)&&Integer.parseInt(pageCurrent)!=1?true:false);
		queryResult.setTotalPage(total/Integer.parseInt(pageSize) +1);
		queryResult.setPageSize(Integer.parseInt(pageSize));
		String r=callback+"("+new Gson().toJson(queryResult)+")";
		
		reslutJson=new ByteArrayInputStream(r.getBytes("UTF-8"));  
		
		logUtil.logInfo("查询生成表1");
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
        	String where=" where 1=1 ";
    		if (!"".equals(materialNo)&&materialNo!=null) {
    			where+=" and A.no = '"+materialNo+"' ";
    		}		
    		if (!"".equals(productNo)&&productNo!=null) {
    			where+=" AND B.no = '"+productNo+"'  ";
    		}	
    		if (!"".equals(batchNumber)&&batchNumber!=null) {
    			where+=" AND A.batchNumber = '"+batchNumber+"'  ";
    		}	
    		
    		where+=" order by A.no desc";
        	
    		String hql=" select B.no,A.no,'1' ,"
    				+ " ROUND(A.quantityOrdered/1000.0,3),"
    				+ " case when B.declareUnitCode='030' then '0.03' else '0' end,"
    				+ "'0',"
    				+ "'',"
    				+ "'100',"
    				+ "'3',"
    				+ "'20420701',"
    				+ "'',"
    				+ "A.batchNumber,"
    				+ "ROUND(A.quantityOrdered*(1+(case when B.declareUnitCode='030' then 0.03 else 0 end))/1000.0,2), "
    				+ "'' "
    				+ " from CustomsClearance A "
    				+ " left join CustomsProduct B "
    				+ " on A.shipmentIems=B.materialNo"
    				+ where;
    		
    		
        	ByteArrayInputStream  is = customsReportBIZ.exportData(hql,lHeadColumns,"单耗表");
        	
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
}
