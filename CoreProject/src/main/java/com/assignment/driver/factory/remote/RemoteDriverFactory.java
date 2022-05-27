package com.assignment.driver.factory.remote;

import com.assignment.driver.manager.remote.SeleniumGridChromeManager;
import com.assignment.driver.manager.remote.SeleniumGridEdgeManager;
import com.assignment.enums.BrowserEnum;
import org.openqa.selenium.WebDriver;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

public final class RemoteDriverFactory {

    private RemoteDriverFactory() {
    }

    private static final Map<BrowserEnum, Supplier<WebDriver>> REMOTE_DRIVER_MAP = new EnumMap<>(BrowserEnum.class);

    static {
        REMOTE_DRIVER_MAP.put(BrowserEnum.CHROME, SeleniumGridChromeManager::getDriver);
        REMOTE_DRIVER_MAP.put(BrowserEnum.EDGE, SeleniumGridEdgeManager::getDriver);
    }

    public static WebDriver getDriver(BrowserEnum browserEnum) {
        return REMOTE_DRIVER_MAP.get(browserEnum).get();
    }
}
