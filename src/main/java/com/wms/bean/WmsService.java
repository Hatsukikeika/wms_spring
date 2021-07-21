package com.wms.bean;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.wms.bean.enu.ServiceClass;

@Entity
@Table(name="wmsservice")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name = "service_discriminator", discriminatorType = DiscriminatorType.STRING)
public abstract class WmsService extends HasIdentity {

	@Column(name="service_name", nullable=false)
	private String name;
	
	@Column(name="service_intro", nullable=false)
	private String intro;
	
	@Column(name="service_unitprice", nullable=false)
	private Double unitprice;
	
	@Column(name="service_type", nullable=false)
	private ServiceClass type;
	
	@Column(name="service_count", nullable=false)
	private Integer count;
	
	@Column(name="service_pricing", nullable=false)
	private Integer pricing_method;
	
	public WmsService() {
		super();
	}

	public String getName() {
		return name;
	}

	public WmsService setName(String name) {
		this.name = name;
		return this;
	}

	public String getIntro() {
		return intro;
	}

	public WmsService setIntro(String intro) {
		this.intro = intro;
		return this;
	}

	public Double getUnitprice() {
		return unitprice;
	}

	public WmsService setUnitprice(Double price) {
		this.unitprice = price;
		return this;
	}

	public ServiceClass getType() {
		return type;
	}

	public WmsService setType(ServiceClass type) {
		this.type = type;
		return this;
	}

	public Integer getCount() {
		return count;
	}

	public WmsService setCount(Integer count) {
		this.count = count;
		return this;
	}

	public Integer getPricing_method() {
		return pricing_method;
	}

	public WmsService setPricing_method(Integer pricing_method) {
		this.pricing_method = pricing_method;
		return this;
	}	
	
}
