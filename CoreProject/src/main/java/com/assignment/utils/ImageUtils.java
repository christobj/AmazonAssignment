package com.assignment.utils;

import com.assignment.constants.CoreConstants;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;

public final class ImageUtils {

    private ImageUtils() {}

    public static String takeScreenshot(WebDriver driver, String methodName) {
        String fileLocation = CoreConstants.getPATH_TO_EXTENT_REPORTS() + methodName + ".png";
        try {
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(srcFile, new File(fileLocation));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return methodName + ".png";
    }
}
