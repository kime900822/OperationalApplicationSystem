package com.customs.action;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.customs.biz.CustomsClearanceBIZ;
import com.customs.model.CustomsClearance;
import com.customs.model.CustomsImportsAndExports;
import com.customs.other.CustomsClearanceHelp;
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
public class CustomsClearanceAction extends ActionBase {

	@Autowired
	CustomsClearanceBIZ clearanceBIZ;
	
	String id;
	String no;
	String deliveryDate;
	String shipmentIems;
	String longProjectNo;
	String cimtasNo;
	String poseLongItemNo;
	String oldItemNo;
	String materialDescription;
	String dia;
	String sch;
	String quantityOrdered;
	String quantityIssued;
	String weight;
	String scn;
	String orTy;
	String orderNumber;
	String manufacName;
	String lotSerialNumber;
	String batchNumber;
	String operator;
	String operationDate;
	String BOMDate;
	String newBOMDate;
	
	
	
	public String getNewBOMDate() {
		return newBOMDate;
	}
	public void setNewBOMDate(String newBOMDate) {
		this.newBOMDate = newBOMDate;
	}
	public String getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public String getLongProjectNo() {
		return longProjectNo;
	}
	public void setLongProjectNo(String longProjectNo) {
		this.longProjectNo = longProjectNo;
	}
	public String getCimtasNo() {
		return cimtasNo;
	}
	public void setCimtasNo(String cimtasNo) {
		this.cimtasNo = cimtasNo;
	}
	public String getOldItemNo() {
		return oldItemNo;
	}
	public void setOldItemNo(String oldItemNo) {
		this.oldItemNo = oldItemNo;
	}
	public String getMaterialDescription() {
		return materialDescription;
	}
	public void setMaterialDescription(String materialDescription) {
		this.materialDescription = materialDescription;
	}
	public String getDia() {
		return dia;
	}
	public void setDia(String dia) {
		this.dia = dia;
	}
	public String getSch() {
		return sch;
	}
	public void setSch(String sch) {
		this.sch = sch;
	}
	public String getQuantityOrdered() {
		return quantityOrdered;
	}
	public void setQuantityOrdered(String quantityOrdered) {
		this.quantityOrdered = quantityOrdered;
	}
	public String getQuantityIssued() {
		return quantityIssued;
	}
	public void setQuantityIssued(String quantityIssued) {
		this.quantityIssued = quantityIssued;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getScn() {
		return scn;
	}
	public void setScn(String scn) {
		this.scn = scn;
	}
	public String getOrTy() {
		return orTy;
	}
	public void setOrTy(String orTy) {
		this.orTy = orTy;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getManufacName() {
		return manufacName;
	}
	public void setManufacName(String manufacName) {
		this.manufacName = manufacName;
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
	public String getLotSerialNumber() {
		return lotSerialNumber;
	}
	public void setLotSerialNumber(String lotSerialNumber) {
		this.lotSerialNumber = lotSerialNumber;
	}
	public String getPoseLongItemNo() {
		return poseLongItemNo;
	}
	public void setPoseLongItemNo(String poseLongItemNo) {
		this.poseLongItemNo = poseLongItemNo;
	}
	public String getShipmentIems() {
		return shipmentIems;
	}
	public void setShipmentIems(String shipmentIems) {
		this.shipmentIems = shipmentIems;
	}
	public String getBOMDate() {
		return BOMDate;
	}
	public void setBOMDate(String bOMDate) {
		BOMDate = bOMDate;
	}
	public String getBatchNumber() {
		return batchNumber;
	}
	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Action(value="deleteCustomsClearanceByBatchNumber",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String deleteCustomsClearanceByBatchNumber() throws UnsupportedEncodingException{
		if (!CommonUtil.isAdmin(getUser())) {
			result.setMessage("非管理员，没有权限！");
			result.setStatusCode("300");
			reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8"));
		}else{
			String r=clearanceBIZ.deleteByBatchNumber(batchNumber);
			if (r==null) {
				result.setMessage("Success!");
				result.setStatusCode("200");
				reslutJson=new ByteArrayInputStream((callback+"("+new Gson().toJson(null)+")").getBytes()); 
			}else{
				result.setMessage(r);
				result.setStatusCode("300");
				reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8")); 
			}
			
		}
		
		return SUCCESS;
	}

	@Action(value="customsClearanceEditNo",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String customsClearanceEditNo() throws UnsupportedEncodingException{
		String r=clearanceBIZ.editNo(id, no);
		if (r==null) {
			CustomsClearance clearance=clearanceBIZ.getById(id);
			result.setMessage("Success!");
			result.setStatusCode("200");
			reslutJson=new ByteArrayInputStream((callback+"("+new Gson().toJson(clearance)+")").getBytes()); 
		}else{
			result.setMessage(r);
			result.setStatusCode("300");
			reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8")); 
		}
		return SUCCESS;
	}
	
	@Action(value="customsClearanceBOMDate",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String customsClearanceBOMDate() throws UnsupportedEncodingException{
		
		String r=clearanceBIZ.customsClearanceBOMDate(getQueryWhere(), newBOMDate);
		if (r==null) {
			result.setMessage("Success!");
			result.setStatusCode("200");
		}else{
			result.setMessage(r);
			result.setStatusCode("300");
		}
		reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8")); 
		return SUCCESS;
	}
	
	@Action(value="queryCustomsClearance",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String queryCustomsClearance() throws UnsupportedEncodingException{	

		List list  =clearanceBIZ.query(getQueryWhere(), Integer.parseInt(pageSize),Integer.parseInt(pageCurrent));
		int total=clearanceBIZ.query(getQueryWhere()).size();
		
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
	
	
	@Action(value="exportCustomsClearance",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson",
					"contentType","application/vnd.ms-excel",
					"contentDisposition","attachment;filename=%{fileName}",
					"bufferSize","1024"
			})})
    public String exportCustomsClearance() {
        try {
        	List<HeadColumn> lHeadColumns=new Gson().fromJson(thead, new TypeToken<ArrayList<HeadColumn>>() {}.getType());
    		
        	ByteArrayInputStream  is = clearanceBIZ.exportData(getQueryWhere(),lHeadColumns);
        	
        	HttpServletResponse response = (HttpServletResponse)
        			ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_RESPONSE);
        	response.setHeader("Set-Cookie", "fileDownload=true; path=/");
        	
        	
    		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");		 
    		fileName = "CustomsClearance"+sf.format(new Date()).toString()+ ".xls";
    		fileName= new String(fileName.getBytes(), "ISO8859-1");
    		//文件流
            reslutJson = is;            
            logUtil.logInfo(CustomsClearanceHelp.title,"导出CustomsClearance！"+fileName);
        }
        catch(Exception e) {
        	logUtil.logInfo(CustomsClearanceHelp.title,"导出CustomsClearance！"+e.getMessage());
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
	@Action(value="importCustomsClearance",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
    public String  importCustomsClearance() throws FileNotFoundException, IOException{
        try {
	    	if (upfile!=null) {
				clearanceBIZ.importData(getUser(), upfile, first, upfileFileName[0], 2);
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
		
		String where =" where 1=1 ";
		if (no!=null&&!no.equals("")) {
			where+= " and no = '"+no+"' ";
		}
		if (deliveryDate!=null&&!deliveryDate.equals("")) {
			where+= " and deliveryDate like '%"+deliveryDate+"%' ";
		}
		if (shipmentIems!=null&&!shipmentIems.equals("")) {
			where+= " and shipmentIems = '"+shipmentIems+"' ";
		}
		if (longProjectNo!=null&&!longProjectNo.equals("")) {
			where+= " and longProjectNo = '"+longProjectNo+"' ";
		}
		if (cimtasNo!=null&&!cimtasNo.equals("")) {
			where+= " and cimtasNo = '"+cimtasNo+"' ";
		}
		if (poseLongItemNo!=null&&!poseLongItemNo.equals("")) {
			where+= " and poseLongItemNo like '%"+poseLongItemNo+"%' ";
		}
		if (oldItemNo!=null&&!oldItemNo.equals("")) {
			where+= " and oldItemNo like '%"+oldItemNo+"%' ";
		}
		if (materialDescription!=null&&!materialDescription.equals("")) {
			where+= " and materialDescription like '%"+materialDescription+"%' ";
		}
		if (dia!=null&&!dia.equals("")) {
			where+= " and dia like '%"+dia+"%' ";
		}
		if (sch!=null&&!sch.equals("")) {
			where+= " and sch like '%"+sch+"%' ";
		}
		if (quantityOrdered!=null&&!quantityOrdered.equals("")) {
			where+= " and quantityOrdered like '%"+quantityOrdered+"%' ";
		}
		if (quantityIssued!=null&&!quantityIssued.equals("")) {
			where+= " and quantityIssued like '%"+quantityIssued+"%' ";
		}
		if (weight!=null&&!weight.equals("")) {
			where+= " and weight like '%"+weight+"%' ";
		}
		if (scn!=null&&!scn.equals("")) {
			where+= " and scn like '%"+scn+"%' ";
		}
		if (orTy!=null&&!orTy.equals("")) {
			where+= " and orTy like '%"+orTy+"%' ";
		}
		if (orderNumber!=null&&!orderNumber.equals("")) {
			where+= " and orderNumber like '%"+orderNumber+"%' ";
		}
		if (manufacName!=null&&!manufacName.equals("")) {
			where+= " and manufacName like '%"+manufacName+"%' ";
		}
		if (lotSerialNumber!=null&&!lotSerialNumber.equals("")) {
			where+= " and lotSerialNumber like '%"+lotSerialNumber+"%' ";
		}
		if (batchNumber!=null&&!batchNumber.equals("")) {
			where+= " and batchNumber like '%"+batchNumber+"%' ";
		}
		if (operator!=null&&!operator.equals("")) {
			where+= " and operator like '%"+operator+"%' ";
		}
		if (operationDate!=null&&!operationDate.equals("")) {
			where+= " and operationDate like '%"+operationDate+"%' ";
		}
		if (BOMDate!=null&&!BOMDate.equals("")) {
			where+= " and BOMDate like '%"+BOMDate+"%' ";
		}
		if (newBOMDate!=null&&!newBOMDate.equals("")) {
			where+= " and BOMDate = '"+newBOMDate+"' ";
		}
		where += " order by operationDate desc";
		return where;
		
	}
	
}
