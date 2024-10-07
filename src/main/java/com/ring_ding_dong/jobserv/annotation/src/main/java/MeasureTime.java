import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation used to measure and log the execution time of a method. When applied to a method, it
 * will trigger the measurement of the method's execution time and log the results based on the
 * specified parameters.
 *
 * <p>Usage example:
 * <pre>
 * {@code
 * @MeasureTime(value = "importantOperation", logToFile = true, threshold = 100)
 * public void importantOperation() {
 *     // Method implementation
 * }
 * }
 * </pre>
 *
 * @author chimaek
 * @version 1.0
 * @since 2024-09-30
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MeasureTime {

    /**
     * Specifies a custom name for the measured operation. If not provided, the method name will be
     * used.
     *
     * @return the custom name for the measured operation
     */
    String value() default "";

    /**
     * Determines whether the execution time should be logged to the console.
     *
     * @return true if logging to console is enabled, false otherwise
     */
    boolean logToConsole() default true;

    /**
     * Determines whether the execution time should be logged to a file.
     *
     * @return true if logging to file is enabled, false otherwise
     */
    boolean logToFile() default false;

    /**
     * Specifies the time unit to be used for reporting the execution time. Possible values are
     * "NANOSECONDS", "MICROSECONDS", "MILLISECONDS", "SECONDS".
     *
     * @return the time unit as a string
     */
    String timeUnit() default "MILLISECONDS";

    /**
     * Specifies the minimum execution time (in the specified time unit) required for the
     * measurement to be logged. Executions faster than this threshold will not be logged.
     *
     * @return the threshold value
     */
    double threshold() default 0.0;

    /**
     * Specifies a custom logger name to be used for logging the execution time. This can be used to
     * route logs to specific loggers in the logging framework.
     *
     * @return the name of the logger to use
     */
    String loggerName() default "";

    /**
     * Determines whether to include method parameters in the log output. Note: Enable this with
     * caution as it may log sensitive information.
     *
     * @return true if method parameters should be included in logs, false otherwise
     */
    boolean includeParameters() default false;

    /**
     * Specifies tags to be included with the log entry. These can be used for filtering or
     * categorizing log entries.
     *
     * @return an array of tags
     */
    String[] tags() default {};

    /**
     * Determines whether to track and log memory usage before and after method execution.
     *
     * @return true if memory usage should be tracked, false otherwise
     */
    boolean trackMemoryUsage() default false;

    /**
     * Specifies the maximum number of times this method's execution should be logged. After this
     * limit is reached, further executions will not be logged. A value of 0 means no limit.
     *
     * @return the maximum number of times to log this method's execution
     */
    int maxLogCount() default 0;

    /**
     * Determines whether to log stack trace for executions exceeding the threshold.
     *
     * @return true if stack trace should be logged for slow executions, false otherwise
     */
    boolean logStackTraceOnThresholdExceeded() default false;
}