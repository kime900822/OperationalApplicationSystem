package com.sign.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kime.base.ActionBase;
import com.kime.biz.ApproveHisBIZ;
import com.kime.biz.DictBIZ;
import com.kime.infoenum.Message;
import com.kime.model.ApproveHis;
import com.kime.model.Dict;
import com.kime.model.HeadColumn;
import com.kime.model.User;
import com.kime.utils.CommonUtil;
import com.kime.utils.ExcelUtil;
import com.kime.utils.PDFUtil;
import com.opensymphony.xwork2.ActionContext;
import com.sign.biz.StampBIZ;
import com.sign.model.Stamp;
import com.sign.other.FileSave;
import com.sign.other.StampState;

@Controller
@Scope("prototype")
@ParentPackage("Struts 2")
public class StampAction extends ActionBase{

		@Autowired
		private StampBIZ stampBIZ;
		@Autowired
		private FileSave fileSave;
		@Autowired
		private DictBIZ dictBIZ;
		@Autowired
		private ApproveHisBIZ approveHisBIZ;

		private String id;
		private String applicationDate;
		private String applicationCode;
		private String formFillerID;
		private String formFiller;
		private String departmentOfFormFillerID;
		private String departmentOfFormFiller;
		private String applicantID;
		private String applicant;
		private String departmentOfApplicantID;
		private String departmentOfApplicant;
		private String contactNumber;
		private String stampType;
		private String documentType;
		private String projectResponsible;
		private String chopDate;
		private String lendDate;
		private String giveBackDate;
		private String chopQty;
		private String chopObject;
		private String urgent;
		private String usageDescription;
		private String attacmentUpload;
		private String dateTmp;
		private String state;
		private String usedFile;
		
		private File[] file;
		private String[] fileFileName;
		private String dfile;
		
		private String applicationDate_f;
		private String applicationDate_t;
		private String queryType;
		
		
		private String tradeId;
		private String comment;
		private String level;
		private String approveState;
		
	
		public String getUsedFile() {
			return usedFile;
		}
		public void setUsedFile(String usedFile) {
			this.usedFile = usedFile;
		}
		public String getApproveState() {
			return approveState;
		}
		public void setApproveState(String approveState) {
			this.approveState = approveState;
		}
		public String getTradeId() {
			return tradeId;
		}
		public void setTradeId(String tradeId) {
			this.tradeId = tradeId;
		}
		public String getComment() {
			return comment;
		}
		public void setComment(String comment) {
			this.comment = comment;
		}
		public String getLevel() {
			return level;
		}
		public void setLevel(String level) {
			this.level = level;
		}
		public ApproveHisBIZ getApproveHisBIZ() {
			return approveHisBIZ;
		}
		public void setApproveHisBIZ(ApproveHisBIZ approveHisBIZ) {
			this.approveHisBIZ = approveHisBIZ;
		}
		public DictBIZ getDictBIZ() {
			return dictBIZ;
		}
		public void setDictBIZ(DictBIZ dictBIZ) {
			this.dictBIZ = dictBIZ;
		}

		public String getQueryType() {
			return queryType;
		}
		public void setQueryType(String queryType) {
			this.queryType = queryType;
		}
		public String getDfile() {
			return dfile;
		}
		public String getApplicationDate_f() {
			return applicationDate_f;
		}
		public void setApplicationDate_f(String applicationDate_f) {
			this.applicationDate_f = applicationDate_f;
		}
		public String getApplicationDate_t() {
			return applicationDate_t;
		}
		public void setApplicationDate_t(String applicationDate_t) {
			this.applicationDate_t = applicationDate_t;
		}
		public File[] getFile() {
			return file;
		}
		public void setDfile(String dfile) {
			this.dfile = dfile;
		}
		public String[] getFileFileName() {
			return fileFileName;
		}
		public void setFileFileName(String[] fileFileName) {
			this.fileFileName = fileFileName;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		public FileSave getFileSave() {
			return fileSave;
		}
		public void setFileSave(FileSave fileSave) {
			this.fileSave = fileSave;
		}
		public void setFile(File[] file) {
			this.file = file;
		}
		public String getDateTmp() {
			return dateTmp;
		}
		public void setDateTmp(String dateTmp) {
			this.dateTmp = dateTmp;
		}
		public String getUrgent() {
			return urgent;
		}
		public void setUrgent(String urgent) {
			this.urgent = urgent;
		}
		public String getUsageDescription() {
			return usageDescription;
		}
		public void setUsageDescription(String usageDescription) {
			this.usageDescription = usageDescription;
		}
		public String getAttacmentUpload() {
			return attacmentUpload;
		}
		public void setAttacmentUpload(String attacmentUpload) {
			this.attacmentUpload = attacmentUpload;
		}
		public StampBIZ getStampBIZ() {
			return stampBIZ;
		}
		public void setStampBIZ(StampBIZ stampBIZ) {
			this.stampBIZ = stampBIZ;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getApplicationDate() {
			return applicationDate;
		}
		public void setApplicationDate(String applicationDate) {
			this.applicationDate = applicationDate;
		}
		public String getApplicationCode() {
			return applicationCode;
		}
		public void setApplicationCode(String applicationCode) {
			this.applicationCode = applicationCode;
		}
		public String getFormFillerID() {
			return formFillerID;
		}
		public void setFormFillerID(String formFillerID) {
			this.formFillerID = formFillerID;
		}
		public String getFormFiller() {
			return formFiller;
		}
		public void setFormFiller(String formFiller) {
			this.formFiller = formFiller;
		}
		public String getDepartmentOfFormFillerID() {
			return departmentOfFormFillerID;
		}
		public void setDepartmentOfFormFillerID(String departmentOfFormFillerID) {
			this.departmentOfFormFillerID = departmentOfFormFillerID;
		}
		public String getDepartmentOfFormFiller() {
			return departmentOfFormFiller;
		}
		public void setDepartmentOfFormFiller(String departmentOfFormFiller) {
			this.departmentOfFormFiller = departmentOfFormFiller;
		}
		public String getApplicantID() {
			return applicantID;
		}
		public void setApplicantID(String applicantID) {
			this.applicantID = applicantID;
		}
		public String getApplicant() {
			return applicant;
		}
		public void setApplicant(String applicant) {
			this.applicant = applicant;
		}
		public String getDepartmentOfApplicantID() {
			return departmentOfApplicantID;
		}
		public void setDepartmentOfApplicantID(String departmentOfApplicantID) {
			this.departmentOfApplicantID = departmentOfApplicantID;
		}
		public String getDepartmentOfApplicant() {
			return departmentOfApplicant;
		}
		public void setDepartmentOfApplicant(String departmentOfApplicant) {
			this.departmentOfApplicant = departmentOfApplicant;
		}
		public String getContactNumber() {
			return contactNumber;
		}
		public void setContactNumber(String contactNumber) {
			this.contactNumber = contactNumber;
		}
		public String getStampType() {
			return stampType;
		}
		public void setStampType(String stampType) {
			this.stampType = stampType;
		}
		public String getDocumentType() {
			return documentType;
		}
		public void setDocumentType(String documentType) {
			this.documentType = documentType;
		}
		public String getProjectResponsible() {
			return projectResponsible;
		}
		public void setProjectResponsible(String projectResponsible) {
			this.projectResponsible = projectResponsible;
		}
		public String getChopDate() {
			return chopDate;
		}
		public void setChopDate(String chopDate) {
			this.chopDate = chopDate;
		}
		public String getLendDate() {
			return lendDate;
		}
		public void setLendDate(String lendDate) {
			this.lendDate = lendDate;
		}
		public String getGiveBackDate() {
			return giveBackDate;
		}
		public void setGiveBackDate(String giveBackDate) {
			this.giveBackDate = giveBackDate;
		}
		public String getChopQty() {
			return chopQty;
		}
		public void setChopQty(String chopQty) {
			this.chopQty = chopQty;
		}
		public String getChopObject() {
			return chopObject;
		}
		public void setChopObject(String chopObject) {
			this.chopObject = chopObject;
		}
		
		@Action(value="saveFileOfStamp",results={@org.apache.struts2.convention.annotation.Result(type="stream",
				params={
						"inputName", "reslutJson"
				})})
	    public String saveFileOfStamp() throws FileNotFoundException, IOException{
	        try {
	        	List<String> list=new ArrayList<>();
		    	if (file!=null) {
		    		list=fileSave.fileSave(file, fileFileName);
		            if (!list.get(0).equals("File Exists")&&!list.get(0).equals("Filepath Error")) {
		            	result.setMessage("upload Success!");
						result.setStatusCode("200");
						Map<String, List<String>> params=new HashMap<>();
						params.put("url", list);
						result.setParams(params);
					}else{			
						result.setMessage(list.get(0));
						result.setStatusCode("300");	
					}
				}else{
					result.setMessage("No File upload");
					result.setStatusCode("300");
				}
			} catch (Exception e) {
				result.setMessage(e.getMessage());
				result.setStatusCode("300");
			}
	        reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8"));  
	    	return SUCCESS;
	    }
		
		
		@Action(value="getFileOfStamp",results={@org.apache.struts2.convention.annotation.Result(type="stream",
				params={
						"inputName", "reslutJson",
						"contentType","application/octet-stream",
						"contentDisposition","attachment;filename=%{fileName}",
						"bufferSize","1024"
				})})
	    public String getFileOfStamp() throws FileNotFoundException, IOException{
	        try {
		    	if (dfile!=null) {   
		    		String[] f=dfile.split("/");
		    		fileName= new String(f[f.length-1].getBytes(), "ISO8859-1");
		    		byte[] file=fileSave.getFile(dfile);    		
		    		ByteArrayInputStream is = new ByteArrayInputStream(file);    		
		    		reslutJson = is; 	
				}else{
					result.setMessage("No File download");
					result.setStatusCode("300");
			        reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8"));  
				}
			} catch (Exception e) {
				result.setMessage(e.getMessage());
				result.setStatusCode("300");
		        reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8"));  
			}
	    	return SUCCESS;
	    }
		
		
		
		@Action(value="deleteFileOfStamp",results={@org.apache.struts2.convention.annotation.Result(type="stream",
				params={
						"inputName", "reslutJson",
						"contentType","application/octet-stream",
						"contentDisposition","attachment;filename=%{fileName}",
						"bufferSize","1024"
				})})
	    public String deleteFileOfStamp() throws FileNotFoundException, IOException{
	        try {
		    	if (dfile!=null) {   
		    		String r=fileSave.fileDelete(dfile);	
		    		if (r.equals("1")) {
			    		if (id!=null&&!id.equals("")) {
							Stamp t=stampBIZ.getStamp(" where id='"+id+"'").get(0);
							t.setAttacmentUpload(t.getAttacmentUpload().replaceAll(dfile+"|", ""));
							stampBIZ.update(t);
						}
		    			result.setMessage("Delete Success!");
						result.setStatusCode("200");
					}else{
						result.setMessage(r);
						result.setStatusCode("300");
					}
				}else{
					result.setMessage("No File");
					result.setStatusCode("300");
			        
				}
			} catch (Exception e) {
				result.setMessage(e.getMessage());
				result.setStatusCode("300");
		        reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8"));  
			}
	        reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8"));  
	    	return SUCCESS;
	    }
		
		
		@Action(value="deleteFileOfStamp4UsedFile",results={@org.apache.struts2.convention.annotation.Result(type="stream",
				params={
						"inputName", "reslutJson",
						"contentType","application/octet-stream",
						"contentDisposition","attachment;filename=%{fileName}",
						"bufferSize","1024"
				})})
	    public String deleteFileOfStamp4UsedFile() throws FileNotFoundException, IOException{
	        try {
		    	if (dfile!=null) {   
		    		String r=fileSave.fileDelete(dfile);	
		    		if (r.equals("1")) {
			    		if (id!=null&&!id.equals("")) {
							Stamp t=stampBIZ.getStamp(" where id='"+id+"'").get(0);
							t.setUsedFile(t.getUsedFile().replaceAll(dfile+"|", ""));
							stampBIZ.update(t);
						}
		    			result.setMessage("Delete Success!");
						result.setStatusCode("200");
					}else{
						result.setMessage(r);
						result.setStatusCode("300");
					}
				}else{
					result.setMessage("No File");
					result.setStatusCode("300");
			        
				}
			} catch (Exception e) {
				result.setMessage(e.getMessage());
				result.setStatusCode("300");
		        reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8"));  
			}
	        reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8"));  
	    	return SUCCESS;
	    }
		
		@Action(value="savefile4UseFile",results={@org.apache.struts2.convention.annotation.Result(type="stream",
				params={
						"inputName", "reslutJson"
				})})
	    public String savefile4UseFile() throws FileNotFoundException, IOException{
        	List<String> list=new ArrayList<>();
			try {
		    	if (file!=null) {
		    		Stamp t=stampBIZ.getStamp(" where id='"+id+"'").get(0);
		    		if (!t.getState().equals(StampState.INFORM)) {
		    			result.setMessage("Not "+StampState.INFORM);
						result.setStatusCode("300");
						reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8"));  
				    	return SUCCESS;
					}
		    		list=fileSave.fileSave(file, fileFileName);
		            if (!list.get(0).equals("File Exists")&&!list.get(0).equals("Filepath Error")) {		            	
		            	if (t.getUsedFile()==null||t.getUsedFile().equals("")) {
							t.setUsedFile(list.get(0));
						}else {
							t.setUsedFile(t.getUsedFile()+"|"+ list.get(0));
						}
		            	stampBIZ.update(t);
		            	result.setMessage("upload Success!");
						result.setStatusCode("200");
						Map<String, List<String>> params=new HashMap<>();
						params.put("url", list);
						result.setParams(params);
					}else{			
						result.setMessage(list.get(0));
						result.setStatusCode("300");	
					}
				}else{
					result.setMessage("No File upload");
					result.setStatusCode("300");
				}
			} catch (Exception e) {
				fileSave.fileDelete(list.get(0));
				result.setMessage(e.getMessage());
				result.setStatusCode("300");
			}
	        reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8"));  
	    	return SUCCESS;
	    }
		
		@Action(value="submitStamp",results={@org.apache.struts2.convention.annotation.Result(type="stream",
				params={
						"inputName", "reslutJson"
				})})
		public String submitStamp() throws UnsupportedEncodingException{
			try {
 				Stamp stamp=new Gson().fromJson(json, Stamp.class);	
				
				if (stamp.getFormFillerID()==null||stamp.getFormFillerID().equals("")) {
					result.setMessage("User can`t be NULL,Plese reflash page!");
					result.setStatusCode("300");
					reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8")); 	
					return SUCCESS;
				}
				
				if (id==null||id.equals("")) {
					String[] deStrings=stamp.getDepartmentOfApplicant().split("-");
					stamp.setDepartmentOfApplicant(deStrings[0]);
					stamp.setDepartmentOfApplicantID(deStrings[1]);
					
					String code=stampBIZ.getMaxCode();
					stamp.setApplicationCode(code);
					id=UUID.randomUUID().toString().replaceAll("-", "");
					stamp.setId(id);
					stamp.setDateTmp(CommonUtil.getDateTemp());
					stamp.setState(StampState.SAVE);
					stampBIZ.saveStamp(stamp);
					
				}else{
					stamp=stampBIZ.getStampById(id);					
					stamp.setDateTmp(CommonUtil.getDateTemp());
					if (!stamp.getState().equals(StampState.SAVE)) {
						if (!stamp.getState().equals(StampState.INFORM_REJECT)) {
							stamp.setState(stamp.getStampApprove().get(0).getName()+" Approval");
						}else{
							stamp.setState(stamp.getStampApprove().get(stamp.getStampApprove().size()-1).getName()+" Approval");
						}
					}else {
						stamp.setState(StampState.SUBMIT);
					}
					
					
					stampBIZ.update(stamp);		
					result.setMessage(Message.SAVE_MESSAGE_SUCCESS);
					result.setStatusCode("200");				
					logUtil.logInfo("提交用章申请单:"+stamp.getApplicationCode());	
				}

					
				Map<String, String> map=new HashMap<>();
				map.put("id", stamp.getId());
				result.setParams(map);


				
			} catch (Exception e) {
				logUtil.logInfo("新增用章申请单异常:"+e.getMessage());
				result.setMessage(e.getMessage());
				result.setStatusCode("300");
			}
			
			reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8")); 	
			return SUCCESS;
		}
		
		
		
		
		@Action(value="saveStamp",results={@org.apache.struts2.convention.annotation.Result(type="stream",
				params={
						"inputName", "reslutJson"
				})})
		public String saveStamp() throws UnsupportedEncodingException{
			try {
				Stamp stamp=new Gson().fromJson(json, Stamp.class);		
			
				if (stamp.getFormFillerID()==null||stamp.getFormFillerID().equals("")) {
					result.setMessage("User can`t be NULL,Plese reflash page!");
					result.setStatusCode("300");
					reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8")); 	
					return SUCCESS;
				}
				
				String[] deStrings=stamp.getDepartmentOfApplicant().split("-");
				stamp.setDepartmentOfApplicant(deStrings[0]);
				stamp.setDepartmentOfApplicantID(deStrings[1]);
				
				
				if (!stamp.getId().equals("")&&stamp.getId()!=null) {

					
					stamp.setDateTmp(CommonUtil.getDateTemp());
					stampBIZ.update(stamp);				
					result.setMessage(Message.SAVE_MESSAGE_SUCCESS);
					result.setStatusCode("200");
					Map<String, String> map=new HashMap<>();
					map.put("id", stamp.getId());
					map.put("applicationCode", stamp.getApplicationCode());
					map.put("state", stamp.getState());
					result.setParams(map);
					logUtil.logInfo("更新用章申请单:"+stamp.getId());			
				}else{

					
					String code=stampBIZ.getMaxCode();
					stamp.setApplicationCode(code);
					stamp.setId(UUID.randomUUID().toString().replaceAll("-", ""));
					stamp.setDateTmp(CommonUtil.getDateTemp());
					stamp.setState(StampState.SAVE);
					stampBIZ.saveStamp(stamp);
					result.setMessage(Message.SAVE_MESSAGE_SUCCESS);
					result.setStatusCode("200");
					Map<String, String> map=new HashMap<>();
					map.put("id", stamp.getId());
					map.put("applicationCode", code);
					map.put("state", stamp.getState());
					result.setParams(map);
					logUtil.logInfo("新增用章申请单:"+stamp.getApplicationCode());

				}

				
			} catch (Exception e) {
				logUtil.logInfo("新增用章申请单异常:"+e.getMessage());
				result.setMessage(e.getMessage());
				result.setStatusCode("300");
			}
			
			reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8")); 	
			return SUCCESS;
		}
		
		
		@Action(value="deleteStamp",results={@org.apache.struts2.convention.annotation.Result(type="stream",
				params={
						"inputName", "reslutJson"
				})})
		public String deleteStamp() throws UnsupportedEncodingException{
				try {
					Stamp stamp=stampBIZ.getStamp(" where id='"+id+"'").get(0);
					stampBIZ.deleteStamp(stamp);
					
					result.setMessage(Message.DEL_MESSAGE_SUCCESS);
					result.setStatusCode("200");
					logUtil.logInfo("删除用章申请单:"+stamp.getApplicationCode());
				} catch (Exception e) {
					logUtil.logInfo("删除用章申请单异常:"+e.getMessage());
					result.setMessage(e.getMessage());
					result.setStatusCode("300");
				}
		
			reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8")); 	
			return SUCCESS;
		}
		
		
		@Action(value="StampApprove",results={@org.apache.struts2.convention.annotation.Result(type="stream",
				params={
						"inputName", "reslutJson"
				})})
		public String StampApprove() throws UnsupportedEncodingException{	
			ApproveHis approveHis=new ApproveHis();
			try {
				approveHis=stampBIZ.StampApprove(level, comment, approveState, tradeId);
				result.setStatusCode("200");
				result.setMessage("Success");
			} catch (Exception e) {
				result.setStatusCode("300");
				result.setMessage(e.getMessage());
			}
			Map<String, Object> params=new HashMap<>();
			params.put("data", approveHis);		
			result.setParams(params);
			
			reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8"));  	
			return SUCCESS;
		}
		
		
		
		
		@Action(value="getStamp",results={@org.apache.struts2.convention.annotation.Result(type="stream",
				params={
						"inputName", "reslutJson"
				})})
		public String getStamp() throws UnsupportedEncodingException{
		
			User user=(User)session.getAttribute("user");
			String hql="";
			String where="";
			
			if (!"".equals(applicationDate_f)&&applicationDate_f!=null) {
				where += " AND P.applicationDate>='"+applicationDate_f+"' ";
			}
			if (!"".equals(applicationDate_t)&&applicationDate_t!=null) {
				where += " AND P.applicationDate <= '"+applicationDate_t+"' ";
			}
			if (!"".equals(applicationCode)&&applicationCode!=null) {
				where += " AND P.applicationCode like '%"+applicationCode+"%' ";
			}
			if (!"".equals(state)&&state!=null) {
				where += " AND P.state = '"+state+"' ";
			}
			if (!"".equals(urgent)&&urgent!=null) {
				where += " AND P.urgent = '"+urgent+"' ";
			}
			if (!"".equals(stampType)&&stampType!=null) {
				where += " AND P.stampType like '%"+stampType+"%' ";
			}
			if (!"".equals(documentType)&&documentType!=null) {
				where += " AND P.documentType='"+documentType+"' ";
			}
			if (!"".equals(applicantID)&&applicantID!=null) {
				where += " AND P.applicantID like '%"+applicantID+"%' ";
			}
				
				

			if ("user".equals(queryType)) {
				hql=" select P from Stamp P where P.formFillerID='"+user.getUid()+"' "+where+" order By P.dateTmp desc";
			}
			if ("approve".equals(queryType)) {
				hql="select S from Stamp S where S.id in (select P.id from Stamp P left join ApproveHis A on  P.id=A.tradeId  where (P.nextApprover='"+user.getUid()+"' OR A.uId='"+user.getUid()+"') "+where+" ) order By S.dateTmp desc";
			}
			if ("all".equals(queryType)) {
				hql=" select P from Stamp P where 1=1 "+where+" order By P.dateTmp desc";
			}

			List<Stamp> list=stampBIZ.getStampByHql(hql, Integer.parseInt(pageSize),Integer.parseInt(pageCurrent));
			int total=stampBIZ.getStampByHql(hql).size();
			
			for (Stamp stamp : list) {
				String tmp="";
				if(stamp.getStampType().indexOf("|")>0)
					tmp=stamp.getStampType().substring(0, stamp.getStampType().length()-1);
				else
					tmp=stamp.getStampType();
				stamp.setStampType(tmp.replace("|", "<br>"));
				tmp=stamp.getUsageDescription();
				stamp.setUsageDescription(tmp.replace("/r/n", "<br>"));
			}
			
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
		
		
		
		@Action(value="getStampByID",results={@org.apache.struts2.convention.annotation.Result(type="stream",
				params={
						"inputName", "reslutJson"
				})})
		public String getStampByID() throws UnsupportedEncodingException{

			Stamp Stamp=new Stamp();		
			try {
				Stamp=stampBIZ.getStampById(id);
				reslutJson=new ByteArrayInputStream(new Gson().toJson(Stamp).getBytes("UTF-8")); 	
				logUtil.logInfo("查询用章申请单:"+Stamp.getId());
			} catch (Exception e) {
				logUtil.logInfo("查询用章申请单异常:"+e.getMessage());
				result.setMessage(e.getMessage());
				result.setStatusCode("300");
				reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8")); 	
			}
			
			return SUCCESS;
		}
		
		
		 /**
	     * excel导出
	     * @return
	     */
		@Action(value="exportStampExcel",results={@org.apache.struts2.convention.annotation.Result(type="stream",
				params={
						"inputName", "reslutJson",
						"contentType","application/vnd.ms-excel",
						"contentDisposition","attachment;filename=%{fileName}",
						"bufferSize","1024"
				})})
	    public String exportStampExcel() {
	        try {
	        	User user=(User)session.getAttribute("user");
	        	List<HeadColumn> lHeadColumns=new Gson().fromJson(thead, new TypeToken<ArrayList<HeadColumn>>() {}.getType());
	        	
	        	
	    		String hql="";
	    		String where="";
	    		
				if (!"".equals(applicationDate_f)&&applicationDate_f!=null) {
					where += " AND P.applicationDate>='"+applicationDate_f+"' ";
				}
				if (!"".equals(applicationDate_t)&&applicationDate_t!=null) {
					where += " AND P.applicationDate <= '"+applicationDate_t+"' ";
				}
				if (!"".equals(applicationCode)&&applicationCode!=null) {
					where += " AND P.applicationCode like '%"+applicationCode+"%' ";
				}
				if (!"".equals(state)&&state!=null) {
					where += " AND P.state = '"+state+"' ";
				}
				if (!"".equals(urgent)&&urgent!=null) {
					where += " AND P.urgent = '"+urgent+"' ";
				}
				if (!"".equals(stampType)&&stampType!=null) {
					where += " AND P.stampType like '%"+stampType+"%' ";
				}
				if (!"".equals(documentType)&&documentType!=null) {
					where += " AND P.documentType='"+documentType+"' ";
				}
				if (!"".equals(applicantID)&&applicantID!=null) {
					where += " AND P.applicantID like '%"+applicantID+"%' ";
				}				
				if (!"".equals(chopObject)&&chopObject!=null) {
					where += " AND P.chopObject like '%"+chopObject+"%' ";
				}
				
				if ("user".equals(queryType)) {
					hql=" select P from Stamp P where P.formFillerID='"+user.getUid()+"' "+where+" order By P.dateTmp desc";
				}
				if ("approve".equals(queryType)) {
					hql="select S from Stamp S where S.id in (select P.id from Stamp P left join ApproveHis A on  P.id=A.tradeId  where (P.nextApprover='"+user.getUid()+"' OR A.uId='"+user.getUid()+"' )"+where+" ) order By S.dateTmp desc";
				}
				if ("all".equals(queryType)) {
					hql=" select P from Stamp P where 1=1 "+where+" order By P.dateTmp desc";
				}
	    		
	    		List<Stamp> lStamps=stampBIZ.getStampByHql(hql);
	    		
				for (Stamp stamp : lStamps) {
					stamp.setUrgent(stamp.getUrgent()==null?"N":"Y");
					Dict documenttype=dictBIZ.getDict(" where id='"+stamp.getDocumentType()+"'").get(0);
					stamp.setDocumentType(documenttype.getValue());				
				}
				
	        	HttpServletResponse response = (HttpServletResponse)
	        			ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_RESPONSE);
	        	response.setHeader("Set-Cookie", "fileDownload=true; path=/");
	    		
	        	Class c = (Class) new Stamp().getClass();  
	        	ByteArrayOutputStream os=ExcelUtil.exportExcel("Stamp", c, lStamps, "yyy-MM-dd",lHeadColumns);
	        	byte[] fileContent = os.toByteArray();
	        	ByteArrayInputStream is = new ByteArrayInputStream(fileContent);
	        	
	    		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");		 
	    		fileName = "Stamp"+sf.format(new Date()).toString()+ ".xls";
	    		fileName= new String(fileName.getBytes(), "ISO8859-1");
	    		//文件流
	            reslutJson = is;            
	            logUtil.logInfo("导出Stamp！"+fileName);
	        }
	        catch(Exception e) {
	        	logUtil.logInfo("导出Stamp！"+e.getMessage());
	            e.printStackTrace();
	        }

	        return "success";
	    }
		
		
		@Action(value="exportStampPDF",results={@org.apache.struts2.convention.annotation.Result(type="stream",
				params={
						"inputName", "reslutJson",
						"contentType","application/vnd.ms-excel",
						"contentDisposition","attachment;filename=%{fileName}",
						"bufferSize","1024"
				})})
	    public String exportStampPDF() {
	        try {
	        	User user=(User)session.getAttribute("user");
	        	List<HeadColumn> lHeadColumns=new Gson().fromJson(thead, new TypeToken<ArrayList<HeadColumn>>() {}.getType());
	        	
	        	
	    		String hql="";
	    		String where="";
	    		
				if (!"".equals(applicationDate_f)&&applicationDate_f!=null) {
					where += " AND P.applicationDate>='"+applicationDate_f+"' ";
				}
				if (!"".equals(applicationDate_t)&&applicationDate_t!=null) {
					where += " AND P.applicationDate <= '"+applicationDate_t+"' ";
				}
				if (!"".equals(applicationCode)&&applicationCode!=null) {
					where += " AND P.applicationCode like '%"+applicationCode+"%' ";
				}
				if (!"".equals(state)&&state!=null) {
					where += " AND P.state = '"+state+"' ";
				}
				if (!"".equals(urgent)&&urgent!=null) {
					where += " AND P.urgent = '"+urgent+"' ";
				}
				if (!"".equals(stampType)&&stampType!=null) {
					where += " AND P.stampType like '%"+stampType+"%' ";
				}
				if (!"".equals(documentType)&&documentType!=null) {
					where += " AND P.documentType='"+documentType+"' ";
				}
				if (!"".equals(applicantID)&&applicantID!=null) {
					where += " AND P.applicantID like '%"+applicantID+"%' ";
				}
				if (!"".equals(chopObject)&&chopObject!=null) {
					where += " AND P.chopObject like '%"+chopObject+"%' ";
				}
				
				if ("user".equals(queryType)) {
					hql=" select P from Stamp P where P.formFillerID='"+user.getUid()+"' "+where+" order By P.dateTmp desc";
				}
				if ("approve".equals(queryType)) {
					hql="select S from Stamp S where S.id in (select P.id from Stamp P left join ApproveHis A on  P.id=A.tradeId  where (P.nextApprover='"+user.getUid()+"' OR A.uId='"+user.getUid()+"' )"+where+" ) order By S.dateTmp desc";
				}
				if ("all".equals(queryType)) {
					hql=" select P from Stamp P where 1=1 "+where+" order By P.dateTmp desc";
				}
				
				
	    		List<Stamp> lStamps=stampBIZ.getStampByHql(hql);
	    		
				for (Stamp stamp : lStamps) {
					stamp.setUrgent(stamp.getUrgent()==null?"N":"Y");
					Dict documenttype=dictBIZ.getDict(" where id='"+stamp.getDocumentType()+"'").get(0);
					stamp.setDocumentType(documenttype.getValue());				
				}
	    		
	        	Class c = (Class) new Stamp().getClass();  
	        	ByteArrayOutputStream os=PDFUtil.exportPDF("Seal", c, lStamps, "yyy-MM-dd",lHeadColumns);
	        	byte[] fileContent = os.toByteArray();
	        	ByteArrayInputStream is = new ByteArrayInputStream(fileContent);
	        	
	        	HttpServletResponse response = (HttpServletResponse)
	        			ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_RESPONSE);
	        	response.setHeader("Set-Cookie", "fileDownload=true; path=/");
	        	
	    		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");		 
	    		fileName = "Seal"+sf.format(new Date()).toString()+ ".pdf";
	    		fileName= new String(fileName.getBytes(), "ISO8859-1");
	    		//文件流
	            reslutJson = is;            
	            logUtil.logInfo("导出Stamp！"+fileName);
	        }
	        catch(Exception e) {
	        	logUtil.logInfo("导出Stamp！"+e.getMessage());
	            e.printStackTrace();
	        }
	        

	        return "success";
	    }

		
}
