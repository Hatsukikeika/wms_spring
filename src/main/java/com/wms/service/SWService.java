package com.wms.service;

import com.wms.bean.SellerCompany;
import com.wms.bean.DTO.ForecastRequest;

public interface SWService {

	void forecastReg(SellerCompany company, ForecastRequest forecastRequest);
	
}
