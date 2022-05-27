package com.assignment.driver.factory;

import com.assignment.driver.entity.DriverData;
import com.assignment.driver.factory.local.LocalDriverFactory;
import com.assignment.driver.factory.remote.RemoteDriverFactory;
import com.assignment.enums.BrowserEnum;
import com.assignment.enums.ExecutionMode;
import org.openqa.selenium.WebDriver;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Function;

public final class DriverFactory {

    private DriverFactory() {}

    private static final Map<ExecutionMode, Function<BrowserEnum, WebDriver>> DRIVER_FACTORY_MAP = new EnumMap<>(ExecutionMode.class);

    static {
        DRIVER_FACTORY_MAP.put(ExecutionMode.LOCAL, LocalDriverFactory::getDriver);
        DRIVER_FACTORY_MAP.put(ExecutionMode.REMOTE, RemoteDriverFactory::getDriver);
    }

    public static WebDriver getDriver(DriverData driverData) {
        return DRIVER_FACTORY_MAP.get(driverData.getExecutionMode()).apply(driverData.getBrowserEnum());
    }
}
