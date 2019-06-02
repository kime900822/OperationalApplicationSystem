package com.customs.biz.impl;

import com.customs.biz.CustomsMappingBIZ;
import com.customs.dao.CustomsMappingDAO;
import com.customs.model.CustomsClearance;
import com.customs.model.CustomsMapping;
import com.customs.model.CustomsMaterial;
import com.customs.model.CustomsProduct;
import com.customs.other.CustomsClearanceHelp;
import com.customs.other.CustomsImportsAndExportsHelp;
import com.kime.base.BizBase;
import com.kime.model.HeadColumn;
import com.kime.model.User;
import com.kime.utils.CommonUtil;
import com.kime.utils.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.List;

@Service
public class CustomsMappingBIZImpl extends BizBase implements CustomsMappingBIZ {

    private static String title="海关物料Mapping";

    @Autowired
    CustomsMappingDAO customsMappingDAO;

    @Override
    public void importData(User user, File file, String first, String upfileFileName, int start) throws Exception {

        try {
            List<CustomsMapping> lMappings=ExcelUtil.FileToList(new CustomsMapping().getClass(),file,first,upfileFileName,start);
            if (lMappings.size()>0) {
                for (CustomsMapping  m: lMappings) {
                    List<CustomsMapping> list=customsMappingDAO.query(" where JDEItemCode='"+m.getJDEItemCode()+"'");
                    if (list.size()>1) {
                        logUtil.logError( title,"导入报错："+m.getJDEItemCode()+"已存在维护记录");
                        throw new Exception("导入报错："+m.getJDEItemCode()+"已存在维护记录");
                    }

                    customsMappingDAO.save(m);
                }

            }else{
                logUtil.logError(title,"导入报错：文件没数据");
                throw new Exception("No data!");
            }

            logUtil.logInfo(title,"导入成功！");

        } catch (Exception e) {
            logUtil.logError(title,"导入报错："+e.getMessage());
            throw new Exception("进（进出口）导入报错："+e.getMessage());
        }

    }

    @Override
    public ByteArrayInputStream exportData(String where, List<HeadColumn> lHeadColumns) throws Exception {
        List list  =customsMappingDAO.query(where);
        Class c = (Class) new CustomsMapping().getClass();
        ByteArrayOutputStream os = ExcelUtil.exportExcel("CustomsMapping", c, list, "yyy-MM-dd",lHeadColumns);
        byte[] fileContent = os.toByteArray();
        return new ByteArrayInputStream(fileContent);
    }

    @Override
    public List<CustomsMapping> query(String where) {
        return customsMappingDAO.query(where);
    }

    @Override
    public List<CustomsMapping> query(String where, int pageSize, int pageCurrent) {
        return customsMappingDAO.query(where,pageSize,pageCurrent);
    }
}
