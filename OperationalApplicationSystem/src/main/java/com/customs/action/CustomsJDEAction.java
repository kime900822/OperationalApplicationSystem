package com.customs.action;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.persistence.Column;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.customs.biz.CUstomsJDEBIZ;
import com.google.gson.Gson;
import com.kime.base.ActionBase;
import com.kime.infoenum.Message;
import com.kime.utils.CommonUtil;

@Controller
@Scope("prototype")
@ParentPackage("Customs")
public class CustomsJDEAction extends ActionBase {

	@Autowired
	CUstomsJDEBIZ customsJDEBIZ;
	String businessUnit;
	String orTy;
	String orderNumber;
	String orderDate;
	String location;
	String shortItemNo;
	String cimtasLongItemNo;
	String doTy;
	String transactionExplanation;
	String transQTY;
	String extendedCostPrice;
	String glDate;
	String documentNumber;
	String lotStatCode;
	String longProjectNo;
	String lotNumber;
	String userID;
	String materialLongDescription;
	String importNo;
	String heatNo;
	String NCRNo;
	String note;
	String weight;
	String purchaseOrderLineNumber;
	String note1;
	String operationDate;
	String batchNumber;

	public String getBatchNumber() {
		return batchNumber;
	}
	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}
	public String getBusinessUnit() {
		return businessUnit;
	}
	public void setBusinessUnit(String businessUnit) {
		this.businessUnit = businessUnit;
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
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getShortItemNo() {
		return shortItemNo;
	}
	public void setShortItemNo(String shortItemNo) {
		this.shortItemNo = shortItemNo;
	}
	public String getCimtasLongItemNo() {
		return cimtasLongItemNo;
	}
	public void setCimtasLongItemNo(String cimtasLongItemNo) {
		this.cimtasLongItemNo = cimtasLongItemNo;
	}
	public String getDoTy() {
		return doTy;
	}
	public void setDoTy(String doTy) {
		this.doTy = doTy;
	}
	public String getTransactionExplanation() {
		return transactionExplanation;
	}
	public void setTransactionExplanation(String transactionExplanation) {
		this.transactionExplanation = transactionExplanation;
	}
	public String getTransQTY() {
		return transQTY;
	}
	public void setTransQTY(String transQTY) {
		this.transQTY = transQTY;
	}
	public String getExtendedCostPrice() {
		return extendedCostPrice;
	}
	public void setExtendedCostPrice(String extendedCostPrice) {
		this.extendedCostPrice = extendedCostPrice;
	}
	public String getGlDate() {
		return glDate;
	}
	public void setGlDate(String glDate) {
		this.glDate = glDate;
	}
	public String getDocumentNumber() {
		return documentNumber;
	}
	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}
	public String getLotStatCode() {
		return lotStatCode;
	}
	public void setLotStatCode(String lotStatCode) {
		this.lotStatCode = lotStatCode;
	}
	public String getLongProjectNo() {
		return longProjectNo;
	}
	public void setLongProjectNo(String longProjectNo) {
		this.longProjectNo = longProjectNo;
	}
	public String getLotNumber() {
		return lotNumber;
	}
	public void setLotNumber(String lotNumber) {
		this.lotNumber = lotNumber;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getMaterialLongDescription() {
		return materialLongDescription;
	}
	public void setMaterialLongDescription(String materialLongDescription) {
		this.materialLongDescription = materialLongDescription;
	}
	public String getImportNo() {
		return importNo;
	}
	public void setImportNo(String importNo) {
		this.importNo = importNo;
	}
	public String getHeatNo() {
		return heatNo;
	}
	public void setHeatNo(String heatNo) {
		this.heatNo = heatNo;
	}
	public String getNCRNo() {
		return NCRNo;
	}
	public void setNCRNo(String nCRNo) {
		NCRNo = nCRNo;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getPurchaseOrderLineNumber() {
		return purchaseOrderLineNumber;
	}
	public void setPurchaseOrderLineNumber(String purchaseOrderLineNumber) {
		this.purchaseOrderLineNumber = purchaseOrderLineNumber;
	}
	public String getNote1() {
		return note1;
	}
	public void setNote1(String note1) {
		this.note1 = note1;
	}
	public String getOperationDate() {
		return operationDate;
	}
	public void setOperationDate(String operationDate) {
		this.operationDate = operationDate;
	}
	@Action(value="deleteCustomsJDE",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String deleteCustomsJDE() throws UnsupportedEncodingException {
		
		if (!CommonUtil.isAdmin(getUser())) {
			result.setMessage("非管理员，没有权限！");
			result.setStatusCode("300");
		}else{
			String r=customsJDEBIZ.deleteByBatchNumber(batchNumber);
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


	@Action(value="queryCustomsJDE",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String queryCustomsJDE() throws UnsupportedEncodingException{	
		
		List list  =customsJDEBIZ.query(getQueryWhere(), Integer.parseInt(pageSize),Integer.parseInt(pageCurrent));
		int total=customsJDEBIZ.query(getQueryWhere()).size();
		
		queryResult.setList(list);
		queryResult.setTotalRow(total);
		queryResult.setFirstPage(Integer.parseInt(pageCurrent)==1?true:false);
		queryResult.setPageNumber(Integer.parseInt(pageCurrent));
		queryResult.setLastPage(total/Integer.parseInt(pageSize) +1==Integer.parseInt(pageCurrent)&&Integer.parseInt(pageCurrent)!=1?true:false);
		queryResult.setTotalPage(total/Integer.parseInt(pageSize) +1);
		queryResult.setPageSize(Integer.parseInt(pageSize));
		String r=callback+"("+new Gson().toJson(queryResult)+")";
		
		reslutJson=new ByteArrayInputStream(r.getBytes("UTF-8"));  
		
		logUtil.logInfo("查询海关 上传2.进（JDE）");
		return SUCCESS;
	}
	
	
	/**
     * excel导入
     * @return
     * @throws IOException 
     * @throws FileNotFoundException 
     */
	@Action(value="importCustomsJDE",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
    public String  importCustomsJDE() throws FileNotFoundException, IOException{
        try {
	    	if (upfile!=null) {
	    		customsJDEBIZ.importData(getUser(), upfile, first, upfileFileName[0], 2);
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
	
	
	String getQueryWhere() {
		String where =" where 1=1 ";
		if (businessUnit!=null&&!businessUnit.equals("")) {
			where+= " and businessUnit like '%"+businessUnit+"%' ";
		}
		if (orTy!=null&&!orTy.equals("")) {
			where+= " and orTy like '%"+orTy+"%' ";
		}
		if (orderNumber!=null&&!orderNumber.equals("")) {
			where+= " and orderNumber like '%"+orderNumber+"%' ";
		}
		if (orderDate!=null&&!orderDate.equals("")) {
			where+= " and orderDate like '%"+orderDate+"%' ";
		}
		if (location!=null&&!location.equals("")) {
			where+= " and location like '%"+location+"%' ";
		}
		if (shortItemNo!=null&&!shortItemNo.equals("")) {
			where+= " and shortItemNo like '%"+shortItemNo+"%' ";
		}
		if (cimtasLongItemNo!=null&&!cimtasLongItemNo.equals("")) {
			where+= " and cimtasLongItemNo like '%"+cimtasLongItemNo+"%' ";
		}
		if (doTy!=null&&!doTy.equals("")) {
			where+= " and doTy like '%"+doTy+"%' ";
		}
		if (transactionExplanation!=null&&!transactionExplanation.equals("")) {
			where+= " and transactionExplanation like '%"+transactionExplanation+"%' ";
		}
		if (transQTY!=null&&!transQTY.equals("")) {
			where+= " and transQTY like '%"+transQTY+"%' ";
		}
		if (extendedCostPrice!=null&&!extendedCostPrice.equals("")) {
			where+= " and extendedCostPrice like '%"+extendedCostPrice+"%' ";
		}
		if (glDate!=null&&!glDate.equals("")) {
			where+= " and glDate like '%"+glDate+"%' ";
		}
		if (documentNumber!=null&&!documentNumber.equals("")) {
			where+= " and documentNumber like '%"+documentNumber+"%' ";
		}
		if (lotStatCode!=null&&!lotStatCode.equals("")) {
			where+= " and lotStatCode like '%"+lotStatCode+"%' ";
		}
		if (longProjectNo!=null&&!longProjectNo.equals("")) {
			where+= " and longProjectNo like '%"+longProjectNo+"%' ";
		}
		if (lotNumber!=null&&!lotNumber.equals("")) {
			where+= " and lotNumber like '%"+lotNumber+"%' ";
		}
		if (userID!=null&&!userID.equals("")) {
			where+= " and userID like '%"+userID+"%' ";
		}
		if (materialLongDescription!=null&&!materialLongDescription.equals("")) {
			where+= " and materialLongDescription like '%"+materialLongDescription+"%' ";
		}
		if (importNo!=null&&!importNo.equals("")) {
			where+= " and importNo like '%"+importNo+"%' ";
		}
		if (heatNo!=null&&!heatNo.equals("")) {
			where+= " and heatNo like '%"+heatNo+"%' ";
		}
		if (NCRNo!=null&&!NCRNo.equals("")) {
			where+= " and NCRNo like '%"+NCRNo+"%' ";
		}
		if (note!=null&&!note.equals("")) {
			where+= " and note like '%"+note+"%' ";
		}
		if (weight!=null&&!weight.equals("")) {
			where+= " and weight like '%"+weight+"%' ";
		}
		if (purchaseOrderLineNumber!=null&&!purchaseOrderLineNumber.equals("")) {
			where+= " and purchaseOrderLineNumber like '%"+purchaseOrderLineNumber+"%' ";
		}
		if (note1!=null&&!note1.equals("")) {
			where+= " and note1 like '%"+note1+"%' ";
		}
		if (operationDate!=null&&!operationDate.equals("")) {
			where+= " and operationDate like '%"+operationDate+"%' ";
		}
		if (batchNumber!=null&&!batchNumber.equals("")) {
			where+= " and batchNumber like '%"+batchNumber+"%' ";
		}
		if (businessUnit!=null&&!businessUnit.equals("")) {
			where+= " and businessUnit like '%"+businessUnit+"%' ";
		}
		
		
		return where;
	}
}
