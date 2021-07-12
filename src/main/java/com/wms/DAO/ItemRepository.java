package com.wms.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wms.bean.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

	
}
