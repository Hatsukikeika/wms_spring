package com.wms.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wms.bean.Instock;

public interface InstockRepository extends JpaRepository<Instock, Long> {

	boolean existsByStorageId(Long storageid);
}
