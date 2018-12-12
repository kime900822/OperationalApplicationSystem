package com.cuntoms.action;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kime.action.DictAction;
import com.kime.infoenum.Message;
import com.kime.model.Dict;
import com.kime.other.dictType;

public class CustomsUnitAction extends DictAction{
	
	
	@Action(value="getCustomsUnit",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String getCustomsUnit() throws UnsupportedEncodingException{
	
		String where=" where type='"+dictType.CUSTOMS_UNIT_TYPE+"' ";
		if (!"".equals(key)&&key!=null) {
			where+=" AND key like '%"+key+"%' ";
		}
		
		if (!"".equals(value)&&value!=null) {
			where+=" AND value like '%"+value+"%' ";
		}
		
		List list=dictBIZ.getDict(where, Integer.parseInt(pageSize),Integer.parseInt(pageCurrent));
		int total=dictBIZ.getDict(where).size();
		
		
		queryResult.setList(list);
		queryResult.setTotalRow(total);
		queryResult.setFirstPage(Integer.parseInt(pageCurrent)==1?true:false);
		queryResult.setPageNumber(Integer.parseInt(pageCurrent));
		queryResult.setLastPage(total/Integer.parseInt(pageSize) +1==Integer.parseInt(pageCurrent)&&Integer.parseInt(pageCurrent)!=1?true:false);
		queryResult.setTotalPage(total/Integer.parseInt(pageSize) +1);
		queryResult.setPageSize(Integer.parseInt(pageSize));
		String r=callback+"("+new Gson().toJson(queryResult)+")";
		
		reslutJson=new ByteArrayInputStream(r.getBytes("UTF-8"));  
		
		logUtil.logInfo("查询字典信息，条件:"+where);
		return SUCCESS;
		
	}
	
	@Action(value="modeCustomsUnit",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String modeCustomsUnit() throws UnsupportedEncodingException{
		
		List<Dict> list=new Gson().fromJson(json, new TypeToken<ArrayList<Dict>>() {}.getType());
		Dict object=list.get(0);

		try {
			if (object.getAddFlag()!=null) {
				if (dictBIZ.getDict(" where type='"+dictType.CUSTOMS_UNIT_TYPE+"' and key='"+object.getKey()+"'").size()==1) {
					logUtil.logInfo(":已存在相同type和相同key的记录：");
					result.setMessage(Message.SAVE_MESSAGE_ERROR_DICT);
					result.setStatusCode("300");
				}else{
					object.setType(dictType.CUSTOMS_UNIT_TYPE);
					dictBIZ.save(object);
					logUtil.logInfo("新增字典:"+object.getType()+" "+object.getKey());
					result.setMessage(Message.SAVE_MESSAGE_SUCCESS);
					result.setStatusCode("200");
				}
				
			}else{
				dictBIZ.update(object);
				logUtil.logInfo("修改字典:"+object.getType()+" "+object.getKey());
				result.setMessage(Message.SAVE_MESSAGE_SUCCESS);
				result.setStatusCode("200");
			}
			
			
		} catch (Exception e) {
			logUtil.logInfo("修改字典:"+e.getMessage());
			result.setMessage(e.getMessage());
			result.setStatusCode("300");	
		}

		reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8"));  
		return SUCCESS;
		
	}

}
