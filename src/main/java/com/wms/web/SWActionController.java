package com.wms.web;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wms.DAO.SellerCompanyRepository;
import com.wms.bean.SellerCompany;
import com.wms.bean.DTO.ForecastRequest;
import com.wms.model.ResponseBodyWrapper;
import com.wms.service.SWService;
import com.wms.service.Exceptions.DataNotFoundException;

@RestController
@RequestMapping(value = "/api/sw")
public class SWActionController {

	@Autowired
	private SellerCompanyRepository sellerCompanyRepository;
	
	@Autowired
	private SWService sWService;
	
	
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @Transactional
    public ResponseBodyWrapper addItemToInventory(@RequestAttribute("$COMPID") Long compid, @RequestBody @Valid ForecastRequest forecastRequest) throws DataNotFoundException {
    	
    	SellerCompany company = sellerCompanyRepository.findByOpenid(compid);
    	
    	sWService.forecastReg(company, forecastRequest);
    	
    	ResponseBodyWrapper responseBodyWrapper = new ResponseBodyWrapper();
    	
        return responseBodyWrapper;
    }
	
	
}
