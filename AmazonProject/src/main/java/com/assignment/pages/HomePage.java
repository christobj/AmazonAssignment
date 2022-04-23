package com.assignment.pages;

import com.assignment.enums.AmazonPropertyEnums;
import com.assignment.enums.TextConditionEnum;
import com.assignment.filereaders.PropertyReader;
import com.assignment.logging.Log;
import com.assignment.seleniumcore.CorePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends CorePage {

    @FindBy (id = "nav-belt")
    private WebElement navigationBelt;

    @FindBy (id = "nav-hamburger-menu")
    private WebElement hamburgerMenu;

    public HomePage() {
        PageFactory.initElements(getDriver(), this);
    }

    public HomePage launchApplicationUnderTest() {
        Log.info("Launching Application Under Test..!");
        openURL(PropertyReader.getProperty(AmazonPropertyEnums.AUT));
        verifyContentLoaded();
        return this;
    }

    private HomePage verifyContentLoaded() {
        waitTillVisible(navigationBelt);
        return this;
    }

    public ProductsListPage navigateToProductListViaMenu(String mainMenu, String subMenu) {
        Log.info("Navigating to "+subMenu +" under " +mainMenu);
        clickOn(hamburgerMenu);
        waitAndClick(generateWebElementForText("div", TextConditionEnum.EQUALS, mainMenu));
        waitAndClick(generateWebElementForText("a", TextConditionEnum.EQUALS, subMenu));
        return new ProductsListPage();
    }
}
