package com.wms.bean;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wms.bean.DTO.ForecastCheckIn;
import com.wms.bean.enu.RequestStatus;

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
	
	@Column(name="forecastitem_ci_count", nullable=true)
	private Integer ci_count;
	
	@Column(name="forecastitem_sellerAccepted", nullable=true)
	private Boolean sellerAccepted;
	
	@Column(name="forecastitem_warehouseAccepted", nullable=true)
	private Boolean warehouseAccepted;
	
	@Column(name="forecastitem_onReturn", nullable=true)
	private Boolean onReturn;
	
	@Column(name="forecastitem_status", nullable=false)
	private RequestStatus itemStatus;

	public ForecastItem() {
		super();
		this.itemStatus = RequestStatus.PENDING;
	}
	
	public ForecastItem(ItemInfo iteminfo) {
		super();
		this.Update(iteminfo);
		this.itemStatus = RequestStatus.PENDING;
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

	public Integer getCi_count() {
		return ci_count;
	}

	public ForecastItem setCi_count(Integer ci_count) {
		this.ci_count = ci_count;
		return this;
	}

	public Boolean getWarehouseAccepted() {
		return warehouseAccepted;
	}

	public ForecastItem setWarehouseAccepted(Boolean warehouseAccepted) {
		this.warehouseAccepted = warehouseAccepted;
		return this;
	}

	public RequestStatus getItemStatus() {
		return itemStatus;
	}

	public ForecastItem setItemStatus(RequestStatus itemStatus) {
		this.itemStatus = itemStatus;
		return this;
	}

	public void warehouseCheckIn(ForecastCheckIn checkIn) {
		this.ci_height = checkIn.getCi_height();
		this.ci_length = checkIn.getCi_length();
		this.ci_weight = checkIn.getCi_weight();
		this.ci_width = checkIn.getCi_height();
		this.ci_count = checkIn.getCi_count();
		
		this.warehouseAccepted = true;
	}
	
	
	public boolean anyMismatch() {
		if(super.getHeight() != this.ci_height)
			return true;
		
		if(super.getLength() != this.ci_length)
			return true;
		
		if(super.getWidth() != this.ci_width)
			return true;
		
		if(super.getWeight() != this.ci_weight)
			return true;
		
		if(super.getCount() != this.ci_count)
			return true;
		
		return false;
	}
}
