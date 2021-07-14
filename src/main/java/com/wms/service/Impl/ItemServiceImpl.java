package com.wms.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.wms.DAO.ItemRepository;
import com.wms.bean.Company;
import com.wms.bean.Inventory;
import com.wms.bean.Item;
import com.wms.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private InventoryRepository inventoryRepository;

	@Autowired
	private ItemRepository itemRepository;
	
	@Override
	public void addItem(Company company, Item item) {
		Inventory inventory = company.getInventory();
		
		inventory.putItem(item);
		
		inventoryRepository.save(inventory);
		
		itemRepository.save(item);

	}

	@Override
	public void updateItem(Company company, Item item) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteItem(Company company, Item item) {
		// TODO Auto-generated method stub

	}

	@Override
	public Page<Item> itemByGroup(long groupid, int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

}
