package com.wms.service;

import org.springframework.data.domain.Page;

import com.wms.bean.enu.GroupType;
import com.wms.bean.relations.mtm.FriendRequest;

public interface RequestService {

	// Seller creates a linker request.
	void sendLinkRequest(Long groupid, Long linkerspace);
	
	// Retrieve linker request for seller.
	Page<FriendRequest> getSentLinkerRequests(Long groupid, int pageNum, int pageSize);
	
	// Retrieve linker request for storage provider
	Page<FriendRequest> getReceivedLinkerRequests(Long groupid, int pageNum, int pageSize);
	
	// Set the status of linker request to {Cancelled}. 
	void cancelLinkerRequest(GroupType grouptype, Long groupid, Long requestid);
	
	// Set the status of linker request to {Delayed}, which means the storage provider likely to take this deal but cannot accept it right now.
	void delayLinkerRequest(Long groupid, Long requestid);
	
	// Set the status of linker request to {Achieved}, and create business relation.
	void acceptLinkerRequest(Long groupid, Long requestid);
	
}
