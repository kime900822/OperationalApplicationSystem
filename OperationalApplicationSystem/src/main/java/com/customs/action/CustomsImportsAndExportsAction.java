package com.customs.action;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.customs.biz.CustomsImportsAndExportsBIZ;
import com.customs.model.CustomsImportsAndExports;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kime.base.ActionBase;
import com.kime.infoenum.Message;
import com.kime.model.HeadColumn;
import com.kime.utils.CommonUtil;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
@ParentPackage("Customs")
public class CustomsImportsAndExportsAction extends ActionBase {

	@Autowired
	CustomsImportsAndExportsBIZ customsImportsAndExportsBIZ;
	
	String orderNumber;
	String cimtasCode;
	String entryDate_f;
	String entryDate_t;
	String id;
	String no;
	String batchNumber;
	String entryDate;
	String entryNo;
	String orTy;
	String name;
	String description;
	String quantity;
	String unit;
	String currency;
	String unitPrice;
	String amount;
	String unitPriceUSD;
	String amountUSD;
	String netWeight;
	String origin;
	String operator;
	String operationDate;
	
	
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public String getEntryNo() {
		return entryNo;
	}
	public void setEntryNo(String entryNo) {
		this.entryNo = entryNo;
	}
	public String getOrTy() {
		return orTy;
	}
	public void setOrTy(String orTy) {
		this.orTy = orTy;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getUnitPriceUSD() {
		return unitPriceUSD;
	}
	public void setUnitPriceUSD(String unitPriceUSD) {
		this.unitPriceUSD = unitPriceUSD;
	}
	public String getAmountUSD() {
		return amountUSD;
	}
	public void setAmountUSD(String amountUSD) {
		this.amountUSD = amountUSD;
	}
	public String getNetWeight() {
		return netWeight;
	}
	public void setNetWeight(String netWeight) {
		this.netWeight = netWeight;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getOperationDate() {
		return operationDate;
	}
	public void setOperationDate(String operationDate) {
		this.operationDate = operationDate;
	}
	public String getBatchNumber() {
		return batchNumber;
	}
	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public CustomsImportsAndExportsBIZ getCustomsImportsAndExportsBIZ() {
		return customsImportsAndExportsBIZ;
	}
	public void setCustomsImportsAndExportsBIZ(CustomsImportsAndExportsBIZ customsImportsAndExportsBIZ) {
		this.customsImportsAndExportsBIZ = customsImportsAndExportsBIZ;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getCimtasCode() {
		return cimtasCode;
	}
	public void setCimtasCode(String cimtasCode) {
		this.cimtasCode = cimtasCode;
	}
	public String getEntryDate_f() {
		return entryDate_f;
	}
	public void setEntryDate_f(String entryDate_f) {
		this.entryDate_f = entryDate_f;
	}
	public String getEntryDate_t() {
		return entryDate_t;
	}
	public void setEntryDate_t(String entryDate_t) {
		this.entryDate_t = entryDate_t;
	}
	@Action(value="deleteCustomsAndExportsEditNo",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String deleteCustomsAndExportsEditNo() throws UnsupportedEncodingException{
		
		if (!CommonUtil.isAdmin(getUser())) {
			result.setMessage("非管理员，没有权限！");
			result.setStatusCode("300");
		}else{
			String r=customsImportsAndExportsBIZ.deleteByBatchNumber(batchNumber);
			if (r==null) {
				result.setMessage("Success!");
				result.setStatusCode("200");
			}else{
				result.setMessage(r);
				result.setStatusCode("300");
			}
		}
		
		reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8"));  
		return SUCCESS;
	}
	
	@Action(value="importsCustomsAndExportsEditNo",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String importsCustomsAndExportsEditNo() throws UnsupportedEncodingException{
		String r=customsImportsAndExportsBIZ.editNo(id, no);
		if (r==null) {
			CustomsImportsAndExports customsImportsAndExports=customsImportsAndExportsBIZ.getById(id);
			result.setMessage("Success!");
			result.setStatusCode("200");
			reslutJson=new ByteArrayInputStream((callback+"("+new Gson().toJson(customsImportsAndExports)+")").getBytes()); 
		}else{
			result.setMessage(r);
			result.setStatusCode("300");
			reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8")); 
		}
		return SUCCESS;
	}
	
	@Action(value="queryCustomsImportsAndExports",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String queryCustomsImportsAndExports() throws UnsupportedEncodingException{	
		
		List list  =customsImportsAndExportsBIZ.query(getQueryWhere(), Integer.parseInt(pageSize),Integer.parseInt(pageCurrent));
		int total=customsImportsAndExportsBIZ.query(getQueryWhere()).size();
		
		queryResult.setList(list);
		queryResult.setTotalRow(total);
		queryResult.setFirstPage(Integer.parseInt(pageCurrent)==1?true:false);
		queryResult.setPageNumber(Integer.parseInt(pageCurrent));
		queryResult.setLastPage(total/Integer.parseInt(pageSize) +1==Integer.parseInt(pageCurrent)&&Integer.parseInt(pageCurrent)!=1?true:false);
		queryResult.setTotalPage(total/Integer.parseInt(pageSize) +1);
		queryResult.setPageSize(Integer.parseInt(pageSize));
		String r=callback+"("+new Gson().toJson(queryResult)+")";
		
		reslutJson=new ByteArrayInputStream(r.getBytes("UTF-8"));  
		
		logUtil.logInfo("查询进（进出口）数据，条件:"+getQueryWhere());
		return SUCCESS;
	}
	
	 /**
     * excel导出
     * @return
     */
	@Action(value="exportCustomsImportsAndExports",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson",
					"contentType","application/vnd.ms-excel",
					"contentDisposition","attachment;filename=%{fileName}",
					"bufferSize","1024"
			})})
    public String exportCustomsImportsAndExports() {
        try {
        	List<HeadColumn> lHeadColumns=new Gson().fromJson(thead, new TypeToken<ArrayList<HeadColumn>>() {}.getType());
    		
        	ByteArrayInputStream  is = customsImportsAndExportsBIZ.exportData(getQueryWhere(),lHeadColumns);
        	
        	HttpServletResponse response = (HttpServletResponse)
        			ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_RESPONSE);
        	response.setHeader("Set-Cookie", "fileDownload=true; path=/");
        	
        	
    		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");		 
    		fileName = "ImportsAndExports"+sf.format(new Date()).toString()+ ".xls";
    		fileName= new String(fileName.getBytes(), "ISO8859-1");
    		//文件流
            reslutJson = is;            
            logUtil.logInfo("导出ImportsAndExports！"+fileName);
        }
        catch(Exception e) {
        	logUtil.logInfo("导出ImportsAndExports！"+e.getMessage());
            e.printStackTrace();
        }

        return "success";
    }
	
	
	/**
     * excel导入
     * @return
     * @throws IOException 
     * @throws FileNotFoundException 
     */
	@Action(value="importCustomsImportsAndExports",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
    public String  importCustomsImportsAndExports() throws FileNotFoundException, IOException{
        try {
	    	if (upfile!=null) {
				customsImportsAndExportsBIZ.importData(getUser(), upfile, first, upfileFileName[0], 2);
				result.setMessage(Message.UPLOAD_MESSAGE_SUCCESS);
				result.setStatusCode("200");
			}else{
				
				result.setMessage(Message.UPLOAD_MESSAGE_ERROE);
				result.setStatusCode("300");
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			result.setStatusCode("300");
		}
        reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8"));  
    	return SUCCESS;
    }
	
	public String getQueryWhere() {
		String where=" where 1=1 ";
		
		if (entryDate!=null&&!entryDate.equals("")) {
			where+= " and entryDate like '%"+entryDate+"%' ";
		}
		if (entryDate_f!=null&&!entryDate_f.equals("")) {
			where+= " and entryDate >= '"+entryDate_f+"' ";
		}
		if (entryDate_t!=null&&!entryDate_t.equals("")) {
			where+= " and entryDate <= '"+entryDate_t+"' ";
		}
		if (entryNo!=null&&!entryNo.equals("")) {
			where+= " and entryNo like '%"+entryNo+"%' ";
		}
		if (orTy!=null&&!orTy.equals("")) {
			where+= " and orTy like '%"+orTy+"%' ";
		}
		if (orderNumber!=null&&!orderNumber.equals("")) {
			where+= " and orderNumber like '%"+orderNumber+"%' ";
		}
		if (cimtasCode!=null&&!cimtasCode.equals("")) {
			where+= " and cimtasCode like '%"+cimtasCode+"%' ";
		}
		if (no!=null&&!no.equals("")) {
			where+= " and no like '%"+no+"%' ";
		}
		if (name!=null&&!name.equals("")) {
			where+= " and name like '%"+name+"%' ";
		}
		if (description!=null&&!description.equals("")) {
			where+= " and description like '%"+description+"%' ";
		}
		if (quantity!=null&&!quantity.equals("")) {
			where+= " and quantity like '%"+quantity+"%' ";
		}
		if (unit!=null&&!unit.equals("")) {
			where+= " and unit like '%"+unit+"%' ";
		}
		if (currency!=null&&!currency.equals("")) {
			where+= " and currency like '%"+currency+"%' ";
		}
		if (unitPrice!=null&&!unitPrice.equals("")) {
			where+= " and unitPrice like '%"+unitPrice+"%' ";
		}
		if (amount!=null&&!amount.equals("")) {
			where+= " and amount like '%"+amount+"%' ";
		}
		if (unitPriceUSD!=null&&!unitPriceUSD.equals("")) {
			where+= " and unitPriceUSD like '%"+unitPriceUSD+"%' ";
		}
		if (amountUSD!=null&&!amountUSD.equals("")) {
			where+= " and amountUSD like '%"+amountUSD+"%' ";
		}
		if (netWeight!=null&&!netWeight.equals("")) {
			where+= " and netWeight like '%"+netWeight+"%' ";
		}
		if (origin!=null&&!origin.equals("")) {
			where+= " and origin like '%"+origin+"%' ";
		}
		if (operator!=null&&!operator.equals("")) {
			where+= " and operator like '%"+operator+"%' ";
		}
		if (operationDate!=null&&!operationDate.equals("")) {
			where+= " and operationDate like '%"+operationDate+"%' ";
		}
		if (batchNumber!=null&&!batchNumber.equals("")) {
			where+= " and batchNumber like '%"+batchNumber+"%' ";
		}
		
		
		
		return where;
	}
}
