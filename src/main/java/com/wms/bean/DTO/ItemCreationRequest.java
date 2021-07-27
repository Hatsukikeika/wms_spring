package com.wms.bean.DTO;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class ItemCreationRequest {

	@NotNull
	private Double length;
	
	@NotNull
	private Double height;
	
	@NotNull
	private Double width;
	
	@NotNull
	private Double weight;
	
	@NotBlank
	private String weight_unit;
	
	@NotBlank
	private String size_unit;
	
	@NotBlank
	private String unit;
	
	@NotBlank
	private String name;
	
	private String imgurl;
	
	@Length(min = 0, max = 150)
	private String notes;
	
	@NotBlank
	private String sku;

	public Double getLength() {
		return length;
	}

	public void setLength(Double length) {
		this.length = length;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Double getWidth() {
		return width;
	}

	public void setWidth(Double width) {
		this.width = width;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getWeight_unit() {
		return weight_unit;
	}

	public void setWeight_unit(String weight_unit) {
		this.weight_unit = weight_unit;
	}

	public String getSize_unit() {
		return size_unit;
	}

	public void setSize_unit(String size_unit) {
		this.size_unit = size_unit;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	
	
}
