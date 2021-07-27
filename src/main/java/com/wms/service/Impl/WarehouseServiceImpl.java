package com.wms.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wms.DAO.CompanyRepository;
import com.wms.bean.Inventory;
import com.wms.bean.WarehouseCompany;
import com.wms.service.WarehouseService;

@Service
public class WarehouseServiceImpl implements WarehouseService {
	
	@Autowired
	private CompanyRepository companyRepository;

	@Override
	public void addStorage(WarehouseCompany company, String stroageName) {
		
		Inventory inventory = new Inventory();
		
		inventory.setCompany(company)
				.setInventoryName(stroageName);
		
		company.setInventory(inventory);
		
		companyRepository.save(company);
		
		
	}

}
