package com.assignment.driver;

import com.assignment.driver.factory.BrowserFactory;
import com.assignment.enums.LoggerEnums;
import com.assignment.enums.PropertyEnum;
import com.assignment.filereaders.PropertyReader;
import org.openqa.selenium.WebDriver;

import java.time.Duration;
import java.util.Objects;

import static com.assignment.logging.CoreLog.log;

public final class DriverFactory {

    private DriverFactory() {
    }

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static synchronized void setDriver(String browser) {
        String browserInPropertyFile = PropertyReader.getProperty(PropertyEnum.BROWSER);
        if (browser.contentEquals("optional")) {
            if (browserInPropertyFile.isEmpty())
                browser = "chrome";
            else
                browser = browserInPropertyFile;
        }
        if (PropertyReader.getProperty(PropertyEnum.HUBURL).isEmpty())
            setLocalDriver(browser);
        else
            setRemoteDriver(browser);
        setTimeouts();
    }

    private static synchronized void setLocalDriver(String browser) {
        log(LoggerEnums.LOGGERINFO, "Setting up Local Drivers");
        driver.set(BrowserFactory.getLocalDriver(browser));
    }

    private static synchronized void setRemoteDriver(String browser) {
        log(LoggerEnums.LOGGERINFO, "Setting up Remote Drivers");
        driver.set(BrowserFactory.getRemoteDriver(browser));
    }

    private static synchronized void setTimeouts() {
        String implicitWaitTimeFromProperty = PropertyReader.getProperty(PropertyEnum.IMPLICITWAIT);
        String timeoutFromProperty = PropertyReader.getProperty(PropertyEnum.WEBDRIVERWAIT);
        int implicitWait = implicitWaitTimeFromProperty.isEmpty() ? 10 : Integer.parseInt(implicitWaitTimeFromProperty) / 1000;
        int timeout = timeoutFromProperty.isEmpty() ? 60 : Integer.parseInt(timeoutFromProperty) / 1000;
        log(LoggerEnums.LOGGERINFO, "Timeout set from Core properties");
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
        getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(timeout));
        getDriver().manage().timeouts().scriptTimeout(Duration.ofSeconds(timeout));
        getDriver().manage().window().maximize();
    }

    public static synchronized WebDriver getDriver() {
        return driver.get();
    }

    public static synchronized void closeBrowser() {
        if (Objects.nonNull(driver.get())) {
            log(LoggerEnums.EXTENTLOGGERINFO, "Quitting the browser");
            getDriver().quit();
            driver.remove();
        }
    }
}
