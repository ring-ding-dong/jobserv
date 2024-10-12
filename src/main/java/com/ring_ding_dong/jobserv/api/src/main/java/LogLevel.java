/**
 * Defines the severity levels for log messages.
 * These levels allow for filtering and categorizing log messages based on their importance.
 *
 * @author chimaek
 * @version 1.0
 * @since 2024-10-08
 */
public enum LogLevel {
    /** Finest level of detail, typically used for debugging. */
    TRACE,
    /** Detailed information, typically of interest only when diagnosing problems. */
    DEBUG,
    /** General information about the state of the system. */
    INFO,
    /** Information indicating a potential problem or situation that might need attention. */
    WARN,
    /** Error events that might still allow the application to continue running. */
    ERROR
}