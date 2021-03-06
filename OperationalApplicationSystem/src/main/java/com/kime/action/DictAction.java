package com.kime.action;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
import com.opensymphony.xwork2.ActionContext;
import com.sign.model.Beneficiary;
import com.sign.model.SignMan;

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
	protected String keyName;
	protected String value;
	protected String valueName;
	protected String keyExplain;
	protected String valueExplain;
	protected String id;
	protected String tmp1;
	protected String tmp2;
	
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

	public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	public String getValueName() {
		return valueName;
	}

	public void setValueName(String valueName) {
		this.valueName = valueName;
	}

	public String getTmp1() {
		return tmp1;
	}

	public void setTmp1(String tmp1) {
		this.tmp1 = tmp1;
	}

	public String getTmp2() {
		return tmp2;
	}

	public void setTmp2(String tmp2) {
		this.tmp2 = tmp2;
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
				where+=" AND key like '%"+key+"%' ";
			}else{
				where+=" where key like '%"+key+"%' ";
			}
			
		}
		
		if (!"".equals(value)&&value!=null) {
			if (where.equals("")) {
				where+=" AND value like '%"+value+"%' ";
			}else{
				where+=" where value like '%"+value+"%' ";
			}
			
		}
		
		if (!"".equals(keyExplain)&&keyExplain!=null) {
			if (where.equals("")) {
				where+=" AND keyExplain like '%"+keyExplain+"%' ";
			}else{
				where+=" where keyExplain like '%"+keyExplain+"%' ";
			}
			
		}
		
		if (!"".equals(value)&&value!=null) {
			if (where.equals("")) {
				where+=" AND value like '%"+value+"%' ";
			}else{
				where+=" where value like '%"+value+"%' ";
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
		if (!"".equals(key)&&key!=null) {
			where += " and key='"+key+"'";
		}

		if (!"".equals(key)&&key!=null) {
			where += " and key='"+key+"'";
		}
		if (!"".equals(keyExplain)&&keyExplain!=null) {
			where += " and keyExplain='"+keyExplain+"'";
		}
		if (!"".equals(value)&&value!=null) {
			where += " and value='"+value+"'";
		}
		if (!"".equals(valueExplain)&&valueExplain!=null) {
			where += " and valueExplain='"+valueExplain+"'";
		}
		if (!"".equals(tmp1)&&tmp1!=null) {
			where += " and tmp1='"+tmp1+"'";
		}
		if (!"".equals(tmp2)&&tmp2!=null) {
			where += " and tmp2='"+tmp2+"'";
		}



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
		dict.setTmp1(tmp1);
		dict.setTmp2(tmp2);
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
		String where="WHERE type='CHECKTYPE' ";
		if (key!=null&&!key.equals("")) {
			where+=" AND KEY LIKE '%"+key+"%' ";
		}
		if (value!=null&&!value.equals("")) {
			where+=" AND value LIKE '%"+value+"%' ";
		}
		if (keyExplain!=null&&!keyExplain.equals("")) {
			where+=" AND value keyExplain '%"+keyExplain+"%' ";
		}
		if (valueExplain!=null&&!valueExplain.equals("")) {
			where+=" AND valueExplain LIKE '%"+valueExplain+"%' ";
		}
		List<Dict> list=dictBIZ.getDict(where);		
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
		
		logUtil.logInfo("查询签核类型，条件:"+where);
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
				logUtil.logInfo("删除签核类型:"+dict.getType()+" "+dict.getKey());
			}
			result.setMessage(Message.DEL_MESSAGE_SUCCESS);
			result.setStatusCode("200");
		} catch (Exception e) {
			logUtil.logInfo("删除签核类型:"+e.getMessage());
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
		List<Dict> ldDicts= new ArrayList<>();
		try {		
			User user=(User)session.getAttribute("user");
			List<Dict> lDictsOfUser=dictBIZ.getDict("WHERE type='CHECKTYPE' and key='STAMP' and keyExplain='"+user.getUid()+"' ");	
			ldDicts=dictBIZ.getDict("WHERE type='CHECKTYPE' and key='STAMP' and keyExplain='' ");
			for (int i = 0; i < ldDicts.size(); i++) {
				for (int j = 0; j < lDictsOfUser.size(); j++) {
					if (ldDicts.get(i).getKey().equals(lDictsOfUser.get(j).getKey())) {
						ldDicts.get(i).setId(lDictsOfUser.get(j).getId());
						ldDicts.get(i).setValueExplain(lDictsOfUser.get(j).getValueExplain());
					}
									
				}				
			}
			
			
		} catch (Exception e) {
		}
	
		reslutJson=new ByteArrayInputStream(new Gson().toJson(ldDicts).getBytes("UTF-8"));  
		return SUCCESS;
	}
	
	@Action(value="modeCheckType",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String modeCheckType() throws UnsupportedEncodingException{
		
		Dict dict=new Dict();
		boolean t=true;
		if (!"".equals(id) && id != null) {
			dict.setId(id);
		} else {
			dict.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		}
		dict.setKey(key);
		dict.setKeyExplain(keyExplain);
		dict.setType(type);
		dict.setValue(value);
		dict.setValueExplain(valueExplain);
		

		try {
			if (id == null || "".equals(id)) {
				if (dictBIZ.getDict(" where type='CHECKTYPE' and key='"+dict.getKey()+"' and Value='"+dict.getValue()+"'").size()==1) {
					t=false;
					logUtil.logInfo("新增签核类型:已存在相同type,key,name的记录：");
					result.setMessage(Message.SAVE_MESSAGE_ERROR_DICT);
					result.setStatusCode("300");
				}else{
					dictBIZ.save(dict);
					logUtil.logInfo("新增签核类型:"+dict.getType()+" "+dict.getKey());
					result.setMessage(Message.SAVE_MESSAGE_SUCCESS);
					result.setStatusCode("200");
				}
				
			}else{
				dictBIZ.update(dict);
				logUtil.logInfo("修改签核类型:"+dict.getType()+" "+dict.getKey());
				result.setMessage(Message.SAVE_MESSAGE_SUCCESS);
				result.setStatusCode("200");
			}
			
			
		} catch (Exception e) {
			logUtil.logInfo("新增签核类型:"+e.getMessage());
			result.setMessage(e.getMessage());
			result.setStatusCode("300");	
		}

		if (t) {
			dict = dictBIZ.getDict( "where id='" + dict.getId() + "'").get(0);
			String r = callback + "(" + new Gson().toJson(dict) + ")";
			reslutJson = new ByteArrayInputStream(r.getBytes("UTF-8"));
		}else{
			reslutJson = new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8"));
		}		
		
		return SUCCESS;
		
	}
	
	
	
	
	@Action(value="deleteApproveUserCollection",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String deleteApproveUserCollection() throws UnsupportedEncodingException{
		List<Dict> list=new Gson().fromJson(json, new TypeToken<ArrayList<Dict>>() {}.getType());
		try {
			for (Dict dict : list) {
				dictBIZ.delete(dict);
				logUtil.logInfo("删除签核用户集合:"+dict.getType()+" "+dict.getKey());
			}
			result.setMessage(Message.DEL_MESSAGE_SUCCESS);
			result.setStatusCode("200");
		} catch (Exception e) {
			logUtil.logInfo("签核用户集合:"+e.getMessage());
			result.setMessage(e.getMessage());
			result.setStatusCode("300");
		}

		reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8"));  
		return SUCCESS;
	}
	
	
	
	@Action(value="modApproveUserCollection",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String modApproveUserCollection() throws UnsupportedEncodingException{
		
		List<Dict> list=new Gson().fromJson(json, new TypeToken<ArrayList<Dict>>() {}.getType());
		Dict dict=list.get(0);
		dict.setType("APPROVEUSERCOLLECTION");

		try {
			if (dict.getId() == null||"".equals(dict.getId()) ) {
				if (dictBIZ.getDict(" where type='APPROVEUSERCOLLECTION' and key='"+dict.getKey()+"' and value='"+dict.getValue()+"' ").size()==1) {
					logUtil.logInfo("新增签核用户集合:同个集合维护了相同用户：");
					result.setMessage(Message.SAVE_MESSAGE_ERROR_DICT);
					result.setStatusCode("300");
				}else{
					dictBIZ.save(dict);
					logUtil.logInfo("新增签核用户集合:"+dict.getType()+" "+dict.getKey());
					result.setMessage(Message.SAVE_MESSAGE_SUCCESS);
					result.setStatusCode("200");
				}
				
			}else{
				dictBIZ.update(dict);
				logUtil.logInfo("修改签核用户集合:"+dict.getType()+" "+dict.getKey());
				result.setMessage(Message.SAVE_MESSAGE_SUCCESS);
				result.setStatusCode("200");
			}
			
			
		} catch (Exception e) {
			logUtil.logInfo("修改签核用户集合:"+e.getMessage());
			result.setMessage(e.getMessage());
			result.setStatusCode("300");	
		}

		reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8"));  
		return SUCCESS;
		
	}
	
	
	@Action(value="getApproveUserCollection",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String getApproveUserCollection() throws UnsupportedEncodingException{
		String where="WHERE type='APPROVEUSERCOLLECTION' ";
		if (key!=null&&!key.equals("")) {
			where+=" AND KEY LIKE '%"+key+"%' ";
		}
		if (value!=null&&!value.equals("")) {
			where+=" AND value LIKE '%"+value+"%' ";
		}
		if (keyExplain!=null&&!keyExplain.equals("")) {
			where+=" AND value keyExplain '%"+keyExplain+"%' ";
		}
		if (valueExplain!=null&&!valueExplain.equals("")) {
			where+=" AND valueExplain LIKE '%"+valueExplain+"%' ";
		}
		List<Dict> list=dictBIZ.getDict(where);		
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
		
		logUtil.logInfo("查询签核用户集合，条件:"+where);
		return SUCCESS;
		
		
	}
	
	@Action(value="checkApproveUserCollection",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String checkApproveUserCollection() throws UnsupportedEncodingException{
		List<Dict> list=dictBIZ.getDict(" where key='"+key+"' and type='APPROVEUSERCOLLECTION' ");		
		reslutJson=new ByteArrayInputStream(new Gson().toJson(list).getBytes("UTF-8"));  
		return SUCCESS;
		
		
	}
	
	
	
	@Action(value="setAgentEmployee",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String setAgentEmployee() throws UnsupportedEncodingException{
		
		try {
			Dict dict=new Dict();
			dict.setKey(key);
			dict.setKeyName(keyName);
			dict.setKeyExplain(keyExplain);
			dict.setValue(value);
			dict.setValueName(valueName);
			dict.setValueExplain(valueExplain);
			dict.setType("AGENTEMPLOYEE");
			if (dictBIZ.getDict(" where type='AGENTEMPLOYEE' and key='"+key+"'").size()>0) {
				result.setMessage(" 已存在代理信息，请删除重新维护");
				result.setStatusCode("300");
				reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8"));  
				return SUCCESS;
			}
			dictBIZ.save(dict);
			result.setMessage("Success");
			result.setStatusCode("200");
			
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			result.setStatusCode("300");
		}

		reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8"));  
		return SUCCESS;
		
	}
	
	@Action(value="deleteAgentEmployee",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String deleteAgentEmployee() throws UnsupportedEncodingException{
		
		try {
			Dict dict=dictBIZ.getDict(" where type='AGENTEMPLOYEE' and key='"+key+"'").get(0);
			dictBIZ.delete(dict);
			result.setMessage("Success");
			result.setStatusCode("200");
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			result.setStatusCode("300");
		}

		reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8"));  
		return SUCCESS;
		
	}
	
	@Action(value="getAgentEmployee",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String getAgentEmployee() throws UnsupportedEncodingException{
		
		try {
			List<Dict> list=dictBIZ.getDict(" where type='AGENTEMPLOYEE' and key='"+key+"'");
			Map<String, Object> map=new HashMap<>();
			if (list.size()>0) {				
				map.put("data", list.get(0));
			}
			
			result.setParams(map);		
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			result.setStatusCode("300");
		}

		reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8"));  
		return SUCCESS;
		
	}
	
}
