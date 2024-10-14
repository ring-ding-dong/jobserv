package com.ring_ding_dong.jobserv.api;

/**
 * Represents an exception that occurs during logging operations.
 * This exception is thrown when there's an error in logging functionality,
 * such as failure to write to a log file or database.
 *
 * @author chimaek
 * @version 1.0
 * @since 2024-10-08
 */
public class LoggingException extends Exception {

    /**
     * Constructs a new com.ring_ding_dong.jobserv.api.LoggingException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public LoggingException(String message) {
        super(message);
    }

    /**
     * Constructs a new com.ring_ding_dong.jobserv.api.LoggingException with the specified detail message and cause.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     * @param cause   the cause (which is saved for later retrieval by the getCause() method)
     */
    public LoggingException(String message, Throwable cause) {
        super(message, cause);
    }
}
