package com.assignment.logging;

import com.assignment.reporter.ExtentListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

public final class Log {

    private Log() {}

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());

    public static void info (String message) {
        if (ExtentListener.extentMethod.get() != null)
            ExtentListener.extentMethod.get().info(message);
        else
            logger.info(message);
    }

    public static void warn (String message) {
        if (ExtentListener.extentMethod.get() != null)
            ExtentListener.extentMethod.get().warning(message);
        else
            logger.warn(message);
    }
}
