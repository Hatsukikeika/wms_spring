package com.wms.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.wms.DAO.ForecastInstockRepository;
import com.wms.DAO.ForecastItemRepository;
import com.wms.DAO.FriendPairRepository;
import com.wms.DAO.PackageRepository;
import com.wms.bean.BatchPackage;
import com.wms.bean.ForecastInstock;
import com.wms.bean.ForecastItem;
import com.wms.bean.InstockItem;
import com.wms.bean.Inventory;
import com.wms.bean.ItemInfo;
import com.wms.bean.Package;
import com.wms.bean.SellerCompany;
import com.wms.bean.WarehouseCompany;
import com.wms.bean.DTO.ForecastCheckIn;
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
	private ForecastItemRepository forecastItemRepository;
	
	@Autowired
	private PackageRepository packageRepository;
	
	@Autowired
	private InventoryRepository inventoryRepository;

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
				ItemInfo lookup = (ItemInfo) packageRepository.findOne(sbi.getItemId());
				ForecastItem forecastItem = new ForecastItem(lookup);
				forecastItem.setCount(sbi.getCount());
				forecastItemRepository.save(forecastItem);
				batch.putItem(forecastItem);
			}
			
			packageRepository.save(batch);
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

	@Override
	public void forecastCheckIn(ForecastInstock forecast, Long batchId, Long itemId, ForecastCheckIn checkIn) {
		ForecastItem fitem = (ForecastItem)forecast.lookupBatch(batchId).getPackageInside(itemId);

		fitem.warehouseCheckIn(checkIn);
		
		fitem.setWarehouseAccepted(true);
		
		if(fitem.anyMismatch())
			fitem.setSellerAccepted(false);
		else
			fitem.setSellerAccepted(true);
		
		packageRepository.save(fitem);
		
	}

	@Override
	public void forecastConfirm(ForecastInstock forecast, Long batchId, Long itemId, boolean isAccpeted) {
		ForecastItem fitem = (ForecastItem)forecast.lookupBatch(batchId).getPackageInside(itemId);
		
		if(isAccpeted) {
			fitem.setSellerAccepted(true);
		} 
		
		if (!fitem.getSellerAccepted()) {
			fitem.setWarehouseAccepted(false);
		}
		
		packageRepository.save(fitem);
	}

	@Override
	public void forecastAchieve(ForecastInstock forecast) {
		
		WarehouseCompany warehouse = forecast.getWarehouse();
		
		Inventory storage = warehouse.getInventory(forecast.getStorageId());
		
		for(BatchPackage bp: forecast.getBatches()) {
			for(Package fi : bp.getPackagesInside()) {
				
				ForecastItem forecastItem = (ForecastItem)fi;
				
				if(forecastItem.anyMismatch())
					throw new RuntimeException();
				
				InstockItem instockItem = (InstockItem) new InstockItem()
						.setCount(forecastItem.getCi_count())
						.setHeight(forecastItem.getCi_height())
						.setLength(forecastItem.getCi_length())
						.setWidth(forecastItem.getCi_width())
						.setWeight(forecastItem.getCi_weight());
				
				packageRepository.save(instockItem);
			}
		}
		
		inventoryRepository.save(storage);
	}

}
