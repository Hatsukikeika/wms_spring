package com.wms.bean;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Package extends HasIdentity {
	
	
	@Column(name="package_length", nullable = false)
	private Double length;
	
	@Column(name="package_height", nullable = false)
	private Double height;
	
	@Column(name="package_width", nullable = false)
	private Double width;
	
	@Column(name="package_weight", nullable = false)
	private Double weight;
	
	@Column(name="package_weight", nullable = true)
	private String notes;
	
	
	private List<Package> package_insde;
	
}
