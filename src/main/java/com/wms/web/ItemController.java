package com.wms.web;

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

    // Get user info for logged in user.
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @Transactional
    public ResponseBodyWrapper getAccountInfo(@RequestAttribute("$COMPID") Long compid, @RequestBody Item item) throws DataNotFoundException {
    	
    	Company company = companyRepository.getOne(compid);
    	
    	itemService.addItem(company, item);
    	
    	ResponseBodyWrapper responseBodyWrapper = new ResponseBodyWrapper();
    	
        return responseBodyWrapper;
    }
	
}
