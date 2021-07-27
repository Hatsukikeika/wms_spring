package com.wms.bean;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.wms.bean.enu.GroupType;

@Entity
@Table(name = "wmsseller")
@DiscriminatorValue("seller")
public class SellerCompany extends Company implements Serializable {

	@OneToOne(mappedBy="company", cascade = CascadeType.ALL)
	private Inventory inventory;	
	
	public SellerCompany() {
		super();
		this.setType(GroupType.TYPE_SELLER);
	}

	public Inventory getInventory() {
		return inventory;
	}

	public SellerCompany setInventory(Inventory inventory) {
		this.inventory = inventory;
		return this;
	}
}
