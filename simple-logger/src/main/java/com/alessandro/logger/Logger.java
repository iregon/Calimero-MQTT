package com.alessandro.logger;

import com.alessandro.logger.interfaces.ILoggerHandler;

import java.util.ArrayList;

public class Logger {

    private static ArrayList<ILoggerHandler> loggers = new ArrayList<>();

    public Logger() {
    }

    /**
     * Add a logger to logger's list
     * @param logger New logger
     */
    public static void addLogger(ILoggerHandler logger) {
        loggers.add(logger);
    }

    /**
     * Call info method of all loggers
     * @param s Message to send to all loggers
     */
    public static void info(String s) {
        loggers.forEach(logger -> logger.info(s));
    }

    /**
     * Get the numbers of loggers
     * @return numbers of loggers
     */
    public static int getLoggersNum() {
        return loggers.size();
    }

    /**
     * Delete all loggers
     */
    public static void clearAllLoggers() {
        loggers.clear();
    }
}
