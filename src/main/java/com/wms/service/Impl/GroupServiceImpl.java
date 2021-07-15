package com.wms.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.wms.DAO.FriendPairRepository;
import com.wms.bean.relations.mtm.FriendPair;
import com.wms.service.GroupService;

@Service
public class GroupServiceImpl implements GroupService {
	
	@Autowired
	private FriendPairRepository friendPairRepository;
	
	@Override
	public Page<FriendPair> getStorageProviderList(Long groupid, int pageNum, int pageSize) {
		Pageable pagination = new PageRequest(pageNum, pageSize);
		return friendPairRepository.findBySellerOpenid(groupid, pagination);	
	}

	@Override
	public Page<FriendPair> getSellerList(Long groupid, int pageNum, int pageSize) {
		Pageable pagination = new PageRequest(pageNum, pageSize);
		return friendPairRepository.findByLinkerOpenid(groupid, pagination);
	}

}
