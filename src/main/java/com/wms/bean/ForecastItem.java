package com.wms.bean;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "wmsfcitem")
@DiscriminatorValue("iteminstock")
public class ForecastItem extends Package {

	@Column(name="forecastitem_sku")
	private String sku;
	
	@Column(name="forecastitem_iname")
	private String name;
	
	@Column(name="forecastitem_units", nullable=true)
	private String unit;
	
	@Column(name="forecastitem_ci_weight", nullable=true)
	private Double ci_weight;
	
	@Column(name="forecastitem_ci_height", nullable=true)
	private Double ci_height;
	
	@Column(name="forecastitem_ci_length", nullable=true)
	private Double ci_length;
	
	@Column(name="forecastitem_ci_width", nullable=true)
	private Double ci_width;
	
	@Column(name="forecastitem_sellerAccepted", nullable=true)
	private Boolean sellerAccepted;
	
	@Column(name="forecastitem_onReturn", nullable=true)
	private Boolean onReturn;
	
	public ForecastItem() {
		super();
	}
	
	public ForecastItem(ItemInfo iteminfo) {
		super();
		this.Update(iteminfo);
	}
	
	public void Update(ItemInfo iteminfo) {
		this.name = iteminfo.getName();
		this.unit = iteminfo.getUnit();
		this.sku = iteminfo.getSku();
		this.setHeight(iteminfo.getHeight());
		this.setLength(iteminfo.getLength());
		this.setWeight(iteminfo.getWeight());
		this.setWidth(iteminfo.getWidth());
		this.setWeight_unit(iteminfo.getWeight_unit());
		this.setSize_unit(iteminfo.getSize_unit());
		this.setNotes(iteminfo.getNotes());
	}

	public String getSku() {
		return sku;
	}

	public ForecastItem setSku(String sku) {
		this.sku = sku;
		return this;
	}

	public String getName() {
		return name;
	}

	public ForecastItem setName(String name) {
		this.name = name;
		return this;
	}

	public String getUnit() {
		return unit;
	}

	public ForecastItem setUnit(String unit) {
		this.unit = unit;
		return this;
	}

	public Double getCi_weight() {
		return ci_weight;
	}

	public ForecastItem setCi_weight(Double ci_weight) {
		this.ci_weight = ci_weight;
		return this;
	}

	public Double getCi_height() {
		return ci_height;
	}

	public ForecastItem setCi_height(Double ci_height) {
		this.ci_height = ci_height;
		return this;
	}

	public Double getCi_length() {
		return ci_length;
	}

	public ForecastItem setCi_length(Double ci_length) {
		this.ci_length = ci_length;
		return this;
	}

	public Double getCi_width() {
		return ci_width;
	}

	public ForecastItem setCi_width(Double ci_width) {
		this.ci_width = ci_width;
		return this;
	}

	public Boolean getSellerAccepted() {
		return sellerAccepted;
	}

	public ForecastItem setSellerAccepted(Boolean sellerAccepted) {
		this.sellerAccepted = sellerAccepted;
		return this;
	}

	public Boolean getOnReturn() {
		return onReturn;
	}

	public ForecastItem setOnReturn(Boolean onReturn) {
		this.onReturn = onReturn;
		return this;
	}
	
	
}
