package com.wms.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.wms.bean.enu.RequestStatus;

@Entity
@Table(name="wmsforecast_instock")
public class ForecastInstock extends HasIdentity {

	@ElementCollection
	private Map<Long, BatchPackage> batches;
	
	@ManyToOne
	private SellerCompany seller;
	
	@ManyToOne
	private WarehouseCompany warehouse;
	
	@Column
	private String carrier;
	
	@Column
	private String trackingNum;
	
	@Column
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
		return new ArrayList<>(batches.values());
	}

	public ForecastInstock setBatches(Map<Long, BatchPackage> batches) {
		this.batches = batches;
		return this;
	}
	
	public ForecastInstock addBatch(BatchPackage batch) {
		this.batches.put(batch.getOpenid(), batch);
		return this;
	}

	public Boolean canBeDone() {
		for(BatchPackage bp : batches.values()) {
			for(Package pac : bp.getPackagesInside()) {
				if(!((ForecastItem)pac).getSellerAccepted())
					return false;
			}
		}
		
		return true;
	}

	public RequestStatus getStatus() {
		return status;
	}

	public ForecastInstock setStatus(RequestStatus status) {
		this.status = status;
		return this;
	};
	
	
}
