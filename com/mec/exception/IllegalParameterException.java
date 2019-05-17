package com.mec.exception;

public class IllegalParameterException extends Exception {
	private static final long serialVersionUID = -3158766092082790192L;

	public IllegalParameterException() {
	}

	public IllegalParameterException(String arg0) {
		super(arg0);
	}

	public IllegalParameterException(Throwable arg0) {
		super(arg0);
	}

	public IllegalParameterException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public IllegalParameterException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}
