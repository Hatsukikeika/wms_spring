package com.wms.service;

import org.springframework.data.domain.Page;

import com.wms.bean.ForecastInstock;
import com.wms.bean.SellerCompany;
import com.wms.bean.WarehouseCompany;
import com.wms.bean.DTO.ForecastRequest;

public interface SWService {

	void forecastReg(SellerCompany company, ForecastRequest forecastRequest);
	
	Page<ForecastInstock> getAllForcastInstock(SellerCompany company, Long forcastID, Integer Page);
	
	Page<ForecastInstock> getAllForcastInstock(WarehouseCompany company, Long forcastID, Integer Page);
	
	Page<ForecastInstock> getUndoneForcastInstock(SellerCompany company, Long forcastID, Integer Page);
	
	Page<ForecastInstock> getUndoneForcastInstock(WarehouseCompany company, Long forcastID, Integer Page);
	
	Page<ForecastInstock> getFinishedForcastInstock(SellerCompany company, Long forcastID, Integer Page);
	
	Page<ForecastInstock> getFinishedForcastInstock(WarehouseCompany company, Long forcastID, Integer Page);
	
}
