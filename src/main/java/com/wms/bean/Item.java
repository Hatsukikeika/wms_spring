package com.wms.bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "wmsitem")
@DiscriminatorValue("item")
public class Item extends Package implements Serializable {
	
	@ElementCollection
	@Column(name="companyitem_sku")
	private List<String> sku;
	
	@Column(name="companyitem_iname")
	private String name;
	
	@Column(name="companyitem_units", nullable=true)
	private String unit;
	
	@Column(name="companyitem_imgurl", nullable=true)
	private String imgurl;

	@ManyToOne
	@JoinColumn(name = "inventory_item_openid", nullable = false, referencedColumnName = "data_global_id",
	foreignKey = @ForeignKey(name="none",value = ConstraintMode.NO_CONSTRAINT))
	private Inventory belongsto;
	
	public Item() {
		super();
	}

	public List<String> getSKU() {
		return sku;
	}

	public Item setSKU(List<String> SKU) {
		this.sku = SKU;
		return this;
	}

	public String getName() {
		return name;
	}

	public Item setName(String name) {
		this.name = name;
		return this;
	}

	public String getUnit() {
		return unit;
	}

	public Item setUnit(String unit) {
		this.unit = unit;
		return this;
	}

	public String getImgurl() {
		return imgurl;
	}

	public Item setImgurl(String imgurl) {
		this.imgurl = imgurl;
		return this;
	}

	@JsonIgnore
	public Inventory getBelongsTo() {
		return belongsto;
	}

	public Item setBelongsTo(Inventory belongsTo) {
		this.belongsto = belongsTo;
		return this;
	}
	
	
	
}
