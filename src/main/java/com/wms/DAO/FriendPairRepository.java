package com.wms.DAO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.wms.bean.Company;
import com.wms.bean.relations.mtm.FriendPair;


public interface FriendPairRepository extends JpaRepository<FriendPair, Long> {

	Page<FriendPair> findBySellerOpenid(Long sellerid, Pageable pagination);
	
	Page<FriendPair> findByWarehouseOpenid(Long warehouseid, Pageable pagination);
	
	FriendPair findBySellerOpenidAndWarehouseOpenid(Long sellerid, Long warehouseid);
	
	boolean existsBySellerAndWarehouse(Company seller, Company warehouse);
}
