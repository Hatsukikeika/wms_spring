package com.wms.service.Impl;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wms.bean.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

}
