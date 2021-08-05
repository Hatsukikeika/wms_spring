package com.wms.bean.DTO;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ForecastCheckIn {
 
	@NotNull
	@Min(0)
	private Double ci_weight;

	@NotNull
	@Min(0)
	private Double ci_height;

	@NotNull
	@Min(0)
	private Double ci_length;
	
	@NotNull
	@Min(0)
	private Double ci_width;
	
	@NotNull
	@Min(0)
	private Integer ci_count;

	public ForecastCheckIn() {
		super();
	}

	public Double getCi_weight() {
		return ci_weight;
	}

	public void setCi_weight(Double ci_weight) {
		this.ci_weight = ci_weight;
	}

	public Double getCi_height() {
		return ci_height;
	}

	public void setCi_height(Double ci_height) {
		this.ci_height = ci_height;
	}

	public Double getCi_length() {
		return ci_length;
	}

	public void setCi_length(Double ci_length) {
		this.ci_length = ci_length;
	}

	public Double getCi_width() {
		return ci_width;
	}

	public void setCi_width(Double ci_width) {
		this.ci_width = ci_width;
	}

	public Integer getCi_count() {
		return ci_count;
	}

	public void setCi_count(Integer ci_count) {
		this.ci_count = ci_count;
	}
	
}
