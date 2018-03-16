package app.example.annotation;

public class DefaultException extends Exception {

	private static final long serialVersionUID = -2469452646341093322L;

	public DefaultException() {
	}

	public DefaultException(String message) {
		super(message);
	}

	public DefaultException(Throwable cause) {
		super(cause);
	}

	public DefaultException(String message, Throwable cause) {
		super(message, cause);
	}

	public DefaultException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
