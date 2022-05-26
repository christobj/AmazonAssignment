package com.assignment.tests;

import com.assignment.pages.HomePage;
import com.assignment.pojo.AboutItem;
import com.assignment.seleniumcore.CoreTest;
import io.github.sskorol.core.DataSupplier;
import io.github.sskorol.data.CsvReader;
import one.util.streamex.StreamEx;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.github.sskorol.data.TestDataReader.use;

public class ProductDetailsPageTests extends CoreTest {

    @Test(description = "To Verify whether About Item is displayed", dataProvider = "aboutItemFromSupplier")
    public void checkForAboutItem(AboutItem aboutItem) {
        new HomePage().launchApplicationUnderTest()
                .navigateToProductListViaMenu(aboutItem.getMain_Menu(), aboutItem.getSub_Menu())
                .refineBy(aboutItem.getGroup(), aboutItem.getValue())
                .sortResultsBy(aboutItem.getSortBy())
                .selectItemBasedOnOrder(Integer.parseInt(aboutItem.getSelectOrder()))
                .getAboutItemValue();
    }

//    @DataProvider(name = "aboutItem", parallel = true)
//    public Object[][] aboutItem() {
//        String[][] tabArray;
//        List<String> mainMenuList = CSVFileReader.getColumnData(AmazonConstants.getPATHTOTESTDATA(), "Main_Menu");
//        List<String> subMenuLit = CSVFileReader.getColumnData(AmazonConstants.getPATHTOTESTDATA(), "Sub_Menu");
//        List<String> groupList = CSVFileReader.getColumnData(AmazonConstants.getPATHTOTESTDATA(), "Group");
//        List<String> valueList = CSVFileReader.getColumnData(AmazonConstants.getPATHTOTESTDATA(), "Value");
//        List<String> sortByList = CSVFileReader.getColumnData(AmazonConstants.getPATHTOTESTDATA(), "SortBy");
//        List<String> selectOrderList = CSVFileReader.getColumnData(AmazonConstants.getPATHTOTESTDATA(), "SelectOrder");
//        int size = selectOrderList.size();
//        tabArray = new String[size][6];
//        for (int i = 0 ; i <size ; i++) {
//            tabArray[i][0] = mainMenuList.get(i);
//            tabArray[i][1] = subMenuLit.get(i);
//            tabArray[i][2] = groupList.get(i);
//            tabArray[i][3] = valueList.get(i);
//            tabArray[i][4] = sortByList.get(i);
//            tabArray[i][5] = selectOrderList.get(i);
//        }
//        return tabArray;
//    }
    @DataSupplier
    public StreamEx<AboutItem> aboutItemFromSupplier() throws IOException {
        return use(CsvReader.class)
                .withTarget(AboutItem.class)
                .withSource("testData/check.csv")
                .read();
    }

}
