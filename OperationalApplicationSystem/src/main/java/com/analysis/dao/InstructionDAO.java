package com.analysis.dao;

import java.util.List;

import org.apache.poi.poifs.storage.BATBlock.BATBlockAndIndex;
import org.springframework.stereotype.Repository;

import com.analysis.model.Instruction;

@Repository
public interface InstructionDAO {
	public List Query(String where);
	
	public void Save(Instruction instruction);
	
	public void Delete(Instruction instruction);
	
	public void Delete(String where);
}
