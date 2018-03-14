package com.kime.action;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kime.base.ActionBase;
import com.kime.biz.DictBIZ;
import com.kime.biz.UserBIZ;
import com.kime.infoenum.Message;
import com.kime.model.Dict;
import com.kime.model.Editor;
import com.kime.model.User;
import com.sign.model.Beneficiary;

@Controller
@Scope("prototype")
@ParentPackage("Struts 2")
public class DictAction extends ActionBase{

	@Autowired
	protected DictBIZ dictBIZ;
	@Autowired
	protected UserBIZ userBIZ;
	protected String type;
	protected String key;
	protected String value;
	protected String keyExplain;
	protected String valueExplain;
	protected String id;
	
	public DictBIZ getDictBIZ() {
		return dictBIZ;
	}

	public void setDictBIZ(DictBIZ dictBIZ) {
		this.dictBIZ = dictBIZ;
	}
	
	public String getKeyExplain() {
		return keyExplain;
	}

	public void setKeyExplain(String keyExplain) {
		this.keyExplain = keyExplain;
	}

	public String getValueExplain() {
		return valueExplain;
	}

	public void setValueExplain(String valueExplain) {
		this.valueExplain = valueExplain;
	}

	public UserBIZ getUserBIZ() {
		return userBIZ;
	}

	public void setUserBIZ(UserBIZ userBIZ) {
		this.userBIZ = userBIZ;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Action(value="getALLSign",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String GetALLSign() throws UnsupportedEncodingException{
	
		List<Dict> list=dictBIZ.getALLSign();
		
		reslutJson=new ByteArrayInputStream(new Gson().toJson(list).getBytes("UTF-8"));  
		return SUCCESS;
	}
	
	
	@Action(value="getALLType",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String getALLType() throws UnsupportedEncodingException{
	
		List list=dictBIZ.getAllType();
		
		reslutJson=new ByteArrayInputStream(new Gson().toJson(list).getBytes("UTF-8"));  
		return SUCCESS;
	}
	
	@Action(value="getALLAcc",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String getALLAcc() throws UnsupportedEncodingException{
	
		User user=(User)session.getAttribute("user");
		List list=dictBIZ.getDict(" where type='PAYMENT' and value<>'"+user.getUid()+"'" );	
		reslutJson=new ByteArrayInputStream(new Gson().toJson(list).getBytes("UTF-8"));  
		return SUCCESS;
	}
	
	
	@Action(value="getDict",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String getDict() throws UnsupportedEncodingException{
	
		String where="";
		if (!"".equals(type)&&type!=null) {
			where+=" where type='"+type+"' ";
		}
		
		if (!"".equals(key)&&key!=null) {
			if (where.equals("")) {
				where+=" AND key='"+key+"' ";
			}else{
				where+=" where key='"+key+"' ";
			}
			
		}
		
		if (!"".equals(value)&&value!=null) {
			if (where.equals("")) {
				where+=" AND value='"+value+"' ";
			}else{
				where+=" where value='"+value+"' ";
			}
			
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
	
	
	@Action(value="deleteDict",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String deleteDict() throws UnsupportedEncodingException{
		List<Dict> list=new Gson().fromJson(json, new TypeToken<ArrayList<Dict>>() {}.getType());
		try {
			for (Dict dict : list) {
				dictBIZ.delete(dict);
				logUtil.logInfo("删除字典:"+dict.getType()+" "+dict.getKey());
			}
			result.setMessage(Message.DEL_MESSAGE_SUCCESS);
			result.setStatusCode("200");
		} catch (Exception e) {
			logUtil.logInfo("删除字典:"+e.getMessage());
			result.setMessage(e.getMessage());
			result.setStatusCode("300");
		}

		reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8"));  
		return SUCCESS;
	}
	
	
	@Action(value="modeDict",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String modeDict() throws UnsupportedEncodingException{
		
		List<Dict> list=new Gson().fromJson(json, new TypeToken<ArrayList<Dict>>() {}.getType());
		Dict object=list.get(0);

		try {
			if (object.getAddFlag()!=null) {
				if (dictBIZ.getDict(" where type='"+object.getType()+"' and key='"+object.getKey()+"'").size()==1) {
					logUtil.logInfo("新增字典:已存在相同type和相同key的记录：");
					result.setMessage(Message.SAVE_MESSAGE_ERROR_DICT);
					result.setStatusCode("300");
				}else{
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
	
	
	@Action(value="getSignMan4Management",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String getSignMan4Management() throws UnsupportedEncodingException{
	
	
		String where=" where type='SignMan4Manager'  ";
		List<Dict> list=dictBIZ.getDict(where, Integer.parseInt(pageSize),Integer.parseInt(pageCurrent));
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
		
		return SUCCESS;
	}
	

	
	@Action(value="modeSignMan4Management",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String modeSignMan4Management() throws UnsupportedEncodingException{
		Dict dict=new Dict();
		dict.setKey(key);
		dict.setType(type);
		dict.setValue(value);
		dict.setKeyExplain(keyExplain);
		dict.setValueExplain(valueExplain);
		dict.setId(id);
		boolean t=true;
		try {
			if ("".equals(id)||id==null) {
				if (dictBIZ.getDict(" where type='"+dict.getType()+"' and key='"+dict.getKey()+"'").size()==1) {
					logUtil.logInfo("新增字典:已存在相同type和相同key的记录：");
					result.setMessage(Message.SAVE_MESSAGE_ERROR_DICT);
					result.setStatusCode("300");
					t=false;
				}else{
					dictBIZ.save(dict);
					logUtil.logInfo("新增字典:"+dict.getType()+" "+dict.getKey());
					result.setMessage(Message.SAVE_MESSAGE_SUCCESS);
					result.setStatusCode("200");
				}
				
			}else{
				dictBIZ.update(dict);
				logUtil.logInfo("修改字典:"+dict.getType()+" "+dict.getKey());
				result.setMessage(Message.SAVE_MESSAGE_SUCCESS);
				result.setStatusCode("200");
			}
			
			
		} catch (Exception e) {
			logUtil.logInfo("修改字典:"+e.getMessage());
			result.setMessage(e.getMessage());
			result.setStatusCode("300");	
			t=false;
		}

		if (t) {
			String r = callback + "(" + new Gson().toJson(dict) + ")";
			reslutJson = new ByteArrayInputStream(r.getBytes("UTF-8"));
		}else{
			reslutJson = new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8"));
		}	


		return SUCCESS;
		
	}
	
	
	
	@Action(value="getCheckType",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String getCheckType() throws UnsupportedEncodingException{
		String where="";
		if (key!=null&&!key.equals("")) {
			where+=" AND KEY LIKE '%"+key+"%' ";
		}
		if (value!=null&&!value.equals("")) {
			where+=" AND value LIKE '%"+value+"%' ";
		}
		List<Dict> list=dictBIZ.getDict("WHERE type='CHECKTYPE' "+where);		
		reslutJson=new ByteArrayInputStream(new Gson().toJson(list).getBytes("UTF-8"));  
		return SUCCESS;
	}
	
	
	@Action(value="deleteCheckType",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String deleteCheckType() throws UnsupportedEncodingException{
		List<Dict> list=new Gson().fromJson(json, new TypeToken<ArrayList<Dict>>() {}.getType());
		try {
			for (Dict dict : list) {
				dictBIZ.delete(dict);
				logUtil.logInfo("删除字典:"+dict.getType()+" "+dict.getKey());
			}
			result.setMessage(Message.DEL_MESSAGE_SUCCESS);
			result.setStatusCode("200");
		} catch (Exception e) {
			logUtil.logInfo("删除字典:"+e.getMessage());
			result.setMessage(e.getMessage());
			result.setStatusCode("300");
		}

		reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8"));  
		return SUCCESS;
	}
	
	
	@Action(value="getCheckType4Select",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String getCheckType4Select() throws UnsupportedEncodingException{
			Map<String, String>map=new HashMap<>();
		try {		
			List<Dict> ldDicts=dictBIZ.getDict("WHERE type='CHECKTYPE' ");		
			for (Dict dict : ldDicts) {
				map.put(dict.getKey(), dict.getValue());
			}		
		} catch (Exception e) {
		}
	
		reslutJson=new ByteArrayInputStream(new Gson().toJson(map).getBytes("UTF-8"));  
		return SUCCESS;
	}
	
	@Action(value="modeCheckType",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String modeCheckType() throws UnsupportedEncodingException{
		
		List<Dict> list=new Gson().fromJson(json, new TypeToken<ArrayList<Dict>>() {}.getType());
		Dict object=list.get(0);

		try {
			if (object.getAddFlag()!=null) {
				if (dictBIZ.getDict(" where type='CHECKTYPE' and key='"+object.getKey()+"'").size()==1) {
					logUtil.logInfo("新增字典:已存在相同type和相同key的记录：");
					result.setMessage(Message.SAVE_MESSAGE_ERROR_DICT);
					result.setStatusCode("300");
				}else{
					object.setType("CHECKTYPE");
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
