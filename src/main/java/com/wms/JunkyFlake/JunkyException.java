package com.wms.JunkyFlake;

public class JunkyException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public JunkyException() {
		super("Generation is terminated");
	}

	public JunkyException(String message) {
		super("Generation is terminated by: " + message);

	}
}
