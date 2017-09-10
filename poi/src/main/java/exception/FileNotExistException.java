package exception;

public class FileNotExistException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5862245167521783358L;

	public FileNotExistException() {
		super();
	}

	public FileNotExistException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public FileNotExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public FileNotExistException(String message) {
		super(message);
	}

	public FileNotExistException(Throwable cause) {
		super(cause);
	}
}
