package com.assignment.pages;

import com.assignment.constants.AmazonConstants;
import com.assignment.enums.AmazonPropertyEnums;
import com.assignment.enums.TextConditionEnum;
import com.assignment.filereaders.PropertyReader;
import com.assignment.seleniumcore.CorePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.BeforeMethod;

public class HomePage extends CorePage {

    @FindBy (id = "nav-belt")
    private WebElement navigationBelt;

    @FindBy (id = "nav-hamburger-menu")
    private WebElement hamburgerMenu;

    @BeforeMethod(alwaysRun = true)
    public void beforeHomePage() {
        PropertyReader.setProperty(AmazonConstants.getPATHTOAMAZONPROPERTY());
    }

    public HomePage launchApplicationUnderTest() {
        openURL(PropertyReader.getProperty(AmazonPropertyEnums.AUT));
        verifyContentLoaded();
        return this;
    }

    private HomePage verifyContentLoaded() {
        waitTillVisible(navigationBelt);
        return this;
    }

    public ProductsListPage navigateToProductListViaMenu(String mainMenu, String subMenu) {
        clickOn(hamburgerMenu);
        waitAndClick(generateWebElementForText("div", TextConditionEnum.EQUALS, mainMenu));
        waitAndClick(generateWebElementForText("a", TextConditionEnum.EQUALS, subMenu));
        return new ProductsListPage();
    }
}
