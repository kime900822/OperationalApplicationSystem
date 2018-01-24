package com.sign.action;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kime.action.DictAction;
import com.kime.infoenum.Message;
import com.kime.model.Dict;

@Controller
@Scope("prototype")
@ParentPackage("Struts 2")
public class FinanceAction extends DictAction {

	@Action(value="getFinanceDict",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String getFinanceDict() throws UnsupportedEncodingException{
		
		List<Dict> list=dictBIZ.getDict(" WHERE TYPE='PAYMENT' ");
		
		for (Dict dict : list) {
			if (dict.getKey().equals("1")) {
				dict.setKey("Fixed Asset 固定资产");
			}else if (dict.getKey().equals("2")) {
				dict.setKey("Raw Material 原材料");
			}else if (dict.getKey().equals("3")) {
				dict.setKey("Consumable 消耗品");
			}else if (dict.getKey().equals("4")) {
				dict.setKey("Subcontractor 外包");
			}else if (dict.getKey().equals("5")) {
				dict.setKey("Service 服务");
			}else if (dict.getKey().equals("6")) {
				dict.setKey("Petty Cash备用金");
			}else if (dict.getKey().equals("7")) {
				dict.setKey("Other 其他");
			}
			
		}
		
		reslutJson=new ByteArrayInputStream(new Gson().toJson(list).getBytes("UTF-8"));  
		
		logUtil.logInfo("查询付款签核对应财务:");
		return SUCCESS;
		
	}
	
	
	@Action(value="modeFinanceDict",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String modeFinanceDict() throws UnsupportedEncodingException{
		
		List<Dict> list=new Gson().fromJson(json, new TypeToken<ArrayList<Dict>>() {}.getType());
		Dict object=list.get(0);
		
		if (object.getKey().equals("Fixed Asset 固定资产")) {
			object.setKey("1");
		}else if (object.getKey().equals("Raw Material 原材料")) {
			object.setKey("2");
		}else if (object.getKey().equals("Consumable 消耗品")) {
			object.setKey("3");
		}else if (object.getKey().equals("Subcontractor 外包")) {
			object.setKey("4");
		}else if (object.getKey().equals("Service 服务")) {
			object.setKey("5");
		}else if (object.getKey().equals("Petty Cash备用金")) {
			object.setKey("6");
		}else if (object.getKey().equals("Other 其他")) {
			object.setKey("7");
		}
		
		try {
			if (userBIZ.getUser(" where uid='"+object.getValue()+"'").size()==1) {
				dictBIZ.update(object);
				logUtil.logInfo("修改付款签核对应财务:"+object.getType()+" "+object.getKey());
				result.setMessage(Message.SAVE_MESSAGE_SUCCESS);
				result.setStatusCode("200");
			}else{
				logUtil.logInfo("修改付款签核对应财务:对应用户id不存在，"+object.getValue());
				result.setMessage(Message.SAVE_MESSAGE_ERROR_DICT_PAYMENT);
				result.setStatusCode("300");
				
			}	
			
		} catch (Exception e) {
			logUtil.logInfo("修改付款签核对应财务:"+e.getMessage());
			result.setMessage(e.getMessage());
			result.setStatusCode("300");	
		}

		reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8"));  
		return SUCCESS;
		
	}
	
}
