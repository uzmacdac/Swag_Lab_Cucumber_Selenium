package swag.lab.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import swag.lab.base.BaseClass;
import swag.lab.tests.BaseTest;
import swag.lab.utils.ExtentManager;
import swag.lab.utils.ScreenshotUtils;

public class TestListener implements ITestListener {

	WebDriver driver;
    private static ExtentReports extent = ExtentManager.getInstance();
    ThreadLocal<ExtentTest> extent_threadLocal = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {

        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        extent_threadLocal.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        extent_threadLocal.get().log(Status.PASS, "Test Passed");
        //driver = ((BaseTest) result.getInstance()).getDriver();\
        driver = ((BaseClass) result.getInstance()).getDriver();

        String path = ScreenshotUtils.captureScreenshot(driver, result.getMethod().getMethodName());

        extent_threadLocal.get().addScreenCaptureFromPath(path);
    }

    @Override
    public void onTestFailure(ITestResult result) {

        extent_threadLocal.get().fail(result.getThrowable());
        //driver = ((BaseTest) result.getInstance()).getDriver();
        
        driver = ((BaseClass) result.getInstance()).getDriver();

        String path = ScreenshotUtils.captureScreenshot(driver, result.getMethod().getMethodName());

        extent_threadLocal.get().addScreenCaptureFromPath(path);
    }

    @Override
    public void onFinish(ITestContext context) {

        extent.flush();

        try {
            Desktop.getDesktop().browse( new File("reports/ExtentReport.html").toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}