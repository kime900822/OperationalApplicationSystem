package com.sign.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

import com.kime.model.Department;
import com.kime.model.User;

/**
 * 审批维护
 * @author 10603
 *
 */
@Component
@Entity
@Table(name = "t_sign")
public class SignMan {

	@Id
	@GeneratedValue(generator="assigned")
	@GenericGenerator(name = "assigned", strategy = "assigned")
	private String sid;
	@Column
	private String type;
	@Column
	private String uid;
	@Column
	private String did;
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name="did",insertable=false,updatable=false)
	private Department department;
	@Transient
	private String dname;
	@Column
	private String uname;
	@Column
	private String uid2;
	@Column
	private String uname2;
	
	public void setDname(String dname) {
		this.dname = dname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getDname() {
		return department.getName();
	}

	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getDid() {
		return did;
	}
	public void setDid(String did) {
		this.did = did;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public String getUid2() {
		return uid2;
	}
	public void setUid2(String uid2) {
		this.uid2 = uid2;
	}
	public String getUname2() {
		return uname2;
	}
	public void setUname2(String uname2) {
		this.uname2 = uname2;
	}
	public String getUname() {
		return uname;
	}
	
	
}
