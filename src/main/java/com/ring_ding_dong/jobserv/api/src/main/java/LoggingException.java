/**
 * Exception thrown when there's an error during logging operations.
 */
public class LoggingException extends Exception {
    public LoggingException(String message) {
        super(message);
    }

    public LoggingException(String message, Throwable cause) {
        super(message, cause);
    }
}