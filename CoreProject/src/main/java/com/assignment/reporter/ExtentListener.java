package com.assignment.reporter;

import com.assignment.constants.CoreConstants;
import com.assignment.driver.DriverFactory;
import com.assignment.enums.PropertyEnum;
import com.assignment.filereaders.PropertyReader;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentListener implements ITestListener, ISuiteListener {

    private static ExtentReports extentReport;

    public static final ThreadLocal<ExtentTest> extentMethod = new ThreadLocal<>();

    @Override
    public void onStart(ISuite suite) {
        PropertyReader.setProperty(CoreConstants.getCONFIGPROPERTYPATH());
        extentReport = ExtentManager.getInstance("Amazon Assignment");
        extentReport.setSystemInfo("browser", PropertyReader.getProperty(PropertyEnum.BROWSER));
    }

    @Override
    public void onFinish(ISuite suite) {
        extentReport.flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        Object[] parameters = result.getParameters();
        if (parameters != null && parameters.length>0) {
            methodName = methodName + "_" + parameters[0].toString();
        }
        extentMethod.set(extentReport.createTest(methodName, result.getMethod().getDescription())
                .assignDevice(getDevicename()));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        Object[] parameters = result.getParameters();
        if (parameters != null && parameters.length>0) {
            methodName = methodName + "_" + parameters[0].toString();
        }
        extentMethod.get().pass("Test is Passed " +methodName);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        Object[] parameters = result.getParameters();
        if (parameters != null && parameters.length>0) {
            methodName = methodName + "_" + parameters[0].toString();
        }
        extentMethod.get().fail("Test " +methodName +" is Failed due to " +result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        Object[] parameters = result.getParameters();
        if (parameters != null && parameters.length>0) {
            methodName = methodName + "_" + parameters[0].toString();
        }
        extentMethod.get().fail("Test " +methodName +" is Skipped due to " +result.getThrowable());
    }

    private String getDevicename() {
        WebDriver driver = DriverFactory.getDriver();
        String platform;
        String browser;
        String version;
        if (driver != null) {
            Capabilities capabilities = ((RemoteWebDriver) driver).getCapabilities();
            platform = capabilities.getPlatformName().name();
            browser = capabilities.getBrowserName();
            version = capabilities.getBrowserVersion();
            return platform + "_" + browser + "_" +version;
        } else
            return "UNKNOWN";
    }
}