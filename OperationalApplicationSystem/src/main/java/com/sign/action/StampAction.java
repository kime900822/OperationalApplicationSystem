package com.sign.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.persistence.Column;

import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;
import com.kime.base.ActionBase;
import com.kime.infoenum.Message;
import com.kime.model.Dict;
import com.kime.utils.CommonUtil;
import com.sign.biz.StampBIZ;
import com.sign.model.Payment;
import com.sign.model.Stamp;
import com.sign.other.FileSave;

@Controller
public class StampAction extends ActionBase{

		@Autowired
		private StampBIZ stampBIZ;
		@Autowired
		private FileSave fileSave;
		
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
		private boolean urgent;
		private String usageDescription;
		private String attacmentUpload;
		private String dateTmp;
		
		private File[] file;
		private String[] fileFileName;
		private String dfile;
		
		public String getDfile() {
			return dfile;
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
		public boolean isUrgent() {
			return urgent;
		}
		public void setUrgent(boolean urgent) {
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
							stampBIZ.updateStamp(t);
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
				
				if (attacmentUpload!=null&&!"".equals(attacmentUpload)) {
					stamp.setAttacmentUpload(attacmentUpload);
				}
				
				if (!stamp.getId().equals("")&&stamp.getId()!=null) {

			
					stamp.setDateTmp(CommonUtil.getDateTemp());
					stampBIZ.updateStamp(stamp);				
					result.setMessage(Message.SAVE_MESSAGE_SUCCESS);
					result.setStatusCode("200");
					Map<String, String> map=new HashMap<>();
					map.put("id", stamp.getId());
					result.setParams(map);
					logUtil.logInfo("更新付款申请单:"+stamp.getId());			
				}else{
					String code=stampBIZ.getMaxCode();
					stamp.setApplicationCode(code);
					stamp.setId(UUID.randomUUID().toString().replaceAll("-", ""));
					stamp.setDateTmp(CommonUtil.getDateTemp());
					stampBIZ.saveStamp(stamp);
					result.setMessage(Message.SAVE_MESSAGE_SUCCESS);
					result.setStatusCode("200");
					Map<String, String> map=new HashMap<>();
					map.put("id", stamp.getId());
					map.put("applicationCode", code);
					result.setParams(map);
					logUtil.logInfo("新增付款申请单:"+stamp.getId());

				}

				
			} catch (Exception e) {
				logUtil.logInfo("新增付款申请单异常:"+e.getMessage());
				result.setMessage(e.getMessage());
				result.setStatusCode("300");
			}
			
			reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8")); 	
			return SUCCESS;
		}
		
}
