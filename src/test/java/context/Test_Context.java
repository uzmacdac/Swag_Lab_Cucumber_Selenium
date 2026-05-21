package context;

import org.openqa.selenium.WebDriver;

import swag.lab.base.BaseClass;
import swag.lab.pages.CartPage;
import swag.lab.pages.InventoryPage;
import swag.lab.pages.LoginPage;
import swag.lab.pages.ProductDetailsPage;

public class Test_Context extends BaseClass {
	private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private CartPage cartPage;
    private ProductDetailsPage productDetailsPage;

    public LoginPage getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(LoginPage loginPage) {
        this.loginPage = loginPage;
    }

    public InventoryPage getInventoryPage() {
        return inventoryPage;
    }

    public void setInventoryPage(InventoryPage inventoryPage) {
        this.inventoryPage = inventoryPage;
    }

    public CartPage getCartPage() {
        return cartPage;
    }

    public void setCartPage(CartPage cartPage) {
        this.cartPage = cartPage;
    }

    public ProductDetailsPage getProductDetailsPage() {
        return productDetailsPage;
    }

    public void setProductDetailsPage(ProductDetailsPage productDetailsPage) {
        this.productDetailsPage = productDetailsPage;
    }

}
