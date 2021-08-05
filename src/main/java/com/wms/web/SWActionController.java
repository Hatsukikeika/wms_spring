package com.wms.web;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wms.DAO.CompanyRepository;
import com.wms.DAO.ForecastInstockRepository;
import com.wms.bean.ForecastInstock;
import com.wms.bean.SellerCompany;
import com.wms.bean.WarehouseCompany;
import com.wms.bean.DTO.ForecastCheckIn;
import com.wms.bean.DTO.ForecastRequest;
import com.wms.bean.enu.GroupType;
import com.wms.model.ResponseBodyWrapper;
import com.wms.service.SWService;
import com.wms.service.Exceptions.DataNotFoundException;

@RestController
@RequestMapping(value = "/api/sw")
public class SWActionController {

	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private ForecastInstockRepository forecastInstockRepository;
	
	@Autowired
	private SWService sWService;
	
	
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @Transactional
    public ResponseBodyWrapper createForecastRequest(@RequestAttribute("$COMPID") Long compid, @RequestBody @Valid ForecastRequest forecastRequest) throws DataNotFoundException {
    	
    	SellerCompany company = (SellerCompany) companyRepository.findByOpenid(compid);
    	
    	sWService.forecastReg(company, forecastRequest);
    	
    	ResponseBodyWrapper responseBodyWrapper = new ResponseBodyWrapper();
    	
        return responseBodyWrapper;
    }
	
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @Transactional
    public ResponseBodyWrapper getForecastRequest(@RequestAttribute("$COMPID") Long compid, @RequestAttribute("COMPTYPE") GroupType type
    		,@RequestParam(defaultValue = "0") int pageNum) throws DataNotFoundException {
    	
    	ResponseBodyWrapper responseBodyWrapper = new ResponseBodyWrapper();
    	
    	switch(type) {
		case TYPE_SELLER:
			SellerCompany sc = (SellerCompany) companyRepository.findByOpenid(compid);
			return responseBodyWrapper.putData(sWService.getAllForcastInstock(sc, pageNum));
		case TYPE_STORAGE:
			WarehouseCompany wc = (WarehouseCompany) companyRepository.findByOpenid(compid);
			return responseBodyWrapper.putData(sWService.getAllForcastInstock(wc, pageNum));
		default:
			return responseBodyWrapper.setStatus(400).setMessage("Not supported company type");
    	}
    }
	
    @RequestMapping(value = "/checkIn", method = RequestMethod.POST)
    @Transactional
    public ResponseBodyWrapper checkInForecastRequest(@RequestAttribute("$COMPID") Long compid, 
    		@RequestParam Long forecastId,
    		@RequestParam Long batchId,
    		@RequestParam Long itemId,
    		@RequestBody @Valid ForecastCheckIn checkIn) throws DataNotFoundException {

    	ForecastInstock forecastInstock = forecastInstockRepository.findOne(forecastId);
    	
    	sWService.forecastCheckIn(forecastInstock, batchId, itemId, checkIn);
    	
    	return new ResponseBodyWrapper();
    }
    
    @RequestMapping(value = "/confirm", method = RequestMethod.PUT)
    @Transactional
    public ResponseBodyWrapper confirmForecastRequest(@RequestAttribute("$COMPID") Long compid, 
    		@RequestParam Long forecastId,
    		@RequestParam Long batchId,
    		@RequestParam Long itemId,
    		@RequestParam boolean isAccepted) throws DataNotFoundException {

    	ForecastInstock forecastInstock = forecastInstockRepository.findOne(forecastId);
    	
    	sWService.forecastConfirm(forecastInstock, batchId, itemId, isAccepted);
    	
    	return new ResponseBodyWrapper();
    }
    
    
    @RequestMapping(value = "/achieve", method = RequestMethod.PUT)
    @Transactional
    public ResponseBodyWrapper achieveForecastRequest(@RequestAttribute("$COMPID") Long compid, 
    		@RequestParam Long forecastId) throws DataNotFoundException {

    	ForecastInstock forecastInstock = forecastInstockRepository.findOne(forecastId);
    	
    	sWService.forecastAchieve(forecastInstock);
    	
    	return new ResponseBodyWrapper();
    } 
}
