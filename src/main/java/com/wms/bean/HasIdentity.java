package com.wms.bean;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.wms.utils.ID.IdGen;

@MappedSuperclass
public abstract class HasIdentity {

	@Column(name = "data_uuid", unique = true, nullable = false)
	private String uuid; // scope: server. 
	
	//@Id
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	//@Column(name = "data_id")
	//private Long id; //  scope: table. 
	
	@Id
	@Column(name = "data_global_id", unique = true, nullable = false)
	private Long openid; // scope: global. 
	
	@Column(name = "date_create_on", nullable = false)
	private Long createOn;
	
	@Column(name = "date_update_on", nullable = false)
	private Long updateOn;
	
	@Column(name = "object_isDel", nullable = false)
	private Boolean isDel;
	
	public HasIdentity() {
		this.createOn = this.updateOn = new Date().getTime();
		this.openid = IdGen.nextId();
		this.uuid = UUID.randomUUID().toString();
		this.isDel = false;
	}
	
	public String getUuid() {
		return this.uuid;
	}

	public Long getCreateOn() {
		return createOn;
	}

	public void setCreateOn() {
		this.createOn = this.updateOn = new Date().getTime();
	}

	public Long getUpdateOn() {
		return updateOn;
	}

	public void setUpdateOn() {
		this.updateOn = new Date().getTime();
	}

	public Long getOpenid() {
		return openid;
	}

	public HasIdentity setOpenid(Long openid) {
		this.openid = openid;
		return this;
	}

	public Boolean getIsDel() {
		return isDel;
	}

	public HasIdentity setIsDel(Boolean isDel) {
		this.isDel = isDel;
		return this;
	}

	public HasIdentity setUuid(String uuid) {
		this.uuid = uuid;
		return this;
	}

	public HasIdentity setCreateOn(Long createOn) {
		this.createOn = createOn;
		return this;
	}

	public HasIdentity setUpdateOn(Long updateOn) {
		this.updateOn = updateOn;
		return this;
	}
	
	
}
