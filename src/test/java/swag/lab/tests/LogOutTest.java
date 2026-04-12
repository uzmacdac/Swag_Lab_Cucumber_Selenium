package swag.lab.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import swag.lab.base.BaseClass;
import swag.lab.pages.CartPage;
import swag.lab.pages.InventoryPage;
import swag.lab.pages.LoginPage;
import swag.lab.pages.ProductDetailsPage;
import swag.lab.pages.SideBarMenuPage;

public class LogOutTest extends BaseClass{

	

	LoginPage loginPage;

	InventoryPage inventoryPage;
	
	@BeforeTest
	public void setUp() throws IOException {
		
		loginPage = launchApplication();
		
		inventoryPage = loginPage.loginIntoSwagLab();
		
		ProductDetailsPage pdp = new ProductDetailsPage(driver);
		
		CartPage cart = new CartPage(driver);
		
		
		
		
	}
	
	@Test(enabled=false)
	public void verifyLogout() {
        //inventoryPage.openMenu();
        inventoryPage.clickLogout();

        Assert.assertTrue(driver.getCurrentUrl().contains("saucedemo.com"), "User not redirected to login page");
    }
	
	
	@Test(enabled=false)
	public void verifyUserLogout() {

	    SideBarMenuPage sideBar = inventoryPage.openSideBar();
	    sideBar.clickLogout();

	    Assert.assertTrue(driver.getCurrentUrl().contains("saucedemo"));
	}
	
	
	
	 // ✅ Logout + login → cart reset
    @Test(enabled=true)
    public void verifyCartResetAfterLogout() throws IOException {

    inventoryPage.addProduct("Sauce Labs Backpack");

    SideBarMenuPage sideBar = inventoryPage.openSideBar();
    LoginPage loginPage = sideBar.clickLogout();

    inventoryPage = loginPage.loginIntoSwagLab();

    Assert.assertEquals(inventoryPage.getCartCount(), 0);
}

	
	
	
	
	
	
}
