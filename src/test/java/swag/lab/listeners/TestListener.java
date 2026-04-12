package swag.lab.listeners;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import swag.lab.tests.BaseTest;
import swag.lab.utils.AllureUtils;
import swag.lab.utils.ExtentManager;
import swag.lab.utils.ScreenshotUtils;

public class TestListener implements ITestListener {

    private static ExtentReports extent = ExtentManager.getInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {

        ExtentTest extentTest =
                extent.createTest(result.getMethod().getMethodName());

        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        test.get().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        test.get().fail(result.getThrowable());

        WebDriver driver = ((BaseTest) result.getInstance()).getDriver();

        String path =
                ScreenshotUtils.captureScreenshot(
                        driver,
                        result.getMethod().getMethodName());

        test.get().addScreenCaptureFromPath(path);
        AllureUtils.takeScreenshot(driver);
    }

    
 
    
    @Override
    public void onFinish(ITestContext context) {

        extent.flush();
    }
}