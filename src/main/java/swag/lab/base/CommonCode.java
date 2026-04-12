package swag.lab.base;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.AfterSuite;

import swag.lab.pages.CartPage;
import swag.lab.pages.LoginPage;
import swag.lab.pages.ProductDetailsPage;

public class CommonCode extends BaseClass {
	
	public WebDriver driver;
	
	public LoginPage loginPage;
	
	public CartPage cartPage;
	
	public ProductDetailsPage productDetailPage;
	
	@FindBy(css=".shopping_cart_link")
	WebElement cart_link;
	
	
	
	public  CommonCode(WebDriver driver){
		this.driver = driver;
	}
	
	
	
	@AfterSuite
	public void tearDown() throws InterruptedException {
		Thread.sleep(5000);
		driver.quit();
	}

	
}
