package swag.lab.tests;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import swag.lab.base.BaseClass;
import swag.lab.input.data.DataInput;
import swag.lab.input.data.DataInput.TestType;
import swag.lab.listeners.RetryAnalyzer;
import swag.lab.pages.CartPage;
import swag.lab.pages.CheckoutInformationPage;
import swag.lab.pages.CheckoutOverviewPage;
import swag.lab.pages.InventoryPage;
import swag.lab.pages.LoginPage;
import swag.lab.pages.OrderPlacedPage;

public class OrderPlacedTest extends BaseClass{

	LoginPage loginPage;
    CartPage cartPage;
    InventoryPage inventoryPage;
    CheckoutOverviewPage checkoutOverviewPage ;
    WebDriverWait wait;
    JavascriptExecutor js;
	
	//@BeforeTest
	public OrderPlacedPage setupForOrderPlced(String firstName, String lastName, String postalCode ) throws IOException {
						
		js = (JavascriptExecutor) driver;
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		loginPage = launchApplication();
        
        inventoryPage = loginPage.loginIntoSwagLab();
        
        inventoryPage.addProduct("Sauce Labs Backpack");

        CartPage cartPage = inventoryPage.clickCart();
        
        CheckoutInformationPage checkoutInformationPage = cartPage.goToCheckoutPage();
        
        
        checkoutOverviewPage =  checkoutInformationPage.goToCheckoutOverviewPage(firstName, lastName, postalCode);
        
        OrderPlacedPage orderPlacedPage = checkoutOverviewPage.clickOnFinishBtn();
       
        return orderPlacedPage;
					
	}
	
	// Parameter Test 
	@Parameters({"first_name", "last_name", "postal_code"})
	@Test(enabled=false )
	public void verifyPriceDisplayedUsingParameter(String first_name, String last_name, String postal_code) throws IOException {

		OrderPlacedPage page = setupForOrderPlced(first_name, last_name, postal_code);
	    
	    System.out.println("Total Item : "+page);
	    String title = page.getPageTitle();

        Assert.assertEquals(title, "Checkout: Complete!");
	 
	  
	}
	
	
	@Parameters({"first_name", "last_name", "postal_code"})
	@Test(enabled=false )
	public void verifyThankYouMessage(String first_name, String last_name, String postal_code) throws IOException {

		OrderPlacedPage page = setupForOrderPlced(first_name, last_name, postal_code);
	    
		String message = page.getThankMsg();
		System.out.println("Thank you msg : "+message);

        Assert.assertEquals(message, "Thank you for your order!");
	 
	  
	}	
	
	
	@Parameters({"first_name", "last_name", "postal_code"})
	@Test(enabled=false )
	public void verifyDispatchMessage(String first_name, String last_name, String postal_code) throws IOException {

		OrderPlacedPage page = setupForOrderPlced(first_name, last_name, postal_code);
		
		String dispatchText = page.getDispatchText();
		System.out.println("Thank you msg : "+dispatchText);

        Assert.assertTrue(dispatchText.contains("Your order has been dispatched"));
	 
	  
	}	
		
	
	@Parameters({"first_name", "last_name", "postal_code"})
	@Test(enabled=false )
	public void verifyBackHomeButtonNavigation(String first_name, String last_name, String postal_code) throws IOException {

		OrderPlacedPage page = setupForOrderPlced(first_name, last_name, postal_code);
		
		page.goBackToProduct();

        String currentUrl = driver.getCurrentUrl();

        Assert.assertTrue(currentUrl.contains("inventory.html"));
	  
	}		
	
	
	@Parameters({"first_name", "last_name", "postal_code"})
	@Test(enabled=false )
	public void verifyUserStillLoggedIn(String first_name, String last_name, String postal_code) throws IOException {

		OrderPlacedPage page = setupForOrderPlced(first_name, last_name, postal_code);
		
		page.goBackToProduct();
		
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));
	  
	}		
		
	
	@Test(enabled=true, dataProvider = "checkoutCompleteData", dataProviderClass = DataInput.class, retryAnalyzer = RetryAnalyzer.class)
	public void verifyCheckoutCompletePage( String testType, String expectedValue) throws IOException {

	        OrderPlacedPage orderPlacedPage = setupForOrderPlced("standard_user", "secret_sauce", "401148");

	        switch (testType) {

	        case "TITLE":
	            Assert.assertEquals(orderPlacedPage.getPageTitle(), expectedValue);
	            break;

	        case "THANK_MESSAGE":
	            Assert.assertEquals(orderPlacedPage.getThankMsg(), expectedValue);
	            break;

	        case "DISPATCH_TEXT":
	            Assert.assertTrue(orderPlacedPage 
	                    .getDispatchText()
	                    .contains(expectedValue));
	            break;

	        case "BACK_HOME":
	            orderPlacedPage.goBackToProduct();
	            Assert.assertTrue(driver.getCurrentUrl()
	                    .contains(expectedValue));
	            break;

	        default:
	            Assert.fail("Invalid Test Type");
	        }

	 }


	@Test(dataProvider = "checkoutCompleteInputData", dataProviderClass = DataInput.class)
	public void verifyCheckoutCompletePage(TestType type, String expectedValue) throws IOException {

	    OrderPlacedPage orderPlacedPage = setupForOrderPlced("standard_user", "secret_sauce", "401148");
	    switch (type) {

	    case TITLE:
	        Assert.assertEquals(orderPlacedPage.getPageTitle(), expectedValue);
	        break;

	    case THANK_MESSAGE:
	        Assert.assertEquals(orderPlacedPage.getThankMsg(), expectedValue);
	        break;

	    case DISPATCH_TEXT:
	        Assert.assertTrue(orderPlacedPage
	                .getDispatchText()
	                .contains(expectedValue));
	        break;

	    case BACK_HOME:
	        orderPlacedPage.goBackToProduct();
	        Assert.assertTrue(driver.getCurrentUrl()
	                .contains(expectedValue));
	        break;

	    }
	}	
	
	
	
	
	
	
	
	
	
}
