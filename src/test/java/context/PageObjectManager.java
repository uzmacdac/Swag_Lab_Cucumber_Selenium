package context;

import org.openqa.selenium.WebDriver;

import swag.lab.pages.CartPage;
import swag.lab.pages.InventoryPage;
import swag.lab.pages.LoginPage;
import swag.lab.pages.ProductDetailsPage;

public class PageObjectManager {

	private WebDriver driver;

	public PageObjectManager(WebDriver driver) {
		this.driver = driver;
	}

	public LoginPage getLoginPage() {
		return new LoginPage(driver);
	}

	public InventoryPage getInventoryPage() {
		return new InventoryPage(driver);
	}

	public CartPage getCartPage() {
		return new CartPage(driver);
	}

	public ProductDetailsPage getProductDetailsPage() {
		return new ProductDetailsPage(driver);
	}
}
