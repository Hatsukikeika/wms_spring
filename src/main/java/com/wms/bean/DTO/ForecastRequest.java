package com.wms.bean.DTO;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.wms.bean.BatchPackage;

public class ForecastRequest {

	@NotNull
	private Long warehouseId;
	
	@NotBlank
	private String carrier;
	
	private String trackingNum;
	
	@NotEmpty(message = "Do not create empty forecast request.")
	private List<BatchItemRequest> batches;
	
	public Long getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(Long warehouseId) {
		this.warehouseId = warehouseId;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public String getTrackingNum() {
		return trackingNum;
	}

	public void setTrackingNum(String trackingNum) {
		this.trackingNum = trackingNum;
	}

	public List<BatchItemRequest> getBatches() {
		return batches;
	}

	public void setBatches(List<BatchItemRequest> batches) {
		this.batches = batches;
	}

	public class BatchItemRequest{
		
		@NotNull
		@Min(0)
		private Double length;
		
		@NotNull
		@Min(0)
		private Double width;
		
		@NotNull
		@Min(0)
		private Double height;
		
		@NotNull
		@Min(0)
		private Double weight;
		
		@NotBlank
		private String weight_unit;
		
		@NotBlank
		private String size_unit;
		
		private String unit = "--";
		
		@NotEmpty(message = "Do not create empty forecast batch.")
		private List<simpleBatchItem> batches;

		public Double getLength() {
			return length;
		}

		public void setLength(Double length) {
			this.length = length;
		}

		public Double getWidth() {
			return width;
		}

		public void setWidth(Double width) {
			this.width = width;
		}

		public Double getHeight() {
			return height;
		}

		public void setHeight(Double height) {
			this.height = height;
		}

		public Double getWeight() {
			return weight;
		}

		public void setWeight(Double weight) {
			this.weight = weight;
		}

		public String getWeight_unit() {
			return weight_unit;
		}

		public void setWeight_unit(String weight_unit) {
			this.weight_unit = weight_unit;
		}

		public String getSize_unit() {
			return size_unit;
		}

		public void setSize_unit(String size_unit) {
			this.size_unit = size_unit;
		}

		public String getUnit() {
			return unit;
		}

		public void setUnit(String unit) {
			this.unit = unit;
		}

		public List<simpleBatchItem> getBatches() {
			return batches;
		}

		public void setBatches(List<simpleBatchItem> batches) {
			this.batches = batches;
		}

		public class simpleBatchItem{
			@NotNull
			private Long itemId;
			
			@NotNull
			@Min(0)
			@Max(999999)
			private Integer count;

			public Long getItemId() {
				return itemId;
			}

			public void setItemId(Long itemId) {
				this.itemId = itemId;
			}

			public Integer getCount() {
				return count;
			}

			public void setCount(Integer count) {
				this.count = count;
			}
		}
	}
	
}
