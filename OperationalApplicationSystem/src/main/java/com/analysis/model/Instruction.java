package com.analysis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

@Component
@Entity @Table(name = "t_instruction")
public class Instruction {
	@Id
	@GeneratedValue(generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	private String ID;
	@Column
	private String Frame_PO_Repore;
	@Column
	private String Frame_Projection;
	@Column
	private String Mix;
	
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getFrame_PO_Repore() {
		return Frame_PO_Repore;
	}
	public void setFrame_PO_Repore(String frame_PO_Repore) {
		Frame_PO_Repore = frame_PO_Repore;
	}
	public String getFrame_Projection() {
		return Frame_Projection;
	}
	public void setFrame_Projection(String frame_Projection) {
		Frame_Projection = frame_Projection;
	}
	public String getMix() {
		return Mix;
	}
	public void setMix(String mix) {
		Mix = mix;
	}
	
}

