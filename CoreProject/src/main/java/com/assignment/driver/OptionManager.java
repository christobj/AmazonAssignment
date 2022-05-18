package com.assignment.driver;

import com.assignment.enums.BrowserEnum;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public final class OptionManager {

    private OptionManager() {}

    private static final Map<String, Supplier<Capabilities>>optionMap = new HashMap<>();

    private static final Supplier<Capabilities> CHROME = () -> {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        return chromeOptions;
    };
    private static final Supplier<Capabilities> EDGE = () -> {
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        edgeOptions.setHeadless(true);
        return edgeOptions;
    };

    static {
        optionMap.put(BrowserEnum.CHROME.getBrowser(), CHROME);
        optionMap.put(BrowserEnum.EDGE.getBrowser(), EDGE);
    }

    public static Capabilities getCapability(BrowserEnum browser) {
        return optionMap.get(browser.getBrowser()).get();
    }
}
