package com.wms.bean.component;

import java.util.Date;

public class TimeStamp extends Component {

	private Long displayAs;
	
	public TimeStamp() {
		super.componentType = null; // TimeStamp
	}

	public TimeStamp(Long parent) {
		super(parent);
		super.componentType = null;
	}

	public Long getDisplayAs() {
		return displayAs;
	}

	public TimeStamp setDisplayAs(Date date) {
		this.displayAs = date.getTime();
		return this;
	}
	
	
}
