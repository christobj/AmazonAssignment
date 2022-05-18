package com.assignment.reporter;

import com.assignment.constants.CoreConstants;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;

public final class ExtentManager {

    private ExtentManager() {}

    private static ExtentReports extentReports;

    public static ExtentReports getInstance() { return extentReports;}

    public static ExtentReports getInstance(String fileName) {
        if (extentReports == null)
            extentReports = createInstances(fileName);
        return extentReports;
    }

    private static ExtentReports createInstances(String fileName) {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(CoreConstants.getPATH_TO_EXTENT_REPORTS() + "index" +".html").viewConfigurer()
                .viewOrder()
                .as(new ViewName[] {
                        ViewName.DASHBOARD,
                        ViewName.TEST,
                        ViewName.DEVICE,
                        ViewName.EXCEPTION,
                        ViewName.LOG
                })
                .apply();
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setTimeStampFormat("dd-MM-yyyy HH:mm:ss a");
        sparkReporter.config().setDocumentTitle(fileName);
        sparkReporter.config().setEncoding("utf-8");
        sparkReporter.config().setReportName(fileName);
        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);
        return extentReports;
    }
}
