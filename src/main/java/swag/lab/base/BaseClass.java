package swag.lab.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;

import io.github.bonigarcia.wdm.WebDriverManager;
import swag.lab.pages.LoginPage;


public class BaseClass {

	
	public WebDriver driver;
	
	public LoginPage loginPage;
	
	
	
	public WebDriver initializeDriver() throws IOException {
		
		// Properties class
		Properties property = new Properties();
		
		// Golabl Setup property file path
		String path = System.getProperty("user.dir")+"\\src\\main\\java\\swag\\lab\\resources\\Global_Setup.properties";
		
		// for file 
		FileInputStream fs = new FileInputStream(path);
		
		// load the property file
		property.load(fs);
		
		String browser_name = System.getProperty("browser") != null 
		        ? System.getProperty("browser") 
		        : property.getProperty("browser");
		
		if (browser_name == null) {
		    throw new RuntimeException("Browser not specified. Use -Dbrowser=chrome or define in properties file.");
		}
		
		if(browser_name.contains("chrome")) {
			
			WebDriverManager.chromedriver().clearDriverCache().clearResolutionCache().setup();
			
			ChromeOptions option = new ChromeOptions();
			
			 if(browser_name.contains("headless")) {
				 option.addArguments("--headless=new");
				 option.addArguments("--disable-gpu");
				 option.addArguments("--window-size=1920,1080");
				 option.addArguments("--disable-dev-shm-usage");
				 option.addArguments("--no-sandbox");
			 }
			 //option.addArguments("window-size=1920,1080");
			
			// Create instance of Chrome driver
			 driver = new ChromeDriver(option);
			
			
		}
		else if(browser_name.equalsIgnoreCase("edge"))
		{
			System.setProperty("webdriver.edge.driver", "edge.exe");
			
			// Create instance of Chrome driver
			driver = new EdgeDriver();
			
		}
		
		// Explicit wait
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
							
		// implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
									
		// maximize window
		driver.manage().window().maximize();
		
		return driver;
	}
	
	
	public Properties getPropertiesObject() throws IOException {
		
		
		// Properties class
		Properties property = new Properties();
				
		// Golabl Setup property file path
		String path = System.getProperty("user.dir")+"\\src\\main\\java\\swag\\lab\\resources\\Global_Setup.properties";
				
		// for file 
		FileInputStream fs = new FileInputStream(path);
				
		// load the property file
		property.load(fs);
				
		return property;
		
	
	}
	
	
	public String getScreenshot(String test_case_name, WebDriver driver) throws IOException {
		
		// 
		TakesScreenshot ts = (TakesScreenshot) driver;
		
		File source = ts.getScreenshotAs(OutputType.FILE);
		
		File destination_file = new File("D:\\Automation_Project\\Selenium_Project\\Swag_Lab\\Screenshots\\"+test_case_name+".png");
		
		FileUtils.copyFile(source, destination_file);
		
		return "D:\\Automation_Project\\Selenium_Project\\Swag_Lab\\Screenshots\\"+test_case_name+".png";
		
	}
	
	
	
	
	//@BeforeMethod(alwaysRun = true)
	public LoginPage launchApplication() throws IOException {
		
		driver = initializeDriver();
		
		loginPage = new LoginPage(driver);
		
		loginPage.goToSwagLab();
			
		return loginPage;
	}
	
	@AfterSuite
	public void tearDown() throws InterruptedException {
		Thread.sleep(5000);
		driver.quit();
	}


	public WebDriver getDriver() {
		// TODO Auto-generated method stub
		return driver;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
