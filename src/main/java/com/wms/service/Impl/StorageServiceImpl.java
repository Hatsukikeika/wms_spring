package com.wms.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.wms.DAO.GroupRepository;
import com.wms.DAO.InstockRepository;
import com.wms.DAO.StorageRepository;
import com.wms.bean.Group;
import com.wms.bean.Storage;
import com.wms.service.StorageService;
import com.wms.service.Exceptions.DataNotFoundException;
import com.wms.service.Exceptions.FieldMissingException;
import com.wms.service.Exceptions.IllegalActionException;
import com.wms.service.helper.ObjectHelper;

@Service
public class StorageServiceImpl implements StorageService {

	@Autowired
	private GroupRepository groupRepository;
	
	@Autowired
	private StorageRepository storageRepository;
	
	@Autowired 
	private InstockRepository instockRepository;
	
	private ObjectHelper<Storage> objectHelper = new ObjectHelper<>();
	
	@Override
	public void createStorage(Long groupid, Storage storage) {
		Group group = groupRepository.findOne(groupid);
		
		storage.setGroup(group);
		
		try {
			objectHelper.testNull(storage, "id","group","instocks");
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new RuntimeException(e.getMessage());
		}
		
		storageRepository.save(storage);
	}

	@Override
	public void updateStorage(Long groupid, Storage storage) {
		
		if(storage.getId() == null)
			throw new FieldMissingException("id");
		
		Storage toUpdate = storageRepository.findByGroupIdAndId(groupid, storage.getId());
		
		if(toUpdate == null)
			throw new DataNotFoundException(storage.getId().toString(), "storage ID", "Storage");
		
		try {
			toUpdate = objectHelper.mergeObjects(toUpdate, storage, "id", "createOn", "group");
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new RuntimeException(e.getMessage());
		}
		
		storageRepository.save(toUpdate);
	}

	@Override
	public void removeStorage(Long groupid, Long storageid) {
		
		Storage todelete = storageRepository.findByGroupIdAndId(groupid, storageid);
		if(todelete == null)
			throw new DataNotFoundException(storageid.toString(), "storage ID", "Storage");

		if(instockRepository.existsByStorageId(storageid))
			throw new IllegalActionException("cannot delete storage when it contains items");
		
		storageRepository.delete(todelete);
	}

	@Override
	public Page<Storage> retrieveStorageList(Long groupid, int pageNum, int pageSize) {
		
		Pageable pagination = new PageRequest(pageNum, pageSize);
		return storageRepository.findByGroupId(groupid, pagination);	
		
	}

}
