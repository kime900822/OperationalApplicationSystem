package com.customs.dao;

import com.customs.model.CustomsMapping;

import java.util.List;

public interface CustomsMappingDAO {

    public void save(CustomsMapping customsMapping);

    public void update(CustomsMapping customsMapping);

    public void delete(CustomsMapping customsMapping);

    public List query(String where);

    public List query(String where,Integer pageSize,Integer pageCurrent);
}
