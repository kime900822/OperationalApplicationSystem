package com.kime.model;

public class HeadColumn {
	private String name;
	
	private String width;
	
	private String align;
	
	private String label;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getAlign() {
		return align;
	}

	public void setAlign(String align) {
		this.align = align;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	public HeadColumn(String name,String width,String align,String lable) {
		this.name=name;
		this.width=width;
		this.align=align;
		this.label=lable;				
	}
	
}
