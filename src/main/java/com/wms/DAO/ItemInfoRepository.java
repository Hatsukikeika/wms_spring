package com.wms.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wms.bean.Inventory;
import com.wms.bean.ItemInfo;

public interface ItemInfoRepository extends JpaRepository<ItemInfo, Long> {

	List<ItemInfo> findByBelongstoAndSku(Inventory inventory, String sku);
	
	List<ItemInfo> findByBelongstoAndNameContains(Inventory inventory, String name);
	
	Boolean existsByBelongstoAndSku(Inventory inventory, String sku);
	
}
