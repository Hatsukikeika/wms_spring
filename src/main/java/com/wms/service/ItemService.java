package com.wms.service;

import org.springframework.data.domain.Page;

import com.wms.bean.Item;

public interface ItemService {

	void addItem(long groupid, Item item) ;
	
	void updateItem(long groupid, long itemid, Item item);
	
	void deleteItem(long groupid, long itemid);
	
	Page<Item> itemByGroup(long groupid, int pageNum, int pageSize);
}
