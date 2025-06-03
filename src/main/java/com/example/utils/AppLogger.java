package com.example.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppLogger {
    private final Logger logger;

    private AppLogger(Class<?> tclass){
        this.logger = LoggerFactory.getLogger(tclass);
    }

    public static AppLogger getLogger(Class<?> tclass) {
        return new AppLogger(tclass);
    }

    public String wrap(String message){
        return String.format("%s |", message);
    }
    public String wrap(String className, String message, String reqID){
        return String.format("%s | %s| %s",className, reqID, message);
    }

    public void info(String message) {
        logger.info(wrap(message));
    }
    public void info(String className, String message, String reqID){
        logger.info(wrap(className, message, reqID));
    }

    public void debug(String className, String message, String reqID){
        logger.info(wrap(className, message, reqID));
    }

    public void error(String className, String message, String reqID){
        logger.info(wrap(className, message, reqID));
    }

    public void error(String className, String message, String reqID, Throwable e){
        logger.info(wrap(className, message, reqID), e);
    }

}
