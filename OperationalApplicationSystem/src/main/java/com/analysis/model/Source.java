package com.analysis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

@Component
@Entity @Table(name = "t_source")
public class Source {
	@Id
	@GeneratedValue(generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	private String ID;
	@Column
	private String Project_Code;
	@Column
	private String Type;
	@Column
	private String Labour_Cost_50;
	@Column
	private String Material_50;
	@Column
	private String Consumption_50;
	@Column
	private String Goods_Transport_50;
	@Column
	private String Other_50;
	@Column
	private String Indirect_Labour_Cost_50;
	@Column
	private String General_MFG_Expenses_50;
	@Column
	private String Research_Development_50;
	@Column
	private String Factory_Amortization_50;
	@Column
	private String Sales_Marketing_50;
	@Column
	private String General_Administration_Cost_50;
	@Column
	private String Labour_Cost_60;
	@Column
	private String Material_60;
	@Column
	private String Consumption_60;
	@Column
	private String Goods_Transport_60;
	@Column
	private String Other_60;
	@Column
	private String Indirect_Labour_Cost_60;
	@Column
	private String General_MFG_Expenses_60;
	@Column
	private String Research_Development_60;
	@Column
	private String Factory_Amortization_60;
	@Column
	private String Sales_Marketing_60;
	@Column
	private String General_Administration_Cost_60;
	@Column
	private String Labour_Cost_70;
	@Column
	private String Material_70;
	@Column
	private String Consumption_70;
	@Column
	private String Goods_Transport_70;
	@Column
	private String Other_70;
	@Column
	private String Indirect_Labour_Cost_70;
	@Column
	private String General_MFG_Expenses_70;
	@Column
	private String Research_Development_70;
	@Column
	private String Factory_Amortization_70;
	@Column
	private String Sales_Marketing_70;
	@Column
	private String General_Administration_Cost_70;
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getProject_Code() {
		return Project_Code;
	}
	public void setProject_Code(String project_Code) {
		Project_Code = project_Code;
	}

	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public String getLabour_Cost_50() {
		return Labour_Cost_50;
	}
	public void setLabour_Cost_50(String labour_Cost_50) {
		Labour_Cost_50 = labour_Cost_50;
	}
	public String getMaterial_50() {
		return Material_50;
	}
	public void setMaterial_50(String material_50) {
		Material_50 = material_50;
	}
	public String getConsumption_50() {
		return Consumption_50;
	}
	public void setConsumption_50(String consumption_50) {
		Consumption_50 = consumption_50;
	}
	public String getGoods_Transport_50() {
		return Goods_Transport_50;
	}
	public void setGoods_Transport_50(String goods_Transport_50) {
		Goods_Transport_50 = goods_Transport_50;
	}
	public String getOther_50() {
		return Other_50;
	}
	public void setOther_50(String other_50) {
		Other_50 = other_50;
	}
	public String getGeneral_MFG_Expenses_50() {
		return General_MFG_Expenses_50;
	}
	public void setGeneral_MFG_Expenses_50(String general_MFG_Expenses_50) {
		General_MFG_Expenses_50 = general_MFG_Expenses_50;
	}
	public String getResearch_Development_50() {
		return Research_Development_50;
	}
	public void setResearch_Development_50(String research_Development_50) {
		Research_Development_50 = research_Development_50;
	}
	public String getFactory_Amortization_50() {
		return Factory_Amortization_50;
	}
	public void setFactory_Amortization_50(String factory_Amortization_50) {
		Factory_Amortization_50 = factory_Amortization_50;
	}
	public String getSales_Marketing_50() {
		return Sales_Marketing_50;
	}
	public void setSales_Marketing_50(String sales_Marketing_50) {
		Sales_Marketing_50 = sales_Marketing_50;
	}
	public String getGeneral_Administration_Cost_50() {
		return General_Administration_Cost_50;
	}
	public void setGeneral_Administration_Cost_50(String general_Administration_Cost_50) {
		General_Administration_Cost_50 = general_Administration_Cost_50;
	}
	public String getLabour_Cost_60() {
		return Labour_Cost_60;
	}
	public void setLabour_Cost_60(String labour_Cost_60) {
		Labour_Cost_60 = labour_Cost_60;
	}
	public String getMaterial_60() {
		return Material_60;
	}
	public void setMaterial_60(String material_60) {
		Material_60 = material_60;
	}
	public String getConsumption_60() {
		return Consumption_60;
	}
	public void setConsumption_60(String consumption_60) {
		Consumption_60 = consumption_60;
	}
	public String getGoods_Transport_60() {
		return Goods_Transport_60;
	}
	public void setGoods_Transport_60(String goods_Transport_60) {
		Goods_Transport_60 = goods_Transport_60;
	}
	public String getOther_60() {
		return Other_60;
	}
	public void setOther_60(String other_60) {
		Other_60 = other_60;
	}
	public String getGeneral_MFG_Expenses_60() {
		return General_MFG_Expenses_60;
	}
	public void setGeneral_MFG_Expenses_60(String general_MFG_Expenses_60) {
		General_MFG_Expenses_60 = general_MFG_Expenses_60;
	}
	public String getResearch_Development_60() {
		return Research_Development_60;
	}
	public void setResearch_Development_60(String research_Development_60) {
		Research_Development_60 = research_Development_60;
	}
	public String getFactory_Amortization_60() {
		return Factory_Amortization_60;
	}
	public void setFactory_Amortization_60(String factory_Amortization_60) {
		Factory_Amortization_60 = factory_Amortization_60;
	}
	public String getSales_Marketing_60() {
		return Sales_Marketing_60;
	}
	public void setSales_Marketing_60(String sales_Marketing_60) {
		Sales_Marketing_60 = sales_Marketing_60;
	}
	public String getGeneral_Administration_Cost_60() {
		return General_Administration_Cost_60;
	}
	public void setGeneral_Administration_Cost_60(String general_Administration_Cost_60) {
		General_Administration_Cost_60 = general_Administration_Cost_60;
	}
	public String getLabour_Cost_70() {
		return Labour_Cost_70;
	}
	public void setLabour_Cost_70(String labour_Cost_70) {
		Labour_Cost_70 = labour_Cost_70;
	}
	public String getMaterial_70() {
		return Material_70;
	}
	public void setMaterial_70(String material_70) {
		Material_70 = material_70;
	}
	public String getConsumption_70() {
		return Consumption_70;
	}
	public void setConsumption_70(String consumption_70) {
		Consumption_70 = consumption_70;
	}
	public String getGoods_Transport_70() {
		return Goods_Transport_70;
	}
	public void setGoods_Transport_70(String goods_Transport_70) {
		Goods_Transport_70 = goods_Transport_70;
	}
	public String getOther_70() {
		return Other_70;
	}
	public void setOther_70(String other_70) {
		Other_70 = other_70;
	}
	public String getGeneral_MFG_Expenses_70() {
		return General_MFG_Expenses_70;
	}
	public void setGeneral_MFG_Expenses_70(String general_MFG_Expenses_70) {
		General_MFG_Expenses_70 = general_MFG_Expenses_70;
	}
	public String getResearch_Development_70() {
		return Research_Development_70;
	}
	public void setResearch_Development_70(String research_Development_70) {
		Research_Development_70 = research_Development_70;
	}
	public String getFactory_Amortization_70() {
		return Factory_Amortization_70;
	}
	public void setFactory_Amortization_70(String factory_Amortization_70) {
		Factory_Amortization_70 = factory_Amortization_70;
	}
	public String getSales_Marketing_70() {
		return Sales_Marketing_70;
	}
	public void setSales_Marketing_70(String sales_Marketing_70) {
		Sales_Marketing_70 = sales_Marketing_70;
	}
	public String getGeneral_Administration_Cost_70() {
		return General_Administration_Cost_70;
	}
	public void setGeneral_Administration_Cost_70(String general_Administration_Cost_70) {
		General_Administration_Cost_70 = general_Administration_Cost_70;
	}
	public String getIndirect_Labour_Cost_50() {
		return Indirect_Labour_Cost_50;
	}
	public void setIndirect_Labour_Cost_50(String indirect_Labour_Cost_50) {
		Indirect_Labour_Cost_50 = indirect_Labour_Cost_50;
	}
	public String getIndirect_Labour_Cost_60() {
		return Indirect_Labour_Cost_60;
	}
	public void setIndirect_Labour_Cost_60(String indirect_Labour_Cost_60) {
		Indirect_Labour_Cost_60 = indirect_Labour_Cost_60;
	}
	public String getIndirect_Labour_Cost_70() {
		return Indirect_Labour_Cost_70;
	}
	public void setIndirect_Labour_Cost_70(String indirect_Labour_Cost_70) {
		Indirect_Labour_Cost_70 = indirect_Labour_Cost_70;
	}
	
}
