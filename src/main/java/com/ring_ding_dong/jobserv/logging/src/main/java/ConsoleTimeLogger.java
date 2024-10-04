import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ConsoleTimeLogger implements TimeLogger {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(
        "yyyy-MM-dd HH:mm:ss.SSS");
    private final ConcurrentLinkedQueue<String> messageQueue = new ConcurrentLinkedQueue<>();
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    public ConsoleTimeLogger() {
        scheduler.scheduleAtFixedRate(this::processQueue, 0, 100, TimeUnit.MILLISECONDS);
    }

    @Override
    public void log(String message) {
        String timestamp = LocalDateTime.now().format(DATE_FORMATTER);
        String threadName = Thread.currentThread().getName();
        messageQueue.offer(String.format("[%s] [%s] %s", timestamp, threadName, message));
    }

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
        String message;
        while ((message = messageQueue.poll()) != null) {
            System.out.println(message);
        }
    }

    @Override
    public void close() {
        try {
            scheduler.shutdown();
            if (!scheduler.awaitTermination(5, TimeUnit.SECONDS)) {
                scheduler.shutdownNow();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}