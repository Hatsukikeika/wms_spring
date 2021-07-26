package com.wms.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wms.bean.WarehouseCompany;

public interface WarehouseRepository extends JpaRepository<WarehouseCompany, Long> {

	WarehouseCompany findByName(String name);
	
	WarehouseCompany findByOpenid(Long openid);
	
	WarehouseCompany findByInvcode(String invcode);

}
