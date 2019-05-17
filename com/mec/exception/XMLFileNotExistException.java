package com.mec.exception;

public class XMLFileNotExistException extends Exception {
	private static final long serialVersionUID = 4273209033547953676L;

	public XMLFileNotExistException() {
	}

	public XMLFileNotExistException(String arg0) {
		super(arg0);
	}

	public XMLFileNotExistException(Throwable arg0) {
		super(arg0);
	}

	public XMLFileNotExistException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public XMLFileNotExistException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}
