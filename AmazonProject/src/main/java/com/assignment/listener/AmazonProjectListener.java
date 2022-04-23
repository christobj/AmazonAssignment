package com.assignment.listener;

import com.assignment.constants.AmazonConstants;
import com.assignment.enums.AmazonPropertyEnums;
import com.assignment.filereaders.PropertyReader;
import com.assignment.reporter.ExtentManager;
import com.aventstack.extentreports.ExtentReports;
import org.testng.ISuite;
import org.testng.ISuiteListener;

public final class AmazonProjectListener implements ISuiteListener {

    @Override
    public void onStart(ISuite suite) {
        PropertyReader.setProperty(AmazonConstants.getPATHTOAMAZONPROPERTY());
        ExtentReports extentReports = ExtentManager.getInstance();
        extentReports.setSystemInfo(AmazonPropertyEnums.AUT.name(), PropertyReader.getProperty(AmazonPropertyEnums.AUT));
    }
}
