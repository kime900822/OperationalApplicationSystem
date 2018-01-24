package com.kime.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kime.biz.EditorBIZ;
import com.kime.infoenum.Message;
import com.kime.model.Editor;
import com.kime.model.QueryResult;
import com.kime.model.Result;
import com.kime.model.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
@ParentPackage("Struts 2")
public class EditorAction extends ActionBase  {
	
	@Autowired
	private EditorBIZ editorBIZ; 
	@Autowired
	private Editor editor;
	
	private String id;
	private String title;
	private String content;
	private String date;
	
	



	public EditorBIZ getEditorBIZ() {
		return editorBIZ;
	}


	public void setEditorBIZ(EditorBIZ editorBIZ) {
		this.editorBIZ = editorBIZ;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public void setEditor(Editor editor) {
		this.editor = editor;
	}


	@Action(value="getEditor",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String getEditor() throws UnsupportedEncodingException{	
		String where="";
		if (!"".equals(title)&&title!=null) {
			where+=" where title='"+title+"' ";
		}
		
		if (!"".equals(date)&&date!=null) {
			if (where.equals("")) {
				where+=" AND date='"+date+"' ";
			}else{
				where+=" where date='"+date+"' ";
			}
			
		}
		
		List leditor = editorBIZ.getEditor(where, Integer.parseInt(pageSize),Integer.parseInt(pageCurrent));
		int total=editorBIZ.getEditor(where).size();
		
		queryResult.setList(leditor);
		queryResult.setTotalRow(total);
		queryResult.setFirstPage(Integer.parseInt(pageCurrent)==1?true:false);
		queryResult.setPageNumber(Integer.parseInt(pageCurrent));
		queryResult.setLastPage(total/Integer.parseInt(pageSize) +1==Integer.parseInt(pageCurrent)&&Integer.parseInt(pageCurrent)!=1?true:false);
		queryResult.setTotalPage(total/Integer.parseInt(pageSize) +1);
		queryResult.setPageSize(Integer.parseInt(pageSize));
		String r=callback+"("+new Gson().toJson(queryResult)+")";
		
		reslutJson=new ByteArrayInputStream(r.getBytes("UTF-8"));  
		
		logUtil.logInfo("查询信息发布，条件:"+where);
		return SUCCESS;
	}
	
	
	@Action(value="deleteEditor",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String deleteEditor() throws UnsupportedEncodingException{
		List<Editor> leditor=new Gson().fromJson(json, new TypeToken<ArrayList<Editor>>() {}.getType());
		try {
			for (Editor editor : leditor) {
				editorBIZ.deleteEditor(editor);	
				logUtil.logInfo("删除信息发布:"+editor.getTitle());
			}
			result.setMessage(Message.DEL_MESSAGE_SUCCESS);
			result.setStatusCode("200");
		} catch (Exception e) {
			logUtil.logInfo("删除信息发布:"+e.getMessage());
			result.setMessage(e.getMessage());
			result.setStatusCode("300");
		}

		reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8"));  
		return SUCCESS;
	}
	
	
	@Action(value="getEditorByID",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String getEditorByID() throws Exception{
		List<Editor> lEditors=editorBIZ.getEditor(" where id='"+id+"'");
		reslutJson=new ByteArrayInputStream(new Gson().toJson(lEditors.get(0)).getBytes("UTF-8"));  

		return SUCCESS;	
		
	}
	
	
	@Action(value="saveEditor",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String saveEditor() throws UnsupportedEncodingException{
		Date d1=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:dd");
		User user=new User();
		Map<String,Object> session = null;
		session=ActionContext.getContext().getSession();
		if(session.containsKey("user")) {
			user=(User)session.get("user");
			
		}
		
		editor.setContent(content);
		editor.setDate(sdf.format(d1));
		editor.setId(id);
		editor.setTitle(title);
		editor.setUser(user);
		editor.setUid(user.getUid());

		try {
			if ("".equals(id)||id==null) {
				editorBIZ.saveEditor(editor);
			}else{
				editorBIZ.updateEditor(editor);
			}
			logUtil.logInfo("修改信息:"+editor.getTitle());
			result.setMessage(Message.SAVE_MESSAGE_SUCCESS);
			result.setStatusCode("200");
		} catch (Exception e) {
			logUtil.logInfo("修改信息:"+e.getMessage());
			result.setMessage(e.getMessage());
			result.setStatusCode("300");	
		}

		reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8"));  
		return SUCCESS;
		
	}
}
