
package com.ring_ding_dong.jobserv.core;

import com.ring_ding_dong.jobserv.annotation.MeasureTime;
import com.ring_ding_dong.jobserv.api.TimeLogger;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;


/**
 * Processes methods annotated with {@link MeasureTime}, measuring their execution time and logging
 * the results according to the annotation's parameters.
 * <p>
 * This class uses caching to improve performance for repeated method invocations.
 *
 * @author chimaek
 * @version 1.0
 * @since 2024-10-08
 */
public class MeasureTimeProcessor {

    private final TimeLogger logger;
    private final ConcurrentHashMap<Method, MeasureTime> annotationCache;

    /**
     * Constructs a new MeasureTimeProcessor with the specified logger.
     *
     * @param logger the TimeLogger to use for logging execution times
     */
    public MeasureTimeProcessor(TimeLogger logger) {
        this.logger = logger;
        this.annotationCache = new ConcurrentHashMap<>();
    }

    /**
     * Processes a method invocation, measuring its execution time if annotated with
     * {@link MeasureTime}.
     *
     * @param method the method to be invoked
     * @param args   the arguments to be passed to the method
     * @param target the target object on which to invoke the method
     * @return the result of the method invocation
     * @throws Throwable if an exception occurs during method invocation
     */
    public Object process(Method method, Object[] args, Object target) throws Throwable {
        MeasureTime annotation = getAnnotation(method);
        if (annotation == null) {
            return method.invoke(target, args);
        }

        long startTime = System.nanoTime();
        try {
            return method.invoke(target, args);
        } finally {
            long duration = System.nanoTime() - startTime;
            logExecutionTime(method, annotation, duration);
        }
    }

    private MeasureTime getAnnotation(Method method) {
        return annotationCache.computeIfAbsent(method, m -> m.getAnnotation(MeasureTime.class));
    }

    /**
     * Logs the execution time of a method if it exceeds the specified threshold.
     *
     * @param method     the method whose execution time is being logged
     * @param annotation the MeasureTime annotation associated with the method
     * @param duration   the measured execution time in nanoseconds
     */
    private void logExecutionTime(Method method, MeasureTime annotation, long duration) {
        String methodName = annotation.value().isEmpty() ? method.getName() : annotation.value();
        TimeUnit timeUnit = annotation.timeUnit();
        double threshold = annotation.threshold();

        if (convertDuration(duration, timeUnit) >= threshold) {
            try {
                logger.logExecutionTime(methodName, duration, timeUnit, threshold);
            } catch (Exception e) {
                // Log the exception or handle it as per your error handling strategy
                System.err.println("Error logging execution time: " + e.getMessage());
            }
        }
    }

    /**
     * Converts the given duration from nanoseconds to the specified time unit.
     *
     * @param duration the duration in nanoseconds
     * @param unit     the target TimeUnit for conversion
     * @return the duration converted to the specified unit
     */
    private double convertDuration(long duration, TimeUnit unit) {
        return (double) duration / TimeUnit.NANOSECONDS.convert(1, unit);
    }
}