package com.wms.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wms.bean.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);
	
	boolean existsByEmail(String email);
}
