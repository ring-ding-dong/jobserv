package com.ring_ding_dong.jobserv.common.utils;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * Utility class for measuring the execution time of tasks. This class provides methods to measure
 * the duration of given tasks and return the results in various time units.
 *
 * <p>This class supports both automatic and manual time measurements,
 * as well as measurements for tasks that return results.</p>
 *
 * <p>Usage example:
 * <pre>
 * {@code
 * // Measure a simple Runnable task
 * Runnable task = () -> {
 *     // Some time-consuming operation
 *     try {
 *         Thread.sleep(1000);
 *     } catch (InterruptedException e) {
 *         Thread.currentThread().interrupt();
 *     }
 * };
 * MeasurementResult<Void> result = TimeMeasurer.measure(task);
 * System.out.println("Task took " + result.getDuration(TimeUnit.MILLISECONDS) + " milliseconds");
 *
 * // Measure a task that returns a result
 * Supplier<String> taskWithResult = () -> {
 *     // Some operation that returns a result
 *     return "Hello, World!";
 * };
 * MeasurementResult<String> resultWithValue = TimeMeasurer.measure(taskWithResult);
 * System.out.println("Task result: " + resultWithValue.getResult());
 * System.out.println("Task took " + resultWithValue.getDuration(TimeUnit.MILLISECONDS) + " milliseconds");
 *
 * // Manual measurement
 * TimeMeasurer.start();
 * // Perform some operations
 * long duration = TimeMeasurer.stop();
 * System.out.println("Operations took " + TimeUnit.MILLISECONDS.convert(duration, TimeUnit.NANOSECONDS) + " milliseconds");
 *
 * // Reusable measurement for repeated operations
 * ReusableMeasurement measurement = new ReusableMeasurement();
 * for (int i = 0; i < 5; i++) {
 *     TimeMeasurer.start();
 *     // Perform some operation
 *     long iterationDuration = TimeMeasurer.stop();
 *     measurement.addMeasurement(iterationDuration);
 * }
 * System.out.println("Average duration: " + measurement.getAverageDuration(TimeUnit.MILLISECONDS) + " milliseconds");
 * }
 * </pre>
 *
 * @author Chimaek
 * @version 1.2
 * @since 2024-10-08
 */
public class TimeMeasurer {

    private static final ThreadLocal<Long> START_TIME = new ThreadLocal<>();

    private TimeMeasurer() {
        // Private constructor to prevent instantiation
    }

    /**
     * Starts a manual time measurement. This method should be paired with a subsequent call to
     * {@link #stop()}.
     */
    public static void start() {
        START_TIME.set(System.nanoTime());
    }

    /**
     * Stops a manual time measurement and returns the elapsed time.
     *
     * @return the elapsed time in nanoseconds
     * @throws IllegalStateException if {@link #start()} was not called before this method
     */
    public static long stop() {
        Long startTime = START_TIME.get();
        if (startTime == null) {
            throw new IllegalStateException("Timer not started");
        }
        long duration = System.nanoTime() - startTime;
        START_TIME.remove();
        return duration;
    }

    /**
     * Measures the execution time of a given task that returns a result.
     *
     * @param task the task to be measured
     * @param <V>  the type of the result returned by the task
     * @return a MeasurementResult containing the duration and result of the task
     */
    public static <V> MeasurementResult<V> measure(Supplier<V> task) {
        start();
        V result = task.get();
        long duration = stop();
        return new MeasurementResult<>(duration, result);
    }

    /**
     * Measures the execution time of a given task that does not return a result.
     *
     * @param task the task to be measured
     * @return a MeasurementResult containing the duration of the task
     */
    public static MeasurementResult<Void> measure(Runnable task) {
        return measure((Supplier<Void>) () -> {
            task.run();
            return null;
        });
    }

    /**
     * Measures the execution time of a given task that may throw an exception.
     *
     * @param task the task to be measured
     * @param <V>  the type of the result returned by the task
     * @return a MeasurementResult containing the duration and result of the task
     * @throws Exception if the task throws an exception
     */
    public static <V> MeasurementResult<V> measure(Callable<V> task) throws Exception {
        start();
        V result = task.call();
        long duration = stop();
        return new MeasurementResult<>(duration, result);
    }

    /**
     * Represents the result of a time measurement. This class provides methods to retrieve the
     * duration in various time units and the result of the measured task (if any).
     *
     * @param <V> the type of the result returned by the measured task (if any)
     */
    public static class MeasurementResult<V> {

        private final long durationNanos;
        private final V result;

        /**
         * Constructs a new MeasurementResult with the given duration and result.
         *
         * @param durationNanos the duration of the measurement in nanoseconds
         * @param result        the result of the measured task
         */
        public MeasurementResult(long durationNanos, V result) {
            this.durationNanos = durationNanos;
            this.result = result;
        }

        /**
         * Returns the duration in the specified time unit.
         *
         * @param unit the time unit to convert the duration to
         * @return the duration in the specified time unit
         */
        public long getDuration(TimeUnit unit) {
            return unit.convert(durationNanos, TimeUnit.NANOSECONDS);
        }

        /**
         * Returns the duration as a double value in the specified time unit.
         *
         * @param unit the time unit to convert the duration to
         * @return the duration as a double value in the specified time unit
         */
        public double getDurationAsDouble(TimeUnit unit) {
            return (double) durationNanos / TimeUnit.NANOSECONDS.convert(1, unit);
        }

        /**
         * Returns the result of the measured task, if any.
         *
         * @return the result of the measured task, or null if no result was produced
         */
        public V getResult() {
            return result;
        }
    }

    /**
     * Provides functionality for reusable measurements, useful for repeated operations. This class
     * allows adding multiple measurements and calculating average and total durations.
     */
    public static class ReusableMeasurement {

        private long totalDuration = 0;
        private long count = 0;

        /**
         * Adds a new measurement to this ReusableMeasurement.
         *
         * @param durationNanos the duration to add, in nanoseconds
         */
        public void addMeasurement(long durationNanos) {
            totalDuration += durationNanos;
            count++;
        }

        /**
         * Calculates and returns the average duration of all measurements.
         *
         * @param unit the time unit for the returned average
         * @return the average duration in the specified time unit, or 0 if no measurements have
         * been added
         */
        public double getAverageDuration(TimeUnit unit) {
            return count > 0 ? (double) totalDuration / count / TimeUnit.NANOSECONDS.convert(1,
                unit) : 0;
        }

        /**
         * Returns the total duration of all measurements.
         *
         * @param unit the time unit for the returned total duration
         * @return the total duration in the specified time unit
         */
        public long getTotalDuration(TimeUnit unit) {
            return unit.convert(totalDuration, TimeUnit.NANOSECONDS);
        }

        /**
         * Returns the number of measurements added to this ReusableMeasurement.
         *
         * @return the count of measurements
         */
        public long getCount() {
            return count;
        }
    }
}