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
@Table(name = "wmsgroup_item")
public class Item extends HasIdentity implements Serializable {
	
	private String SKU;
	
	private String name;

	public Item() {
		super();
	}
	
}
