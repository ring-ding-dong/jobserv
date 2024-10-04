/**
 * Interface for logging time measurement messages. Implementations of this interface provide
 * different ways to log messages, such as console output or file logging.
 */
public interface TimeLogger {

    /**
     * Logs a message.
     *
     * @param message the message to be logged
     * @throws LoggingException if there's an error during logging
     */
    void log(String message) throws LoggingException;

    void logExecutionTime(String methodName, long duration, String timeUnit, double threshold);

    /**
     * Closes any resources associated with the logger. This method should be called when the logger
     * is no longer needed.
     *
     * @throws LoggingException if there's an error during closing
     */
    void close() throws LoggingException;
}






