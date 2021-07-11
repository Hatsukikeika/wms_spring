package com.wms.bean;
import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wms.bean.enu.GroupType;

@Entity
@Table(name="wmsgroup")
public class Group extends HasIdentity implements Serializable {
	
	@Column(name = "group_space", unique = true, nullable = false)
	private Long space;
	
	@Column(name = "group_contact", nullable = true)
	private Long contact;
	
	@Column(name = "group_icon", nullable = true)
	private String icon;
	
	@Column(name = "group_email", unique = true, nullable = false)
	private String email;
	
	@Column(name = "group_firstname", nullable = false)
	private String firstname;
	
	@Column(name = "group_lastname", nullable = false)
	private String lastname;
	
	@Column(name = "group_company", nullable = false)
	private String company;
	
	@Column(name = "location", columnDefinition="varchar(45) default 'blank'", nullable = true)
	private String location;
	
	@Column(name = "group_type", nullable = false, columnDefinition="tinyint default 1")
	private GroupType type;
	
	@Column(name = "group_isValidated", nullable = false, columnDefinition="tinyint default 0")
	private Boolean activated;
	
	public Group() {
		super();
		this.space = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
		this.activated = false;
	}

	public Long getSpace() {
		return space;
	}

	public Group setSpace(Long space) {
		this.space = space;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public Group setEmail(String email) {
		this.email = email;
		return this;
	}

	public Long getContact() {
		return contact;
	}

	public Group setContact(Long long1) {
		this.contact = long1;
		return this;
	}

	public String getFirstname() {
		return firstname;
	}

	public Group setFirstname(String firstname) {
		this.firstname = firstname;
		return this;
	}

	public String getLastname() {
		return lastname;
	}

	public Group setLastname(String lastname) {
		this.lastname = lastname;
		return this;
	}

	public String getCompany() {
		return company;
	}

	public Group setCompany(String company) {
		this.company = company;
		return this;
	}

	public String getLocation() {
		return location;
	}

	public Group setLocation(String location) {
		this.location = location;
		return this;
	}

	public GroupType getType() {
		return type;
	}
	
	public Group setType(GroupType type) {
		this.type = type;
		return this;
	}

	public Boolean getActivated() {
		return activated;
	}

	public Group setActivated(Boolean activated) {
		this.activated = activated;
		return this;
	}

	public String getIcon() {
		return icon;
	}

	public Group setIcon(String icon) {
		this.icon = icon;
		return this;
	}
}
