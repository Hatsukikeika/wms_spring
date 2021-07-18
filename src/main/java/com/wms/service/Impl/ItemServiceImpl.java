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
		
		Item i = itemRepository.getOne(item.getOpenid());
		
		if(i == null)
			throw new RuntimeException();
		
		try {
			i = itemHelper.mergeObjects(i, item, "uuid","openid","createOn","updateOn","isDel");
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new RuntimeException();
		}
		
		i.setUpdateOn();
		
		itemRepository.save(i);

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
		
		Item i = company.getInventory().getItem(item);
		
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
		
        int start =  new PageRequest(pageNum, pageSize).getOffset();
        int end = (start + new PageRequest(pageNum, pageSize).getPageSize()) > itemlist.size() ? itemlist.size() : (start + new PageRequest(pageNum, pageSize).getPageSize());
		
        if(start > end) {
        	throw new RuntimeException("Bad pagination request");
        }
        
		Page<Item> page = new PageImpl<Item>(itemlist.subList(start, end),new PageRequest(pageNum, pageSize), itemlist.size());
		
		return page;
	}

}