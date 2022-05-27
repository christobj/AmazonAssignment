package com.assignment.reporter;

import com.assignment.constants.CoreConstants;
import com.assignment.driver.Driver;
import com.assignment.driver.DriverManager;
import com.assignment.enums.LoggerEnums;
import com.assignment.enums.PropertyEnum;
import com.assignment.filereaders.PropertyReader;
import com.assignment.utils.ImageUtils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import static com.assignment.logging.CoreLog.log;

public class ExtentListener implements ITestListener, ISuiteListener {

    private static ExtentReports extentReport;

    public static final ThreadLocal<ExtentTest> extentMethod = new ThreadLocal<>();

    @Override
    public void onStart(ISuite suite) {
        PropertyReader.setProperty(CoreConstants.getCONFIG_PROPERTY_PATH());
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
        log(LoggerEnums.EXTENTLOGGERPASS, "Test is Passed " +methodName);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        Object[] parameters = result.getParameters();
        if (parameters != null && parameters.length>0) {
            methodName = methodName + "_" + parameters[0].toString();
        }
        String imagePath = ImageUtils.takeScreenshot(DriverManager.getDriver(), methodName);
        extentMethod.get().fail(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(imagePath).build());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        Object[] parameters = result.getParameters();
        if (parameters != null && parameters.length>0) {
            methodName = methodName + "_" + parameters[0].toString();
        }
        log(LoggerEnums.EXTENTLOGGERFAIL, "Test " +methodName +" is Skipped due to " +result.getThrowable());
    }

    private String getDevicename() {
        WebDriver driver = DriverManager.getDriver();
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
