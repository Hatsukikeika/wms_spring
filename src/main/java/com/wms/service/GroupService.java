package com.wms.service;

import org.springframework.data.domain.Page;

import com.wms.bean.relations.mtm.FriendPair;

public interface GroupService {
	
	// Retrieve a list for seller to see the authorized storage provider.
	Page<FriendPair> getStorageProviderList(Long groupid, int pageNum, int pageSize);
	
	// Retrieve a list for storage provider to see the serving seller.
	Page<FriendPair> getSellerList(Long groupid, int pageNum, int pageSize);
}
