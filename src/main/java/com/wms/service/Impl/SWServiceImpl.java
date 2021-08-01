package com.wms.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.wms.DAO.BatchPackageRepository;
import com.wms.DAO.ForecastInstockRepository;
import com.wms.DAO.ForecastItemRepository;
import com.wms.DAO.FriendPairRepository;
import com.wms.DAO.ItemInfoRepository;
import com.wms.bean.BatchPackage;
import com.wms.bean.ForecastInstock;
import com.wms.bean.ForecastItem;
import com.wms.bean.ItemInfo;
import com.wms.bean.SellerCompany;
import com.wms.bean.WarehouseCompany;
import com.wms.bean.DTO.ForecastRequest;
import com.wms.bean.DTO.ForecastRequest.BatchItemRequest;
import com.wms.bean.enu.RequestStatus;
import com.wms.bean.relations.mtm.FriendPair;
import com.wms.service.SWService;
import com.wms.service.helper.ObjectHelper;

@Service
public class SWServiceImpl implements SWService {
	
	@Autowired
	private FriendPairRepository friendPairRepository;
	
	@Autowired
	private ForecastInstockRepository forecastInstockRepository;
	
	@Autowired
	private ItemInfoRepository itemInfoRepository;
	
	@Autowired
	private ForecastItemRepository forecastItemRepository;
	
	@Autowired
	private BatchPackageRepository batchPackageRepository;

	private final int SEARCH_PAGE_SIZE = 10;	
	
	@Override
	public void forecastReg(SellerCompany company, ForecastRequest forecastRequest) {

		ForecastInstock forcast = new ForecastInstock();
		
		FriendPair friendPair = friendPairRepository.findBySellerOpenidAndWarehouseOpenid(company.getOpenid(), forecastRequest.getWarehouseId());
		
		if(friendPair == null)
			throw new RuntimeException("Error partnership");

		for(BatchItemRequest br : forecastRequest.getBatches()) {
			BatchPackage batch = new BatchPackage();
			batch.setHeight(br.getHeight())
				.setWeight(br.getWeight())
				.setWidth(br.getWidth())
				.setWeight_unit(br.getWeight_unit())
				.setSize_unit(br.getSize_unit());
			
			
			for(BatchItemRequest.simpleBatchItem sbi : br.getBatches()) {
				ItemInfo lookup = itemInfoRepository.findOne(sbi.getItemId());
				ForecastItem forecastItem = new ForecastItem(lookup);
				forecastItem.setCount(sbi.getCount());
				forecastItemRepository.save(forecastItem);
				batch.putItem(forecastItem);
			}
			
			batchPackageRepository.save(batch);
			forcast.addBatch(batch);			
		}
		
		forcast.setSeller(friendPair.getSeller())
				.setWarehouse(friendPair.getWarehouse())
				.setTrackingNum(forecastRequest.getTrackingNum())
				.setCarrier(forecastRequest.getCarrier())
				.setStatus(RequestStatus.PENDING);
		
		forecastInstockRepository.save(forcast);
		
		
	}

	@Override
	public Page<ForecastInstock> getAllForcastInstock(SellerCompany company, Integer Page) {
		List<ForecastInstock> list = forecastInstockRepository.findBySeller(company);
		return ObjectHelper.listToPageCovert(list, Page, SEARCH_PAGE_SIZE);
	}

	@Override
	public Page<ForecastInstock> getAllForcastInstock(WarehouseCompany company, Integer Page) {
		List<ForecastInstock> list = forecastInstockRepository.findByWarehouse(company);
		return ObjectHelper.listToPageCovert(list, Page, SEARCH_PAGE_SIZE);
	}

	@Override
	public Page<ForecastInstock> getUndoneForcastInstock(SellerCompany company, Integer Page) {
		List<ForecastInstock> list = forecastInstockRepository.findBySellerAndStatus(company, RequestStatus.PENDING);
		return ObjectHelper.listToPageCovert(list, Page, SEARCH_PAGE_SIZE);
	}

	@Override
	public Page<ForecastInstock> getUndoneForcastInstock(WarehouseCompany company, Integer Page) {
		List<ForecastInstock> list = forecastInstockRepository.findByWarehouseAndStatus(company, RequestStatus.PENDING);
		return ObjectHelper.listToPageCovert(list, Page, SEARCH_PAGE_SIZE);
	}

	@Override
	public Page<ForecastInstock> getFinishedForcastInstock(SellerCompany company, Integer Page) {
		List<ForecastInstock> list = forecastInstockRepository.findBySellerAndStatus(company, RequestStatus.ACHIEVED);
		return ObjectHelper.listToPageCovert(list, Page, SEARCH_PAGE_SIZE);
	}

	@Override
	public Page<ForecastInstock> getFinishedForcastInstock(WarehouseCompany company, Integer Page) {
		List<ForecastInstock> list = forecastInstockRepository.findByWarehouseAndStatus(company, RequestStatus.ACHIEVED);
		return ObjectHelper.listToPageCovert(list, Page, SEARCH_PAGE_SIZE);
	}

}
