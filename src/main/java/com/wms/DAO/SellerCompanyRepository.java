package com.wms.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wms.bean.SellerCompany;

public interface SellerCompanyRepository extends JpaRepository<SellerCompany, Long> {

	SellerCompany findByName(String name);
	
	SellerCompany findByOpenid(Long openid);
	
	SellerCompany findByInvcode(String invcode);
	
}
