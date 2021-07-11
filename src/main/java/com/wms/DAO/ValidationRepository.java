package com.wms.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wms.bean.Validation;

public interface ValidationRepository extends JpaRepository<Validation, Long> {

}
