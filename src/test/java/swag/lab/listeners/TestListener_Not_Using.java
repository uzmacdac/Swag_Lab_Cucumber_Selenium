package swag.lab.listeners;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import swag.lab.tests.BaseTest;
import swag.lab.utils.AllureUtils;
import swag.lab.utils.ExtentManager;
import swag.lab.utils.ScreenshotUtils;

public class TestListener_Not_Using implements ITestListener {

	WebDriver driver;
    private static ExtentReports extent = ExtentManager.getInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {

        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());

        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        test.get().pass("Test Passed");
        driver = ((BaseTest) result.getInstance()).getDriver();

        String path = ScreenshotUtils.captureScreenshot(driver, result.getMethod().getMethodName());

        test.get().addScreenCaptureFromPath(path);
    }

    @Override
    public void onTestFailure(ITestResult result) {

        test.get().fail(result.getThrowable());

        driver = ((BaseTest) result.getInstance()).getDriver();

        String path = ScreenshotUtils.captureScreenshot(driver, result.getMethod().getMethodName());

        test.get().addScreenCaptureFromPath(path);
        
        
        
    	
    	try {
    		
    		driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	
//    	String file_path = null;
//    	try {
//    		//file_path = getScreenshot(result.getMethod().getMethodName(), driver);
//    	}
//    	catch(IOException e) {
//    		e.printStackTrace();
//    		
//    	}
    	
    	
    	// 1. take screenshot
    	//test.addScreenCaptureFromPath(file_path, result.getMethod().getMethodName());
    	
    	//extent_threadLocal.get().addScreenCaptureFromPath(file_path, result.getMethod().getMethodName());
        
        // Add screenshot capture logic if needed
        
        
        AllureUtils.takeScreenshot(driver);
    }

    
 
    
    @Override
    public void onFinish(ITestContext context) {

        extent.flush();
    }
}