package com.sign.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

@Component
@Entity @Table(name = "t_payment_week")
public class PaymentWeek {
	
	
	@Column
	private String week;
	
	@Id
	@GeneratedValue(generator="assigned")
	@GenericGenerator(name = "assigned", strategy = "assigned")
	private String pid;


	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}


	
	

}
