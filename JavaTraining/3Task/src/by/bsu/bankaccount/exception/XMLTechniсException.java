package by.bsu.bankaccount.exception;

/**
 * Created by note on 10.03.2015.
 */
public class XMLTechniсException extends Exception {
    public XMLTechniсException() {
    }

    public XMLTechniсException(String message) {
        super(message);
    }

    public XMLTechniсException(String message, Throwable cause) {
        super(message, cause);
    }

    public XMLTechniсException(Throwable cause) {
        super(cause);
    }

    public XMLTechniсException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
