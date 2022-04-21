package com.assignment.pages;


import com.assignment.enums.TextConditionEnum;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductsListPage extends HomePage{

    public ProductsListPage refineBy (String group, String value) {
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
        WebElement sortByButton = find(By.className("a-dropdown-container"));
        waitTillClickable(sortByButton);
        clickOn(sortByButton);
        waitAndClick(generateWebElementForText("a", TextConditionEnum.EQUALS, sortBy));
        return this;
    }

    public ProductDetailPage selectHighestPricedItem (int order) {
        List<WebElement> prices = findAll(By.xpath("//span[@class='a-price-whole']"));
        clickOn(prices.get(order-1));
        return new ProductDetailPage();
    }
}
