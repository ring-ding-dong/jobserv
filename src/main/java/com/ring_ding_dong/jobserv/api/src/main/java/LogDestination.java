/**
 * Specifies the possible destinations for log output.
 * This enum is used to configure where log messages should be sent.
 *
 * @author chimaek
 * @version 1.0
 * @since 2024-10-08
 */
public enum LogDestination {
    /** Log messages are output to the console. */
    CONSOLE,
    /** Log messages are written to a file. */
    FILE,
    /** Log messages are stored in a database. */
    DATABASE
}