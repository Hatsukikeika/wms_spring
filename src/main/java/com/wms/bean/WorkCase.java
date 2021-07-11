package com.wms.bean;

import java.io.Serializable;

import com.wms.bean.enu.RequestStatus;

public class WorkCase extends HasIdentity implements Serializable {
	
	private String title;
	
	private String descrip;
	
	private Group from;
	
	private Group to;
	
	private RequestStatus requestStatus;

	public WorkCase() {
		super();
		this.requestStatus = RequestStatus.PENDING;
	}

	public String getDescrip() {
		return descrip;
	}

	public WorkCase setDescrip(String descrip) {
		this.descrip = descrip;
		return this;
	}

	public Group getFrom() {
		return from;
	}

	public WorkCase setFrom(Group from) {
		this.from = from;
		return this;
	}

	public Group getTo() {
		return to;
	}

	public WorkCase setTo(Group to) {
		this.to = to;
		return this;
	}

	public RequestStatus getRequestStatus() {
		return requestStatus;
	}

	public WorkCase setRequestStatus(RequestStatus requestStatus) {
		this.requestStatus = requestStatus;
		return this;
	}

	public String getTitle() {
		return title;
	}

	public WorkCase setTitle(String title) {
		this.title = title;
		return this;
	}
	
	
	
}
