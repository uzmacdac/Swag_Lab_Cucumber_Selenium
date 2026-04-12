package swag.lab.listeners;



import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import swag.lab.tests.BaseTest;
import swag.lab.utils.ExtentManager;
import swag.lab.utils.ScreenshotUtils;

public class TestListernerParallelTesting implements ITestListener {

    private static ExtentReports extent = ExtentManager.getInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    
    WebDriver driver;

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

        //WebDriver driver;
		try {
			driver = new BaseTest().getDriver();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        String path =
                ScreenshotUtils.captureScreenshot(
                        driver,
                        result.getMethod().getMethodName());

        test.get().addScreenCaptureFromPath(path);
    }

    @Override
    public void onFinish(ITestContext context) {

        extent.flush();
    }
}
