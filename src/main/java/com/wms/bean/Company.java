package com.wms.bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.wms.bean.enu.GroupType;

@Entity
@Table(name="wmscompany")
public class Company extends HasIdentity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name="comp_name")
	private String name;
	
	@Column(name="comp_type")
	private GroupType type;
	
	@Column(name="comp_addr")
	private Address addr;
	
	@Column(name="comp_activated")
	private Boolean activated;
	
	@Column(name="comp_baned")
	private Boolean baned;
	
	@Column(name="comp_inv")
	private String invcode;
	
	@OneToOne(mappedBy="ownedCompany")
	private User owner;
	
	public Company() {
		super();
	}

	public String getName() {
		return name;
	}

	public Company setName(String name) {
		this.name = name;
		return this;
	}

	public Address getAddr() {
		return addr;
	}

	public Company setAddr(Address addr) {
		this.addr = addr;
		return this;
	}

	public Boolean getBaned() {
		return baned;
	}

	public Company setBaned(Boolean baned) {
		this.baned = baned;
		return this;
	}

	public String getInvcode() {
		return invcode;
	}

	public Company setType(GroupType type) {
		this.type = type;
		return this;
	}

	public User getOwner() {
		return owner;
	}

	public Company setOwner(User owner) {
		this.owner = owner;
		return this;
	}

	public Company setActivated(Boolean activated) {
		this.activated = activated;
		return this;
	}

	public boolean getActivated() {
		
		return activated;
	}

	public GroupType getType() {
		
		return type;
	}	
	
}
