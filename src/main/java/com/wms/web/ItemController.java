package com.wms.web;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wms.DAO.CompanyRepository;
import com.wms.bean.Company;
import com.wms.bean.Item;
import com.wms.bean.User;
import com.wms.model.ResponseBodyWrapper;
import com.wms.service.ItemService;
import com.wms.service.Exceptions.DataNotFoundException;

@RestController
@RequestMapping(value = "/api/item")
public class ItemController {
	
	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private ItemService itemService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @Transactional
    public ResponseBodyWrapper addItemToInventory(@RequestAttribute("$COMPID") Long compid, @RequestBody Item item) throws DataNotFoundException {
    	
    	Company company = companyRepository.findByOpenid(compid);
    	
    	itemService.addItem(company, item);
    	
    	ResponseBodyWrapper responseBodyWrapper = new ResponseBodyWrapper();
    	
        return responseBodyWrapper;
    }
    
    @RequestMapping(value = "/addBatch", method = RequestMethod.POST)
    @Transactional
    public ResponseBodyWrapper addItemsToInventory(@RequestAttribute("$COMPID") Long compid, @RequestBody List<Item> items) throws DataNotFoundException {
    	
    	Company company = companyRepository.findByOpenid(compid);
    	
    	for(Item i : items) {
        	itemService.addItem(company, i);
    	}

    	
    	ResponseBodyWrapper responseBodyWrapper = new ResponseBodyWrapper();
    	
        return responseBodyWrapper;
    }
    
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @Transactional
    public ResponseBodyWrapper getItemsInInventory(@RequestAttribute("$COMPID") Long compid, @RequestParam(defaultValue = "0") int pageNum, @RequestParam(defaultValue = "10") int pageSize) throws DataNotFoundException {
    	
    	Company company = companyRepository.findByOpenid(compid);
    	
    	ResponseBodyWrapper responseBodyWrapper = new ResponseBodyWrapper().putData(itemService.getItemList(company, pageNum, pageSize));
    	
        return responseBodyWrapper;
    }
	
}
