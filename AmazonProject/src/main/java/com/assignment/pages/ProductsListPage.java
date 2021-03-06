package com.assignment.pages;


import com.assignment.enums.LoggerEnums;
import com.assignment.enums.TextConditionEnum;
import com.assignment.seleniumcore.CorePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.assignment.logging.CoreLog.log;

public class ProductsListPage extends CorePage {

    public ProductsListPage refineBy (String group, String value) {
        log(LoggerEnums.EXTENTLOGGERINFO, "Refining by " +value + " under Group " +group);
        WebElement refineElement = find(generateRefinedXpath(group,value));
        if (isElementPresent(refineElement)) {
            clickOn(refineElement);
        } else {
            scrollAndClick(refineElement);
        }
        return this;
    }

    private By generateRefinedXpath (String group, String value) {
        return By.xpath(String.format("//span[text()='%s']/parent::div/following-sibling::ul//span[text()='%s']", group, value));
    }

    public ProductsListPage sortResultsBy(String sortBy) {
        sleep(3);
        log(LoggerEnums.EXTENTLOGGERINFO, "Sorting Results by " +sortBy);
        waitTillClickable(generateWebElementForText("span", TextConditionEnum.EQUALS, "RESULTS"));
        WebElement sortByButton = find(By.className("a-button-inner"));
        waitAndClick(sortByButton);
        waitAndClick(generateWebElementForText("a", TextConditionEnum.EQUALS, sortBy));
        return this;
    }

    public ProductDetailPage selectItemBasedOnOrder(int order) {
        log(LoggerEnums.EXTENTLOGGERINFO, "Selecting " +order + " largest item");
        List<WebElement> prices = findAll(By.xpath("//span[@class='a-price-whole']"));
        clickOn(prices.get(order-1));
        return new ProductDetailPage();
    }
}
