package com.wms.DAO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.wms.bean.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

	Item findByGroupIdAndId(long groupid, long id);

	Page<Item> findByGroupIdAndIsDel(long groupid, boolean isDel, Pageable pagination);
	
}
