package com.wms.DAO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.wms.bean.Storage;

public interface StorageRepository extends JpaRepository<Storage, Long> {

	// Get recorded storages for storage provider.
	Page<Storage> findByGroupId(Long groupid, Pageable pagination);
	
	// Check if there is a storage under group:{groupid}
	boolean existsByGroupIdAndId(Long groupid, Long id);
	
	// Get a storage under group:{groupid}
	Storage findByGroupIdAndId(Long groupid, Long id);
}
