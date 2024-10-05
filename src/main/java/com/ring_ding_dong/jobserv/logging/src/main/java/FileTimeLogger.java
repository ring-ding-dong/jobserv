import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * FileTimeLogger is an implementation of TimeLogger that writes log messages to a file. It uses a
 * separate thread for writing to the file to minimize the impact on the application's performance.
 *
 * <p>This logger is thread-safe and can be used in multi-threaded environments.</p>
 *
 * <p>Usage example:</p>
 * <pre>
 * TimeLogger logger = new FileTimeLogger("application.log");
 * logger.log("Application started");
 * // ... use the logger ...
 * logger.close(); // Remember to close the logger when it's no longer needed
 * </pre>
 */
public class FileTimeLogger implements TimeLogger {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(
        "yyyy-MM-dd HH:mm:ss.SSS");
    private final String fileName;
    private final BlockingQueue<String> messageQueue;
    private final Thread writerThread;
    private volatile boolean running;

    /**
     * Constructs a new FileTimeLogger that writes to the specified file.
     *
     * @param fileName the name of the file to write logs to. If the file doesn't exist, it will be
     *                 created.
     */
    public FileTimeLogger(String fileName) {
        this.fileName = fileName;
        this.messageQueue = new LinkedBlockingQueue<>();
        this.running = true;
        this.writerThread = new Thread(this::processQueue);
        this.writerThread.start();
    }

    /**
     * Logs a message to the file. The message will be prefixed with a timestamp and the name of the
     * current thread.
     *
     * @param message the message to log
     */
    @Override
    public void log(String message) {
        String timestamp = LocalDateTime.now().format(DATE_FORMATTER);
        String threadName = Thread.currentThread().getName();
        messageQueue.offer(String.format("[%s] [%s] %s", timestamp, threadName, message));
    }

    /**
     * Logs the execution time of a method if it exceeds the specified threshold.
     *
     * @param methodName the name of the method
     * @param duration   the duration of the method execution in nanoseconds
     * @param timeUnit   the desired time unit for the output (e.g., "MILLISECONDS", "SECONDS")
     * @param threshold  the minimum duration (in the specified time unit) required for logging
     */
    @Override
    public void logExecutionTime(String methodName, long duration, String timeUnit,
        double threshold) {
        TimeUnit unit = TimeUnit.valueOf(timeUnit.toUpperCase());
        double convertedDuration = unit.convert(duration, TimeUnit.NANOSECONDS);

        if (convertedDuration >= threshold) {
            String message = String.format("%s executed in %.4f %s",
                methodName, convertedDuration, unit.name().toLowerCase());
            log(message);
        }
    }

    private void processQueue() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            while (running || !messageQueue.isEmpty()) {
                String message = messageQueue.poll(100, TimeUnit.MILLISECONDS);
                if (message != null) {
                    writer.write(message);
                    writer.newLine();
                    writer.flush();
                }
            }
        } catch (IOException | InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Error writing to log file: " + e.getMessage());
        }
    }

    /**
     * Closes the logger, ensuring all pending messages are written to the file. This method should
     * be called when the logger is no longer needed to ensure proper resource cleanup.
     *
     * <p>After calling this method, no more messages can be logged with this logger instance.</p>
     */
    @Override
    public void close() {
        running = false;
        try {
            writerThread.join(5000);  // Wait for up to 5 seconds for the writer thread to finish
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Interrupted while closing logger");
        }
        if (writerThread.isAlive()) {
            writerThread.interrupt();
        }
    }
}