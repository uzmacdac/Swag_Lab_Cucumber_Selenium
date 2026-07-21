package stepdefinitions;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import context.Test_Context;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import swag.lab.base.BaseClass;
import swag.lab.pages.CartPage;
import swag.lab.pages.InventoryPage;
import swag.lab.pages.ProductDetailsPage;

public class InventoryStepDefinition extends BaseClass {

	private Test_Context context;

	private InventoryPage inventoryPage;
	private ProductDetailsPage pdp;
	private CartPage cartPage;

	int beforeCartCount;

	public InventoryStepDefinition(Test_Context context) {

		this.context = context;
	}

	
	@Then("user should land on inventory page")
	public void user_should_land_on_inventory_page() {

		Assert.assertNotNull(driver, "Driver is null");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		wait.until(ExpectedConditions.urlContains("inventory"));

		Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));
	}

	@Then("inventory page title should be {string}")
	public void inventory_page_title_should_be(String title) {

		inventoryPage = context.getInventoryPage();

		Assert.assertEquals(inventoryPage.getPageTitle(), title);
	}

	@Then("all inventory products should be displayed")
	public void all_inventory_products_should_be_displayed() {

		inventoryPage = context.getInventoryPage();

		Assert.assertTrue(inventoryPage.isProductListDisplayed());
	}

	@Then("total product count should be {int}")
	public void total_product_count_should_be(Integer count) {

		inventoryPage = context.getInventoryPage();

		Assert.assertEquals(inventoryPage.totalProductDisplayed(), count.intValue());
	}

	@Then("all inventory items should contain name description price and button")
	public void verify_all_elements() {

		inventoryPage = context.getInventoryPage();

		int count = inventoryPage.getItemCount();

		Assert.assertTrue(count > 0);

		for (int i = 0; i < count; i++) {

			Assert.assertTrue(inventoryPage.isItemDisplayed(i));

			Assert.assertFalse(inventoryPage.getItemName(i).isEmpty());

			Assert.assertFalse(inventoryPage.getItemDescription(i).isEmpty());

			Assert.assertFalse(inventoryPage.getItemPrice(i).isEmpty());

			Assert.assertFalse(inventoryPage.getButtonText(i).isEmpty());
		}
	}

	@Then("cart icon should be visible")
	public void cart_icon_should_be_visible() {

		inventoryPage = context.getInventoryPage();

		Assert.assertTrue(inventoryPage.isCartIconVisible());
	}

	@Then("hamburger menu should be visible")
	public void hamburger_menu_should_be_visible() {

		inventoryPage = context.getInventoryPage();

		Assert.assertTrue(inventoryPage.isHambergerMenuVisible());
	}

	@Then("inventory should display {int} products")
	public void inventory_should_display_products(Integer count) {

		inventoryPage = context.getInventoryPage();

		Assert.assertTrue(inventoryPage.areProductsDisplayed(count));
	}

	@Then("all product names should be unique")
	public void verify_product_names_unique() {

		inventoryPage = context.getInventoryPage();

		Assert.assertTrue(inventoryPage.areProductNamesUnique());
	}

	@Then("all product prices should be valid")
	public void verify_product_prices_valid() {

		inventoryPage = context.getInventoryPage();

		Assert.assertTrue(inventoryPage.arePricesValid());
	}

	@Then("all product images should load successfully")
	public void verify_product_images() {

		inventoryPage = context.getInventoryPage();

		Assert.assertTrue(inventoryPage.areImagesValid());
	}

	@When("user adds product {string} to cart")
	public void add_product_to_cart(String product) {

		inventoryPage = context.getInventoryPage();

		inventoryPage.addProductByName(product);
	}

	@When("user adds {int} products to cart")
	public void add_multiple_products(Integer count) {

		inventoryPage = context.getInventoryPage();

		inventoryPage.addMultipleProducts(count);
	}

	@Then("cart count should be {int}")
	public void cart_count_should_be(Integer expected) {

		inventoryPage = context.getInventoryPage();

		//Assert.assertEquals(inventoryPage.getCartCount(), expected.intValue());
		

	    int actual = inventoryPage.getCartCount();

	    System.out.println("Actual Cart Count = "+ actual);

	    Assert.assertEquals(actual, expected.intValue());
	}

	@When("user refreshes inventory page")
	public void refresh_page() {

		inventoryPage = context.getInventoryPage();

		beforeCartCount = inventoryPage.getCartCount();

		inventoryPage.refreshPage();
	}

	@Then("cart count should remain same")
	public void cart_should_remain_same() {

		inventoryPage = context.getInventoryPage();

		Assert.assertEquals(inventoryPage.getCartCount(), beforeCartCount);
	}

	@When("user removes product {string}")
	public void remove_product(String product) {

		inventoryPage = context.getInventoryPage();

		beforeCartCount = inventoryPage.getCartCount();
		
		 System.out.println("Before Remove Count = " + beforeCartCount);

		inventoryPage.removeProductByName(product);
	}

	@Then("cart count should decrease by 1")
	public void cart_count_decrease() {

		inventoryPage = context.getInventoryPage();

		//Assert.assertEquals(inventoryPage.getCartCount(), beforeCartCount - 1);
		int afterRemove = inventoryPage.getCartCount();

	    System.out.println("After Remove Count = " + afterRemove);

	    Assert.assertEquals(afterRemove, beforeCartCount - 1, "Cart count did not decrease");
	}

	@Then("application should not crash")
	public void application_should_not_crash() {

		Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));
	}

	@When("user removes product {string} multiple times")
	public void remove_multiple_times(String product) {

		inventoryPage = context.getInventoryPage();

		for (int i = 0; i < 3; i++) {
			inventoryPage.removeProductByName(product);
		}
	}

	@Then("add to cart button should be visible for {string}")
	public void verify_add_to_cart(String product) {

		inventoryPage = context.getInventoryPage();

		Assert.assertTrue(inventoryPage.isProductRemoved(product));
	}

	@When("user sorts products by {string}")
	public void sort_products(String option) {

		inventoryPage = context.getInventoryPage();

		inventoryPage.selectSortOption(option);
	}

	@Then("products should be sorted ascending")
	public void names_sorted_ascending() {

		inventoryPage = context.getInventoryPage();

		List<String> names = inventoryPage.getProductNames();

		Assert.assertTrue(inventoryPage.isSortedAscending(names));
	}

	@Then("products should be sorted descending")
	public void names_sorted_descending() {

		inventoryPage = context.getInventoryPage();

		List<String> names = inventoryPage.getProductNames();

		Assert.assertTrue(inventoryPage.isSortedDescending(names));
	}

	@Then("product prices should be sorted ascending")
	public void prices_sorted_ascending() {

		inventoryPage = context.getInventoryPage();

		List<Double> prices = inventoryPage.getProductPrices();

		Assert.assertTrue(inventoryPage.isSortedAscending(prices));
	}

	@Then("product prices should be sorted descending")
	public void prices_sorted_descending() {

		inventoryPage = context.getInventoryPage();

		List<Double> prices = inventoryPage.getProductPrices();

		Assert.assertTrue(inventoryPage.isSortedDescending(prices));
	}

	@When("user clicks on product {string}")
	public void click_product(String product) {

		inventoryPage = context.getInventoryPage();

		inventoryPage.clickProduct(product);
	}

	@Then("user should navigate to product details page")
	public void navigate_product_page() {

		pdp = new ProductDetailsPage(driver);

		Assert.assertTrue(pdp.getCurrentUrl().contains("inventory-item"));
	}

	@When("user clicks on cart")
	public void click_cart() {

		inventoryPage = context.getInventoryPage();

		cartPage = inventoryPage.clickCart();
	}

	@Then("user should navigate to cart page")
	public void navigate_cart_page() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		wait.until(ExpectedConditions.urlContains("cart"));

		Assert.assertTrue(driver.getCurrentUrl().contains("cart"));
	}

	@When("user opens hamburger menu")
	public void open_menu() {

		inventoryPage = context.getInventoryPage();

		inventoryPage.openMenu();
	}

	@Then("all sidebar menu options should be visible")
	public void verify_menu_options() {

		inventoryPage = context.getInventoryPage();

		Assert.assertTrue(inventoryPage.isMenuOptionsVisible());
	}

	@When("user navigates to cart and comes back")
	public void cart_navigation_back() {

		inventoryPage = context.getInventoryPage();

		beforeCartCount = inventoryPage.getCartCount();

		inventoryPage.clickCart();

		driver.navigate().back();
	}

	@Then("inventory page should not contain broken images")
	public void no_broken_images() {

		inventoryPage = context.getInventoryPage();

		Assert.assertFalse(inventoryPage.hasBrokenImages());
	}

	@When("user rapidly adds product {string} multiple times")
	public void rapid_add_clicks(String product) {

		inventoryPage = context.getInventoryPage();

		for (int i = 0; i < 5; i++) {
			inventoryPage.addProduct(product);
		}
	}

	@Then("all product names length should be less than 100")
	public void long_product_names() {

		inventoryPage = context.getInventoryPage();

		inventoryPage.getProductNames().forEach(name -> Assert.assertTrue(name.length() < 100));
	}
}