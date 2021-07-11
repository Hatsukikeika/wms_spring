package com.wms.model;

import com.wms.utils.mailing.MailContent;

public class AccMailContent extends MailContent {
	
	public static final String CATAGORY = "ACCOUNT_SERVICE_MAIL";
	
	public AccMailContent() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getCatagory() {
		// TODO Auto-generated method stub
		return CATAGORY;
	}

}
