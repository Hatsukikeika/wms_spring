package com.wms.web;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wms.DAO.CompanyRepository;
import com.wms.bean.WarehouseCompany;
import com.wms.model.ResponseBodyWrapper;
import com.wms.service.WarehouseService;
import com.wms.service.Exceptions.DataNotFoundException;

@RestController
@RequestMapping(value = "/api/warehouse")
public class WarehouseController {

	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private WarehouseService warehouseService;
	
    @RequestMapping(value = "/addStorage", method = RequestMethod.POST)
    @Transactional
    public ResponseBodyWrapper addItemToInventory(@RequestAttribute("$COMPID") Long compid, @RequestParam String name) throws DataNotFoundException {
    	
    	WarehouseCompany company = (WarehouseCompany) companyRepository.findByOpenid(compid);
    	
    	warehouseService.addStorage(company, name);
    	
    	ResponseBodyWrapper responseBodyWrapper = new ResponseBodyWrapper();
    	
        return responseBodyWrapper;
    }
	
}
