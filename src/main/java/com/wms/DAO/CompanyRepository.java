package com.wms.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wms.bean.Company;
import com.wms.bean.User;

public interface CompanyRepository extends JpaRepository<Company, Long> {
	
	Company findByName(String name);
	
	Company findByOpenid(Long openid);
	
	Company findByInvcode(String invcode);

}
