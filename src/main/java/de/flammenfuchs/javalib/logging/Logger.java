package de.flammenfuchs.javalib.logging;

import java.util.Date;
import java.util.logging.ConsoleHandler;
import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;

public class Logger {

    public static final String DEFAULT_LOGGING_FORMAT = "[%1$tT] [%2$s/%3$s] %4$s %n";


    private final LogLevel level;

    private final java.util.logging.Logger utilLogger;

    public Logger(String loggerName, LogLevel level, String formatString) {
        this.level = level;
        this.utilLogger = java.util.logging.Logger.getLogger(loggerName);
        init(formatString);
    }

    public Logger() {
        this.level = LogLevel.BASIC;
        this.utilLogger = java.util.logging.Logger.getLogger(Thread.currentThread().getName());
        init(DEFAULT_LOGGING_FORMAT);
    }

    public Logger(LogLevel level)  {
        this.level = level;
        this.utilLogger = java.util.logging.Logger.getLogger(Thread.currentThread().getName());
        init(DEFAULT_LOGGING_FORMAT);
    }

    public Logger(String name)  {
        this.level = LogLevel.BASIC;
        this.utilLogger = java.util.logging.Logger.getLogger(name);
        init(DEFAULT_LOGGING_FORMAT);
    }

    private void init(String formatString) {
        this.utilLogger.setUseParentHandlers(false);
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new SimpleFormatter() {
            @Override
            public String format(LogRecord record) {
                return String.format(formatString, new Date(), record.getLoggerName(), record.getLevel(), record.getMessage());
            }
        });
        this.utilLogger.addHandler(consoleHandler);

    }


    public void info(LogLevel required, String msg) {
        if (level.level() >= required.level()) {
            utilLogger.info(msg);
        }
    }

    public void info(String msg) {
        info(LogLevel.BASIC, msg);
    }

    public void warn(LogLevel required, String msg) {
        if (level.level() >= required.level()) {
            utilLogger.warning(msg);
        }
    }

    public void warn(String msg) {
        warn(LogLevel.BASIC, msg);
    }

    public void err(String msg) {
        utilLogger.severe(msg);
    }
}
