package swag.lab.utils;



import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtils {

    public static String captureScreenshot(WebDriver driver, String testName) {

        String path = "D:\\Automation_Project\\Selenium_Project\\Swag_Lab\\Screenshots\\" + testName + ".png";

        File src = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.FILE);

        try {
            Files.copy(src.toPath(), Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return path;
    }
    
    
    public static String captureScreenshotFile(WebDriver driver, String testName) {

        new File("screenshots").mkdirs();

        String path ="D:\\Automation_Project\\Selenium_Project\\Swag_Lab\\Screenshots\\" + testName + ".png";

        File src = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.FILE);

        try {
            Files.copy(src.toPath(), Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return path;
    }
    
    
    public static String captureScreenshotParallelTesting(WebDriver driver, String testName) {

        new File("screenshots").mkdirs();

        String path = "D:\\Automation_Project\\Selenium_Project\\Swag_Lab\\Screenshots\\"
                + testName + "_" 
                + Thread.currentThread().getId() 
                + ".png";

        File src = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.FILE);

        try {
            Files.copy(src.toPath(), Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return path;
    }
    
    
    
}
