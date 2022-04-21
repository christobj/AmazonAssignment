package com.assignment.tests;

import com.assignment.pages.HomePage;
import org.testng.annotations.Test;

public class ProductDetailsPageTests extends HomePage {

    @Test
    public void checkForAbout() {
        launchApplicationUnderTest()
                .navigateToProductListViaMenu("TV, Appliances, Electronics", "Televisions")
                .refineBy("Brands", "Samsung")
                .sortResultsBy("Price: High to Low")
                .selectHighestPricedItem(2)
                .getAboutItemValue();
    }

}
