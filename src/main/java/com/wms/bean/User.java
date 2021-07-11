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
import com.wms.bean.enu.UserRole;

@Entity
@Table(name = "wmsgroup_user")
public class User extends HasIdentity implements Serializable  {

	@Column(name="user_email", nullable = false)
	private String email;

	@Column(name="user_password", nullable = false)
	private String password;

	@Column(name="user_role", nullable = false, columnDefinition="int")
	private UserRole role;

	@Column(name="user_isban", nullable = false, columnDefinition="tinyint default 0")
	private Boolean isban;
	
	@Column(name="user_isValidated", nullable = false, columnDefinition="tinyint default 0")
	private Boolean activated;
	
	
	private String company;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_group_openid", nullable = false, referencedColumnName = "data_global_id",
			foreignKey = @ForeignKey(name="none",value = ConstraintMode.NO_CONSTRAINT))
	private Group group;
	
	public User() {
		super();
		this.isban = false;
	}
	
	public User(Group group) {
		super();
		this.isban = false;
		this.group = group;
	}

	public User(String email, String password, UserRole role, Group group) {
		super();
		this.isban = false;
		this.email = email;
		this.password = password;
		this.role = role;
		this.group = group;
	}


	public String getEmail() {
		return email;
	}

	public User setEmail(String email) {
		this.email = email;
		return this;
	}

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

	@JsonIgnore
	public Group getGroup() {
		return group;
	}

	public User setGroup(Group group) {
		this.group = group;
		return this;
	}

	public Boolean getActivated() {
		return activated;
	}

	public User setActivated(Boolean activated) {
		this.activated = activated;
		return this;
	}
	
	

}
