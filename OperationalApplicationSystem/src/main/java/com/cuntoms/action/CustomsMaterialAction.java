package com.cuntoms.action;

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

import com.cuntoms.biz.CustomsMaterialBIZ;
import com.cuntoms.biz.CustomsProductBIZ;
import com.cuntoms.other.CustomsMaterialHelp;
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
public class CustomsMaterialAction extends ActionBase{

	@Autowired
	CustomsMaterialBIZ customsMaterialBIZ;
	
	String no_f;
	String no_t;
	String materialNo;
	String materialName;
	String productNo;
	String exemptedMode;
	String batchNumber;

	
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public String getNo_f() {
		return no_f;
	}
	public void setNo_f(String no_f) {
		this.no_f = no_f;
	}
	public String getNo_t() {
		return no_t;
	}
	public void setNo_t(String no_t) {
		this.no_t = no_t;
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
	public String getExemptedMode() {
		return exemptedMode;
	}
	public void setExemptedMode(String exemptedMode) {
		this.exemptedMode = exemptedMode;
	}
	public String getBatchNumber() {
		return batchNumber;
	}
	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}
	
	
	@Action(value="deleteCustomsMaterial",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String deleteCustomsJDE() throws UnsupportedEncodingException{
		
		if (!CommonUtil.isAdmin(getUser())) {
			result.setMessage("非管理员，没有权限！");
			result.setStatusCode("300");
		}else{
			String r=customsMaterialBIZ.deleteByBatchNumber(batchNumber);
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
	
	@Action(value="materialHandingOK",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String materialHandingOK() throws UnsupportedEncodingException{
		String r=customsMaterialBIZ.customsHandingOK(batchNumber,getUser());
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
	
	@Action(value="materialHandingNO",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String materialHandingNO() throws UnsupportedEncodingException{
		String r=customsMaterialBIZ.customsHandingNO(batchNumber,getUser());
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
	
	
	@Action(value="queryCustomsMaterial",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String queryCustomsMaterial() throws UnsupportedEncodingException{	
		String where="";
		if (!"".equals(no_f)&&no_f!=null) {
			where+=" where no >= '"+no_f+"' ";
		}		
		if (!"".equals(no_t)&&no_t!=null) {
			if (where.equals("")) {
				where+=" where no <= '"+no_t+"' ";
			}else{
				where+=" AND no <= '"+no_t+"'  ";
			}
			
		}		
		if (!"".equals(materialNo)&&materialNo!=null) {
			if (where.equals("")) {
				where+=" where materialNo like '%"+materialNo+"%' ";
			}else{
				where+=" AND materialNo like '%"+materialNo+"%' ";
			}
			
		}	
		if (!"".equals(productNo)&&productNo!=null) {
			if (where.equals("")) {
				where+=" where productNo like '%"+productNo+"%' ";
			}else{
				where+=" AND productNo like '%"+productNo+"%' ";
			}
			
		}
		if (!"".equals(exemptedMode)&&exemptedMode!=null) {
			if (where.equals("")) {
				where+=" where exemptedMode like '%"+exemptedMode+"%' ";
			}else{
				where+=" AND exemptedMode like '%"+exemptedMode+"%' ";
			}
			
		}
		if (!"".equals(batchNumber)&&batchNumber!=null) {
			if (where.equals("")) {
				where+=" where batchNumber like '%"+batchNumber+"%' ";
			}else{
				where+=" AND batchNumber like '%"+batchNumber+"%' ";
			}
			
		}
		if (!"".equals(materialName)&&materialName!=null) {
			if (where.equals("")) {
				where+=" where materialName like '%"+materialName+"%' ";
			}else{
				where+=" AND materialName like '%"+materialName+"%' ";
			}
			
		}
		where+=" order by no desc";
		List list  =customsMaterialBIZ.query(where, Integer.parseInt(pageSize),Integer.parseInt(pageCurrent));
		int total=customsMaterialBIZ.query(where).size();
		
		queryResult.setList(list);
		queryResult.setTotalRow(total);
		queryResult.setFirstPage(Integer.parseInt(pageCurrent)==1?true:false);
		queryResult.setPageNumber(Integer.parseInt(pageCurrent));
		queryResult.setLastPage(total/Integer.parseInt(pageSize) +1==Integer.parseInt(pageCurrent)&&Integer.parseInt(pageCurrent)!=1?true:false);
		queryResult.setTotalPage(total/Integer.parseInt(pageSize) +1);
		queryResult.setPageSize(Integer.parseInt(pageSize));
		String r=callback+"("+new Gson().toJson(queryResult)+")";
		
		reslutJson=new ByteArrayInputStream(r.getBytes("UTF-8"));  
		
		logUtil.logInfo(CustomsMaterialHelp.title,"查询海关料件数据，条件:"+where);
		return SUCCESS;
	}
	
	
	 /**
     * excel导出
     * @return
     */
	@Action(value="exportCustomsMaterial",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson",
					"contentType","application/vnd.ms-excel",
					"contentDisposition","attachment;filename=%{fileName}",
					"bufferSize","1024"
			})})
    public String exportCustomsMaterial() {
        try {
        	List<HeadColumn> lHeadColumns=new Gson().fromJson(thead, new TypeToken<ArrayList<HeadColumn>>() {}.getType());
    		String where="";
    		if (!"".equals(no_f)&&no_f!=null) {
    			where+=" where no >= '"+no_f+"' ";
    		}		
    		if (!"".equals(no_t)&&no_t!=null) {
    			if (where.equals("")) {
    				where+=" where no <= '"+no_t+"' ";
    			}else{
    				where+=" AND no <= '"+no_t+"'  ";
    			}
    			
    		}		
    		if (!"".equals(materialNo)&&materialNo!=null) {
    			if (where.equals("")) {
    				where+=" where materialNo like '%"+materialNo+"%' ";
    			}else{
    				where+=" AND materialNo like '%"+materialNo+"%' ";
    			}
    			
    		}	
    		if (!"".equals(productNo)&&productNo!=null) {
    			if (where.equals("")) {
    				where+=" where productNo like '%"+productNo+"%' ";
    			}else{
    				where+=" AND productNo like '%"+productNo+"%' ";
    			}
    			
    		}
    		if (!"".equals(exemptedMode)&&exemptedMode!=null) {
    			if (where.equals("")) {
    				where+=" where exemptedMode like '%"+exemptedMode+"%' ";
    			}else{
    				where+=" AND exemptedMode like '%"+exemptedMode+"%' ";
    			}
    			
    		}
    		if (!"".equals(batchNumber)&&batchNumber!=null) {
    			if (where.equals("")) {
    				where+=" where batchNumber like '%"+batchNumber+"%' ";
    			}else{
    				where+=" AND batchNumber like '%"+batchNumber+"%' ";
    			}
    			
    		}
    		if (!"".equals(materialName)&&materialName!=null) {
    			if (where.equals("")) {
    				where+=" where materialName like '%"+materialName+"%' ";
    			}else{
    				where+=" AND materialName like '%"+materialName+"%' ";
    			}
    			
    		}
    		
    		where+=" order by no desc";
        	
        	ByteArrayInputStream  is = customsMaterialBIZ.exportData(where,lHeadColumns);
        	
        	HttpServletResponse response = (HttpServletResponse)
        			ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_RESPONSE);
        	response.setHeader("Set-Cookie", "fileDownload=true; path=/");
        	
        	
    		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");		 
    		fileName = "CustomsProduct"+sf.format(new Date()).toString()+ ".xls";
    		fileName= new String(fileName.getBytes(), "ISO8859-1");
    		//文件流
            reslutJson = is;            
            logUtil.logInfo("导出CustomsProduct！"+fileName);
        }
        catch(Exception e) {
        	logUtil.logInfo("导出CustomsProduct！"+e.getMessage());
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
	@Action(value="importCustomsMaterial",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
    public String  importCustomsMaterial() throws FileNotFoundException, IOException{
        try {
	    	if (upfile!=null) {
				customsMaterialBIZ.importData(getUser(), upfile, first, upfileFileName[0], 2);
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
}
