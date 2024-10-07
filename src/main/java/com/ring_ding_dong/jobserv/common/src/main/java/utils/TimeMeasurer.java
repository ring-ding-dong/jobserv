package utils;

/**
 * Utility class for measuring the execution time of tasks.
 * This class provides methods to measure the duration of a given task
 * and return the result in various time units.
 *
 * <p>Usage example:
 * <pre>
 * {@code
 * Runnable task = () -> {
 *     // Some time-consuming operation
 *     try {
 *         Thread.sleep(1000);
 *     } catch (InterruptedException e) {
 *         e.printStackTrace();
 *     }
 * };
 *
 * MeasurementResult result = TimeMeasurer.measure(task);
 * System.out.println("Task took " + result.getDurationMillis() + " milliseconds");
 * }
 * </pre>
 *
 * @author Chimaek
 * @version 1.0
 * @since 2024-10-08
 */
public class TimeMeasurer {

    /**
     * Measures the execution time of the given task.
     *
     * @param task the task to be measured
     * @return a MeasurementResult object containing the duration of the task
     */
    public static MeasurementResult measure(Runnable task) {
        long startTime = System.nanoTime();
        task.run();
        long endTime = System.nanoTime();
        return new MeasurementResult(endTime - startTime);
    }

    /**
     * Represents the result of a time measurement.
     * This class provides methods to retrieve the duration in various time units.
     */
    public static class MeasurementResult {

        private final long durationNanos;

        /**
         * Constructs a new MeasurementResult with the given duration in nanoseconds.
         *
         * @param durationNanos the duration of the measurement in nanoseconds
         */
        public MeasurementResult(long durationNanos) {
            this.durationNanos = durationNanos;
        }

        /**
         * Returns the duration in nanoseconds.
         *
         * @return the duration in nanoseconds
         */
        public long getDurationNanos() {
            return durationNanos;
        }

        /**
         * Returns the duration in milliseconds as a double.
         *
         * @return the duration in milliseconds
         */
        public double getDurationMillis() {
            return durationNanos / 1_000_000.0;
        }

        /**
         * Returns the duration in seconds as a double.
         *
         * @return the duration in seconds
         */
        public double getDurationSeconds() {
            return durationNanos / 1_000_000_000.0;
        }

        /**
         * Returns the duration in microseconds as a double.
         *
         * @return the duration in microseconds
         */
        public double getDurationMicros() {
            return durationNanos / 1_000.0;
        }

        /**
         * Returns the duration in seconds as an integer.
         *
         * @return the duration in seconds, rounded down to the nearest integer
         */
        public int getDurationSecondsInt() {
            return (int) (durationNanos / 1_000_000_000.0);
        }

        /**
         * Returns the duration in milliseconds as an integer.
         *
         * @return the duration in milliseconds, rounded down to the nearest integer
         */
        public int getDurationMillisInt() {
            return (int) (durationNanos / 1_000_000.0);
        }

        /**
         * Returns the duration in microseconds as an integer.
         *
         * @return the duration in microseconds, rounded down to the nearest integer
         */
        public int getDurationMicrosInt() {
            return (int) (durationNanos / 1_000.0);
        }

        /**
         * Returns the duration in nanoseconds as an integer.
         *
         * @return the duration in nanoseconds, truncated to an integer
         */
        public int getDurationNanosInt() {
            return (int) durationNanos;
        }

        /**
         * Returns the duration in hours.
         *
         * @return the duration in hours, rounded down to the nearest integer
         */
        public int getDurationHours() {
            return (int) (durationNanos / 3_600_000_000_000.0);
        }

        /**
         * Returns the duration in minutes.
         *
         * @return the duration in minutes, rounded down to the nearest integer
         */
        public int getDurationMinutes() {
            return (int) (durationNanos / 60_000_000_000.0);
        }

        /**
         * Returns the duration in days.
         *
         * @return the duration in days, rounded down to the nearest integer
         */
        public int getDurationDays() {
            return (int) (durationNanos / 86_400_000_000_000.0);
        }

        /**
         * Returns the duration in weeks.
         *
         * @return the duration in weeks, rounded down to the nearest integer
         */
        public int getDurationWeeks() {
            return (int) (durationNanos / 604_800_000_000_000.0);
        }

        /**
         * Returns the duration in months (assuming 30.44 days per month).
         *
         * @return the duration in months, rounded down to the nearest integer
         */
        public int getDurationMonths() {
            return (int) (durationNanos / 2_629_746_000_000_000.0);
        }

        /**
         * Returns the duration in years (assuming 365.24 days per year).
         *
         * @return the duration in years, rounded down to the nearest integer
         */
        public int getDurationYears() {
            return (int) (durationNanos / 31_556_952_000_000_000.0);
        }
    }
}