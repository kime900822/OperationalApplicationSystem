package com.kime.biz;

import java.util.List;

import com.kime.model.Dict;

public interface DictBIZ {
	
	/**
	 * 获取虽有签核类型
	 * @return
	 */
	public List<Dict> getALLSign();
	
	
	public List<Dict> getAllType();
	
	public List<Dict> getDict(String where);
	
	public List<Dict> getDict(String where,Integer pageSize,Integer pageCurrent);
	
	public void save(Dict dict);
	
	public void update(Dict dict);
	
	public void delete(Dict dict);

}
