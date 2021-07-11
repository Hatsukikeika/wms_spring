package com.wms.service.Exceptions;

public class BadAuthParamException extends RuntimeException {

	public BadAuthParamException(String string) {
		super(string);
	}

	private static final long serialVersionUID = 1L;

}
