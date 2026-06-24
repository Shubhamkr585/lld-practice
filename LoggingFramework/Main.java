
import java.time.LocalDateTime;
import java.util.*;

// * LOG LEVEL
enum LogLevel {

    DEBUG(1),
    INFO(2),
    WARN(3),
    ERROR(4);

    private final int priority;

    LogLevel(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
}
// * LOG MESSAGE
class LogMessage {

    private final String message;
    private final LogLevel level;
    private final LocalDateTime timestamp;

    public LogMessage(String message,LogLevel level) {
        this.message = message;
        this.level = level;
        this.timestamp = LocalDateTime.now();
    }
    public String getMessage() {
        return message;
    }
    public LogLevel getLevel() {
        return level;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
// * FORMATTER STRATEGY
interface Formatter {
    String format(LogMessage message);
}

// * SIMPLE FORMATTER

class SimpleFormatter implements Formatter {

    @Override
    public String format(LogMessage message) {
        return "[" +
                message.getLevel() +
                "] " +
                message.getMessage() +
                " (" +
                message.getTimestamp() +
                ")";
    }
}
// * JSON FORMATTER

class JsonFormatter implements Formatter {

    @Override
    public String format(LogMessage message) {
        return "{\n" +
                "  \"level\": \"" +
                message.getLevel() +
                "\",\n" +

                "  \"message\": \"" +
                message.getMessage() +
                "\",\n" +

                "  \"timestamp\": \"" +
                message.getTimestamp() +
                "\"\n" +
                "}";
    }
}
// * APPENDER STRATEGY
interface Appender {
    void append(String formattedLog);
}
// * CONSOLE APPENDER
class ConsoleAppender implements Appender {
    @Override
    public void append(String formattedLog) {
        System.out.println(formattedLog);
    }
}
// * FILE APPENDER

class FileAppender implements Appender {
    @Override
    public void append(String formattedLog) {
        System.out.println("[FILE] " + formattedLog);
    }
}
// * LOGGER

class Logger {
    private final LogLevel configuredLevel;
    private final Formatter formatter;
    private final List<Appender> appenders;

    public Logger(LogLevel configuredLevel, Formatter formatter,
                   List<Appender> appenders) {
        this.configuredLevel = configuredLevel;
        this.formatter = formatter;
        this.appenders = appenders;
    }

    public void debug(String message) {
        log(LogLevel.DEBUG, message);
    }
    public void info(String message) {
        log(LogLevel.INFO, message);
    }
    public void warn(String message) {
        log(LogLevel.WARN, message);
    }
    public void error(String message) {
        log(LogLevel.ERROR, message);
    }
    private void log(LogLevel level, String message) {
//         * LEVEL FILTERING
        if (level.getPriority() < configuredLevel.getPriority()){
            return;
        }
//         CREATE LOG EVENT
        LogMessage logMessage = new LogMessage(message,level);
//         * FORMAT

        String formattedLog = formatter.format(logMessage);
        /*
         * APPEND TO ALL
         * DESTINATIONS
         */
        for (Appender appender : appenders) {
            appender.append(formattedLog);
        }
    }
}



public class Main {
    public static void main(String[] args) {
        Formatter formatter = new JsonFormatter();
        List<Appender> appenders = new ArrayList<>();
        appenders.add(new ConsoleAppender());
        appenders.add(new FileAppender());
        Logger logger = new Logger(
                        LogLevel.INFO,
                        formatter,
                        appenders);

        logger.debug("Debug Message");
        logger.info("Application Started");
        logger.warn("Memory Usage High");
        logger.error("Database Down");
    }
}