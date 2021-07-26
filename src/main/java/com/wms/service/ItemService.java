package com.wms.service;

import org.springframework.data.domain.Page;

import com.wms.bean.ItemInfo;
import com.wms.bean.SellerCompany;
import com.wms.bean.DTO.ItemCreationRequest;

public interface ItemService {

	void addItem(SellerCompany company , ItemCreationRequest item) ;
	
	void updateItem(SellerCompany company, ItemCreationRequest item, Long itemid);
	
	void deleteItem(SellerCompany company , Long itemid);
	
	void deleteItemPerma(SellerCompany company , Long itemid);
	
	Page<ItemInfo> searchUsingSku(SellerCompany company , String sku);
	
	Page<ItemInfo> searchUsingName(SellerCompany company , String name, int pageNum);
	
	Page<ItemInfo> getItemList(SellerCompany company , int pageNum, int pageSize);
}
