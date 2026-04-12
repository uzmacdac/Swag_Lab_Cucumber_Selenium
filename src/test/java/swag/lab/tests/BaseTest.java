package swag.lab.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import swag.lab.listeners.TestListener;

@Listeners(swag.lab.listeners.TestListener.class)
public class BaseTest {


	 public WebDriver driver;

	    @BeforeMethod
	    public void setup() {

	        // driver initialization
	    }

	    @AfterMethod
	    public void tearDown() {

	        driver.quit();
	    }

	    public WebDriver getDriver() {
	        return driver;
	    }	
	
}
