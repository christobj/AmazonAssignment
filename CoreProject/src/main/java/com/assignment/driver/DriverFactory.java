package com.assignment.driver;

import com.assignment.enums.BrowserEnum;
import com.assignment.enums.PropertyEnum;
import com.assignment.filereaders.PropertyReader;
import com.assignment.logging.Log;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.time.Duration;
import java.util.Objects;

import static org.testng.Assert.fail;

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
            setLocalDriver(browser);
            setTimeouts();
        }
    }

    private static synchronized void setLocalDriver(String browser) {
        Log.info("Setting up Local Drivers");
        if (browser.equalsIgnoreCase(BrowserEnum.CHROME.name())) {
            WebDriverManager.chromedriver().setup();
            driver.set(new ChromeDriver());
        } else if (browser.equalsIgnoreCase(BrowserEnum.EDGE.name())) {
            WebDriverManager.edgedriver().setup();
            driver.set(new EdgeDriver());
        } else {
            Log.warn("No Browser is mentioned");
            fail("Unable to create driver, Specified Browser not Found: " + browser);
        }
    }

    private static synchronized void setTimeouts() {
        String implicitWaitTimeFromProperty = PropertyReader.getProperty(PropertyEnum.IMPLICITWAIT);
        String timeoutFromProperty = PropertyReader.getProperty(PropertyEnum.WEBDRIVERWAIT);
        int implicitWait = implicitWaitTimeFromProperty.isEmpty() ? 10 : Integer.parseInt(implicitWaitTimeFromProperty) / 1000;
        int timeout = timeoutFromProperty.isEmpty() ? 60 : Integer.parseInt(timeoutFromProperty) / 1000;
        Log.info("Timeout set from Core properties");
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
            Log.info("Quitting the browser");
            getDriver().quit();
            driver.remove();
        }
    }
}
