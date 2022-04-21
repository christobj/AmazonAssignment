package com.assignment.pages;

import com.assignment.seleniumcore.CorePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class ProductDetailPage extends CorePage {

    public ProductDetailPage getAboutItemValue() {
        switchToLatestWindow();
        WebElement aboutItem = find(By.id("featurebullets_feature_div"));
        List<WebElement> aboutDescriptionText = findAll(By.xpath("//ul[@class='a-unordered-list a-vertical a-spacing-mini']//li"));
        jsScrollToElement(aboutItem);
        assertTrue(aboutItem.isDisplayed());
        aboutDescriptionText.stream()
                .forEach(e -> System.out.println(e.getText()));
        return this;
    }
}
