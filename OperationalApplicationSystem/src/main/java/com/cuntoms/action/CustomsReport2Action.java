package com.cuntoms.action;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import com.cuntoms.biz.CustomsReport2BIZ;
import com.google.gson.Gson;
import com.kime.base.ActionBase;

public class CustomsReport2Action extends ActionBase {

	@Autowired
	CustomsReport2BIZ customsReport2BIZ;
	
	
	@Action(value="queryCustomsReport2",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String queryCustomsReport2() throws UnsupportedEncodingException{	
		
		List list  =customsReport2BIZ.query("", Integer.parseInt(pageSize),Integer.parseInt(pageCurrent));
		int total=customsReport2BIZ.query("").size();
		
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
}
