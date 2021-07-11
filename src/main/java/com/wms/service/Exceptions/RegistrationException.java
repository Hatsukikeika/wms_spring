package com.wms.service.Exceptions;

public class RegistrationException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public RegistrationException() {
		super();
	}
	
	public RegistrationException(String message) {
		super(message);
	}

}
