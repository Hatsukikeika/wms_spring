package com.wms.DAO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.wms.bean.Group;
import com.wms.bean.relations.mtm.FriendRequest;

public interface LinkerRequestRepository extends JpaRepository<FriendRequest, Long> {
	
	Page<FriendRequest> findBySellerId(Long sellerid, Pageable pagination);
	
	Page<FriendRequest> findByLinkerId(Long linkerid, Pageable pagination);
	
	FriendRequest findBySellerIdAndId(Long sellerid, Long id);
	
	FriendRequest findByLinkerIdAndId(Long linkerid, Long id);
	
	FriendRequest findBySellerAndLinker(Group seller, Group linker);
	
	boolean existsByLinkerIdAndSellerId(Long linkerid, Long sellerid);
	
}
