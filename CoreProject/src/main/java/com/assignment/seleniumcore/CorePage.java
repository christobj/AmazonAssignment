package com.assignment.seleniumcore;

import com.assignment.enums.PropertyEnum;
import com.assignment.enums.TextConditionEnum;
import com.assignment.filereaders.PropertyReader;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Clock;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import static org.testng.Assert.fail;

public class CorePage extends CoreTest {

    private static int timeout;

    protected static void openURL(String url) {
        timeout = Integer.parseInt(PropertyReader.getProperty(PropertyEnum.WEBDRIVERWAIT));
        getDriver().get(url);
    }

    protected static void waitTillVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeout));
        wait.until(driver -> element.isDisplayed());
    }

    protected static boolean clickOn(WebElement element) {
        boolean isClickSuccess = true;
        try {
            element.click();
        } catch (Exception e) {
            isClickSuccess = false;
            fail("Unable to click target element " + element + "due to " + e.getMessage());
        }
        return isClickSuccess;
    }

    protected static void waitAndClick(WebElement element) {
        waitTillVisible(element);
        clickOn(element);
    }

    protected static String generateXPathForText(String tag, TextConditionEnum condition, String value) {
        return condition.equals(TextConditionEnum.CONTAINS) ?
                String.format("//%s[contains(text(),'%s]", tag, value) :
                String.format("//%s[text()='%s']", tag, value);
    }

    protected static WebElement generateWebElementForText(String tag, TextConditionEnum condition, String value) {
        return find(By.xpath(generateXPathForText(tag, condition, value)));
    }

    protected static WebElement find(By locator) {
        return getDriver().findElement(locator);
    }

    protected static List<WebElement> findAll(By locator) {
        return getDriver().findElements(locator);
    }

    protected static void sleep (int seconds) {
        try {
            Thread.sleep(seconds*1000L);
        } catch (Exception e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    protected static boolean isElementPresent(WebElement element) {
        return element.isDisplayed();
    }

    protected static void jsScrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    protected static void scrollAndClick(WebElement element) {
        jsScrollToElement(element);
        clickOn(element);
    }

    protected static FluentWait waitForClickable () {
        return (new FluentWait(getDriver(), Clock.systemDefaultZone(), Sleeper.SYSTEM_SLEEPER)).withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofSeconds(1)).ignoring(ElementClickInterceptedException.class, NoSuchElementException.class);
    }

    protected static void waitTillClickable(WebElement element) {
        waitForClickable().until(ExpectedConditions.elementToBeClickable(element));
    }

    protected static void switchToLatestWindow() {
        Set<String> winHandles = getDriver().getWindowHandles();
        for (String handle : winHandles) {
            getDriver().switchTo().window(handle);
        }
    }

    protected static void mouseHoverAndClick (WebElement element) {
        Actions actions = new Actions(getDriver());
        actions.moveToElement(element).click().build().perform();
    }

}
