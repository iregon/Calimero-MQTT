package com.alessandro.logger;

import com.alessandro.logger.interfaces.ILogger;
import com.alessandro.logger.interfaces.ILoggerHandler;

import java.util.ArrayList;

public class Logger implements ILogger {

    private ArrayList<ILoggerHandler> loggers = new ArrayList<>();
    private static Logger handler = new Logger();

    public static Logger getInstance() {
        return handler;
    }

    public Logger() {
    }

    /**
     * Add a logger to logger's list
     * @param logger New logger
     */
    public void addLogger(ILoggerHandler logger) {
        loggers.add(logger);
    }

    /**
     * Call info method of all loggers
     * @param s Message to send to all loggers
     */
    public void info(String s) {
        loggers.forEach(logger -> logger.info(s));
    }

    /**
     * Get the numbers of loggers
     * @return numbers of loggers
     */
    public int getLoggersNum() {
        return loggers.size();
    }

    public void clearAllLoggers() {
        loggers.clear();
    }
}
