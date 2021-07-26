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

}
