package com.wms.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.Table;
import javax.persistence.InheritanceType;
import javax.persistence.DiscriminatorType;
import javax.persistence.ElementCollection;

@Entity
@Table(name = "wmspackage")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name = "package_discriminator", discriminatorType = DiscriminatorType.STRING)
public abstract class Package extends HasIdentity {
	
	
	@Column(name="package_length", nullable = true)
	private Double length;
	
	@Column(name="package_height", nullable = true)
	private Double height;
	
	@Column(name="package_width", nullable = true)
	private Double width;
	
	@Column(name="package_weight", nullable = true)
	private Double weight;
	
	@Column(name="package_wunit", nullable = true)
	private String weight_unit;
	
	@Column(name="package_sunit", nullable = true)
	private String size_unit;
	
	@Column(name="package_notes", nullable = true)
	private String notes;
	
	@Column(name="package_count", nullable = true)
	private Integer count;	
	
	@ElementCollection
	private Map<Long, Package> package_inside;

	public Package() {
		super();
		this.package_inside = new HashMap<>();
	}

	public Double getLength() {
		return length;
	}

	public Package setLength(Double length) {
		this.length = length;
		return this;
	}

	public Double getHeight() {
		return height;
	}

	public Package setHeight(Double height) {
		this.height = height;
		return this;
	}

	public Double getWidth() {
		return width;
	}

	public Package setWidth(Double width) {
		this.width = width;
		return this;
	}

	public Double getWeight() {
		return weight;
	}

	public Package setWeight(Double weight) {
		this.weight = weight;
		return this;
	}

	public String getNotes() {
		return notes;
	}

	public Package setNotes(String notes) {
		this.notes = notes;
		return this;
	}

	public String getWeight_unit() {
		return weight_unit;
	}

	public Package setWeight_unit(String weight_unit) {
		this.weight_unit = weight_unit;
		return this;
	}

	public String getSize_unit() {
		return size_unit;
	}

	public Package setSize_unit(String size_unit) {
		this.size_unit = size_unit;
		return this;
	}

	public Integer getCount() {
		return count;
	}

	public Package setCount(Integer count) {
		this.count = count;
		return this;
	}
	
	public void putItem(Package item) {
		package_inside.put(item.getOpenid(), item);
	}
	
	public List<Package> getPackagesInside(){
		return new ArrayList<Package>(package_inside.values());
	}
	
	public Package getPackageInside(Long id) {
		return package_inside.get(id);
	}
}
