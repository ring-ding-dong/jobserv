package com.ring_ding_dong.jobserv.api;

import java.util.concurrent.TimeUnit;

/**
 * Defines the contract for logging time measurement messages. Implementations of this interface
 * provide different ways to log messages, such as console output, file logging, or database
 * storage.
 *
 * @author chimaek
 * @version 1.1
 * @since 2024-10-08
 */
public interface TimeLogger {

    /**
     * Logs a message with the specified log level and optional formatting arguments.
     *
     * @param level   the severity level of the log message
     * @param message the message to be logged, which may contain formatting placeholders
     * @param args    optional arguments to be used for message formatting
     * @throws LoggingException if there's an error during logging
     */
    void log(LogLevel level, String message, Object... args) throws LoggingException;

    /**
     * Logs the execution time of a method.
     *
     * @param methodName the name of the method whose execution time is being logged
     * @param duration   the duration of the method execution
     * @param timeUnit   the time unit of the duration
     * @param threshold  the threshold duration above which the log should be considered
     *                   significant
     * @throws LoggingException if there's an error during logging
     */
    void logExecutionTime(String methodName, long duration, TimeUnit timeUnit, double threshold)
        throws LoggingException;

    /**
     * Closes any resources associated with the logger. This method should be called when the logger
     * is no longer needed.
     *
     * @throws LoggingException if there's an error during closing of resources
     */
    void close() throws LoggingException;
}