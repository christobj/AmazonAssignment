package com.assignment.driver;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;

public final class OptionManager {

    protected OptionManager() {}

    protected ChromeOptions getChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        return chromeOptions;
    }

    protected EdgeOptions getEdgeOptions() {
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        edgeOptions.setHeadless(true);
        return edgeOptions;
    }
}
