package com.alessandro.logger;

import com.alessandro.logger.interfaces.ILogger;

import java.util.ArrayList;

public class LoggerHandler {

    private ArrayList<ILogger> loggers = new ArrayList<ILogger>();
    private static LoggerHandler handler = new LoggerHandler();

    public static LoggerHandler getInstance() {
        return handler;
    }

    public LoggerHandler() {
    }

    /**
     * Add a logger to logger's list
     * @param logger New logger
     */
    public void addLogger(ILogger logger) {
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
