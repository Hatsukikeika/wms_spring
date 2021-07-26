package com.wms.bean;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "wmsseller")
@DiscriminatorValue("seller")
public class SellerCompany extends Company {

	@OneToOne(mappedBy="company")
	private Inventory inventory;
	
	public Inventory getInventory() {
		return inventory;
	}

	public SellerCompany setInventory(Inventory inventory) {
		this.inventory = inventory;
		return this;
	}
}
