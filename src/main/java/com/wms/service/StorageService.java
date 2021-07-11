package com.wms.service;

import org.springframework.data.domain.Page;

import com.wms.bean.Storage;

public interface StorageService {
	
	// storage provider adds a storage
	void createStorage(Long groupid, Storage storage);
	
	// storage provider updates a storage
	void updateStorage(Long groupid, Storage storage);
	
	// storage provider removes a storage
	void removeStorage(Long groupid, Long storageid);
	
	// retrieve the storages that are recorded.
	Page<Storage> retrieveStorageList(Long groupid, int pageNum, int pageSize);
	
	
}
