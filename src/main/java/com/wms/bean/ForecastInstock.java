package com.wms.bean;

import java.util.List;

import javax.persistence.ManyToOne;

import com.wms.bean.enu.RequestStatus;

public class ForecastInstock extends HasIdentity {

	@ManyToOne
	private SellerCompany seller;
	
	@ManyToOne
	private WarehouseCompany warehouse;
	
	private String carrier;
	
	private String trackingNum;
	
	private List<BatchPackage> batches;
	
	private Boolean sellerAccepted;
	
	private Boolean warehouseAccepted;	
	
	private RequestStatus status;
	
	public ForecastInstock() {}

	public SellerCompany getSeller() {
		return seller;
	}

	public ForecastInstock setSeller(SellerCompany seller) {
		this.seller = seller;
		return this;
	}

	public WarehouseCompany getWarehouse() {
		return warehouse;
	}

	public ForecastInstock setWarehouse(WarehouseCompany warehouse) {
		this.warehouse = warehouse;
		return this;
	}

	public String getCarrier() {
		return carrier;
	}

	public ForecastInstock setCarrier(String carrier) {
		this.carrier = carrier;
		return this;
	}

	public String getTrackingNum() {
		return trackingNum;
	}

	public ForecastInstock setTrackingNum(String trackingNum) {
		this.trackingNum = trackingNum;
		return this;
	}

	public List<BatchPackage> getBatches() {
		return batches;
	}

	public ForecastInstock setBatches(List<BatchPackage> batches) {
		this.batches = batches;
		return this;
	}

	public Boolean getSellerAccepted() {
		return sellerAccepted;
	}

	public ForecastInstock setSellerAccepted(Boolean sellerAccepted) {
		this.sellerAccepted = sellerAccepted;
		return this;
	}

	public Boolean getWarehouseAccepted() {
		return warehouseAccepted;
	}

	public ForecastInstock setWarehouseAccepted(Boolean warehouseAccepted) {
		this.warehouseAccepted = warehouseAccepted;
		return this;
	}

	public RequestStatus getStatus() {
		return status;
	}

	public ForecastInstock setStatus(RequestStatus status) {
		this.status = status;
		return this;
	};
	
	
}
