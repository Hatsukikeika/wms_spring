package com.wms.bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OrderColumn;
import javax.persistence.Table;


@Entity
@Table(name = "wmsitem")
public class Item extends HasIdentity implements Serializable {
	
	@OrderColumn(name="companyitem_sku")
	@ElementCollection
	private List<String> SKU;
	
	@Column(name="companyitem_iname")
	private String name;
	
	@Column(name="companyitem_units", nullable=true)
	private String unit;
	
	@Column(name="companyitem_imgurl", nullable=true)
	private String imgurl;

	public Item() {
		super();
	}

	public List<String> getSKU() {
		return SKU;
	}

	public Item setSKU(List<String> sKU) {
		SKU = sKU;
		return this;
	}

	public String getName() {
		return name;
	}

	public Item setName(String name) {
		this.name = name;
		return this;
	}

	public String getUnit() {
		return unit;
	}

	public Item setUnit(String unit) {
		this.unit = unit;
		return this;
	}

	public String getImgurl() {
		return imgurl;
	}

	public Item setImgurl(String imgurl) {
		this.imgurl = imgurl;
		return this;
	}
	
	
}
