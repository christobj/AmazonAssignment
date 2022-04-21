package com.assignment.driver;

import com.assignment.enums.BrowserEnum;
import com.assignment.enums.PropertyEnum;
import com.assignment.filereaders.PropertyReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;
import java.util.Objects;

import static org.testng.Assert.fail;

public class DriverFactory {

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

    private static void setLocalDriver(String browser) {
        if (browser.equalsIgnoreCase(BrowserEnum.CHROME.name())) {
            WebDriverManager.chromedriver().setup();
            driver.set(new ChromeDriver());
        } else if (browser.equalsIgnoreCase(BrowserEnum.EDGE.name())) {
            WebDriverManager.edgedriver().setup();
            driver.set(new EdgeDriver());
        } else if (browser.equalsIgnoreCase(BrowserEnum.FIREFOX.name())) {
            WebDriverManager.firefoxdriver().setup();
            driver.set(new FirefoxDriver());
        } else {
            fail("Unable to create driver, Specified Browser not Found: " + browser);
        }
    }

    private static void setTimeouts() {
        String implicitWaitTimeFromProperty = PropertyReader.getProperty(PropertyEnum.IMPLICITWAIT);
        String timeoutFromProperty = PropertyReader.getProperty(PropertyEnum.WEBDRIVERWAIT);
        int implicitWait = implicitWaitTimeFromProperty.isEmpty() ? 10 : Integer.parseInt(implicitWaitTimeFromProperty) / 1000;
        int timeout = timeoutFromProperty.isEmpty() ? 60 : Integer.parseInt(timeoutFromProperty) / 1000;
        //TODO Log
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
        getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(timeout));
        getDriver().manage().timeouts().scriptTimeout(Duration.ofSeconds(timeout));
        getDriver().manage().window().maximize();
    }

    public static synchronized WebDriver getDriver() {
        return driver.get();
    }

    public static void closeBrowser() {
        if (Objects.nonNull(getDriver())) {
            //TODO
            getDriver().quit();
            driver.remove();
        }
    }
}
