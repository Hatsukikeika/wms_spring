package com.wms.service.Exceptions;

public class DataNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DataNotFoundException() {
		super();
	}

	public DataNotFoundException(String key, String keyType, String dataType) {
		super(dataType + " with " + keyType + ": " + key + " does not exist");
	}
	
}
