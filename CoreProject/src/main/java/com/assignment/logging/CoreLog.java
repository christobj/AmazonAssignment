package com.assignment.logging;

import com.assignment.enums.LoggerEnums;
import com.assignment.reporter.ExtentListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.Consumer;

import static com.assignment.enums.LoggerEnums.*;

public final class CoreLog {

    private CoreLog() {}

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());
    private static Map<LoggerEnums, Consumer<String>> logMap = new EnumMap<>(LoggerEnums.class);

    private static Consumer<String> extentPass = message -> ExtentListener.extentMethod.get().pass(message);
    private static Consumer<String> extentFail = message -> ExtentListener.extentMethod.get().fail(message);
    private static Consumer<String> extentInfo = message -> ExtentListener.extentMethod.get().info(message);
    private static Consumer<String> extentWarn = message -> ExtentListener.extentMethod.get().warning(message);
    private static Consumer<String> loggerInfo = message -> logger.info(message);
    private static Consumer<String> loggerWarn = message -> logger.warn(message);
    private static Consumer<String> loggerError = message -> logger.error(message);
    private static Consumer<String> loggerDebug = message -> logger.debug(message);
    private static Consumer<String> loggerTrace = message -> logger.trace(message);
    private static Consumer<String> extentLoggerPass = extentPass.andThen(loggerInfo);
    private static Consumer<String> extentLoggerFail = extentFail.andThen(loggerError);
    private static Consumer<String> extentLoggerWarn = extentWarn.andThen(loggerWarn);
    private static Consumer<String> extentLoggerInfo = extentInfo.andThen(loggerInfo);

    static {
        logMap.put(EXTENTPASS, extentPass);
        logMap.put(EXTENTFAIL, extentFail);
        logMap.put(EXTENTINFO, extentInfo);
        logMap.put(EXTENTWARN, extentWarn);
        logMap.put(LOGGERINFO, loggerInfo);
        logMap.put(LOGGERWARN, loggerWarn);
        logMap.put(LOGGERERROR, loggerError);
        logMap.put(LOGGERDEBUG, loggerDebug);
        logMap.put(LOGGERTRACE, loggerTrace);
        logMap.put(EXTENTLOGGERPASS, extentLoggerPass);
        logMap.put(EXTENTLOGGERFAIL, extentLoggerFail);
        logMap.put(EXTENTLOGGERWARN, extentLoggerWarn);
        logMap.put(EXTENTLOGGERINFO, extentLoggerInfo);
    }

    public static void log(LoggerEnums logType, String message) {
        logMap.get(logType).accept(message);
    }

}
