package com.z.exception;

public class ColumnTitleNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5862245167521783358L;

	public ColumnTitleNotFoundException() {
		super();
	}

	public ColumnTitleNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ColumnTitleNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ColumnTitleNotFoundException(String message) {
		super(message);
	}

	public ColumnTitleNotFoundException(Throwable cause) {
		super(cause);
	}
}
