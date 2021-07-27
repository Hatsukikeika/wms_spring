package com.wms.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wms.bean.ForecastItem;

public interface ForecastItemRepository extends JpaRepository<ForecastItem, Long> {
	
}
