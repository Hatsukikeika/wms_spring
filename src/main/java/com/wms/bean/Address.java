package com.wms.bean;

import java.io.Serializable;

import javax.persistence.Column;

public class Address extends HasIdentity implements Serializable {
	
	@Column(name="addr_fname", nullable = false)
	private String firstname;
	
	@Column(name="addr_lname", nullable = false)
	private String lastname;
	
	@Column(name="addr_receiver", nullable = true)
	private String to;
	
	@Column(name="addr_street1", nullable = false)
	private String main_street;
	
	@Column(name="addr_street2", nullable = true)
	private String sub_street;
	
	@Column(name="addr_city", nullable = false)
	private String city;
	
	@Column(name="addr_state", nullable = false)
	private String state;
	
	@Column(name="addr_country", nullable = false)
	private String country;
	
	@Column(name="addr_zipcode", nullable = false)
	private int zipcode;
	
}
