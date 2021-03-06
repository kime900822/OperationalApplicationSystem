

package com.kime.base;

import java.io.File;
import java.io.InputStream;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.kime.model.QueryResult;
import com.kime.model.Result;
import com.kime.model.User;
import com.kime.utils.LogUtil;
import com.opensymphony.xwork2.ActionSupport;
/**
 * action 基础类
 * @author kime
 *
 */
@Controller
public class ActionBase extends ActionSupport{
	@Autowired
	protected Result result;
	@Autowired
	protected QueryResult queryResult;
	@Autowired
	protected LogUtil logUtil;
    @Autowired  
    protected  HttpSession session;   
	protected InputStream reslutJson;
	protected String json;
	protected String pageSize;
	protected String pageCurrent;
	protected String callback;
	protected String fileName;
	protected File upfile;
	protected String first;
	
	protected String thead;
	protected String orders;
	protected String printUrl;
	protected String queryType;
	
	public String getQueryType() {
		return queryType;
	}
	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}
	public String getPrintUrl() {
		return printUrl;
	}
	public void setPrintUrl(String printUrl) {
		this.printUrl = printUrl;
	}
	public String getOrders() {
		return orders;
	}
	public void setOrders(String orders) {
		this.orders = orders;
	}
	public String getThead() {
		return thead;
	}
	public void setThead(String thead) {
		this.thead = thead;
	}
	protected String[] upfileFileName;
	
	public HttpSession getSession() {
		return session;
	}
	public void setSession(HttpSession session) {
		this.session = session;
	}
	public InputStream getReslutJson() {
		return reslutJson;
	}
	public void setReslutJson(InputStream reslutJson) {
		this.reslutJson = reslutJson;
	}
	public String getJson() {
		return json;
	}
	public void setJson(String json) {
		this.json = json;
	}
	public String[] getUpfileFileName() {
		return upfileFileName;
	}
	public void setUpfileFileName(String[] upfileFileName) {
		this.upfileFileName = upfileFileName;
	}
	public Result getResult() {
		return result;
	}
	public void setResult(Result result) {
		this.result = result;
	}
	public QueryResult getQueryResult() {
		return queryResult;
	}
	public void setQueryResult(QueryResult queryResult) {
		this.queryResult = queryResult;
	}
	public LogUtil getLogUtil() {
		return logUtil;
	}
	public void setLogUtil(LogUtil logUtil) {
		this.logUtil = logUtil;
	}
	public String getPageSize() {
		return pageSize;
	}
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
	public String getPageCurrent() {
		return pageCurrent;
	}
	public void setPageCurrent(String pageCurrent) {
		this.pageCurrent = pageCurrent;
	}
	public String getCallback() {
		return callback;
	}
	public void setCallback(String callback) {
		this.callback = callback;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public File getUpfile() {
		return upfile;
	}
	public void setUpfile(File upfile) {
		this.upfile = upfile;
	}
	public String getFirst() {
		return first;
	}
	public void setFirst(String first) {
		this.first = first;
	}
	
	
	public User getUser(){
		return (User)session.getAttribute("user");
	}
	
	
}
