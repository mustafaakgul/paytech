package org.fintech.paytech.util;

import org.slf4j.Logger;
import org.slf4j.event.Level;

public class LoggerUtil {

    public static void logMessage(Logger log, Level level, String message, Object... args) {
        switch (level) {
            case INFO -> log.info(message, args);
            case WARN -> log.warn(message, args);
            case ERROR -> log.error(message, args);
            case DEBUG -> log.debug(message, args);
            case TRACE -> log.trace(message, args);
            default -> log.info(message, args);
        }
    }

    public static String trimToLength(String value, int maxLength) {
        if (value == null) return "";
        return value.length() > maxLength ? value.substring(0, maxLength) : value;
    }
}
