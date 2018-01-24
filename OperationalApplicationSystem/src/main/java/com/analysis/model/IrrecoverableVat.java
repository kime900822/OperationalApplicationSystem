package com.analysis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

@Component
@Entity @Table(name = "t_irrecoverable_vat")
public class IrrecoverableVat {
	@Id
	@GeneratedValue(generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	private String ID;
	@Column
	private String Short_Item_No;
	@Column
	private String Batch_Number;
	@Column
	private String Sub_ledger;
	@Column
	private String Purchase_Order;
	@Column
	private String Managrl_Code_1;	
	@Column
	private String Account_Number;
	@Column
	private String Account_Description;
	@Column
	private String Do_Ty;
	@Column
	private String Doc_Number;
	@Column
	private String Doc_Co;
	@Column
	private String GL_Date;
	@Column
	private String Address_Number;
	@Column
	private String Explanation;
	@Column
	private String LT_1_Amount;
	
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getBatch_Number() {
		return Batch_Number;
	}
	public void setBatch_Number(String batch_Number) {
		Batch_Number = batch_Number;
	}
	public String getSub_ledger() {
		return Sub_ledger;
	}
	public void setSub_ledger(String sub_ledger) {
		Sub_ledger = sub_ledger;
	}
	public String getPurchase_Order() {
		return Purchase_Order;
	}
	public void setPurchase_Order(String purchase_Order) {
		Purchase_Order = purchase_Order;
	}
	public String getManagrl_Code_1() {
		return Managrl_Code_1;
	}
	public void setManagrl_Code_1(String managrl_Code_1) {
		Managrl_Code_1 = managrl_Code_1;
	}
	public String getAccount_Number() {
		return Account_Number;
	}
	public void setAccount_Number(String account_Number) {
		Account_Number = account_Number;
	}
	public String getAccount_Description() {
		return Account_Description;
	}
	public void setAccount_Description(String account_Description) {
		Account_Description = account_Description;
	}
	public String getDo_Ty() {
		return Do_Ty;
	}
	public void setDo_Ty(String do_Ty) {
		Do_Ty = do_Ty;
	}
	public String getDoc_Number() {
		return Doc_Number;
	}
	public void setDoc_Number(String doc_Number) {
		Doc_Number = doc_Number;
	}
	public String getDoc_Co() {
		return Doc_Co;
	}
	public void setDoc_Co(String doc_Co) {
		Doc_Co = doc_Co;
	}
	public String getGL_Date() {
		return GL_Date;
	}
	public void setGL_Date(String gL_Date) {
		GL_Date = gL_Date;
	}
	public String getAddress_Number() {
		return Address_Number;
	}
	public void setAddress_Number(String address_Number) {
		Address_Number = address_Number;
	}
	public String getExplanation() {
		return Explanation;
	}
	public void setExplanation(String explanation) {
		Explanation = explanation;
	}
	public String getLT_1_Amount() {
		return LT_1_Amount;
	}
	public void setLT_1_Amount(String lT_1_Amount) {
		LT_1_Amount = lT_1_Amount;
	}
	public String getShort_Item_No() {
		return Short_Item_No;
	}
	public void setShort_Item_No(String short_Item_No) {
		Short_Item_No = short_Item_No;
	}
	
}
