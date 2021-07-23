package com.wms.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.wms.bean.enu.UserRole;

@Entity
@Table(name = "wmsgroup_user")
public class User extends HasIdentity implements Serializable  {

	@Column(name="user_email", nullable = false, unique=true)
	private String email;

	@Column(name="user_password", nullable = false)
	private String password;

	@Column(name="user_firstname", nullable = false)
	private String firstname;
	
	@Column(name="user_lastname", nullable = false)
	private String lastname;
	
	@Column(name="user_role", nullable = false, columnDefinition="int")
	private UserRole role;

	@Column(name="user_isban", nullable = false, columnDefinition="tinyint default 0")
	private Boolean isban;
	
	@Column(name="user_isValidated", nullable = false, columnDefinition="tinyint default 0")
	private Boolean activated;
	
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_company_openid", nullable = false, referencedColumnName = "data_global_id",
			foreignKey = @ForeignKey(name="none",value = ConstraintMode.NO_CONSTRAINT))
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler","uuid","openid","createOn","updateOn","isDel","owner","inventory"})
	private Company ownedCompany;
	
	public User() {
		super();
		this.isban = false;
	}
	
	public User(Company company) {
		super();
		this.isban = false;
		this.ownedCompany = company;
	}

	public User(String email, String password, UserRole role, Company company) {
		super();
		this.isban = false;
		this.email = email;
		this.password = password;
		this.role = role;
		this.ownedCompany = company;
	}


	public String getEmail() {
		return email;
	}

	public User setEmail(String email) {
		this.email = email;
		return this;
	}

	@JsonProperty(access = Access.WRITE_ONLY)
	public String getPassword() {
		return password;
	}

	public User setPassword(String password) {
		this.password = password;
		return this;
	}

	public UserRole getRole() {
		return role;
	}

	public User setRole(UserRole role) {
		this.role = role;
		return this;
	}

	public Boolean getIsban() {
		return isban;
	}

	public User setIsban(Boolean isban) {
		this.isban = isban;
		return this;
	}

	public Boolean getActivated() {
		return activated;
	}

	public User setActivated(Boolean activated) {
		this.activated = activated;
		return this;
	}

	public String getFirstname() {
		return firstname;
	}

	public User setFirstname(String firstname) {
		this.firstname = firstname;
		return this;
	}

	public String getLastname() {
		return lastname;
	}

	public User setLastname(String lastname) {
		this.lastname = lastname;
		return this;
	}

	public Company getOwnedCompany() {
		return ownedCompany;
	}

	public User setOwnedCompany(Company company) {
		this.ownedCompany = company;
		return this;
	}

}
