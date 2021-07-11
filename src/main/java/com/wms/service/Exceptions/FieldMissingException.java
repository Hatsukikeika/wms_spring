package com.wms.service.Exceptions;

public class FieldMissingException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public FieldMissingException() {
		super("Request field is missing");
	}

	public FieldMissingException(String field) {
		super("Request field {" + field + "} is missing");
	}

	
}
