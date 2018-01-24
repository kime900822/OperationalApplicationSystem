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
	private String Id;
	@Column
	private String type;
	@Column(name="`key`")
	private String key;
	@Column
	private String value;
	@Column
	private String keyExplain;
	@Column
	private String valueExplain;
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
		return Id;
	}
	public void setId(String id) {
		Id = id;
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

	
	
}
