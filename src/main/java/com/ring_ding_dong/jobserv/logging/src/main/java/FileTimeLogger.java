import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * A TimeLogger implementation that logs messages to a file.
 */
public class FileTimeLogger implements TimeLogger {

    private final String fileName;
    private BufferedWriter writer;

    /**
     * Constructs a FileTimeLogger with the specified file name.
     *
     * @param fileName the name of the file to log to
     * @throws LoggingException if the file cannot be opened for writing
     */
    public FileTimeLogger(String fileName) throws LoggingException {
        this.fileName = fileName;
        try {
            this.writer = new BufferedWriter(new FileWriter(fileName, true));
        } catch (IOException e) {
            throw new LoggingException("Failed to open log file: " + fileName, e);
        }
    }

    /**
     * Logs a message to the file.
     *
     * @param message the message to be logged
     * @throws LoggingException if there's an error writing to the file
     */
    @Override
    public void log(String message) throws LoggingException {
        try {
            writer.write(message);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            throw new LoggingException("Failed to write to log file: " + fileName, e);
        }
    }

    /**
     * Closes the file writer.
     *
     * @throws LoggingException if there's an error closing the file
     */
    @Override
    public void close() throws LoggingException {
        try {
            if (writer != null) {
                writer.close();
            }
        } catch (IOException e) {
            throw new LoggingException("Failed to close log file: " + fileName, e);
        }
    }
}