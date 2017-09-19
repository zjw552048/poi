package com.z.exception;

public class CellTypeIllegalException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5862245167521783358L;

	public CellTypeIllegalException() {
		super();
	}

	public CellTypeIllegalException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CellTypeIllegalException(String message, Throwable cause) {
		super(message, cause);
	}

	public CellTypeIllegalException(String message) {
		super(message);
	}

	public CellTypeIllegalException(Throwable cause) {
		super(cause);
	}
}
