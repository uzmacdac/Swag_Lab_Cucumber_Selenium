package swag.lab.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import swag.lab.base.BaseClass;
import swag.lab.pages.CartPage;
import swag.lab.pages.InventoryPage;
import swag.lab.pages.LoginPage;
import swag.lab.pages.ProductDetailsPage;

public class InventoryTests extends BaseClass{
	
	LoginPage loginPage;

	InventoryPage inventoryPage;
	
	WebDriverWait wait;
	
	@BeforeTest
	public void setUp() throws IOException {
		
		loginPage = launchApplication();
		
		inventoryPage = loginPage.loginIntoSwagLab();
		
		ProductDetailsPage pdp = new ProductDetailsPage(driver);
		
		CartPage cart = new CartPage(driver);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		
		
		
	}
	
	
	@Test(enabled=false, priority = 1)
	public void VerifyUserLoginAndLandToInventoryPage() throws IOException {
		
		System.out.print("Landing url : "+loginPage.getCurrentUrlOfLandingPage() );
		
		Assert.assertTrue("https://www.saucedemo.com/inventory.html".equalsIgnoreCase(loginPage.getCurrentUrlOfLandingPage()));
		
	}

	@Test(enabled=true,  groups = { "smoke", "Regression"})
	public void verifyPageTitle() {
		
		// Inventory Page Title
		String title = "Products";
		System.out.println("Inventory Page Title : "+inventoryPage.getPageTitle());
		
		// Verify Inventory page title
		Assert.assertEquals(title, inventoryPage.getPageTitle());
		
	}
	
	@Test(enabled=false)
	public void VerifyProductListIsDisplayed() {
		System.out.println("Total Items : "+inventoryPage.totalProductDisplayed());
		
		Assert.assertEquals(inventoryPage.totalProductDisplayed(), 6);
		Assert.assertTrue(inventoryPage.isProductListDisplayed());
	}
	
	@Test( enabled = true)
	public void VerifyAllElementArePresent() {
		int count = inventoryPage.getItemCount();
		
        Assert.assertTrue(count > 0, "No items found");

        for (int i = 0; i < count; i++) {

            // Validate visibility
            Assert.assertTrue(inventoryPage.isItemDisplayed(i), "Item not visible at index: " + i);

            String name = inventoryPage.getItemName(i);
            String desc = inventoryPage.getItemDescription(i);
            String price = inventoryPage.getItemPrice(i);
            String button = inventoryPage.getButtonText(i);

            // Validate not empty
            Assert.assertFalse(name.isEmpty(), "Name empty at index: " + i);
            Assert.assertFalse(desc.isEmpty(), "Description empty at index: " + i);
            Assert.assertFalse(price.isEmpty(), "Price empty at index: " + i);
            Assert.assertFalse(button.isEmpty(), "Button text empty at index: " + i);

            // Print
            System.out.println("Item " + (i + 1));
            System.out.println("Name: " + name);
            System.out.println("Description: " + desc);
            System.out.println("Price: " + price);
            System.out.println("Button: " + button);
            System.out.println("-----------------------------");
        }
	
	}
	
	@Test(enabled=true,  groups = { "smoke" })
	public void VerifyCartIconIsVisible() {
		
		System.out.println("Is Cart Icon Visible : "+inventoryPage.isCartIconVisible());
		
		Assert.assertTrue(inventoryPage.isCartIconVisible());
	}
	
	@Test(enabled=true,  groups = { "smoke"})
	public void VerifyHamburgerMenuIsVisible() {
			
		System.out.println("Is Hamburger Menu Visible : "+inventoryPage.isHambergerMenuVisible());
		
		Assert.assertTrue(inventoryPage.isHambergerMenuVisible());
		
		
	}
	
	@Test(enabled=true,  groups = { "smoke", "Regression" })
	 public void verifyAllProductsDisplayed() {
		 Assert.assertTrue(inventoryPage.areProductsDisplayed(6),  "Product count mismatch");
	 }

	@Test(enabled=false)
	 public void verifyProductNamesUnique() {
		 Assert.assertTrue(inventoryPage.areProductNamesUnique(),  "Duplicate product names found");
	    }

	@Test(enabled=false)
	 public void verifyProductPricesValid() {
	        Assert.assertTrue(inventoryPage.arePricesValid(), "Invalid product price found");
	 }

	@Test(enabled=false)
	 public void verifyProductImagesNotBroken() {
	      Assert.assertTrue(inventoryPage.areImagesValid(), "Broken product images found");
	 }
	    
	 // ✅ Add single product → cart badge = 1
	 @Test(enabled=true,  groups = { "smoke", "Regression"})
	 public void verifySingleProductAddToCart() {
	      inventoryPage.addProductByName("Sauce Labs Backpack");

        Assert.assertEquals(inventoryPage.getCartCount(), 1);
     }

	    // ✅ Add multiple products → correct count
    @Test(enabled=true,  groups = { "Regression" })
    public void verifyMultipleProductsAddToCart() {
        inventoryPage.addMultipleProducts(3);

        Assert.assertEquals(inventoryPage.getCartCount(), 3);
    }

	    // ✅ Verify button changes: Add → Remove
//	    @Test(enabled=true)
//	    public void verifyButtonChanges() {
//	        String product = "Sauce Labs Backpack";
//
//	        inventoryPage.addProductByName(product);
//
//	        Assert.assertTrue(inventoryPage.isProductAdded(product));
//	    }

	    // ✅ Verify cart persists after refresh
    @Test(enabled=true,  groups = { "Regression" })
	public void verifyCartPersistenceAfterRefresh() {
    	inventoryPage.addProductByName("Sauce Labs Bolt T-Shirt");
        int before = inventoryPage.getCartCount();

	    inventoryPage.refreshPage();

	    int after = inventoryPage.getCartCount();

	    Assert.assertEquals(after, before);
	}
	
	    
	    
	// ✅ Remove product → cart badge decreases
	@Test(enabled=false)
	public void verifyRemoveProductUpdatesCartCount() {

	    String product = "Sauce Labs Backpack";

	    // Add first
	    inventoryPage.addProductByName(product);
	    int beforeRemove = inventoryPage.getCartCount();

	     // Remove
	    inventoryPage.removeProductByName(product);
	    int afterRemove = inventoryPage.getCartCount();

	    Assert.assertEquals(afterRemove, beforeRemove - 1, "Cart count did not decrease after removal");
	    
	}
	
	
	// Remove Edge Cases
	@Test(enabled=false)
	public void removeNonExistingItem() {
	    inventoryPage.removeProductByName("Invalid Product"); // should not crash
	}
	
		
	@Test
	public void rapidRemoveClicks() {

	    String product = "Sauce Labs Backpack";

	    inventoryPage.addProduct(product);

	    for (int i = 0; i < 3; i++) {
	        inventoryPage.removeProductByName(product);
	    }

	    Assert.assertEquals(inventoryPage.getCartCount(), 0);
	}
	

	  // ✅ Verify button toggles back to "Add to cart"
    @Test(enabled=false)
    public void verifyButtonTogglesBackToAddToCart() {

	     String product = "Sauce Labs Bike Light";

	     // Add then remove
	     inventoryPage.addProductByName(product);
	     inventoryPage.removeProductByName(product);

	     Assert.assertTrue(inventoryPage.isProductRemoved(product),  "Button did not toggle back to 'Add to cart'");
	}
	
	
	
    // ✅ Name (A → Z)
    @Test(enabled=false)
    public void verifySortNameAscending() {
        inventoryPage.selectSortOption("Name (A to Z)");

        List<String> names = inventoryPage.getProductNames();

        Assert.assertTrue(inventoryPage.isSortedAscending(names), "Names not sorted A → Z");
    }

    // ✅ Name (Z → A)
    @Test(enabled=false)
    public void verifySortNameDescending() {
        inventoryPage.selectSortOption("Name (Z to A)");

        List<String> names = inventoryPage.getProductNames();

        Assert.assertTrue(inventoryPage.isSortedDescending(names), "Names not sorted Z → A");
    }

    // ✅ Price (Low → High)
    @Test(enabled=false)
    public void verifySortPriceLowToHigh() {
        inventoryPage.selectSortOption("Price (low to high)");

        List<Double> prices = inventoryPage.getProductPrices();

        Assert.assertTrue(inventoryPage.isSortedAscending(prices), "Prices not sorted Low → High");
    }

    // ✅ Price (High → Low)
    @Test(enabled=false)
    public void verifySortPriceHighToLow() {
        inventoryPage.selectSortOption("Price (high to low)");

        List<Double> prices = inventoryPage.getProductPrices();

        Assert.assertTrue(inventoryPage.isSortedDescending(prices), "Prices not sorted High → Low");
    }
	
    // ✅ Click product → navigate to details page
    @Test(enabled=false)
    public void verifyProductNavigation() {
        inventoryPage.clickProduct("Sauce Labs Backpack");

        ProductDetailsPage pdp = new ProductDetailsPage(driver);

        Assert.assertTrue(pdp.getCurrentUrl().contains("inventory-item"),  "Did not navigate to product details page");
    }

    // ✅ Click cart → navigate to cart page
    @Test(enabled=false)
    public void verifyCartNavigation() {

        CartPage cart = inventoryPage.clickCart();
          
        wait.until(ExpectedConditions.urlContains("cart"));

        Assert.assertTrue(driver.getCurrentUrl().contains("cart"));
    }

    // ✅ Menu options visible
    @Test(enabled=false)
    public void verifyMenuOptions() {
        inventoryPage.openMenu();

        Assert.assertTrue(inventoryPage.isMenuOptionsVisible(),  "Menu options not visible");
    }

    // ✅ Cart persistence
    @Test(enabled=false)
    public void verifyCartPersistence() {
        inventoryPage.addProduct("Sauce Labs Backpack");

        int before = inventoryPage.getCartCount();	

        // Navigate to cart
        inventoryPage.clickCart();

        // Back to inventory
        driver.navigate().back();

        int after = inventoryPage.getCartCount();

        Assert.assertEquals(after, before, "Cart did not persist");
    }

    
    // Broken Image Validation
    @Test(enabled= false)
    public void verifyNoBrokenImages() {
        Assert.assertFalse(inventoryPage.hasBrokenImages(), "Broken images found");
    }
    
    
	// Rapid Click
    @Test(enabled= false)
    public void verifyRapidAddClicks() {
        String product = "Sauce Labs Backpack";

        for (int i = 0; i < 5; i++) {
            inventoryPage.addProduct(product);
        }

        Assert.assertEquals(inventoryPage.getCartCount(), 1);
    }
	
    // Same Item Multiple Times
    @Test(enabled= false)
    public void verifySingleInstanceInCart() {
        inventoryPage.addProduct("Sauce Labs Backpack");
        inventoryPage.addProduct("Sauce Labs Backpack");

        Assert.assertEquals(inventoryPage.getCartCount(), 1);
    }
    
    
    @Test(enabled= false)
    public void verifyLongProductNames() {
        inventoryPage.getProductNames()
            .forEach(name -> Assert.assertTrue(name.length() < 100));
    }
    
    
//    @Test
//    public void verifyPriceConsistency() {
//        String product = "Sauce Labs Backpack";
//
//        double listPrice = inventoryPage.getPriceByName(product);
//
//        inventoryPage.clickProduct(product);
//
//        double detailPrice = productDetailsPage.getPrice();
//
//        Assert.assertEquals(detailPrice, listPrice);
//    }
//    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
	
}
