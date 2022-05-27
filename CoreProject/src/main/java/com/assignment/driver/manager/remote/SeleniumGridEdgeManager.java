package com.assignment.driver.manager.remote;

import com.assignment.config.factory.CoreConfigFactory;
import com.assignment.enums.BrowserEnum;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public final class SeleniumGridEdgeManager {

    private SeleniumGridEdgeManager() {}

    public static WebDriver getDriver() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(BrowserEnum.EDGE.getBrowser());
        return new RemoteWebDriver(CoreConfigFactory.getConfig().remoteURL(), capabilities);
    }
}
