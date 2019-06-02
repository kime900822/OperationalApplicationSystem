package com.customs.biz;

import com.customs.model.CustomsMapping;
import com.kime.model.HeadColumn;
import com.kime.model.User;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.List;

public interface CustomsMappingBIZ {

    public void importData(User user, File file, String first, String upfileFileName, int start) throws Exception;

    public ByteArrayInputStream exportData(String where, List<HeadColumn> lHeadColumns) throws Exception;

    public List<CustomsMapping> query(String where);

    public List<CustomsMapping> query(String where, int pageSize, int pageCurrent);
}
