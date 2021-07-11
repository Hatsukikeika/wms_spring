package com.wms.utils.mailing.exceptions;

public class RegInfoNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public RegInfoNotFoundException() {
		super();
	}

	public RegInfoNotFoundException(String reg_catagory) {
		super(reg_catagory + " cannot be found in regMap, you must reg a proper session to handle this mailcontent" );
	}


	
}
