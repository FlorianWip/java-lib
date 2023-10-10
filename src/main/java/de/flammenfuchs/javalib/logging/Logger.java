package de.flammenfuchs.javalib.logging;

import java.util.Date;
import java.util.logging.ConsoleHandler;
import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;

/**
 * Simple and easy to use Logger, which uses an {@link java.util.logging.Logger}
 */
public class Logger {

    public static final String DEFAULT_LOGGING_FORMAT = "[%1$tT] [%2$s/%3$s] %4$s %n";


    private final LogLevel level;

    private final java.util.logging.Logger utilLogger;

    /**
     * Create a new logger
     *
     * @param loggerName name of the logger
     * @param level the printing log level
     * @param formatString format string for the console
     * @param highlightNonInfo should warnings and errors be highlighted
     */
    public Logger(String loggerName, LogLevel level, String formatString, boolean highlightNonInfo) {
        this.level = level;
        this.utilLogger = java.util.logging.Logger.getLogger(loggerName);
        init(formatString, highlightNonInfo);
    }
    /**
     * Create a new logger
     */
    public Logger() {
        this.level = LogLevel.BASIC;
        this.utilLogger = java.util.logging.Logger.getLogger(Thread.currentThread().getName());
        init(DEFAULT_LOGGING_FORMAT, true);
    }
    /**
     * Create a new logger
     *
     * @param level the printing log level
     */
    public Logger(LogLevel level)  {
        this.level = level;
        this.utilLogger = java.util.logging.Logger.getLogger(Thread.currentThread().getName());
        init(DEFAULT_LOGGING_FORMAT, true);
    }

    /**
     * Create a new logger
     *
     * @param name name of the logger
     */
    public Logger(String name)  {
        this.level = LogLevel.BASIC;
        this.utilLogger = java.util.logging.Logger.getLogger(name);
        init(DEFAULT_LOGGING_FORMAT, true);
    }

    /**
     * Initialize the logger and register needed handlers
     *
     * @param formatString format string for the console
     * @param highlightNonInfo should warnings and errors be highlighted
     */
    private void init(String formatString, boolean highlightNonInfo) {
        this.utilLogger.setUseParentHandlers(false);
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new SimpleFormatter() {
            @Override
            public String format(LogRecord record) {
                String color;
                if (highlightNonInfo) {
                    color = switch (record.getLevel().getName().toLowerCase()) {
                        case "severe" -> "\u001B[31m";
                        case "warning" -> "\u001B[33m";
                        default -> "\u001B[0m";
                    };
                } else {
                    color = null;
                }
                String format = color != null ? color + formatString + "\u001B[0m" : formatString;
                return String.format(format, new Date(), record.getLoggerName(), record.getLevel(), record.getMessage());
            }
        });
        this.utilLogger.addHandler(consoleHandler);

    }


    /**
     * Log an info
     *
     * @param required required level to print
     * @param msg printing message
     */
    public void info(LogLevel required, String msg) {
        if (level.level() >= required.level()) {
            utilLogger.info(msg);
        }
    }
    /**
     * Log an info
     *
     * @param msg printing message
     */
    public void info(String msg) {
        info(LogLevel.BASIC, msg);
    }

    /**
     * Log a warn
     *
     * @param required required level to print
     * @param msg printing message
     */
    public void warn(LogLevel required, String msg) {
        if (level.level() >= required.level()) {
            utilLogger.warning(msg);
        }
    }

    /**
     * Log a warn
     *
     * @param msg printing message
     */
    public void warn(String msg) {
        warn(LogLevel.BASIC, msg);
    }
    /**
     * Log a warn
     *
     * @param msg printing message
     */
    public void err(String msg) {
        utilLogger.severe(msg);
    }
}
