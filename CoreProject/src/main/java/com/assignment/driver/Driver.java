package com.assignment.driver;

import com.assignment.config.factory.CoreConfigFactory;
import com.assignment.driver.entity.DriverData;
import com.assignment.driver.factory.DriverFactory;
import org.openqa.selenium.WebDriver;

import java.time.Duration;
import java.util.Objects;

import static com.assignment.driver.DriverManager.getDriver;

public final class Driver {

    private Driver() {}

    public static void initDriver() {
        if (Objects.isNull(getDriver())) {
            DriverData driverData = new DriverData(CoreConfigFactory.getConfig().browser(), CoreConfigFactory.getConfig().executionMode());
            WebDriver driver = DriverFactory.getDriver(driverData);
            DriverManager.setDriver(driver);
            setTimeouts();
        }
    }

    public static void quitDriver() {
        if (Objects.nonNull(getDriver())) {
            getDriver().quit();
            DriverManager.unload();
        }
    }

    private static synchronized void setTimeouts() {
        long timeout = CoreConfigFactory.getConfig().webDriverWait();;
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(CoreConfigFactory.getConfig().implicitWait()));
        getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(timeout));
        getDriver().manage().timeouts().scriptTimeout(Duration.ofSeconds(timeout));
        getDriver().manage().window().maximize();
    }

}
