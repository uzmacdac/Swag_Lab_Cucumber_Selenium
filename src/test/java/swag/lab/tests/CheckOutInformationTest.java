package swag.lab.tests;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import swag.lab.base.BaseClass;
import swag.lab.listeners.RetryAnalyzer;
import swag.lab.pages.CartPage;
import swag.lab.pages.CheckoutInformationPage;
import swag.lab.pages.InventoryPage;
import swag.lab.pages.LoginPage;
import swag.lab.pages.ProductDetailsPage;
import swag.lab.utils.Empty;

public class CheckOutInformationTest extends BaseClass{
	LoginPage loginPage;
    CartPage cartPage;
    InventoryPage inventoryPage;
    WebDriverWait wait;
    JavascriptExecutor js;
	
	//@BeforeTest
	public void setUp() throws IOException {
		
		loginPage = launchApplication();
		
		inventoryPage = loginPage.loginIntoSwagLab();
		
		ProductDetailsPage pdp = new ProductDetailsPage(driver);
		
		CartPage cart = new CartPage(driver);
		
		js = (JavascriptExecutor) driver;
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
					
	}
	
	
    public CheckoutInformationPage setupForCheckoutPage() throws IOException {
    	
		js = (JavascriptExecutor) driver;
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));    	

        loginPage = launchApplication();
        
        inventoryPage = loginPage.loginIntoSwagLab();
        inventoryPage.addProduct("Sauce Labs Backpack");

        CartPage cartPage = inventoryPage.clickCart();
        
        CheckoutInformationPage checkoutInformationPage = cartPage.goToCheckoutPage();
       
        return checkoutInformationPage;
    }
    
    
    public CheckoutInformationPage setupForCheckoutInformationPage() throws IOException {

        loginPage = launchApplication();

        inventoryPage = loginPage.loginIntoSwagLab();

        inventoryPage.addProduct("Sauce Labs Backpack");

        CartPage cartPage = inventoryPage.clickCart();

        cartPage.goToCheckoutPage();

        return new CheckoutInformationPage(driver);
    }
    
    
	@Test(enabled=false, retryAnalyzer = RetryAnalyzer.class)
	public void verifyCheckoutPageLoaded() throws IOException {
		
		// CheckoutInformationPage checkoutInformationPage = setupForCheckoutPage();
		
		CheckoutInformationPage checkoutInformationPage = setupForCheckoutPage();
		
		System.out.println("Current URL : "+driver.getCurrentUrl());
		

	    Assert.assertTrue(driver.getCurrentUrl().contains("checkout-step-one"));
		
	}
	
	@Test(enabled=false, retryAnalyzer = RetryAnalyzer.class)
	public void verifyCheckoutInformationPageTitle() throws IOException {
		
		CheckoutInformationPage checkoutInformationPage = setupForCheckoutPage();
		
		//checkoutInformationPage.checkoutInformationPageTitle();
		
		  Assert.assertTrue(driver.getPageSource().contains("Checkout: Your Information"));
		
		System.out.println("CheckoutInformationPage title : "+checkoutInformationPage.checkoutInformationPageTitle());
		Assert.assertTrue(checkoutInformationPage.checkoutInformationPageTitle().equalsIgnoreCase("Checkout: Your Information"), "Page Title is not display");
	}
	
	
	@Test(enabled=false)
	public void verifyFirstNameFieldDisplayed() throws IOException {
		CheckoutInformationPage checkoutInformationPage = setupForCheckoutPage();
		System.out.println("Is First Name Field display ? "+checkoutInformationPage.firstName.isDisplayed());
		Assert.assertTrue(checkoutInformationPage.firstName.isDisplayed(), "First Name Field is not display");
	}
	
	@Test(enabled=false)
	public void verifyLastNameFieldDisplayed() throws IOException {
		CheckoutInformationPage checkoutInformationPage = setupForCheckoutPage();
		System.out.println("Is Last Name Field display ? "+checkoutInformationPage.lastName.isDisplayed());
		Assert.assertTrue(checkoutInformationPage.lastName.isDisplayed(), "Last Name Field is not display");
	}

	@Test(enabled=false)
	public void verifyPostalCodeFieldDisplayed() throws IOException {
		CheckoutInformationPage checkoutInformationPage = setupForCheckoutPage();
		System.out.println("Is Postal Code Field display ? "+checkoutInformationPage.postalCode.isDisplayed());
		Assert.assertTrue(checkoutInformationPage.postalCode.isDisplayed(), "Postal Code Field is not display");
	}
	
	@Test(enabled=false)
	public void verifyContinueButtonDisplayed() throws IOException {
		CheckoutInformationPage checkoutInformationPage = setupForCheckoutPage();
		System.out.println("Is Continue Button display ? "+checkoutInformationPage.continueBtn.isDisplayed());
		Assert.assertTrue(checkoutInformationPage.continueBtn.isDisplayed(), "Continue Button Field is not display");
	}
	
	@Test(enabled=false)
	public void verifyCancelButtonDisplayed() throws IOException {
		CheckoutInformationPage checkoutInformationPage = setupForCheckoutPage();
		System.out.println("Is Cancel Button display ? "+checkoutInformationPage.cancelBtn.isDisplayed());
		Assert.assertTrue(checkoutInformationPage.cancelBtn.isDisplayed(), "Cancel Button Field is not display");
	}
	
	@Test(enabled=false)
	public void verifyFillingCheckoutInformationDetail() throws IOException, InterruptedException {
		CheckoutInformationPage checkoutInformationPage = setupForCheckoutPage();
		checkoutInformationPage.fillFirstName("standard_user");
		checkoutInformationPage.fillLastName("secret_sauce");
		checkoutInformationPage.fillPostalCode("401148");
		
		Thread.sleep(5000);
	}
	
	
	@Test(enabled=false)
	public void verifyClickCheckoutButton() throws IOException {
		
		CheckoutInformationPage checkoutInformationPage = setupForCheckoutPage();

	    checkoutInformationPage.fillCheckoutInformation(
	        "ssss",
	        "secret_sauce",
	        "401148"
	    );

	    checkoutInformationPage.continueWithOrderProcess();
	    System.out.println("Checkoutt Page : "+driver.getCurrentUrl());
	    Assert.assertTrue(driver.getCurrentUrl().equalsIgnoreCase("https://www.saucedemo.com/checkout-step-two.html"), "Go to checkout step two");
	}
	
	@Test(enabled=false)
	public void verifyClickCancelButton() throws IOException {
		
		CheckoutInformationPage checkoutInformationPage = setupForCheckoutPage();
		System.out.println("Checkout Page : "+driver.getCurrentUrl());

	    checkoutInformationPage.fillCheckoutInformation(
	        "standard_user",
	        "secret_sauce",
	        "401148"
	    );

	    checkoutInformationPage.cancelOrder();
	    System.out.println("Back to Cart Page : "+driver.getCurrentUrl());
	    Assert.assertTrue(driver.getCurrentUrl().equalsIgnoreCase("https://www.saucedemo.com/cart.html"), "Back To Cart Page");
	}
	
	
	//----------------------------------- Negative Test Cases ---------------------------------------------
	@Test(enabled=false)
	public void verifyFirstNameFiledEmpty() throws IOException {
		CheckoutInformationPage checkoutInformationPage = setupForCheckoutPage();
		System.out.println("Checkout Page : "+driver.getCurrentUrl());

	    checkoutInformationPage.fillCheckoutInformation(
	        "",
	        "secret_sauce",
	        "401148"
	    );
	    checkoutInformationPage.continueWithOrderProcess();
	    System.out.println("Error : "+ checkoutInformationPage.getErrorMsg());
	    Assert.assertTrue(checkoutInformationPage.getErrorMsg().equalsIgnoreCase("Error: First Name is required"), "First Name Field is empty");
	}
	
	@Test(enabled=false)
	public void verifyLastNameFiledEmpty() throws IOException {
		CheckoutInformationPage checkoutInformationPage = setupForCheckoutInformationPage();
		System.out.println("Checkout Page : "+driver.getCurrentUrl());

//	    checkoutInformationPage.fillCheckoutInformationPage(
//	        "standard_user",
//	        "",
//	        "401148"
//	    );
	    
//		checkoutInformationPage.fill(
//		        "standard_user",
//		        Keys.TAB,
//		        "401148"
//		        
//		);
				
		checkoutInformationPage.fillFirstName("standard_user");
		
		checkoutInformationPage.fillPostalCode("401148");
		
		//checkoutInformationPage.fillLastName(Keys.TAB);
		
	    checkoutInformationPage.continueWithOrderProcess();
	    System.out.println("Error : "+ checkoutInformationPage.getErrorMsg());
	    Assert.assertEquals(checkoutInformationPage.getErrorMsg(), "Error: Last Name is required");
	}
	
	
	@Test(enabled=false)
	public void verifyPostalCodeFiledEmpty() throws IOException {
		CheckoutInformationPage checkoutInformationPage = setupForCheckoutInformationPage();
		System.out.println("Checkout Page : "+driver.getCurrentUrl());

		checkoutInformationPage.fillCheckoutDetail(
			        "standard_user",
			        "secret_sauce",
			        Keys.TAB
		);
				
		
	    checkoutInformationPage.continueWithOrderProcess();
	    System.out.println("Error : "+ checkoutInformationPage.getErrorMsg());
	    Assert.assertEquals(checkoutInformationPage.getErrorMsg(), "Error: Last Name is required");
	}
	
		
	
	@Test(enabled=false)
	public void verifyFirstNameFiledEmptyErrorMag() throws IOException {
		CheckoutInformationPage checkoutInformationPage = setupForCheckoutPage();
		System.out.println("Checkout Page : "+driver.getCurrentUrl());

	    checkoutInformationPage.fillFirstName("");
	    checkoutInformationPage.fillLastName("secret_sauce");
	    checkoutInformationPage.continueWithOrderProcess();
	    System.out.println("Error : "+ checkoutInformationPage.getErrorMsg());
	    Assert.assertTrue(checkoutInformationPage.getErrorMsg().equalsIgnoreCase("Error: First Name is required"), "First Name Field is empty");
	}
	
	@Test(enabled=false)
	public void verifyLastNameFiledEmptyErrorMag() throws IOException {
		CheckoutInformationPage checkoutInformationPage = setupForCheckoutPage();
		System.out.println("Checkout Page : "+driver.getCurrentUrl());

	    checkoutInformationPage.fillFirstName("standard_user");
	   // checkoutInformationPage.fillLastName("secret_sauce");
	    checkoutInformationPage.continueWithOrderProcess();
	    System.out.println("Error : "+ checkoutInformationPage.getErrorMsg());
	    Assert.assertTrue(checkoutInformationPage.getErrorMsg().equalsIgnoreCase("Error: Last Name is required"), "Last Name Field is empty");
	}
	
	
	
	@Test(enabled=true)
	public void verifyAllFieldFilled() throws IOException, InterruptedException {
		CheckoutInformationPage checkoutInformationPage = setupForCheckoutPage();
		System.out.println("Checkout Page : "+driver.getCurrentUrl());

		checkoutInformationPage.fillFirstName("standard_user");
		checkoutInformationPage.fillLastName("secret_sauce");
		checkoutInformationPage.fillPostalCode("401148");
		
		Thread.sleep(5000);
		
	    checkoutInformationPage.continueWithOrderProcess();
	    System.out.println("Current URL : "+driver.getCurrentUrl());
	    
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
