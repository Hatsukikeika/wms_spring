package com.wms.service;

import org.springframework.data.domain.Page;

import com.wms.bean.ForecastInstock;
import com.wms.bean.SellerCompany;
import com.wms.bean.WarehouseCompany;
import com.wms.bean.DTO.ForecastCheckIn;
import com.wms.bean.DTO.ForecastRequest;

public interface SWService {

	void forecastReg(SellerCompany company, ForecastRequest forecastRequest);
	
	void forecastCheckIn(ForecastInstock forecast, Long batchId, Long itemId, ForecastCheckIn checkIn);
	
	void forecastConfirm(ForecastInstock forecast, Long batchId, Long itemId, boolean isAccepted);
	
	void forecastAchieve(ForecastInstock forecast);
	
	Page<ForecastInstock> getAllForcastInstock(SellerCompany company, Integer Page);
	
	Page<ForecastInstock> getAllForcastInstock(WarehouseCompany company, Integer Page);
	
	Page<ForecastInstock> getUndoneForcastInstock(SellerCompany company, Integer Page);
	
	Page<ForecastInstock> getUndoneForcastInstock(WarehouseCompany company, Integer Page);
	
	Page<ForecastInstock> getFinishedForcastInstock(SellerCompany company, Integer Page);
	
	Page<ForecastInstock> getFinishedForcastInstock(WarehouseCompany company, Integer Page);
	
}
