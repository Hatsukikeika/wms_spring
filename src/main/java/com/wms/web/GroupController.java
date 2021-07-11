package com.wms.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wms.model.ResponseBodyWrapper;
import com.wms.service.GroupService;

@RestController
@RequestMapping(value = "/api/group")
public class GroupController {
	
	@Autowired
	private GroupService groupService;
	
	@RequestMapping(value = "/myseller", method = RequestMethod.GET)
	public ResponseBodyWrapper getIncomingRequest(@RequestAttribute("$GROUPID") Long groupid,
			@RequestParam(defaultValue = "0") int pageNum,
			@RequestParam(defaultValue = "10") int pageSize) {
		
		return new ResponseBodyWrapper().putData(groupService.getSellerList(groupid, pageNum, pageSize));
	}
	
	@RequestMapping(value = "/myprovider", method = RequestMethod.GET)
	public ResponseBodyWrapper getSentRequest(@RequestAttribute("$GROUPID") Long groupid,
			@RequestParam(defaultValue = "0") int pageNum,
			@RequestParam(defaultValue = "10") int pageSize) {
		
		return new ResponseBodyWrapper().putData(groupService.getStorageProviderList(groupid, pageNum, pageSize));
	}
	
	
}
