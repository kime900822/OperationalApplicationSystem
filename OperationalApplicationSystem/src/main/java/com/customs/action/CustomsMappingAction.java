package com.customs.action;

import com.customs.biz.CustomsMappingBIZ;
import com.customs.other.CustomsClearanceHelp;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kime.base.ActionBase;
import com.kime.infoenum.Message;
import com.kime.model.HeadColumn;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
@Scope("prototype")
@ParentPackage("Customs")
public class CustomsMappingAction extends ActionBase {

    @Autowired
    CustomsMappingBIZ customsMappingBIZ;

    String id;

    String customsSerialNumber;
    String customsCode;
    String customsMaterialDescription;
    String JDEItemCode;
    String JDEItemDescription;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomsSerialNumber() {
        return customsSerialNumber;
    }

    public void setCustomsSerialNumber(String customsSerialNumber) {
        this.customsSerialNumber = customsSerialNumber;
    }

    public String getCustomsCode() {
        return customsCode;
    }

    public void setCustomsCode(String customsCode) {
        this.customsCode = customsCode;
    }

    public String getCustomsMaterialDescription() {
        return customsMaterialDescription;
    }

    public void setCustomsMaterialDescription(String customsMaterialDescription) {
        this.customsMaterialDescription = customsMaterialDescription;
    }


    public String getJDEItemDescription() {
        return JDEItemDescription;
    }

    public void setJDEItemDescription(String JDEItemDescription) {
        this.JDEItemDescription = JDEItemDescription;
    }

    public String getJDEItemCode() {
        return JDEItemCode;
    }

    public void setJDEItemCode(String JDEItemCode) {
        this.JDEItemCode = JDEItemCode;
    }

    @Action(value="queryCustomsMapping",results={@org.apache.struts2.convention.annotation.Result(type="stream",
            params={
                    "inputName", "reslutJson"
            })})
    public String queryCustomsMapping() throws UnsupportedEncodingException {

        List list  =customsMappingBIZ.query(getQueryWhere(), Integer.parseInt(pageSize),Integer.parseInt(pageCurrent));
        int total=customsMappingBIZ.query(getQueryWhere()).size();

        queryResult.setList(list);
        queryResult.setTotalRow(total);
        queryResult.setFirstPage(Integer.parseInt(pageCurrent)==1?true:false);
        queryResult.setPageNumber(Integer.parseInt(pageCurrent));
        queryResult.setLastPage(total/Integer.parseInt(pageSize) +1==Integer.parseInt(pageCurrent)&&Integer.parseInt(pageCurrent)!=1?true:false);
        queryResult.setTotalPage(total/Integer.parseInt(pageSize) +1);
        queryResult.setPageSize(Integer.parseInt(pageSize));
        String r=callback+"("+new Gson().toJson(queryResult)+")";

        reslutJson=new ByteArrayInputStream(r.getBytes("UTF-8"));

        logUtil.logInfo("查询进（进出口）数据，条件:"+getQueryWhere());
        return SUCCESS;
    }


    @Action(value="exportCustomsMapping",results={@org.apache.struts2.convention.annotation.Result(type="stream",
            params={
                    "inputName", "reslutJson",
                    "contentType","application/vnd.ms-excel",
                    "contentDisposition","attachment;filename=%{fileName}",
                    "bufferSize","1024"
            })})
    public String exportCustomsMapping() {
        try {
            List<HeadColumn> lHeadColumns=new Gson().fromJson(thead, new TypeToken<ArrayList<HeadColumn>>() {}.getType());

            ByteArrayInputStream  is = customsMappingBIZ.exportData(getQueryWhere(),lHeadColumns);

            HttpServletResponse response = (HttpServletResponse)
                    ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_RESPONSE);
            response.setHeader("Set-Cookie", "fileDownload=true; path=/");


            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
            fileName = "CustomsClearance"+sf.format(new Date()).toString()+ ".xls";
            fileName= new String(fileName.getBytes(), "ISO8859-1");
            //文件流
            reslutJson = is;
            logUtil.logInfo(CustomsClearanceHelp.title,"导出CustomsClearance！"+fileName);
        }
        catch(Exception e) {
            logUtil.logInfo(CustomsClearanceHelp.title,"导出CustomsClearance！"+e.getMessage());
            e.printStackTrace();
        }

        return "success";
    }

    /**
     * excel导入
     * @return
     * @throws IOException
     * @throws FileNotFoundException
     */
    @Action(value="importCustomsMapping",results={@org.apache.struts2.convention.annotation.Result(type="stream",
            params={
                    "inputName", "reslutJson"
            })})
    public String  importCustomsMapping() throws FileNotFoundException, IOException{
        try {
            if (upfile!=null) {
                customsMappingBIZ.importData(getUser(), upfile, first, upfileFileName[0], 2);
                result.setMessage(Message.UPLOAD_MESSAGE_SUCCESS);
                result.setStatusCode("200");
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

    public String getQueryWhere() {

        String where =" where 1=1 ";
        if (id!=null&&!id.equals("")) {
            where+= " and id = '"+id+"' ";
        }
        if (customsSerialNumber!=null&&!customsSerialNumber.equals("")) {
            where+= " and customsSerialNumber like '%"+customsSerialNumber+"%' ";
        }
        if (customsCode!=null&&!customsCode.equals("")) {
            where+= " and customsCode = '"+customsCode+"' ";
        }
        if (customsMaterialDescription!=null&&!customsMaterialDescription.equals("")) {
            where+= " and customsMaterialDescription = '"+customsMaterialDescription+"' ";
        }
        if (JDEItemCode!=null&&!JDEItemCode.equals("")) {
            where+= " and JDEItemCode = '"+JDEItemCode+"' ";
        }
        if (JDEItemDescription!=null&&!JDEItemDescription.equals("")) {
            where+= " and JDEItemDescription like '%"+JDEItemDescription+"%' ";
        }

        return where;

    }
}
