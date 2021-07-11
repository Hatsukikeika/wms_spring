package com.wms.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wms.bean.Group;

public interface GroupRepository extends JpaRepository<Group, Long> {
	
	public Group findByContact(String contact);
	
	public Group findBySpace(Long space);
	
	public boolean existsByEmail(String email);
}
