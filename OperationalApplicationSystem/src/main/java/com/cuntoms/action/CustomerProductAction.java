package com.cuntoms.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
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

import com.cuntoms.biz.CustomsProductBIZ;
import com.cuntoms.model.CustomsProduct;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kime.base.ActionBase;
import com.kime.model.HeadColumn;
import com.kime.utils.ExcelUtil;
import com.opensymphony.xwork2.ActionContext;
import com.sign.model.Beneficiary;


@Controller
@Scope("prototype")
@ParentPackage("Customs")
public class CustomerProductAction extends ActionBase {
	
	@Autowired
	CustomsProductBIZ customsProductBIZ;
	
	String no_f;
	String no_t;
	String materialNo;
	String productNo;
	String exemptedMode;
	String batchNumber;
	public CustomsProductBIZ getCustomsProductBIZ() {
		return customsProductBIZ;
	}
	public void setCustomsProductBIZ(CustomsProductBIZ customsProductBIZ) {
		this.customsProductBIZ = customsProductBIZ;
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
	
	
	
	
	@Action(value="queryProduct",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String queryProduct() throws UnsupportedEncodingException{	
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
		
		List list  =customsProductBIZ.query(where, Integer.parseInt(pageSize),Integer.parseInt(pageCurrent));
		int total=customsProductBIZ.query(where).size();
		
		queryResult.setList(list);
		queryResult.setTotalRow(total);
		queryResult.setFirstPage(Integer.parseInt(pageCurrent)==1?true:false);
		queryResult.setPageNumber(Integer.parseInt(pageCurrent));
		queryResult.setLastPage(total/Integer.parseInt(pageSize) +1==Integer.parseInt(pageCurrent)&&Integer.parseInt(pageCurrent)!=1?true:false);
		queryResult.setTotalPage(total/Integer.parseInt(pageSize) +1);
		queryResult.setPageSize(Integer.parseInt(pageSize));
		String r=callback+"("+new Gson().toJson(queryResult)+")";
		
		reslutJson=new ByteArrayInputStream(r.getBytes("UTF-8"));  
		
		logUtil.logInfo("查询海关成品数据，条件:"+where);
		return SUCCESS;
	}
	
	
	 /**
     * excel导出
     * @return
     */
	@Action(value="exportCustomsProduct",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson",
					"contentType","application/vnd.ms-excel",
					"contentDisposition","attachment;filename=%{fileName}",
					"bufferSize","1024"
			})})
    public String exportCustomsProduct() {
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
    		
    		List list  =customsProductBIZ.query(where, Integer.parseInt(pageSize),Integer.parseInt(pageCurrent));
        	Class c = (Class) new CustomsProduct().getClass();  
        	ByteArrayOutputStream os = ExcelUtil.exportExcel("CustomsProduct", c, list, "yyy-MM-dd",lHeadColumns);
        	
        	
        	byte[] fileContent = os.toByteArray();
        	ByteArrayInputStream is = new ByteArrayInputStream(fileContent);
        	
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
	

}
