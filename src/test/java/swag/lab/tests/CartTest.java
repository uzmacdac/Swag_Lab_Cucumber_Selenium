package swag.lab.tests;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import swag.lab.base.BaseClass;
import swag.lab.pages.CartPage;
import swag.lab.pages.InventoryPage;
import swag.lab.pages.LoginPage;
import swag.lab.pages.ProductDetailsPage;

	public class CartTest extends BaseClass {

		LoginPage loginPage;
	    CartPage cartPage;
	    InventoryPage inventoryPage;
	    WebDriverWait wait;
	    JavascriptExecutor js;
	    
	    private CartPage setupCart() throws IOException {

	        loginPage = launchApplication();
	        inventoryPage = loginPage.loginIntoSwagLab();
	        inventoryPage.addProduct("Sauce Labs Backpack");

	        return inventoryPage.clickCart();
	    }
	    
		
		
		@BeforeTest
		public void setUp() throws IOException {
			
			loginPage = launchApplication();
			
			inventoryPage = loginPage.loginIntoSwagLab();
			
			ProductDetailsPage pdp = new ProductDetailsPage(driver);
			
			CartPage cart = new CartPage(driver);
			
			js = (JavascriptExecutor) driver;
			
			wait = new WebDriverWait(driver, Duration.ofSeconds(10));
						
		}
		
	    

	    @Test(priority = 1, enabled=false)
	    public void verifyCartPageLoaded() {
	    	CartPage cartPage = inventoryPage.clickCart();
	        
	        wait.until(ExpectedConditions.urlContains("cart"));

	        Assert.assertTrue(driver.getCurrentUrl().contains("cart"));

	       
	    }

	    @Test(priority = 2, enabled=false)
	    public void verifyCartItemsDisplayed() {
	    	// inventoryPage.addProduct("Sauce Labs Backpack");
	        // int before = inventoryPage.getCartCount();	

	        // Navigate to cart
	        CartPage cartPage = inventoryPage.clickCart();
	        //Assert.assertTrue(cartPage.cartItems.size() > 0);
	        
	        Assert.assertTrue(cartPage.getCartPageTitle().equalsIgnoreCase("Your Cart"), "Cart page is not displayed");
	    }

	    @Test(priority = 3, enabled=false)
	    public void verifyProductNameDisplayed() {
	    	 inventoryPage.addProduct("Sauce Labs Backpack");
	         int before = inventoryPage.getCartCount();	

	        // Navigate to cart
	        CartPage cartPage = inventoryPage.clickCart();
	        
	        Assert.assertTrue(cartPage.itemsName.size() > 0);
	    }

	    @Test(priority = 4, enabled=false, dependsOnMethods="verifyProductNameDisplayed")
	    public void verifyProductDescriptionDisplayed() throws IOException {

	        cartPage = setupCart();

	        Assert.assertTrue(cartPage.isProductDescriptionDisplayed());
	        
	        Assert.assertTrue(cartPage.itemsDesc.size() > 0);
	    }

	    @Test(priority = 5, enabled=false)
	    public void verifyProductPriceDisplayed() throws IOException {

	        cartPage = setupCart();
	        Assert.assertTrue(cartPage.isProductPriceDisplayed());
	        Assert.assertTrue(cartPage.itemsPrice.size() > 0);
	    }

	    @Test(priority = 6, enabled=false)
	    public void verifyProductQuantityDisplayed() throws IOException {
	    	int quantity = inventoryPage.getCartCount();
	    	System.out.println("Total Item in Cart : "+quantity);
	    	cartPage = setupCart();
	    	System.out.println("Total Item in Cart : "+quantity);
	        Assert.assertTrue(cartPage.itemsQuantity.size() > 0);
	    }

	    @Test(priority = 7, enabled=false)
	    public void verifyRemoveButtonPresent() throws IOException {

	        cartPage =  setupCart();
	        Assert.assertTrue(cartPage.removeBtn.size() > 0);
	    }

	    @Test(priority = 8, enabled=false)
	    public void verifyContinueShoppingButton() throws IOException {

	        cartPage =  setupCart();
	        cartPage.goBackToInventoryPage();
	        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));
	    }

	    @Test(priority = 9, enabled=false)
	    public void verifyCheckoutButton() throws IOException {

	        cartPage =  setupCart();
	        cartPage.goToCheckoutPage();
	        Assert.assertTrue(driver.getCurrentUrl().contains("checkout"));
	    }

	    @Test(priority = 10, enabled=false)
	    public void verifyRemoveSingleItem() throws IOException {
	    	inventoryPage.addProduct("Sauce Labs Bike Light");
	    	inventoryPage.addProduct("Sauce Labs Backpack");
	    	CartPage cartPage = inventoryPage.clickCart();
	        
	        int before = cartPage.cartItems.size();

	        cartPage.removeItemFromCart("Sauce Labs Backpack");

	        Assert.assertFalse(cartPage.isItemPresent("Sauce Labs Backpack")
	        );

	        int after = cartPage.cartItems.size();

	        Assert.assertTrue(after < before);
	    }
	    

	    @Test(priority = 11, enabled=false)
	    public void verifyRemoveMultipleItems() {
	    	
	    	 	inventoryPage.addProduct("Sauce Labs Bike Light");
	    	 	inventoryPage.addProduct("Sauce Labs Backpack");

	    	 	CartPage cartPage = inventoryPage.clickCart();

	    	    int before = cartPage.getCartItemsCount();
	    	    System.out.println("Total Item Before : " + before);

	    	    cartPage.removeItemFromCart("Sauce Labs Backpack");
	    	    cartPage.removeItemFromCart("Sauce Labs Bike Light");

	    	    int after = cartPage.getCartItemsCount();
	    	    System.out.println("Total Item After : " + after);

	    	    Assert.assertEquals(after, 0);
	    }

	    @Test(priority = 12, enabled=false)
	    public void verifyNavigateToProductDetail() throws IOException {

	        cartPage =  setupCart();

	        cartPage.goToProductDetail("Sauce Labs Backpack");

	        Assert.assertTrue(driver.getCurrentUrl().contains("inventory-item"));
	    }

	    @Test(priority = 13, enabled=false)
	    public void verifyContinueShoppingNavigation() throws IOException {

	        cartPage =  setupCart();

	        cartPage.goBackToInventoryPage();

	        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));
	    }

	    @Test(priority = 14, enabled=false)
	    public void verifyCheckoutNavigation() throws IOException {

	        cartPage = setupCart();

	        cartPage.goToCheckoutPage();

	        Assert.assertTrue(driver.getCurrentUrl().contains("checkout"));
	    }

	    @Test(priority = 15, enabled=false)
	    public void verifyCartNotEmpty() throws IOException {

	        cartPage = setupCart();

	        Assert.assertTrue(cartPage.cartItems.size() >= 1);
	    }

	    @Test(priority = 16, enabled=false)
	    public void verifyRemoveAllItems() {

	    	inventoryPage.addProduct("Sauce Labs Bike Light");
    	 	inventoryPage.addProduct("Sauce Labs Backpack");

    	 	CartPage cartPage = inventoryPage.clickCart();

    	    int before = cartPage.getCartItemsCount();
    	    System.out.println("Total Item Before : " + before);

    	    System.out.println("Total Item Before Remove : " + cartPage.cartItems.size());
    	    
    	    
    	    
//    	    for (int i = cartPage.removeBtn.size() - 1; i >= 0; i--) {
//    	        js.executeScript("arguments[0].click();", cartPage.removeBtn.get(i));
//    	    }
//    	    
    	    // more stable version 
    	    while (cartPage.getCartItemsCount() > 0) {
    	    	// the list size changes as the element is remove is going down like from starting it 2 then one element is remove the it became 1
    	        cartPage.removeBtn.get(0).click();
    	    }
    	    
    	    
	        int after = cartPage.getCartItemsCount();
    	    System.out.println("Total Item After : " + after);
	        
	        Assert.assertEquals(cartPage.cartItems.size(), 0);
	    }

	    @Test(priority = 17, enabled=false)
	    public void verifyCartPersistenceAfterRefresh() {

	    	inventoryPage.addProduct("Sauce Labs Bike Light");
    	 	inventoryPage.addProduct("Sauce Labs Backpack");

    	 	CartPage cartPage = inventoryPage.clickCart();

	        int before = cartPage.cartItems.size();

	        driver.navigate().refresh();

	        int after = cartPage.cartItems.size();

	        Assert.assertEquals(before, after);
	    }

	    @Test(priority = 18, enabled=false)
	    public void verifyBackButtonNavigation() {

	        cartPage = inventoryPage.clickCart();
	        
	        cartPage.goBackToInventoryPage();

	       // driver.navigate().back();

	        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));
	    }

	    @Test(priority = 19, enabled=false)
	    public void verifyMultipleProductsPresent() {

	    	inventoryPage.addProduct("Sauce Labs Bike Light");
    	 	inventoryPage.addProduct("Sauce Labs Backpack");

    	 	CartPage cartPage = inventoryPage.clickCart();

	        Assert.assertTrue(cartPage.cartItems.size() > 1);
	    }

	    @Test(priority = 20, enabled=true)
	    public void verifyProductDetailsLink() {

	    	inventoryPage.addProduct("Sauce Labs Bike Light");
	    	CartPage cartPage = inventoryPage.clickCart();
	    	cartPage.visibilityOfElementLocated(By.xpath("(//div[@class='inventory_item_name']/parent::a)[1]"));

	       // cartPage.itemsDetailLink.get(0).click();
	        
	        

	        Assert.assertTrue(driver.getCurrentUrl().contains("inventory-item"));
	    }

	}
