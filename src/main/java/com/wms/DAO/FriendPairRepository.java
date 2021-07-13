package com.wms.DAO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.wms.bean.Company;
import com.wms.bean.relations.mtm.FriendPair;


public interface FriendPairRepository extends JpaRepository<FriendPair, Long> {

	Page<FriendPair> findBySellerId(Long sellerid, Pageable pagination);
	
	Page<FriendPair> findByLinkerId(Long linkerid, Pageable pagination);
	
	boolean existsBySellerAndLinker(Company seller, Company linker);
}
