package by.bsu.textcomposit.exception;

/**
 * Created by note on 26.02.2015.
 */
public class TechnikException extends Exception {
    public TechnikException() {
        super();
    }

    public TechnikException(String message) {
        super(message);
    }

    public TechnikException(String message, Throwable cause) {
        super(message, cause);
    }

    public TechnikException(Throwable cause) {
        super(cause);
    }

    protected TechnikException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
