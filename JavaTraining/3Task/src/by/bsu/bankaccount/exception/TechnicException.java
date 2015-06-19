package by.bsu.bankaccount.exception;

/**
 * Created by note on 19.02.2015.
 */
public class TechnicException extends Exception {
    public TechnicException() {
        super();
    }

    public TechnicException(String message) {
        super(message);
    }

    public TechnicException(String message, Throwable cause) {
        super(message, cause);
    }

    public TechnicException(Throwable cause) {
        super(cause);
    }

    protected TechnicException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
