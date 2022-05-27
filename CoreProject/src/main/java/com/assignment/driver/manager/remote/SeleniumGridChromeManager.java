package com.assignment.driver.manager.remote;

import com.assignment.config.factory.CoreConfigFactory;
import com.assignment.enums.BrowserEnum;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public final class SeleniumGridChromeManager {

    private SeleniumGridChromeManager() {}

    public static WebDriver getDriver() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setBrowserName(BrowserEnum.CHROME.getBrowser());
        return new RemoteWebDriver(CoreConfigFactory.getConfig().remoteURL(), desiredCapabilities);
    }
}
