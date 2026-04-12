package swag.lab.tests;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import swag.lab.base.BaseClass;
import swag.lab.pages.CartPage;
import swag.lab.pages.InventoryPage;
import swag.lab.pages.ProductDetailsPage;

public class PerformanceTest extends BaseClass {


	InventoryPage inventoryPage;
	
	WebDriverWait wait;
	
	JavascriptExecutor js ;
	
	@BeforeTest
	public void setUp() throws IOException {
		
		loginPage = launchApplication();
		
		inventoryPage = loginPage.loginIntoSwagLab();
		
		ProductDetailsPage pdp = new ProductDetailsPage(driver);
		
		CartPage cartPage = new CartPage(driver);
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		js = (JavascriptExecutor) driver;
		
		
		
		
		
		
	}
	
	
	// Verify website is responsive 
	@Test(enabled=false)
	public void verifyResponsiveLayout() {
	    driver.manage().window().setSize(new Dimension(375, 812)); // mobile
	    Assert.assertTrue(inventoryPage.items.size() > 0);
	}
	
	
	
	// Slow Network Simulation
	@Test(enabled=false)
	public void verifyAddUnderSlowNetwork() {
		 // simulate delay
	    js.executeScript( "window.setTimeout(()=>{}, 2000);");

	    inventoryPage.addProduct("Sauce Labs Backpack");

	    Assert.assertEquals(inventoryPage.getCartCount(), 1);
	}
	
	
	@Test(enabled=false)
	public void accessWithoutLogin() {
	    driver.get("https://www.saucedemo.com/inventory.html");

	    Assert.assertTrue(driver.getCurrentUrl().contains("saucedemo.com"));
	}
	
	
	@Test(enabled=false)
	public void verifySessionTimeout() throws InterruptedException {
	    Thread.sleep(5000); // simulate idle

	    driver.navigate().refresh();

	    Assert.assertTrue(driver.getCurrentUrl().contains("saucedemo"));
	}
	
	@Test(enabled=false)
	public void verifyInventoryPageLoadTime() {
	    long start = System.currentTimeMillis();

	    driver.get("https://www.saucedemo.com/inventory.html");

	    long end = System.currentTimeMillis();

	    Assert.assertTrue((end - start) < 3000);
	}
	
	
	  @Test
	    public void verifyCartPageLoadTime() {
	        long start = System.currentTimeMillis();
	        driver.get("https://www.saucedemo.com/cart.html");
	        long end = System.currentTimeMillis();
	        Assert.assertTrue((end - start) < 3000);
	    }
	
	
	@Test
	public void verifyXSS() {
	    String script = "<script>alert('xss')</script>";

	    driver.get("https://www.saucedemo.com/inventory.html?test=" + script);

	    Assert.assertFalse(driver.getPageSource().contains(script));
	}
	
}
