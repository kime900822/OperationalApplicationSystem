package com.analysis.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.analysis.biz.InstructionBIZ;
import com.analysis.dao.InstructionDAO;
import com.analysis.model.Instruction;
import com.kime.infoenum.Message;

@Service
@Transactional(readOnly=true)
public class InstructionBIZImpl implements InstructionBIZ {
	
	@Autowired
	InstructionDAO instructionDao;
	
	
	public InstructionDAO getInstructionDao() {
		return instructionDao;
	}

	public void setInstructionDao(InstructionDAO instructionDao) {
		this.instructionDao = instructionDao;
	}

	@Override
	public List GetInstruction(String where) {
		return instructionDao.Query(where);
	}

	
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void SaveInstruction(List<Instruction> lInstructions) {
		for (Instruction instruction : lInstructions) {
			instructionDao.Save(instruction);
		}
		
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void DeleteInstruction(Instruction instruction) {
		instructionDao.Delete(instruction);
		
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void ImportInstruction(List<Instruction> lInstructions) {
				for (int i = 0; i < lInstructions.size(); i++) {
					lInstructions.get(i).setID(Integer.toString(i));
					instructionDao.Save(lInstructions.get(i));					
				}
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class )
	public void DeleteInstruction(String where) {
		instructionDao.Delete(where);
		
	}

}
