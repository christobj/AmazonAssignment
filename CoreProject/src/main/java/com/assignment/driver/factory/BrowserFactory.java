package com.assignment.driver.factory;

import com.assignment.enums.BrowserEnum;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public final class BrowserFactory {

    private BrowserFactory() {}

    private static final Map<String, Supplier<WebDriver>> localBrowserMap = new HashMap<>();
    //private static final String HUB_URL = PropertyReader.getProperty(PropertyEnum.HUBURL);
    private static final Map<String, WebDriver> remoteBrowserMap = new HashMap<>();

//    private static final Function<Capabilities, WebDriver> remote = c -> {
//        try {
//            return new RemoteWebDriver(new URL(HUB_URL), c);
//        } catch (MalformedURLException e) {
//            throw new RuntimeException(e);
//        }
//    };

    static {
        localBrowserMap.put(BrowserEnum.CHROME.getBrowser(), () -> WebDriverManager.chromedriver().create());
        localBrowserMap.put(BrowserEnum.EDGE.getBrowser(), () -> WebDriverManager.edgedriver().create());
//        remoteBrowserMap.put(BrowserEnum.CHROME.getBrowser(), getRemoteWebDriverForCapability(OptionManager.getCapability(BrowserEnum.CHROME)));
//        remoteBrowserMap.put(BrowserEnum.EDGE.getBrowser(), getRemoteWebDriverForCapability(OptionManager.getCapability(BrowserEnum.EDGE)));
    }

    public static WebDriver getLocalDriver(String browser) {
        //TODO need to handle negative case
        return localBrowserMap.get(browser).get();
    }

//    private static WebDriver getRemoteWebDriverForCapability(Capabilities capabilities) {
//        return remote.apply(capabilities);
//    }

    public static WebDriver getRemoteDriver(String browser) {
        return remoteBrowserMap.get(browser);
    }
}
