package com.wms.service.Exceptions;

public class ObjectExpiredException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ObjectExpiredException() {
		super();
	}

	public ObjectExpiredException(String message) {
		super(message + " is expired.");
	}
	
	

}
