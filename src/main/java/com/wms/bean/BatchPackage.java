package com.wms.bean;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="wmsbatch")
@DiscriminatorValue("batch")
public class BatchPackage extends Package {
	
	@Column(name="wmsbatch_isForcast")
	private Boolean isForcastBatch;
	
	@Column(name="wmsbatch_itemCount")
	private Integer forcastItemCount;
	
	@ElementCollection
	private Map<Long, WmsService> services;

	public Boolean getIsForcastBatch() {
		return isForcastBatch;
	}

	public BatchPackage setIsForcastBatch(Boolean isForcastBatch) {
		this.isForcastBatch = isForcastBatch;
		return this;
	}

	public Integer getForcastItemCount() {
		return forcastItemCount;
	}

	public BatchPackage setForcastItemCount(Integer forcastItemCount) {
		this.forcastItemCount = forcastItemCount;
		return this;
	}

	public Map<Long, WmsService> getServices() {
		return services;
	}

	public BatchPackage setServices(Map<Long, WmsService> services) {
		this.services = services;
		return this;
	}
	
	

}
