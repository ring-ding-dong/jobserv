
/**
 * A TimeLogger implementation that logs messages to the console.
 */
public class ConsoleTimeLogger implements TimeLogger {

    /**
     * Logs a message to the console.
     *
     * @param message the message to be logged
     */
    @Override
    public void log(String message) {
        System.out.println(message);
    }

    /**
     * No-op method for ConsoleTimeLogger as it doesn't require any cleanup.
     */
    @Override
    public void close() {
        // No resources to close for console logger
    }
}