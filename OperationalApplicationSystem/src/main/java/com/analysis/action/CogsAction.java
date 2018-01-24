package com.analysis.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.struts2.components.If;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.analysis.biz.CogsBIZ;
import com.analysis.model.COGS_Details;
import com.google.gson.Gson;
import com.kime.action.ActionBase;
import com.kime.model.Result;
import com.kime.utils.ExcelUtil;
import com.opensymphony.xwork2.ActionSupport;

@Scope("prototype")
@ParentPackage("DataImport")
public class CogsAction extends ActionBase {

	@Autowired
	private CogsBIZ cogsBIZ;
	@Autowired
	private COGS_Details cogs_Details;
	
	public COGS_Details getCogs_Details() {
		return cogs_Details;
	}
	public void setCogs_Details(COGS_Details cogs_Details) {
		this.cogs_Details = cogs_Details;
	}

	private String Month;
	private String Customer;
	private String ProductMix;
	private String FramePoReport;
	private String projectNO;
	private String FrameProjection;
	private String Rate;
	private String Notes;
	private String fileName;
	

	public String getNotes() {
		return Notes;
	}
	public void setNotes(String notes) {
		Notes = notes;
	}

	public String getRate() {
		return Rate;
	}
	public void setRate(String rate) {
		Rate = rate;
	}
	public CogsBIZ getCogsBIZ() {
		return cogsBIZ;
	}
	public void setCogsBIZ(CogsBIZ cogsBIZ) {
		this.cogsBIZ = cogsBIZ;
	}
	public InputStream getReslutJson() {
		return reslutJson;
	}
	public void setReslutJson(InputStream reslutJson) {
		this.reslutJson = reslutJson;
	}
	public String getMonth() {
		return Month;
	}
	public void setMonth(String month) {
		Month = month;
	}
	public String getCustomer() {
		return Customer;
	}
	public void setCustomer(String customer) {
		Customer = customer;
	}
	public String getProductMix() {
		return ProductMix;
	}
	public void setProductMix(String productMix) {
		ProductMix = productMix;
	}
	public String getFramePoReport() {
		return FramePoReport;
	}
	public void setFramePoReport(String framePoReport) {
		FramePoReport = framePoReport;
	}
	public String getProjectNO() {
		return projectNO;
	}
	public void setProjectNO(String projectNO) {
		this.projectNO = projectNO;
	}
	public String getFrameProjection() {
		return FrameProjection;
	}
	public void setFrameProjection(String frameProjection) {
		FrameProjection = frameProjection;
	}
	
	
	public String getWhere(){
		StringBuilder sBuilder=new StringBuilder();
		if (!"".equals(Month)&&Month!=null) {
			sBuilder.append(" AND Month='"+Month+"'");
		}
		if (!"".equals(Customer)&&Customer!=null) {
			sBuilder.append(" AND Customer like '%"+Customer+"%' ");
		}
		if (!"".equals(ProductMix)&&ProductMix!=null) {
			sBuilder.append(" AND ProductMix like '%"+ProductMix+"$' ");
		}
		if (!"".equals(FramePoReport)&&FramePoReport!=null) {
			sBuilder.append(" AND FramePoReport like '%"+FramePoReport+"%' ");
		}
		if (!"".equals(projectNO)&&projectNO!=null) {
			sBuilder.append(" AND projectNO like '%"+projectNO+"%' ");
		}
		if (!"".equals(FrameProjection)&&FrameProjection!=null) {
			sBuilder.append(" AND FrameProjection like '%"+FrameProjection+"%' ");
		}
		if (!"".equals(Notes)&&Notes!=null) {
			sBuilder.append(" AND Notes like '%"+Notes+"%' ");
		}
		
		if (sBuilder.length()>0) {
			sBuilder.delete(0, 4);
			sBuilder.insert(0, " where ");
		}
		return sBuilder.toString();
	}
	
	
	@Action(value="getCogsDetail",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String GetCogsDetail() throws UnsupportedEncodingException{
		
		List<COGS_Details> lCogs_Details=cogsBIZ.getCogs(getWhere(),Rate);
		reslutJson=new ByteArrayInputStream(new Gson().toJson(lCogs_Details).getBytes("UTF-8"));  
		return SUCCESS;
	}
	
	
	 /**
     * excel导出
     * @return
     */
	@Action(value="exportCogsDetailExcel",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson",
					"contentType","application/vnd.ms-excel",
					"contentDisposition","attachment;filename=%{fileName}",
					"bufferSize","1024"
			})})
    public String ExportCogsDetailExcel() {
        try {

        	List<COGS_Details> lCogs_Details=cogsBIZ.getCogs(getWhere(),Rate);
        	Class c = (Class) cogs_Details.getClass();  
        	ByteArrayOutputStream os=ExcelUtil.exportExcel("Sales", c, lCogs_Details, "yyy-MM-dd");
        	byte[] fileContent = os.toByteArray();
        	ByteArrayInputStream is = new ByteArrayInputStream(fileContent);
        	
    		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
    		if (!"".equals(Rate)&&Rate!=null) {
    			fileName = "CogsDetailUSD"+sf.format(new Date()).toString()+ ".xls";
			}else{
				fileName = "CogsDetailRMB"+sf.format(new Date()).toString()+ ".xls";
			}
    		
    		fileName= new String(fileName.getBytes(), "ISO8859-1");
    		//文件流
            reslutJson = is;            
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return "success";
    }
	
}
