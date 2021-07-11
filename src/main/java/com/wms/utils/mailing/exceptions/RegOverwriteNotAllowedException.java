package com.wms.utils.mailing.exceptions;

public class RegOverwriteNotAllowedException extends Exception {

	private static final long serialVersionUID = 1L;

	public RegOverwriteNotAllowedException() {
		super();
	}

	public RegOverwriteNotAllowedException(String reg_catagory) {
		super(reg_catagory + " is already in use. To reg a new session for " + reg_catagory + ", unreg it first.");
	}

}
