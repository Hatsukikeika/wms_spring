package com.wms.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wms.bean.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmailAndGroupId(String email, long group_id);
    
    User findByIdAndGroupId(long id, long group_id);
}
