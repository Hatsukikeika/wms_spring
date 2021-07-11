package com.wms.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.wms.DAO.GroupRepository;
import com.wms.DAO.ItemRepository;
import com.wms.bean.Item;
import com.wms.service.ItemService;
import com.wms.service.helper.ObjectHelper;

@Service
public class ItemServiceImpl implements ItemService{

	@Autowired
	private GroupRepository groupRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	private ObjectHelper<Item> objectHelper = new ObjectHelper<>();
	
	@Override
	public void addItem(long groupid, Item item) {

		try {
			Item gen = new Item();
			
			objectHelper.testNull(item,
					"group","detail","length","height","width", "weight","uuid","id","openid","createOn","updateOn","image","isDel");
			
			objectHelper.mergeObjects(gen, item,
					"uuid","id","openid","createOn","updateOn");
			
			itemRepository.save(gen);
			
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new RuntimeException(e.getMessage());
		}
		
	}

	@Override
	public void updateItem(long groupid, long itemid, Item from) {

	}

	@Override
	public void deleteItem(long groupid, long itemid) {
		
		try {
			Item to = itemRepository.findByGroupIdAndId(groupid, itemid);
			
			/*
			 	此处为代码占位符、后续在此添加逻辑判断 
			 */
			
			itemRepository.save(to);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e.getMessage());
		}
		
	}

	@Override
	public Page<Item> itemByGroup(long groupid, int pageNum, int pageSize) {
		Pageable pagination = new PageRequest(pageNum, pageSize);

		return itemRepository.findByGroupIdAndIsDel(groupid, false, pagination);
	}

}
