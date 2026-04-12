package swag.lab.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import swag.lab.base.BaseClass;
import swag.lab.input.data.DataInput;
import swag.lab.pages.CartPage;
import swag.lab.pages.CheckoutInformationPage;
import swag.lab.pages.CheckoutOverviewPage;
import swag.lab.pages.InventoryPage;
import swag.lab.pages.LoginPage;
import swag.lab.pages.OrderPlacedPage;
import swag.lab.pages.ProductDetailsPage;

public class CheckoutOverviewTest extends BaseClass {
	
	LoginPage loginPage;
    CartPage cartPage;
    InventoryPage inventoryPage;
    CheckoutOverviewPage checkoutOverviewPage ;
    WebDriverWait wait;
    JavascriptExecutor js;
	
	//@BeforeTest
	public CheckoutOverviewPage setupCheckoutOverview(String firstName, String lastName, String postalCode ) throws IOException {
						
		js = (JavascriptExecutor) driver;
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		loginPage = launchApplication();
        
        inventoryPage = loginPage.loginIntoSwagLab();
        
        inventoryPage.addProduct("Sauce Labs Backpack");

        CartPage cartPage = inventoryPage.clickCart();
        
        CheckoutInformationPage checkoutInformationPage = cartPage.goToCheckoutPage();
        
        
        checkoutOverviewPage =  checkoutInformationPage.goToCheckoutOverviewPage(firstName, lastName, postalCode);
       
        return checkoutOverviewPage;
					
	}
	
	

    @Test(enabled=false)
    public void verifyOverviewPageLoaded() throws IOException {

        CheckoutOverviewPage page = setupCheckoutOverview("standard_user", "secret_sauce", "401148");
        
        System.out.println("Total Item : "+page.getItemsCount());

        Assert.assertTrue(page.getItemsCount() > 0);
    }

    @Test(enabled=false)
    public void verifyPriceDisplayed() throws IOException {

        CheckoutOverviewPage page = setupCheckoutOverview("standard_user", "secret_sauce", "401148");
        
        System.out.println("Total Item : "+page.getItemTotal());
	    System.out.println("Tax : "+page.getTax());
	    System.out.println("Total Price : "+page.getTotalPrice());

        Assert.assertTrue(page.getItemTotal().contains("Item total"));
        Assert.assertTrue(page.getTax().contains("Tax"));
        Assert.assertTrue(page.getTotalPrice().contains("Total"));
    }

    @Test(enabled=false)
    public void verifyFinishButton() throws IOException {

        CheckoutOverviewPage page = setupCheckoutOverview("standard_user", "secret_sauce", "401148");

        OrderPlacedPage placedPage = page.clickOnFinishBtn();
        System.out.println("Page Title : "+placedPage.getPageTitle());

        Assert.assertEquals(placedPage.getPageTitle(), "Checkout: Complete!");
    }

    @Test(enabled=false)
    public void verifyCancelButton() throws IOException {

        CheckoutOverviewPage page = setupCheckoutOverview("standard_user", "secret_sauce", "401148");

        page.clickCancel();

        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));
    }
	
	
	
	
	// Parameter Test 
	@Parameters({"first_name", "last_name", "postal_code"})
	@Test(enabled=false )
	public void verifyPriceDisplayedUsingParameter(String first_name, String last_name, String postal_code) throws IOException {

	    CheckoutOverviewPage page = setupCheckoutOverview(first_name, last_name, postal_code);
	    
	    System.out.println("Total Item : "+page.getItemTotal());
	    System.out.println("Tax : "+page.getTax());
	    System.out.println("Total Price : "+page.getTotalPrice());

	    Assert.assertTrue(page.getItemTotal().contains("Item total"));
	    Assert.assertTrue(page.getTax().contains("Tax"));
	    Assert.assertTrue(page.getTotalPrice().contains("Total"));
	}

	
	// DataProvider Test
	@Test(enabled=false, dataProvider= "checkoutData", dataProviderClass = DataInput.class )
	public void verifyPriceDisplayedUsingDataProvider(String first_name, String last_name, String postal_code) throws IOException {

	    CheckoutOverviewPage page = setupCheckoutOverview(first_name, last_name, postal_code);
	    
	    System.out.println("Total Item : "+page.getItemTotal());
	    System.out.println("Tax : "+page.getTax());
	    System.out.println("Total Price : "+page.getTotalPrice());

	    Assert.assertTrue(page.getItemTotal().contains("Item total"));
	    Assert.assertTrue(page.getTax().contains("Tax"));
	    Assert.assertTrue(page.getTotalPrice().contains("Total"));
	}
	
	
	
	
	
	@Parameters({"first_name", "last_name", "postal_code"})
	@Test(enabled=false)
	public void verifyItemsDisplayed(String first_name, String last_name, String postal_code) throws IOException {
	    loginPage = launchApplication();
	    inventoryPage = loginPage.loginIntoSwagLab();

	    inventoryPage.addProduct("Sauce Labs Bike Light");
	    inventoryPage.addProduct("Sauce Labs Bolt T-Shirt");

	    cartPage = inventoryPage.clickCart();

	    CheckoutInformationPage infoPage = cartPage.goToCheckoutPage();

	    CheckoutOverviewPage page = infoPage.goToCheckoutOverviewPage(first_name, last_name, postal_code);
	    
		System.out.println("Total Item : "+page.getItemsCount());
		Assert.assertTrue(page.getItemsCount() > 0, "Items are not displayed on checkout overview page");
		
	}
	
	
	@Parameters({"first_name", "last_name", "postal_code"})
	@Test(enabled=false)
	public void verifyItemsQuantityDisplayed(String first_name, String last_name, String postal_code) throws IOException {
	    loginPage = launchApplication();
	    inventoryPage = loginPage.loginIntoSwagLab();

	    inventoryPage.addProduct("Sauce Labs Bike Light");
	    inventoryPage.addProduct("Sauce Labs Bolt T-Shirt");

	    cartPage = inventoryPage.clickCart();

	    CheckoutInformationPage infoPage = cartPage.goToCheckoutPage();

	    CheckoutOverviewPage page = infoPage.goToCheckoutOverviewPage(first_name, last_name, postal_code);
	    
	    List<WebElement> quantities = page.getItemsQuantity();

	    for (WebElement qty : quantities) {

	        System.out.println("Item Quantity : " + qty.getText());

	        Assert.assertTrue(Integer.parseInt(qty.getText()) > 0,  "Quantity should be greater than zero");
	    }
		
	}
	
	
	// DataProvider Test
	@Test(enabled=false, dataProvider= "checkoutData", dataProviderClass = DataInput.class )
	public void verifyItemNameDisplayed(String first_name, String last_name, String postal_code) throws IOException{

		 CheckoutOverviewPage page = setupCheckoutOverview(first_name, last_name, postal_code);
		    
		 System.out.println("Total Item : "+page.getItemTotal());

	        Assert.assertTrue(page.getItemsName().size() > 0);
	}
	
	// DataProvider Test
	@Test(enabled=false, dataProvider= "checkoutData", dataProviderClass = DataInput.class )
	public void verifyItemDescriptionDisplayed(String first_name, String last_name, String postal_code) throws IOException{

		CheckoutOverviewPage page = setupCheckoutOverview(first_name, last_name, postal_code);
			    
		System.out.println("Total Item : "+page.getItemTotal());

		Assert.assertTrue(page.getItemsDescription().size() > 0);
	}
	

	// DataProvider Test
	@Test(enabled=false, dataProvider= "checkoutData", dataProviderClass = DataInput.class )
	public void verifyItemPriceDisplayed(String first_name, String last_name, String postal_code) throws IOException{

		CheckoutOverviewPage page = setupCheckoutOverview(first_name, last_name, postal_code);
			    
		System.out.println("Total Item : "+page.getItemTotal());

		Assert.assertTrue(page.getItemsPrice().size() > 0);
	}
	
	
	// DataProvider Test
	@Test(enabled=false, dataProvider= "checkoutData", dataProviderClass = DataInput.class )
	public void verifyMultipleItemsDisplayed(String first_name, String last_name, String postal_code) throws IOException{

	    loginPage = launchApplication();
	    inventoryPage = loginPage.loginIntoSwagLab();

	    inventoryPage.addProduct("Sauce Labs Bike Light");
	    inventoryPage.addProduct("Sauce Labs Bolt T-Shirt");

	    cartPage = inventoryPage.clickCart();

	    CheckoutInformationPage infoPage = cartPage.goToCheckoutPage();

	    CheckoutOverviewPage page = infoPage.goToCheckoutOverviewPage(first_name, last_name, postal_code);
	    
	    List<WebElement> quantities = page.getItemsQuantity();
			    
		System.out.println("Total Item : "+page.getItemsCount());

		Assert.assertTrue(page.getItemsCount() > 1);
	}
	
	
	// DataProvider Test
	@Test(enabled=false, dataProvider= "checkoutData", dataProviderClass = DataInput.class )
	public void verifyItemCountMatchesCart(String first_name, String last_name, String postal_code) throws IOException{

	    loginPage = launchApplication();
	    inventoryPage = loginPage.loginIntoSwagLab();

	    inventoryPage.addProduct("Sauce Labs Bike Light");
	    inventoryPage.addProduct("Sauce Labs Bolt T-Shirt");

	    cartPage = inventoryPage.clickCart();
	    
	    int cartCount = cartPage.getCartItemsCount();
	    
	    System.out.println("Total Items in Cart : "+cartCount);

	    CheckoutInformationPage infoPage = cartPage.goToCheckoutPage();

	    CheckoutOverviewPage page = infoPage.goToCheckoutOverviewPage(first_name, last_name, postal_code);
	    
	    System.out.println("Total Item : "+page.getItemsCount());
	    
	    Assert.assertEquals(page.getItemsCount(), cartCount);
	 
		
	}
	

	// DataProvider Test
	@Test(enabled=false, dataProvider= "checkoutData", dataProviderClass = DataInput.class )
	public void verifyItemNameNotEmpty(String first_name, String last_name, String postal_code) throws IOException{

		CheckoutOverviewPage page = setupCheckoutOverview(first_name, last_name, postal_code);

		page.getItemsName().forEach(item ->Assert.assertFalse(item.getText().isEmpty()));
	}
	
		
	// DataProvider Test
	@Test(enabled=false, dataProvider= "checkoutData", dataProviderClass = DataInput.class )
	public void verifyItemPriceNotEmpty(String first_name, String last_name, String postal_code) throws IOException{

		CheckoutOverviewPage page = setupCheckoutOverview(first_name, last_name, postal_code);

		page.getItemsPrice().forEach(item ->Assert.assertFalse(item.getText().isEmpty()));
	}	
	
	
	// DataProvider Test
	@Test(enabled=false, dataProvider= "checkoutData", dataProviderClass = DataInput.class )
	public void verifyItemQuantityNotEmpty(String first_name, String last_name, String postal_code) throws IOException{

		CheckoutOverviewPage page = setupCheckoutOverview(first_name, last_name, postal_code);

		page.getItemsQuantity().forEach(item ->Assert.assertFalse(item.getText().isEmpty()));
	}	
		
	
	// DataProvider Test
	@Test(enabled=false, dataProvider= "checkoutData", dataProviderClass = DataInput.class )
	public void verifyQuantityDisplayed(String first_name, String last_name, String postal_code) throws IOException{

		CheckoutOverviewPage page = setupCheckoutOverview(first_name, last_name, postal_code);

		Assert.assertTrue(page.getItemsQuantity().size() > 0);
	}	
	
	
	// Parameter Test 
	@Parameters({"first_name", "last_name", "postal_code"})
	@Test(enabled=false )
	public void verifySingleItemQuantity(String first_name, String last_name, String postal_code) throws IOException {

	    CheckoutOverviewPage page = setupCheckoutOverview(first_name, last_name, postal_code);
	    
	    System.out.println("Total Item : "+page.getItemsQuantity().get(0).getText());

	    Assert.assertEquals(page.getItemsQuantity().get(0).getText(), "1");
	}	
	

	// DataProvider Test
	@Test(enabled=false, dataProvider= "checkoutData", dataProviderClass = DataInput.class )
	public void verifyMultipleItemQuantity(String first_name, String last_name, String postal_code) throws IOException{

	    loginPage = launchApplication();
	    inventoryPage = loginPage.loginIntoSwagLab();

	    inventoryPage.addProduct("Sauce Labs Bike Light");
	    inventoryPage.addProduct("Sauce Labs Bolt T-Shirt");

	    cartPage = inventoryPage.clickCart();
	    
	    CheckoutInformationPage infoPage = cartPage.goToCheckoutPage();

	    CheckoutOverviewPage page = infoPage.goToCheckoutOverviewPage(first_name, last_name, postal_code);
	        
	    page.getItemsQuantity().forEach(item -> Assert.assertEquals(item.getText(), "1"));
	 
		
	}	
	
	
	@Parameters({"first_name", "last_name", "postal_code"})
	@Test(enabled=false)
	public void verifyQuantityNotZero(String first_name, String last_name, String postal_code) throws IOException{

	    loginPage = launchApplication();
	    inventoryPage = loginPage.loginIntoSwagLab();

	    inventoryPage.addProduct("Sauce Labs Bike Light");
	    inventoryPage.addProduct("Sauce Labs Bolt T-Shirt");

	    cartPage = inventoryPage.clickCart();
	    
	    CheckoutInformationPage infoPage = cartPage.goToCheckoutPage();

	    CheckoutOverviewPage page = infoPage.goToCheckoutOverviewPage(first_name, last_name, postal_code);
	        
	    page.getItemsQuantity().forEach(item -> Assert.assertTrue(Integer.parseInt(item.getText()) > 0));
	 
		
	}	
	
	
	@Parameters({"first_name", "last_name", "postal_code"})
	@Test(enabled=false)
	public void verifyQuantityNumeric(String first_name, String last_name, String postal_code) throws IOException{

	    loginPage = launchApplication();
	    inventoryPage = loginPage.loginIntoSwagLab();

	    inventoryPage.addProduct("Sauce Labs Bike Light");
	    inventoryPage.addProduct("Sauce Labs Bolt T-Shirt");

	    cartPage = inventoryPage.clickCart();
	    
	    CheckoutInformationPage infoPage = cartPage.goToCheckoutPage();

	    CheckoutOverviewPage page = infoPage.goToCheckoutOverviewPage(first_name, last_name, postal_code);
	        
	    page.getItemsQuantity().forEach(item -> Integer.parseInt(item.getText()));
	 
		
	}	

	@Parameters({"first_name", "last_name", "postal_code"})
	@Test(enabled=false)
	public void verifyTotalCalculation(String first_name, String last_name, String postal_code) throws IOException{

	    loginPage = launchApplication();
	    inventoryPage = loginPage.loginIntoSwagLab();

	    inventoryPage.addProduct("Sauce Labs Bike Light");
	    inventoryPage.addProduct("Sauce Labs Bolt T-Shirt");

	    cartPage = inventoryPage.clickCart();
	    
	    CheckoutInformationPage infoPage = cartPage.goToCheckoutPage();

	    CheckoutOverviewPage page = infoPage.goToCheckoutOverviewPage(first_name, last_name, postal_code);
	        
	    double subtotal = page.getItemTotalValue();
	    double tax = page.getTaxValue();
	    double total = page.getTotalValue();

	    System.out.println("Subtotal : " + subtotal);
	    System.out.println("Tax : " + tax);
	    System.out.println("Total : " + total);

	    Assert.assertEquals(total, subtotal + tax, 0.01);
	 
		
	}	
	

	@Parameters({"first_name", "last_name", "postal_code"})
	@Test(enabled=false)
	public void verifyFinishWithoutItems(String first_name, String last_name, String postal_code) throws IOException{

	    loginPage = launchApplication();
	    inventoryPage = loginPage.loginIntoSwagLab();

	    inventoryPage.addProduct("Sauce Labs Bike Light");
	    inventoryPage.addProduct("Sauce Labs Bolt T-Shirt");

	    cartPage = inventoryPage.clickCart();
	    
	    CheckoutInformationPage infoPage = cartPage.goToCheckoutPage();

	    CheckoutOverviewPage page = infoPage.goToCheckoutOverviewPage(first_name, last_name, postal_code);
	        
	    page.clickOnFinishBtn();

	    Assert.assertTrue(driver.getCurrentUrl().contains("checkout"));
	 
		
	}	
		
	
	// Parameter Test 
	@Parameters({"first_name", "last_name", "postal_code"})
	@Test(enabled=false )
	public void verifyInvalidSubtotal(String first_name, String last_name, String postal_code) throws IOException {

	    CheckoutOverviewPage page = setupCheckoutOverview(first_name, last_name, postal_code);
	    
	    double subtotal = page.getItemTotalValue();

	    Assert.assertTrue(subtotal > 0, "Invalid subtotal detected");
	}		
	
	
	// Parameter Test 
	@Parameters({"first_name", "last_name", "postal_code"})
	@Test(enabled=false )
	public void verifyInvalidTax(String first_name, String last_name, String postal_code) throws IOException {

	    CheckoutOverviewPage page = setupCheckoutOverview(first_name, last_name, postal_code);
	    
	    double tax = page.getTaxValue();

	    Assert.assertTrue(tax > 0, "Invalid tax value detected");
	}		
	

	@Parameters({"first_name", "last_name", "postal_code"})
	@Test(enabled=false )
	public void verifyTotalMismatch(String first_name, String last_name, String postal_code) throws IOException {

	    CheckoutOverviewPage page = setupCheckoutOverview(first_name, last_name, postal_code);
	    
	    double subtotal = page.getItemTotalValue();
	    double tax = page.getTaxValue();
	    double total = page.getTotalValue();

	    Assert.assertTrue(Math.abs(total - (subtotal + tax)) < 0.01, "Total mismatch detected");
	}		
	
	
	@Parameters({"first_name", "last_name", "postal_code"})
	@Test(enabled=false )
	public void verifyTotalCalculationValueCorrect(String first_name, String last_name, String postal_code) throws IOException {

	    CheckoutOverviewPage page = setupCheckoutOverview(first_name, last_name, postal_code);
	    
	    double subtotal = page.getItemTotalValue();
	    double tax = page.getTaxValue();
	    double total = page.getTotalValue();

	    Assert.assertTrue(page.isTotalCorrect());
	}		
	
	
	@Parameters({"first_name", "last_name", "postal_code"})
	@Test(enabled=false )
	public void verifyLargeNumberOfItems(String first_name, String last_name, String postal_code) throws IOException {

	    loginPage = launchApplication();
	    inventoryPage = loginPage.loginIntoSwagLab();

	    // Add all items
	    inventoryPage.addProduct("Sauce Labs Backpack");
	    inventoryPage.addProduct("Sauce Labs Bike Light");
	    inventoryPage.addProduct("Sauce Labs Bolt T-Shirt");
	    inventoryPage.addProduct("Sauce Labs Fleece Jacket");
	    inventoryPage.addProduct("Sauce Labs Onesie");
	    inventoryPage.addProduct("Test.allTheThings() T-Shirt (Red)");

	    cartPage = inventoryPage.clickCart();

	    CheckoutInformationPage infoPage = cartPage.goToCheckoutPage();

	    CheckoutOverviewPage page = infoPage.goToCheckoutOverviewPage(first_name, last_name, postal_code);

	    System.out.println("Total Items : " + page.getItemsCount());

	    Assert.assertTrue(page.getItemsCount() >= 6, "Large number of items not displayed properly");
	}
	
	
	@Parameters({"first_name", "last_name", "postal_code"})
	@Test(enabled=false )
	public void verifyHighPriceItems(String first_name, String last_name, String postal_code) throws IOException {

	    loginPage = launchApplication();
	    inventoryPage = loginPage.loginIntoSwagLab();

	    // Add all items
	    inventoryPage.addProduct("Sauce Labs Backpack");
	    inventoryPage.addProduct("Sauce Labs Bike Light");
	   
	    cartPage = inventoryPage.clickCart();

	    CheckoutInformationPage infoPage = cartPage.goToCheckoutPage();

	    CheckoutOverviewPage page = infoPage.goToCheckoutOverviewPage(first_name, last_name, postal_code);

	    System.out.println("Total Items : " + page.getItemsCount());

	    double subtotal = page.getItemTotalValue();
	    double tax = page.getTaxValue();
	    double total = page.getTotalValue();

	    System.out.println("Subtotal : " + subtotal);
	    System.out.println("Tax : " + tax);
	    System.out.println("Total : " + total);

	    Assert.assertEquals(total, subtotal + tax, 0.01);
	}
		

	@Parameters({"first_name", "last_name", "postal_code"})
	@Test(enabled=false )
	public void verifyDecimalPriceCalculation(String first_name, String last_name, String postal_code) throws IOException {

	    CheckoutOverviewPage page = setupCheckoutOverview(first_name, last_name, postal_code);
	    
	    double subtotal = page.getItemTotalValue();
	    double tax = page.getTaxValue();
	    double total = page.getTotalValue();

	    Assert.assertEquals(total, subtotal + tax, 0.01);

	}	
	
	
	@Parameters({"first_name", "last_name", "postal_code"})
	@Test(enabled=false )
	public void verifyEmptyDescription(String first_name, String last_name, String postal_code) throws IOException {

	    CheckoutOverviewPage page = setupCheckoutOverview(first_name, last_name, postal_code);
	    
	    page.getItemsDescription().forEach(desc -> {

	        System.out.println("Description : " + desc.getText());

	        Assert.assertNotNull(desc.getText(), "Description should not be null");
	    });

	}
	
	
	@Parameters({"first_name", "last_name", "postal_code"})
	@Test(enabled=true )
	public void verifyDescriptionsNotEmpty(String first_name, String last_name, String postal_code) throws IOException {

		CheckoutOverviewPage page = setupCheckoutOverview(first_name, last_name, postal_code);
	    page.getItemsDescription().forEach(desc ->
	            Assert.assertFalse(desc.getText().isEmpty()));
	}
	
	
	
	
	
	
	
	
	
	

}
