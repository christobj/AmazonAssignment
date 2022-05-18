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
    private static final Map<LoggerEnums, Consumer<String>> logMap = new EnumMap<>(LoggerEnums.class);

    private static final Consumer<String> extentPass = message -> ExtentListener.extentMethod.get().pass(message);
    private static final Consumer<String> extentFail = message -> ExtentListener.extentMethod.get().fail(message);
    private static final Consumer<String> extentInfo = message -> ExtentListener.extentMethod.get().info(message);
    private static final Consumer<String> extentWarn = message -> ExtentListener.extentMethod.get().warning(message);
    private static final Consumer<String> loggerInfo = logger::info;
    private static final Consumer<String> loggerWarn = logger::warn;
    private static final Consumer<String> loggerError = logger::error;
    private static final Consumer<String> loggerDebug = logger::debug;
    private static final Consumer<String> loggerTrace = logger::trace;
    private static final Consumer<String> extentLoggerPass = extentPass.andThen(loggerInfo);
    private static final Consumer<String> extentLoggerFail = extentFail.andThen(loggerError);
    private static final Consumer<String> extentLoggerWarn = extentWarn.andThen(loggerWarn);
    private static final Consumer<String> extentLoggerInfo = extentInfo.andThen(loggerInfo);

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
