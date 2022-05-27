package com.assignment.driver.factory.local;

import com.assignment.driver.manager.local.ChromeManager;
import com.assignment.driver.manager.local.EdgeManager;
import com.assignment.enums.BrowserEnum;
import org.openqa.selenium.WebDriver;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

public final class LocalDriverFactory {

    private LocalDriverFactory() {}

    private static final Map<BrowserEnum, Supplier<WebDriver>> LOCAL_DRIVER_MAP = new EnumMap<>(BrowserEnum.class);

    static {
        LOCAL_DRIVER_MAP.put(BrowserEnum.CHROME, ChromeManager::getDriver);
        LOCAL_DRIVER_MAP.put(BrowserEnum.EDGE, EdgeManager::getDriver);
    }

    public static WebDriver getDriver(BrowserEnum browserEnum) {
        return LOCAL_DRIVER_MAP.get(browserEnum).get();
    }
}
