package com.wms.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="wmsservice")
public class WmsService extends HasIdentity {

	@Column(name="service_name", nullable=false)
	private String name;
	
	@Column(name="service_intro", nullable=false)
	private String intro;
	
	@Column(name="service_price", nullable=false)
	private Double price;
	
	@Column(name="service_type", nullable=false)
	private Integer type;
	
	@Column(name="service_count", nullable=false)
	private Integer count;
	
	public WmsService() {
		super();
	}
	
}
