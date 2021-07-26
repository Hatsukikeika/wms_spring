package com.wms.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wms.DAO.BatchPackageRepository;
import com.wms.DAO.ForecastInstockRepository;
import com.wms.DAO.FriendPairRepository;
import com.wms.DAO.ItemInfoRepository;
import com.wms.bean.BatchPackage;
import com.wms.bean.ForecastInstock;
import com.wms.bean.ItemInfo;
import com.wms.bean.SellerCompany;
import com.wms.bean.DTO.ForecastRequest;
import com.wms.bean.DTO.ForecastRequest.BatchItemRequest;
import com.wms.bean.enu.RequestStatus;
import com.wms.bean.relations.mtm.FriendPair;
import com.wms.service.SWService;

@Service
public class SWServiceImpl implements SWService {
	
	@Autowired
	private FriendPairRepository friendPairRepository;
	
	@Autowired
	private ForecastInstockRepository forecastInstockRepository;
	
	@Autowired
	private ItemInfoRepository itemInfoRepository;
	
	@Autowired
	private BatchPackageRepository batchPackageRepository;

	@Override
	public void forecastReg(SellerCompany company, ForecastRequest forecastRequest) {

		ForecastInstock forcast = new ForecastInstock();
		
		FriendPair friendPair = friendPairRepository.findBySellerOpenidAndWarehouseOpenid(company.getOpenid(), forecastRequest.getWarehouseId());
		
		if(friendPair == null)
			throw new RuntimeException("Error partnership");
		
		List<BatchPackage> batches = new ArrayList<>();
		for(BatchItemRequest br : forecastRequest.getBatches()) {
			BatchPackage batch = new BatchPackage();
			batch.setHeight(br.getHeight())
				.setWeight(br.getWeight())
				.setWidth(br.getWidth())
				.setWeight_unit(br.getWeight_unit())
				.setSize_unit(br.getSize_unit());
			for(BatchItemRequest.simpleBatchItem sbi : br.getBatches()) {
				ItemInfo lookup = itemInfoRepository.findOne(sbi.getItemId());
				ItemInfo iteminfo = new ItemInfo(lookup);
				iteminfo.setCount(sbi.getCount());
				itemInfoRepository.save(iteminfo);
				batch.putItem(iteminfo);
			}
			
			batchPackageRepository.save(batch);
			batches.add(batch);
			
		}
		
		forcast.setSeller(friendPair.getSeller())
				.setWarehouse(friendPair.getWarehouse())
				.setTrackingNum(forecastRequest.getTrackingNum())
				.setCarrier(forecastRequest.getCarrier())
				.setBatches(batches)
				.setSellerAccepted(true)
				.setWarehouseAccepted(false)
				.setStatus(RequestStatus.PENDING);
		
		forecastInstockRepository.save(forcast);
		
		
	}

}
