package com.wms.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wms.bean.relations.mtm.CompanyMember;

public interface CompMemberRepository extends JpaRepository<CompanyMember, Long> {

	CompanyMember findByCompanyNameAndMemberEmail(String company, String user);
	
}
