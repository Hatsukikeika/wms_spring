package com.wms.bean;

import java.io.Serializable;
import java.util.UUID;

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
@Table(name="wmsgroup_validation")
public class Validation extends HasIdentity implements Serializable  {
	
	@Column(name="validate_expire", nullable = false)
	private Long expire;
	
	@Column(name="validate_key", unique = true, nullable = false)
	private String key;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "validate_user_openid", nullable = false, referencedColumnName = "data_global_id",
			foreignKey = @ForeignKey(name="none",value = ConstraintMode.NO_CONSTRAINT))
	private User user;
	
	public Validation() {
		super();
	}
	
	public Validation(User user) {
		super();
		this.expire = this.getCreateOn() + 60 * 1000 * 30;
		this.key = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
		this.user = user;
	}

	public Long getExpire() {
		return expire;
	}

	public Validation setExpire(Long expire) {
		this.expire = expire;
		return this;
	}

	public String getKey() {
		return key;
	}

	public Validation setKey(String key) {
		this.key = key;
		return this;
	}

	@JsonIgnore
	public User getUser() {
		return user;
	}

	public Validation setUser(User user) {
		this.user = user;
		return this;
	}
}
