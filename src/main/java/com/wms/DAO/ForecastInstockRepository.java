package com.wms.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wms.bean.ForecastInstock;
import com.wms.bean.SellerCompany;
import com.wms.bean.WarehouseCompany;
import com.wms.bean.enu.RequestStatus;

public interface ForecastInstockRepository extends JpaRepository<ForecastInstock, Long> {

	List<ForecastInstock> findBySellerAndOpenid(SellerCompany sc, Long id);
	
	List<ForecastInstock> findByWarehouseAndOpenid(WarehouseCompany wc, Long id);
	
	List<ForecastInstock> findBySellerAndOpenidAndStatus(SellerCompany sc, Long id, RequestStatus rs);
	
	List<ForecastInstock> findByWarehouseAndOpenidAndStatus(WarehouseCompany wc, Long id, RequestStatus rs);
	
}
