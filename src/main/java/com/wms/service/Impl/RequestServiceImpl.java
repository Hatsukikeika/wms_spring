package com.wms.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.wms.DAO.FriendPairRepository;
import com.wms.DAO.GroupRepository;
import com.wms.DAO.LinkerRequestRepository;
import com.wms.bean.Group;
import com.wms.bean.enu.GroupType;
import com.wms.bean.enu.RequestStatus;
import com.wms.bean.relations.mtm.FriendPair;
import com.wms.bean.relations.mtm.FriendRequest;
import com.wms.service.RequestService;
import com.wms.service.Exceptions.DataNotFoundException;
import com.wms.service.Exceptions.IllegalActionException;

@Service
public class RequestServiceImpl implements RequestService {
	
	@Autowired
	private GroupRepository groupRepository;
	
	@Autowired
	private LinkerRequestRepository linkerRequestRepository;
	
	@Autowired
	private FriendPairRepository friendPairRepository;

	@Override
	public void sendLinkRequest(Long groupid, Long linkerspace) {
		Group seller = groupRepository.findOne(groupid);
		Group linker = groupRepository.findBySpace(linkerspace);
		
		if(linker == null)
			throw new DataNotFoundException(linkerspace.toString(), "space","storage provider");
		
		if(linker.getActivated() == false)
			throw new IllegalActionException("storage provider: " + linkerspace + " isn't activated");
			
		if(linker.getType() != GroupType.TYPE_STORAGE)
			throw new IllegalActionException(linkerspace + " is not a storage provider");
		
		if(friendPairRepository.existsBySellerAndLinker(seller, linker))
			throw new IllegalActionException(linkerspace + " is already linked to your account");
		
		FriendRequest groupLinkerRequest = linkerRequestRepository.findBySellerAndLinker(seller, linker);
		
		if(groupLinkerRequest == null) {
			groupLinkerRequest = new FriendRequest(seller,linker);
		} else if (groupLinkerRequest.getStatus() == RequestStatus.CANCELLED) {
			groupLinkerRequest.setStatus(RequestStatus.PENDING);
			groupLinkerRequest.setCreateOn();
		} else {
			throw new IllegalActionException("Please wait your current quest to finish");
		}
		
		linkerRequestRepository.save(groupLinkerRequest);
	}

	@Override
	public Page<FriendRequest> getSentLinkerRequests(Long groupid, int pageNum, int pageSize) {
		Pageable pagination = new PageRequest(pageNum, pageSize);
		return linkerRequestRepository.findBySellerId(groupid, pagination);
	}

	@Override
	public Page<FriendRequest> getReceivedLinkerRequests(Long groupid, int pageNum, int pageSize) {
		Pageable pagination = new PageRequest(pageNum, pageSize);
		return linkerRequestRepository.findByLinkerId(groupid, pagination);
	}

	@Override
	public void cancelLinkerRequest(GroupType grouptype, Long groupid, Long requestid) {
		FriendRequest groupLinkerRequest = null;
		
		switch(grouptype) {
		case TYPE_SELLER:
			groupLinkerRequest = linkerRequestRepository.findBySellerIdAndId(groupid, requestid);
			break;
		case TYPE_STORAGE:
			groupLinkerRequest = linkerRequestRepository.findByLinkerIdAndId(groupid, requestid);
			break;
		default:
		}
		
		if(groupLinkerRequest == null)
			throw new DataNotFoundException(requestid.toString(),"request id","request");
		
		beforeStatusChange(groupLinkerRequest);
		
		groupLinkerRequest.setStatus(RequestStatus.CANCELLED);
		linkerRequestRepository.save(groupLinkerRequest);
	}

	@Override
	public void delayLinkerRequest(Long groupid, Long requestid) {
		FriendRequest groupLinkerRequest = linkerRequestRepository.findByLinkerIdAndId(groupid, requestid);
		if(groupLinkerRequest == null)
			throw new DataNotFoundException(requestid.toString(),"request id","request");
		
		beforeStatusChange(groupLinkerRequest);
		
		groupLinkerRequest.setStatus(RequestStatus.DELAYED);
		linkerRequestRepository.save(groupLinkerRequest);
	}

	@Override
	public void acceptLinkerRequest(Long groupid, Long requestid) {
		FriendRequest groupLinkerRequest = linkerRequestRepository.findByLinkerIdAndId(groupid, requestid);
		if(groupLinkerRequest == null)
			throw new DataNotFoundException(requestid.toString(),"request id","request");
		
		beforeStatusChange(groupLinkerRequest);
		
		groupLinkerRequest.setStatus(RequestStatus.ACHIEVED);
		
		FriendPair friendPair = new FriendPair(groupLinkerRequest.getSeller(),groupLinkerRequest.getLinker());
		
		friendPairRepository.save(friendPair);
		linkerRequestRepository.save(groupLinkerRequest);
	}
	
	private void beforeStatusChange(FriendRequest groupLinkerRequest) {
		if(groupLinkerRequest.getStatus() == RequestStatus.ACHIEVED)
			throw new IllegalActionException("cannot modify achieved request");
		
		if(groupLinkerRequest.getStatus() == RequestStatus.CANCELLED)
			throw new IllegalActionException("cannot modify cancelled request");
	}

}
