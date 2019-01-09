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
	
	String BOMDate;
	String batchNumber;
	String no;
	String id;
	String poseLongItemNo;
	String shipmentIems;
	
	
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
		String where=" where 1=1 ";
		if (!"".equals(batchNumber)&&batchNumber!=null) {
			where+=" AND batchNumber like '%"+batchNumber+"%' ";
		}
		if (!"".equals(no)&&no!=null) {
			where+=" AND no = '"+no+"' ";
		}
		if (!"".equals(poseLongItemNo)&&poseLongItemNo!=null) {
			where+=" AND poseLongItemNo like '%"+poseLongItemNo+"%' ";
		}
		if (!"".equals(shipmentIems)&&shipmentIems!=null) {
			where+=" AND shipmentIems like '%"+shipmentIems+"%' ";
		}
		String r=clearanceBIZ.customsClearanceBOMDate(where, BOMDate);
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
		String where=" where 1=1";
		
		if (!"".equals(batchNumber)&&batchNumber!=null) {
			where+=" AND batchNumber like '%"+batchNumber+"%' ";
		}
		if (!"".equals(no)&&no!=null) {
			where+=" AND no = '"+no+"' ";
		}
		if (!"".equals(poseLongItemNo)&&poseLongItemNo!=null) {
			where+=" AND poseLongItemNo like '%"+poseLongItemNo+"%' ";
		}
		if (!"".equals(shipmentIems)&&shipmentIems!=null) {
			where+=" AND shipmentIems like '%"+shipmentIems+"%' ";
		}
		
		List list  =clearanceBIZ.query(where, Integer.parseInt(pageSize),Integer.parseInt(pageCurrent));
		int total=clearanceBIZ.query(where).size();
		
		queryResult.setList(list);
		queryResult.setTotalRow(total);
		queryResult.setFirstPage(Integer.parseInt(pageCurrent)==1?true:false);
		queryResult.setPageNumber(Integer.parseInt(pageCurrent));
		queryResult.setLastPage(total/Integer.parseInt(pageSize) +1==Integer.parseInt(pageCurrent)&&Integer.parseInt(pageCurrent)!=1?true:false);
		queryResult.setTotalPage(total/Integer.parseInt(pageSize) +1);
		queryResult.setPageSize(Integer.parseInt(pageSize));
		String r=callback+"("+new Gson().toJson(queryResult)+")";
		
		reslutJson=new ByteArrayInputStream(r.getBytes("UTF-8"));  
		
		logUtil.logInfo("查询进（进出口）数据，条件:"+where);
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
        	String where=" where 1=1";
    		
    		if (!"".equals(batchNumber)&&batchNumber!=null) {
    			where+=" AND batchNumber like '%"+batchNumber+"%' ";
    		}
    		if (!"".equals(no)&&no!=null) {
    			where+=" AND no = '"+no+"' ";
    		}
    		if (!"".equals(poseLongItemNo)&&poseLongItemNo!=null) {
    			where+=" AND poseLongItemNo like '%"+poseLongItemNo+"%' ";
    		}
    		if (!"".equals(shipmentIems)&&shipmentIems!=null) {
    			where+=" AND shipmentIems like '%"+shipmentIems+"%' ";
    		}
    		
        	ByteArrayInputStream  is = clearanceBIZ.exportData(where,lHeadColumns);
        	
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
	
}
