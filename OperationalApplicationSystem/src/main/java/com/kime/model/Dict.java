package com.kime.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "t_dict")
public class Dict {
	
	@Id
	@GeneratedValue(generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	private String id;
	@Column
	private String type;
	@Column(name="`key`")
	private String key;
	@Column
	private String keyName;
	@Column
	private String value;
	@Column
	private String valueName;
	@Column
	private String keyExplain;
	@Column
	private String valueExplain;
	@Column
	private String tmp1;
	@Column
	private String tmp2;
	@Transient
	private String addFlag;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getAddFlag() {
		return addFlag;
	}
	public void setAddFlag(String addFlag) {
		this.addFlag = addFlag;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getKeyExplain() {
		return keyExplain;
	}
	public void setKeyExplain(String keyExplain) {
		this.keyExplain = keyExplain;
	}
	public String getValueExplain() {
		return valueExplain;
	}
	public void setValueExplain(String valueExplain) {
		this.valueExplain = valueExplain;
	}
	public String getKeyName() {
		return keyName;
	}
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}
	public String getValueName() {
		return valueName;
	}
	public void setValueName(String valueName) {
		this.valueName = valueName;
	}
	public String getTmp1() {
		return tmp1;
	}
	public void setTmp1(String tmp1) {
		this.tmp1 = tmp1;
	}
	public String getTmp2() {
		return tmp2;
	}
	public void setTmp2(String tmp2) {
		this.tmp2 = tmp2;
	}

	
	
}
