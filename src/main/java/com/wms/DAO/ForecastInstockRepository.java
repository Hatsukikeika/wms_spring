package com.wms.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wms.bean.ForecastInstock;

public interface ForecastInstockRepository extends JpaRepository<ForecastInstock, Long> {

}
