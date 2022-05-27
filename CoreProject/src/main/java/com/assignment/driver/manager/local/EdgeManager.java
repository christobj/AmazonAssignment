package com.assignment.driver.manager.local;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;

public final class EdgeManager {

    private EdgeManager() {}

    public static WebDriver getDriver() {
        return WebDriverManager.edgedriver().create();
    }
}
