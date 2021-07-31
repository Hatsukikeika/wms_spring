package com.wms.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.wms.DAO.ItemInfoRepository;
import com.wms.bean.Company;
import com.wms.bean.Inventory;
import com.wms.bean.ItemInfo;
import com.wms.bean.SellerCompany;
import com.wms.bean.DTO.ItemCreationRequest;
import com.wms.service.ItemService;
import com.wms.service.helper.ObjectHelper;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private InventoryRepository inventoryRepository;

	@Autowired
	private ItemInfoRepository itemInfoRepository;

	private final int SEARCH_PAGE_SIZE = 10;

	@Override
	public void addItem(SellerCompany company, ItemCreationRequest item) {

		Inventory inventory = company.getInventory();

		if (itemInfoRepository.existsByBelongstoAndSku(inventory, item.getSku()))
			throw new RuntimeException("SKU must be unique");

		ItemInfo newitem = new ItemInfo(item);

		newitem.setBelongsTo(inventory);

		itemInfoRepository.save(newitem);

		inventory.putItem(newitem);

		inventoryRepository.save(inventory);
	}

	@Override
	public void updateItem(SellerCompany company, ItemCreationRequest item, Long itemid) {

		ItemInfo toUpdate = itemInfoRepository.findOne(itemid);

		Inventory inventory = company.getInventory();

		String sku = item.getSku();
		
		if (itemInfoRepository.existsByBelongstoAndSku(inventory, sku) && !toUpdate.getSku().equals(sku))
			throw new RuntimeException("SKU must be unique");

		toUpdate.Update(item);

		toUpdate.setUpdateOn();

		itemInfoRepository.save(toUpdate);

	}

	@Override
	public void deleteItem(SellerCompany company, Long item) {

		ItemInfo i = company.getInventory().getItem(item);

		if (i == null)
			throw new RuntimeException();

		i.setIsDel(true).setUpdateOn();

		itemInfoRepository.save(i);
	}

	@Override
	public void deleteItemPerma(SellerCompany company, Long item) {

		Inventory inv = company.getInventory();

		ItemInfo i = inv.getItem(item);

		inv.getItems_inbag().remove(item);

		if (i == null)
			throw new RuntimeException();

		if (i.getIsDel() != true)
			throw new RuntimeException();

		itemInfoRepository.delete(i);
	}

	@Override
	public Page<ItemInfo> getItemList(SellerCompany company, int pageNum, int pageSize) {

		List<ItemInfo> itemlist = new ArrayList<ItemInfo>(company.getInventory().getItems_inbag().values());

		return ObjectHelper.listToPageCovert(itemlist, pageNum, pageSize);
	}

	@Override
	public Page<ItemInfo> searchUsingSku(SellerCompany company, String sku) {

		List<ItemInfo> itemlist = itemInfoRepository.findByBelongstoAndSku(company.getInventory(), sku);

		return ObjectHelper.listToPageCovert(itemlist, 0, SEARCH_PAGE_SIZE);
	}

	@Override
	public Page<ItemInfo> searchUsingName(SellerCompany company, String name, int pageNum) {

		List<ItemInfo> itemlist = itemInfoRepository.findByBelongstoAndNameContains(company.getInventory(), name);

		return ObjectHelper.listToPageCovert(itemlist, pageNum, SEARCH_PAGE_SIZE);
	}


}
