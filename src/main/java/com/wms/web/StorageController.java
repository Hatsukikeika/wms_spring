package com.wms.web;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wms.bean.Storage;
import com.wms.model.ResponseBodyWrapper;
import com.wms.service.StorageService;

@RestController
@RequestMapping(value = "/api")
public class StorageController {
	
	@Autowired
	private StorageService storageService;
	
	@RequestMapping(value = "/storage", method = RequestMethod.POST)
	@Transactional
	public ResponseBodyWrapper createStorage(@RequestAttribute("$GROUPID") Long groupid, @RequestBody Storage storage) {
		
		storageService.createStorage(groupid, storage);
		
		return new ResponseBodyWrapper();
	}
	
	@RequestMapping(value = "/storage", method = RequestMethod.PUT)
	@Transactional
	public ResponseBodyWrapper updateStorage(@RequestAttribute("$GROUPID") Long groupid, @RequestBody Storage storage) {
		
		storageService.updateStorage(groupid, storage);
		
		return new ResponseBodyWrapper();
	}
	
	@RequestMapping(value = "/storage", method = RequestMethod.DELETE)
	@Transactional
	public ResponseBodyWrapper removeStorage(@RequestAttribute("$GROUPID") Long groupid, @RequestParam Long storageid) {
		
		storageService.removeStorage(groupid, storageid);
		
		return new ResponseBodyWrapper();
	}
	
	@RequestMapping(value = "/storage", method = RequestMethod.GET)
	@Transactional
	public ResponseBodyWrapper getStorageList(@RequestAttribute("$GROUPID") Long groupid,
			@RequestParam(defaultValue = "0") int pageNum,
			@RequestParam(defaultValue = "10") int pageSize) {
		
		return new ResponseBodyWrapper().putData(storageService.retrieveStorageList(groupid, pageNum, pageSize));
	}
}
