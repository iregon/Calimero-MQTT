package com.alessandro.logger.interfaces;

public interface ILogger {

    /**
     * Add a logger to logger's list
     * @param logger New logger
     */
    void addLogger(ILoggerHandler logger);

    /**
     * Call info method of all loggers
     * @param s Message to send to all loggers
     */
    void info(String s);

    /**
     * Get the numbers of loggers
     * @return numbers of loggers
     */
    int getLoggersNum();

    void clearAllLoggers();
}
