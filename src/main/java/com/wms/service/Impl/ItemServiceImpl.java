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
	
	private final int SEARCH_PAGE_SIZE = 10;
	
	@Override
	public void addItem(Company company, Item item) {
		
		Inventory inventory = company.getInventory();
		
		for(String sku: item.getSKU()) {
			if(itemRepository.existsByBelongstoAndSku(inventory, sku))
				throw new RuntimeException("SKU must be unique");
		}

		item.setBelongsTo(inventory);
		
		itemRepository.save(item);
		
		inventory.putItem(item);
		
		inventoryRepository.save(inventory);
	}

	@Override
	public void updateItem(Company company, Item item) {
		
		Item toUpdate = itemRepository.findOne(item.getOpenid());
		
		toUpdate
		.setName(item.getName())
		.setSKU(item.getSKU())
		.setUnit(item.getUnit())
		.setHeight(item.getHeight())
		.setLength(item.getLength())
		.setWidth(item.getWidth())
		.setWeight(item.getWeight())
		.setNotes(item.getNotes())
		.setWeight_unit(item.getWeight_unit())
		.setSize_unit(item.getSize_unit());
		
		toUpdate.setUpdateOn();
		
		itemRepository.save(toUpdate);

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
		
		List<Item> itemlist = new ArrayList<Item>(company.getInventory().getItems_inbag().values());
		
		return listToPageCovert(itemlist, pageNum, pageSize);
	}

	@Override
	public Page<Item> searchUsingSku(Company company, String sku) {
		
		List<Item> itemlist = itemRepository.findByBelongstoAndSku(company.getInventory(), sku);
		
		return listToPageCovert(itemlist, 0, SEARCH_PAGE_SIZE);
	}

	@Override
	public Page<Item> searchUsingName(Company company, String name, int pageNum) {
		
		List<Item> itemlist = itemRepository.findByBelongstoAndNameContains(company.getInventory(), name);
		
		return listToPageCovert(itemlist, pageNum, SEARCH_PAGE_SIZE);
	}
	
	/**
	 * A method that paginating the List<Item> to Page<Item>
	 * @param itemlist the input itemlist
	 * @param pageNum page number
	 * @param pageSize page size
	 * @return Pagination of items.
	 */
	private Page<Item> listToPageCovert(List<Item> itemlist, int pageNum, int pageSize){
		Pageable pagination = new PageRequest(pageNum, pageSize);
		
        int start =  pagination.getOffset();
        int end = (start + pagination.getPageSize()) > itemlist.size() ? itemlist.size() : (start + pagination.getPageSize());
		
        if(start > end) {
        	throw new RuntimeException("Bad pagination request");
        }
        
		Page<Item> page = new PageImpl<Item>(itemlist.subList(start, end), pagination, itemlist.size());
		
		return page;
	}

}
