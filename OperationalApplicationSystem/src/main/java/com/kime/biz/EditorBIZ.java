package com.kime.biz;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kime.model.Editor;


public interface EditorBIZ {
	public void saveEditor(Editor editor);
	
	public void updateEditor(Editor editor);
	
	public void deleteEditor(Editor editor);
	
	public List getEditor(String where);
	
	public List getEditor(String where, int pageSize, int pageCurrent);
	
}
