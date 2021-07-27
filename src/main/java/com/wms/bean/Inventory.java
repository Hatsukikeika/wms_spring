package com.wms.bean;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="wmsinventory")
public class Inventory extends HasIdentity {
	
	private final int MAX_INVENTORY_SIZE = 100;

	@Column(name="inventory_name", nullable = true)	
	private String inventory_name;
	
	@Column(name="inventory_itemcount")	
	private Integer total_items;
	
	@Column(name="inventory_approxweight")
	private Integer total_approxweight;
	
    @ElementCollection
	private Map<Long, ItemInfo> items_inbag;
    
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "inventory_company_openid", nullable = false, referencedColumnName = "data_global_id",
			foreignKey = @ForeignKey(name="none",value = ConstraintMode.NO_CONSTRAINT))
    private Company company;
    
    public Inventory() {
    	this.total_approxweight = 0;
    	this.total_items = 0;
    }
	
    public Inventory(Company company) {
    	this.company = company;
    	this.total_approxweight = 0;
    	this.total_items = 0;
    }
	
    public void putItem(ItemInfo item) {
    	Long itemid = item.getOpenid();
    	
    	if(items_inbag.containsKey(itemid)) {
    		throw new RuntimeException();
    	}
    	
    	if(total_items >= MAX_INVENTORY_SIZE ) {
    		throw new RuntimeException();
    	}
    	
    	items_inbag.put(itemid, item);
    	total_items++;
    }

	public Integer getTotal_approxweight() {
		return total_approxweight;
	}

	public Inventory setTotal_approxweight(Integer total_approxweight) {
		this.total_approxweight = total_approxweight;
		return this;
	}

	public Map<Long, ItemInfo> getItems_inbag() {
		return items_inbag;
	}
	
	public ItemInfo getItem(Long itemid) {
		
		return items_inbag.get(itemid);
	}

	public Inventory setItems_inbag(Map<Long, ItemInfo> items_inbag) {
		this.items_inbag = items_inbag;
		return this;
	}

	public Company getCompany() {
		return company;
	}

	public Inventory setCompany(Company company) {
		this.company = company;
		return this;
	}

	public String getInventoryName() {
		return inventory_name;
	}

	public Inventory setInventoryName(String inventory_name) {
		this.inventory_name = inventory_name;
		return this;
	}
    
	
}
