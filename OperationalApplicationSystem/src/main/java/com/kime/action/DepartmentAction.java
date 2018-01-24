package com.kime.action;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kime.biz.DepartmentBIZ;
import com.kime.infoenum.Message;
import com.kime.model.Department;
import com.kime.model.Dict;
import com.kime.model.Role;

@Controller
@Scope("prototype")
@ParentPackage("Struts 2")
public class DepartmentAction extends ActionBase {

	@Autowired
	private DepartmentBIZ departmentBIZ;
	@Autowired
	private Department department;
	
	private String did;
	private String name;
	private String addFlag;
	
	public DepartmentBIZ getDepartmentBIZ() {
		return departmentBIZ;
	}
	public void setDepartmentBIZ(DepartmentBIZ departmentBIZ) {
		this.departmentBIZ = departmentBIZ;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public String getDid() {
		return did;
	}
	public void setDid(String did) {
		this.did = did;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddFlag() {
		return addFlag;
	}
	public void setAddFlag(String addFlag) {
		this.addFlag = addFlag;
	}
	
	@Action(value="queryDepartment",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String queryDepartment() throws UnsupportedEncodingException{	
		String where="";
		if (!"".equals(did)&&did!=null) {
			where+=" where did like '%"+did+"%' ";
		}
		
		if (!"".equals(name)&&name!=null) {
			if (where.equals("")) {
				where+=" AND name like '%"+name+"%' ";
			}else{
				where+=" where name like '%"+name+"%' ";
			}
			
		}
		
		List list  =departmentBIZ.queryDepartment(where, Integer.parseInt(pageSize),Integer.parseInt(pageCurrent));
		int total=departmentBIZ.queryDepartment(where).size();
		
		queryResult.setList(list);
		queryResult.setTotalRow(total);
		queryResult.setFirstPage(Integer.parseInt(pageCurrent)==1?true:false);
		queryResult.setPageNumber(Integer.parseInt(pageCurrent));
		queryResult.setLastPage(total/Integer.parseInt(pageSize) +1==Integer.parseInt(pageCurrent)&&Integer.parseInt(pageCurrent)!=1?true:false);
		queryResult.setTotalPage(total/Integer.parseInt(pageSize) +1);
		queryResult.setPageSize(Integer.parseInt(pageSize));
		String r=callback+"("+new Gson().toJson(queryResult)+")";
		
		reslutJson=new ByteArrayInputStream(r.getBytes("UTF-8"));  
		
		logUtil.logInfo("查询部门信息，条件:"+where);
		return SUCCESS;
	}
	
	
	@Action(value="deleteDepartment",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String deleteDepartment() throws UnsupportedEncodingException{
		List<Department> lDepartments=new Gson().fromJson(json, new TypeToken<ArrayList<Department>>() {}.getType());
		try {
			for (Department department : lDepartments) {
				departmentBIZ.deleteDepartment(department);	
				logUtil.logInfo("删除部门信息:"+department.getDid());
			}
			result.setMessage(Message.DEL_MESSAGE_SUCCESS);
			result.setStatusCode("200");
		} catch (Exception e) {
			logUtil.logInfo("删除部门信息:"+e.getMessage());
			result.setMessage(e.getMessage());
			result.setStatusCode("300");
		}

		reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8"));  
		return SUCCESS;
	}
	
	
	@Action(value="saveDepartment",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String saveDepartment() throws UnsupportedEncodingException{
		
		List<Department> list=new Gson().fromJson(json, new TypeToken<ArrayList<Department>>() {}.getType());
		Department department=list.get(0);

		try {
			if (department.getAddFlag().equals("true")) {
				departmentBIZ.saveDepartment(department);
				logUtil.logInfo("新增部门信息:"+department.getDid());
			}else{
				departmentBIZ.updateDepartment(department);
				logUtil.logInfo("修改部门信息:"+department.getDid());
			}
			
			
			result.setMessage(Message.SAVE_MESSAGE_SUCCESS);
			result.setStatusCode("200");
		} catch (Exception e) {
			logUtil.logInfo("修改部门信息:"+e.getMessage());
			result.setMessage(e.getMessage());
			result.setStatusCode("300");	
		}

		reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8"));  
		return SUCCESS;
		
	}
	@Action(value="getAllDepartment",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String getAllDepartment() throws UnsupportedEncodingException{
		
		List<Department> lDepartments=departmentBIZ.queryDepartment("");
		reslutJson=new ByteArrayInputStream(new Gson().toJson(lDepartments).getBytes("UTF-8"));  
		
		return SUCCESS;
	}
	
	
	@Action(value="getAllDepartment_User",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String getAllDepartment_User() throws UnsupportedEncodingException{
		
		List<Department> list=departmentBIZ.queryDepartment("");
		StringBuilder stringBuilder =new StringBuilder();
		stringBuilder.append("[");
		for (Department department : list) {
			stringBuilder.append("{\'"+department.getDid()+"\':\'"+department.getName()+"\'}");
			stringBuilder.append(",");
		}
		stringBuilder.deleteCharAt(stringBuilder.length()-1);
		stringBuilder.append("]");
		String string=stringBuilder.toString();
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		session.setAttribute("allDepartment", string);
		
		return SUCCESS;
	}
	
	@Action(value="getAllDepartmentOfSign",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String getAllDepartmentOfSign() throws UnsupportedEncodingException{
		
		List<Department> list=departmentBIZ.queryDepartment("");
		
		reslutJson=new ByteArrayInputStream(new Gson().toJson(list).getBytes("UTF-8"));  
		return SUCCESS;
		
	}
}
