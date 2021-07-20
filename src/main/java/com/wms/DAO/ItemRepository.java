package com.wms.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wms.bean.Inventory;
import com.wms.bean.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

	List<Item> findByBelongstoAndSku(Inventory inventory, String sku);
	
	List<Item> findByBelongstoAndNameContains(Inventory inventory, String name);
	
	Boolean existsByBelongstoAndSku(Inventory inventory, String sku);
	
}
