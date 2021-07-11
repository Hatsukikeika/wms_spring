package com.wms.bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;

public class Company extends HasIdentity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name="comp_name")
	private String name;
	
	@Column(name="comp_addr")
	private Address addr;
	
	@Column(name="comp_outgoing_addr")
	private List<Address> outgoing_addr;
	
	@Column(name="comp_incoming_addr")
	private List<Address> incoming_addr;
	
	@Column(name="comp_owner")
	private User owner;
	
	@Column(name="comp_member")
	private List<User> member;
	
	public Company() {
		super();
	}
	
	
}
