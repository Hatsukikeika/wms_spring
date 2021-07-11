package com.wms.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wms.bean.Item;
import com.wms.model.ResponseBodyWrapper;
import com.wms.service.ItemService;

@RestController
@RequestMapping(value = "/api/item")
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@RequestMapping(value="/basic", method=RequestMethod.POST)
	public ResponseBodyWrapper addItem(@RequestAttribute("$GROUPID") Long groupid, @RequestBody Item item) {
		itemService.addItem(groupid, item);
		return new ResponseBodyWrapper();
	}
	
	@RequestMapping(value="/basic", method=RequestMethod.PUT)
	public ResponseBodyWrapper updateItem(@RequestAttribute("$GROUPID") Long groupid,
			@RequestParam long itemid, @RequestBody Item item) {
		itemService.updateItem(groupid, itemid, item);
		return new ResponseBodyWrapper();
	}
	
	@RequestMapping(value="/basic", method=RequestMethod.DELETE)
	public ResponseBodyWrapper deleteItem(@RequestAttribute("$GROUPID") Long groupid, @RequestParam long itemid) {
		itemService.deleteItem(groupid, itemid);
		return new ResponseBodyWrapper();
	}
	
	@RequestMapping(value = "/basic/list", method = RequestMethod.GET)
	public ResponseBodyWrapper getAllItem(@RequestAttribute("$GROUPID") Long groupid,
			@RequestParam(defaultValue = "0") int pageNum,
			@RequestParam(defaultValue = "10") int pageSize) {
		
		return new ResponseBodyWrapper().putData(itemService.itemByGroup(groupid, pageNum, pageSize));
	}
}
