package com.assignment.seleniumcore;

import com.assignment.constants.CoreConstants;
import com.assignment.driver.DriverFactory;
import com.assignment.filereaders.PropertyReader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.util.Objects;


public class CoreTest {

    protected CoreTest() {}

    @BeforeMethod(alwaysRun = true)
    @Parameters(value = {"browser"})
    public void beforeMethod(@Optional("optional") String browser) {
        PropertyReader.setProperty(CoreConstants.getCONFIG_PROPERTY_PATH());
        if (Objects.isNull(getDriver()))
            DriverFactory.setDriver(browser);
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        if (!Objects.isNull(getDriver()))
            DriverFactory.closeBrowser();
    }

    public static WebDriver getDriver() {
        return DriverFactory.getDriver();
    }
}
