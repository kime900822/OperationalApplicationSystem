package com.customs.action;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.customs.biz.CustomsGeneralBIZ;
import com.google.gson.Gson;
import com.kime.base.ActionBase;
import com.kime.infoenum.Message;

@Controller
@Scope("prototype")
@ParentPackage("Customs")
public class CustomsGeneralAction extends ActionBase{
	
	@Autowired
	CustomsGeneralBIZ customsGeneralBIZ;
	
	String month;
	String materialNo;
	String jdeMaterialNo;
	String no;
	String productNo;
	String materialName;
	
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}

	public String getMaterialNo() {
		return materialNo;
	}
	public void setMaterialNo(String materialNo) {
		this.materialNo = materialNo;
	}
	public String getJdeMaterialNo() {
		return jdeMaterialNo;
	}
	public void setJdeMaterialNo(String jdeMaterialNo) {
		this.jdeMaterialNo = jdeMaterialNo;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public CustomsGeneralBIZ getCustomsGeneralBIZ() {
		return customsGeneralBIZ;
	}
	public void setCustomsGeneralBIZ(CustomsGeneralBIZ customsGeneralBIZ) {
		this.customsGeneralBIZ = customsGeneralBIZ;
	}
	public String getProductNo() {
		return productNo;
	}
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	
	
	
	/**
     * excel导入
     * @return
     * @throws IOException 
     * @throws FileNotFoundException 
     */
	@Action(value="importCustomsGeneralInit",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
    public String  importCustomsGeneralInit() throws FileNotFoundException, IOException{
        try {
	    	if (upfile!=null) {
	    		customsGeneralBIZ.initData(getUser(), upfile, first, upfileFileName[0], 2);
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
	
	
	
	
	@Action(value="queryCustomsGeneralInit",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String queryCustomsGeneralInit() throws UnsupportedEncodingException{	
		
		String where=" where 1=1";
		if (month!=null&&!month.equals("")) {
			where+= " and month='"+month+"' ";
		}
		if (!"".equals(materialNo)&&materialNo!=null) {
			where+=" AND materialNo like '%"+materialNo+"%'  ";
		}	
		if (!"".equals(jdeMaterialNo)&&jdeMaterialNo!=null) {
			where+=" AND jdeMaterialNo like '%"+jdeMaterialNo+"%'  ";
		}
		if (!"".equals(no)&&no!=null) {
			where+=" AND no like '%"+no+"%'  ";
		}
		 
		List list  =customsGeneralBIZ.query4init(where, Integer.parseInt(pageSize),Integer.parseInt(pageCurrent));
		int total=customsGeneralBIZ.query4init(where).size();
		
		queryResult.setList(list);
		queryResult.setTotalRow(total);
		queryResult.setFirstPage(Integer.parseInt(pageCurrent)==1?true:false);
		queryResult.setPageNumber(Integer.parseInt(pageCurrent));
		queryResult.setLastPage(total/Integer.parseInt(pageSize) +1==Integer.parseInt(pageCurrent)&&Integer.parseInt(pageCurrent)!=1?true:false);
		queryResult.setTotalPage(total/Integer.parseInt(pageSize) +1);
		queryResult.setPageSize(Integer.parseInt(pageSize));
		String r=callback+"("+new Gson().toJson(queryResult)+")";
		
		reslutJson=new ByteArrayInputStream(r.getBytes("UTF-8"));  
		
		return SUCCESS;
	}
	
	
	@Action(value="queryCustomsGeneral",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String queryCustomsGeneral() throws Exception{	
		
		
		String where=" where 1=1";
		if (month!=null&&!month.equals("")) {
			where+= " and month='"+month+"' ";
		}
		if (!"".equals(materialNo)&&materialNo!=null) {
			where+=" AND materialNo like '%"+materialNo+"%'  ";
		}	
		if (!"".equals(jdeMaterialNo)&&jdeMaterialNo!=null) {
			where+=" AND jdeMaterialNo like '%"+jdeMaterialNo+"%'  ";
		}
		if (!"".equals(no)&&no!=null) {
			where+=" AND no like '%"+no+"%'  ";
		}
		if (!"".equals(productNo)&&productNo!=null) {
			where+=" AND productNo like '%"+productNo+"%'  ";
		}
		if (!"".equals(materialName)&&materialName!=null) {
			where+=" AND materialName like '%"+materialName+"%'  ";
		}

		

		
		List list  =customsGeneralBIZ.query(month,where,Integer.parseInt(pageSize),Integer.parseInt(pageCurrent));
		int total=customsGeneralBIZ.query(month,where).size();
		
		queryResult.setList(list);
		queryResult.setTotalRow(total);
		queryResult.setFirstPage(Integer.parseInt(pageCurrent)==1?true:false);
		queryResult.setPageNumber(Integer.parseInt(pageCurrent));
		queryResult.setLastPage(total/Integer.parseInt(pageSize) +1==Integer.parseInt(pageCurrent)&&Integer.parseInt(pageCurrent)!=1?true:false);
		queryResult.setTotalPage(total/Integer.parseInt(pageSize) +1);
		queryResult.setPageSize(Integer.parseInt(pageSize));
		String r=callback+"("+new Gson().toJson(queryResult)+")";
		
		reslutJson=new ByteArrayInputStream(r.getBytes("UTF-8"));  
		
		return SUCCESS;
	}
	
	
	@Action(value="saveCustomsGeneral",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String saveCustomsGeneral() throws Exception{	
		

		String r=customsGeneralBIZ.saveData(month);

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
	
	
	@Action(value="unCustomsGeneral",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String unCustomsGeneral() throws Exception{	
		

		String r=customsGeneralBIZ.saveData(month);

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
}
