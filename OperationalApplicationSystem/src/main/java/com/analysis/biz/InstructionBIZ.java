package com.analysis.biz;

import java.util.List;

import org.springframework.stereotype.Service;

import com.analysis.model.Instruction;

@Service
public interface InstructionBIZ {
	public List GetInstruction(String where);
	
	public void SaveInstruction(List<Instruction> lInstructions);
	
	public void DeleteInstruction(Instruction instruction);
	
	public void ImportInstruction(List<Instruction> lInstructions);
	
	public void DeleteInstruction(String where);
}
