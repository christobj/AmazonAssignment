package com.assignment.seleniumcore;

import com.assignment.driver.Driver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public class CoreTest {

    protected CoreTest() {}

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() {
        Driver.initDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        Driver.quitDriver();
    }
}
