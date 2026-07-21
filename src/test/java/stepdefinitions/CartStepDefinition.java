package stepdefinitions;

import org.testng.Assert;

import context.Test_Context;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import swag.lab.pages.CartPage;
import swag.lab.pages.InventoryPage;
import swag.lab.pages.ProductDetailsPage;

public class CartStepDefinition {
	
	private Test_Context context;

	private InventoryPage inventoryPage;
	private ProductDetailsPage pdp;
	private CartPage cartPage;

	int products_added = 3;
	
	int beforeCartCount;

	public CartStepDefinition(Test_Context context) {

		this.context = context;
	}
	
	
	@And("user has added products to cart")
	public void user_has_added_products_to_cart() {
		InventoryPage inventoryPage = context.getInventoryPage();
			
//		inventoryPage.addMultipleProducts(products_added);
//		
//		int actual = inventoryPage.getCartCount();
//
//	    System.out.println("Actual Cart Count = "+ actual);
//
//	    Assert.assertEquals(actual, products_added);
		inventoryPage.addProductByName("Sauce Labs Backpack");
        inventoryPage.addProductByName("Sauce Labs Bike Light");

        Assert.assertEquals(inventoryPage.getCartCount(), 2,  "Products were not added to cart");
		
	}
	
	@When("user click on Cart Icon")
	public void user_click_on_cart_icon() {
		InventoryPage inventoryPage = context.getInventoryPage();
		context.setCartPage(inventoryPage.clickCart());
	}
	
	@Then("user is on Cart Page")
	public void user_is_on_Cart_Page() {
		Assert.assertEquals(cartPage.getCartPageTitle(), "Your Cart", "User is not on Cart Page");

        Assert.assertTrue(cartPage.getCurrentUrl().contains("cart"), "Cart URL is incorrect");
		
	}
	
	

}
