package com.mec.exception;

public class ViewTypeNotExistException extends Exception {
	private static final long serialVersionUID = 3779630839738431987L;

	public ViewTypeNotExistException() {
	}

	public ViewTypeNotExistException(String arg0) {
		super(arg0);
	}

	public ViewTypeNotExistException(Throwable arg0) {
		super(arg0);
	}

	public ViewTypeNotExistException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public ViewTypeNotExistException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}
