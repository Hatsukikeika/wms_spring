package com.wms.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.wms.DAO.ItemRepository;
import com.wms.bean.Company;
import com.wms.bean.Inventory;
import com.wms.bean.Item;
import com.wms.service.ItemService;
import com.wms.service.helper.ObjectHelper;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private InventoryRepository inventoryRepository;

	@Autowired
	private ItemRepository itemRepository;
	
	private ObjectHelper<Item> itemHelper = new ObjectHelper<>();
	
	@Override
	public void addItem(Company company, Item item) {
		Inventory inventory = company.getInventory();
		
		itemRepository.save(item);
		
		inventory.putItem(item);
		
		inventoryRepository.save(inventory);


	}

	@Override
	public void updateItem(Company company, Item item) {
		
		item.setUpdateOn();
		
		itemRepository.save(item);

	}

	@Override
	public void deleteItem(Company company, Long item) {
		
		Item i = company.getInventory().getItem(item);
		
		if(i == null)
			throw new RuntimeException();
		
		i.setIsDel(true).setUpdateOn();
		
		itemRepository.save(i);
	}

	@Override
	public void deleteItemPerma(Company company, Long item) {
		
		Inventory inv = company.getInventory();
		
		Item i = inv.getItem(item);
		
		inv.getItems_inbag().remove(item);
		
		if(i == null)
			throw new RuntimeException();
		
		if(i.getIsDel() != true)
			throw new RuntimeException();
		
		itemRepository.delete(i);
	}
	
	@Override
	public Page<Item> getItemList(Company company, int pageNum, int pageSize) {
		
		Pageable pagination = new PageRequest(pageNum, pageSize);
		List<Item> itemlist = new ArrayList<Item>(company.getInventory().getItems_inbag().values());
		
        int start =  pagination.getOffset();
        int end = (start + pagination.getPageSize()) > itemlist.size() ? itemlist.size() : (start + pagination.getPageSize());
		
        if(start > end) {
        	throw new RuntimeException("Bad pagination request");
        }
        
		Page<Item> page = new PageImpl<Item>(itemlist.subList(start, end),pagination, itemlist.size());
		
		return page;
	}

}
