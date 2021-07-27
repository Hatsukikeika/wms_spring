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
	@MapKey(name = "openid")
	private Map<Long, Inventory> inventoryList;
	
	public Inventory getInventory(Long inventoryId) {
		return inventoryList.get(inventoryId);
	}
	
	public void removeInventory(Long inventoryId) {
		inventoryList.remove(inventoryId);
	}

	public WarehouseCompany setInventory(Inventory inventory) {
		
		for(Inventory i : inventoryList.values()) {
			if(i.getInventoryName().equals(inventory.getInventoryName()))
				throw new RuntimeException("Inventory name must be unique");
		}
		
		this.inventoryList.putIfAbsent(inventory.getOpenid(),inventory);
		return this;
	}
	
	public List<Inventory> getOwnedInventory(){
		return new ArrayList<Inventory>(inventoryList.values());
	}
	
}
