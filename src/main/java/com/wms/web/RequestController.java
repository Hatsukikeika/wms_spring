package com.wms.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wms.bean.enu.GroupType;
import com.wms.bean.enu.RequestStatus;
import com.wms.model.ResponseBodyWrapper;
import com.wms.service.RequestService;

@RestController
@RequestMapping(value = "/api/request")
public class RequestController {
	
	@Autowired
	private RequestService requestService;

	@RequestMapping(value="/create/linker", method=RequestMethod.POST)
	public ResponseBodyWrapper addStorageProvider(@RequestAttribute("$GROUPID") Long groupid, @RequestParam long linkerspace) {
		
		requestService.sendLinkRequest(groupid, linkerspace);
		return new ResponseBodyWrapper();
	}
	
	@RequestMapping(value="/get/sent/linker", method=RequestMethod.GET)
	public ResponseBodyWrapper getSentLinkerRequest(@RequestAttribute("$GROUPID") Long groupid,
			@RequestParam(defaultValue = "0") int pageNum,
			@RequestParam(defaultValue = "10") int pageSize) {
		
		return new ResponseBodyWrapper().putData(requestService.getSentLinkerRequests(groupid, pageNum, pageSize));
	}
	
	@RequestMapping(value="/get/received/linker", method=RequestMethod.GET)
	public ResponseBodyWrapper getReceivedLinkerRequest(@RequestAttribute("$GROUPID") Long groupid,
			@RequestParam(defaultValue = "0") int pageNum,
			@RequestParam(defaultValue = "10") int pageSize) {
		
		return new ResponseBodyWrapper().putData(requestService.getReceivedLinkerRequests(groupid, pageNum, pageSize));
	}
	
	@RequestMapping(value="/action/changeStatus/linker", method=RequestMethod.PATCH)
	public ResponseBodyWrapper handleLinkerRequest(@RequestAttribute("$GROUPTYPE") GroupType grouptype,
			@RequestAttribute("$GROUPID") Long groupid,
			@RequestParam long requestid,
			@RequestParam int status) {
		
		switch(RequestStatus.get(status)) {
		case DELAYED:
			requestService.delayLinkerRequest(groupid, requestid);
			break;
		case CANCELLED:
			requestService.cancelLinkerRequest(grouptype,groupid, requestid);
			break;
		case ACHIEVED:
			requestService.acceptLinkerRequest(groupid, requestid);
			break;
		default:
			return new ResponseBodyWrapper().setStatus(HttpStatus.BAD_REQUEST.value()).setMessage("Invalid status");
		}

		return new ResponseBodyWrapper();
	}
}
