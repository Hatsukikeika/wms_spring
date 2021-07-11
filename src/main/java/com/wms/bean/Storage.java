package com.wms.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="wmsgroup_storage")
public class Storage extends HasIdentity implements Serializable  {
	
	@Column(name="storage_title", nullable = false)
	private String title;
	
	@Column(name="storage_country", nullable = false)
	private String country;
	
	@Column(name="storage_state", nullable = false)
	private String state;
	
	@Column(name="storage_city", nullable = false)
	private String city;
	
	@Column(name="storage_location", nullable = false)
	private String location;
	
	@Column(name="storage_zipcode", nullable = false)
	private Integer zipcode;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "storage_group_openid", nullable = false, referencedColumnName = "data_global_id",
			foreignKey = @ForeignKey(name="none",value = ConstraintMode.NO_CONSTRAINT))
	private Group group;
	
	public Storage() {
		super();
	}

	public Storage(Group group) {
		super();
		this.group = group;
	}
	
	public Storage(String title, String country, String state, String city, String location, Integer zipcode,
			Group group) {
		super();
		this.title = title;
		this.country = country;
		this.state = state;
		this.city = city;
		this.location = location;
		this.zipcode = zipcode;
		this.group = group;
	}

	public String getTitle() {
		return title;
	}

	public Storage setTitle(String title) {
		this.title = title;
		return this;
	}

	public String getCountry() {
		return country;
	}

	public Storage setCountry(String country) {
		this.country = country;
		return this;
	}

	public String getState() {
		return state;
	}

	public Storage setState(String state) {
		this.state = state;
		return this;
	}

	public String getCity() {
		return city;
	}

	public Storage setCity(String city) {
		this.city = city;
		return this;
	}

	public String getLocation() {
		return location;
	}

	public Storage setLocation(String location) {
		this.location = location;
		return this;
	}

	public Integer getZipcode() {
		return zipcode;
	}

	public Storage setZipcode(Integer zipcode) {
		this.zipcode = zipcode;
		return this;
	}

	@JsonIgnore
	public Group getGroup() {
		return group;
	}

	public Storage setGroup(Group group) {
		this.group = group;
		return this;
	}	
	
}
