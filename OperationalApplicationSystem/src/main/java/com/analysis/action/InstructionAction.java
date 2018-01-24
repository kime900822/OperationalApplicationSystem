package com.analysis.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.analysis.biz.InstructionBIZ;
import com.analysis.model.Instruction;
import com.analysis.model.Sales;
import com.google.gson.Gson;
import com.kime.action.ActionBase;
import com.kime.infoenum.Message;
import com.kime.model.Result;
import com.kime.model.User;
import com.kime.utils.ExcelUtil;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
@ParentPackage("DataImport")
public class InstructionAction extends ActionBase {
	@Autowired
	private InstructionBIZ instructionBIZ;
	@Autowired
	private Instruction instruction;


	public InstructionBIZ getInstructionBIZ() {
		return instructionBIZ;
	}
	public void setInstructionBIZ(InstructionBIZ instructionBIZ) {
		this.instructionBIZ = instructionBIZ;
	}
	public Instruction getInstruction() {
		return instruction;
	}
	public void setInstruction(Instruction instruction) {
		this.instruction = instruction;
	}
	public InputStream getReslutJson() {
		return reslutJson;
	}
	public void setReslutJson(InputStream reslutJson) {
		this.reslutJson = reslutJson;
	}
	
	
	@Action(value="getInstruction",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String GetInstruction() throws UnsupportedEncodingException{
		
		List<Instruction> lInstructions=instructionBIZ.GetInstruction("");		
		reslutJson=new ByteArrayInputStream(new Gson().toJson(lInstructions).getBytes("UTF-8"));  
		return SUCCESS;
	}
	
	/**
     * excel导入
     * @return
     * @throws IOException 
     * @throws FileNotFoundException 
     */
	@Action(value="importInstruction",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
    public String  ImportInstruction() throws FileNotFoundException, IOException{
        try {
//        	List<Instruction> lInstructions=new ArrayList<Instruction>();
	    	if (upfile!=null) {
//	        	POIFSFileSystem fs=new POIFSFileSystem(new FileInputStream(upfile));   
//	        	HSSFWorkbook wb = new HSSFWorkbook(fs); 
//	        	HSSFSheet sheet = wb.getSheetAt(0); 
//	            // 循环遍历表,sheet.getLastRowNum()是获取一个表最后一条记录的记录号
//	            for (int i = Integer.parseInt(first)-1 ; i <= sheet.getLastRowNum(); i++) {
//	                // 创建一个行对象
//	                HSSFRow row = sheet.getRow(i);
//	                	Instruction instruction=new Instruction();
//	                	instruction.setFrame_PO_Repore(row.getCell(0).getStringCellValue());
//	                	instruction.setFrame_Projection(row.getCell(1).getStringCellValue());
//	                	instruction.setMix(row.getCell(2).getStringCellValue());
//	    				lInstructions.add(instruction);
//	            }
//	            instructionBIZ.ImportInstruction(lInstructions);
//	            wb.close();
//	            fs.close();
//	            result.setMessage(Message.UPLOAD_MESSAGE_SUCCESS);
//				result.setStatusCode("200");
	    		
	    		
	    		List<Instruction> lInstructions=ExcelUtil.FileToList(instruction.getClass(),upfile,first,upfileFileName[0],2);
	    		if (lInstructions.size()>0) {
					instructionBIZ.DeleteInstruction("");
					instructionBIZ.SaveInstruction(lInstructions);
					result.setMessage(Message.UPLOAD_MESSAGE_SUCCESS);
					result.setStatusCode("200");
				}else{
					result.setMessage(Message.UPLOAD_MESSAGE_ERROR_NODATA);
					result.setStatusCode("200");					
				}
			}else{
				result.setMessage(Message.UPLOAD_MESSAGE_ERROE);
				result.setStatusCode("300");
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			result.setStatusCode("300");
		}
        reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8"));  
    	return SUCCESS;
    }
	
	
	 /**
     * excel导出
     * @return
     */
	@Action(value="exportInstructionExcel",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson",
					"contentType","application/vnd.ms-excel",
					"contentDisposition","attachment;filename=%{fileName}",
					"bufferSize","1024"
			})})
    public String ExportInstructionExcel() {
        try {
        	
    		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");		 
    		fileName = "Instruction"+sf.format(new Date()).toString()+ ".xls";
    		fileName= new String(fileName.getBytes(), "ISO8859-1");
    		
        	List<Instruction> lInstructions=instructionBIZ.GetInstruction("");
        	Class c = (Class) instruction.getClass();  
        	ByteArrayOutputStream os=ExcelUtil.exportExcel("instruction", c, lInstructions, "yyy-MM-dd");
        	byte[] fileContent = os.toByteArray();
        	ByteArrayInputStream is = new ByteArrayInputStream(fileContent);
        	

    		//文件流
            reslutJson = is;          
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return "success";
    }
	
}
