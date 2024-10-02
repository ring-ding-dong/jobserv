import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation used to measure and log the execution time of a method.
 * When applied to a method, it will trigger the measurement of the method's execution time
 * and log the results based on the specified parameters.
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
     * Specifies a custom name for the measured operation.
     * If not provided, the method name will be used.
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
     * Specifies the time unit to be used for reporting the execution time.
     * Possible values are "NANOSECONDS", "MICROSECONDS", "MILLISECONDS", "SECONDS".
     *
     * @return the time unit as a string
     */
    String timeUnit() default "MILLISECONDS";

    /**
     * Specifies the minimum execution time (in the specified time unit) required
     * for the measurement to be logged. Executions faster than this threshold
     * will not be logged.
     *
     * @return the threshold value
     */
    double threshold() default 0.0;
}