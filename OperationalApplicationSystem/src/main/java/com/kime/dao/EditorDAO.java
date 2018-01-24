package com.kime.dao;

import java.util.List;

import com.kime.model.Editor;

public interface EditorDAO {
	
	public List query(String where);
	
	public void save(Editor editor);
	
	public void delete(Editor editor);
	
	public List query(String where, int pageSize, int pageCurrent);
	
	public void mod(Editor editor);
}
