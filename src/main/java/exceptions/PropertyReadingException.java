package exceptions;

public class PropertyReadingException extends RuntimeException {

    public PropertyReadingException(String message) {
        super(message);
    }

    public PropertyReadingException(String message, Throwable cause) {
        super(message, cause);
    }
}
