package com.wms.service;

import org.springframework.data.domain.Page;

import com.wms.bean.Company;
import com.wms.bean.Item;

public interface ItemService {

	void addItem(Company company , Item item) ;
	
	void updateItem(Company company , Item item);
	
	void deleteItem(Company company , Long itemid);
	
	void deleteItemPerma(Company company , Long itemid);
	
	Page<Item> searchUsingSku(Company company , String sku);
	
	Page<Item> searchUsingName(Company company , String name, int pageNum);
	
	Page<Item> getItemList(Company company , int pageNum, int pageSize);
}
