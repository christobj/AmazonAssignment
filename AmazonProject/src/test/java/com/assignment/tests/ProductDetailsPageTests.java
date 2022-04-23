package com.assignment.tests;

import com.assignment.constants.AmazonConstants;
import com.assignment.filereaders.CSVFileReader;
import com.assignment.pages.HomePage;
import com.assignment.seleniumcore.CoreTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class ProductDetailsPageTests extends CoreTest {

    @Test(description = "To Verify whether About Item is displayed", dataProvider = "aboutItem")
    public void checkForAboutItem(String mainMenu, String subMenu, String group, String value, String sortBy, String order) {
        new HomePage().launchApplicationUnderTest()
                .navigateToProductListViaMenu(mainMenu, subMenu)
                .refineBy(group, value)
                .sortResultsBy(sortBy)
                .selectHighestPricedItemBy(Integer.parseInt(order))
                .getAboutItemValue();
    }

    @DataProvider(name = "aboutItem", parallel = true)
    public Object[][] aboutItem() {
        String[][] tabArray;
        List<String> mainMenuList = CSVFileReader.getColumnData(AmazonConstants.getPATHTOTESTDATA(), "Main_Menu");
        List<String> subMenuLit = CSVFileReader.getColumnData(AmazonConstants.getPATHTOTESTDATA(), "Sub_Menu");
        List<String> groupList = CSVFileReader.getColumnData(AmazonConstants.getPATHTOTESTDATA(), "Group");
        List<String> valueList = CSVFileReader.getColumnData(AmazonConstants.getPATHTOTESTDATA(), "Value");
        List<String> sortByList = CSVFileReader.getColumnData(AmazonConstants.getPATHTOTESTDATA(), "SortBy");
        List<String> selectOrderList = CSVFileReader.getColumnData(AmazonConstants.getPATHTOTESTDATA(), "SelectOrder");
        int size = selectOrderList.size();
        tabArray = new String[size][6];
        for (int i = 0 ; i <size ; i++) {
            tabArray[i][0] = mainMenuList.get(i);
            tabArray[i][1] = subMenuLit.get(i);
            tabArray[i][2] = groupList.get(i);
            tabArray[i][3] = valueList.get(i);
            tabArray[i][4] = sortByList.get(i);
            tabArray[i][5] = selectOrderList.get(i);
        }
        return tabArray;
    }

}
