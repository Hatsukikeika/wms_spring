package com.wms.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "wmsseller")
@DiscriminatorValue("warehouse")
public class WarehouseCompany  extends Company {

	@OneToMany(mappedBy="company")
	@MapKey(name = "Openid")
	private Map<Long, Inventory> inventoryList;
	
	public Inventory getInventory(Long inventoryId) {
		return inventoryList.get(inventoryId);
	}
	
	public void removeInventory(Long inventoryId) {
		inventoryList.remove(inventoryId);
	}

	public WarehouseCompany setInventory(Inventory inventory) {
		this.inventoryList.putIfAbsent(inventory.getOpenid(),inventory);
		return this;
	}
	
	public List<Inventory> getOwnedInventory(){
		return new ArrayList<Inventory>(inventoryList.values());
	}
	
}
