package com.dcode.spring.error.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonLogger {

    private static final Logger logger = LoggerFactory.getLogger(CommonLogger.class);

    private CommonLogger() {
    }

    public static void info(String message) {
        logger.info(message);
    }

    public static void error(String message) {
        logger.error(message);
    }

}
