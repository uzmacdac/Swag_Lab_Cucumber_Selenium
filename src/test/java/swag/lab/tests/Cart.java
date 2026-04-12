package swag.lab.tests;


import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import swag.lab.base.BaseClass;
import swag.lab.pages.CartPage;
import swag.lab.pages.InventoryPage;
import swag.lab.pages.LoginPage;



/*
 
public class Cart extends BaseClass {

    LoginPage loginPage;
    InventoryPage inventoryPage;
    CartPage cartPage;

    @BeforeMethod
    public void setup() throws IOException {
        loginPage = launchApplication();
        inventoryPage = loginPage.loginIntoSwagLab();
    }

    private CartPage addSingleProduct() {
        inventoryPage.addProduct("Sauce Labs Backpack");
        return inventoryPage.clickCart();
    }

    private CartPage addMultipleProducts() {
        inventoryPage.addProduct("Sauce Labs Backpack");
        inventoryPage.addProduct("Sauce Labs Bike Light");
        inventoryPage.addProduct("Sauce Labs Bolt T-Shirt");
        return inventoryPage.clickCart();
    }

    // ===============================
    // Cart UI Test Cases
    // ===============================

    @Test
    public void TC_CART_001_VerifyCartPageLoads() {
        cartPage = inventoryPage.clickCart();
        Assert.assertTrue(cartPage.isCartPageLoaded());
    }

    @Test
    public void TC_CART_002_VerifyCartTitle() {
        cartPage = inventoryPage.clickCart();
        Assert.assertEquals(cartPage.getCartPageTitle(), "Your Cart");
    }

    @Test
    public void TC_CART_003_VerifyCartHeaders() {
        cartPage = inventoryPage.clickCart();
        Assert.assertTrue(cartPage.isCartHeaderDisplayed());
    }

    @Test
    public void TC_CART_004_VerifyContinueShoppingButton() {
        cartPage = inventoryPage.clickCart();
        Assert.assertTrue(cartPage.isContinueShoppingDisplayed());
    }

    @Test
    public void TC_CART_005_VerifyCheckoutButton() {
        cartPage = inventoryPage.clickCart();
        Assert.assertTrue(cartPage.isCheckoutDisplayed());
    }

    // ===============================
    // Product Validation
    // ===============================

    @Test
    public void TC_CART_006_VerifyProductNameDisplayed() {
        cartPage = addSingleProduct();
        Assert.assertTrue(cartPage.isProductNameDisplayed());
    }

    @Test
    public void TC_CART_007_VerifyProductDescription() {
        cartPage = addSingleProduct();
        Assert.assertTrue(cartPage.isProductDescriptionDisplayed());
    }

    @Test
    public void TC_CART_008_VerifyProductPrice() {
        cartPage = addSingleProduct();
        Assert.assertTrue(cartPage.isProductPriceDisplayed());
    }

    @Test
    public void TC_CART_009_VerifyProductImage() {
        cartPage = addSingleProduct();
        Assert.assertTrue(cartPage.isProductImageDisplayed());
    }

    @Test
    public void TC_CART_010_VerifyProductQuantity() {
        cartPage = addSingleProduct();
        Assert.assertEquals(cartPage.getCartItemCount(), 1);
    }

    // ===============================
    // Remove Test Cases
    // ===============================

    @Test
    public void TC_CART_011_RemoveSingleItem() {
        cartPage = addSingleProduct();
        cartPage.removeItemFromCart("Sauce Labs Backpack");
        Assert.assertEquals(cartPage.getCartItemCount(), 0);
    }

    @Test
    public void TC_CART_012_RemoveMultipleItems() {
        cartPage = addMultipleProducts();
        cartPage.removeAllItems();
        Assert.assertEquals(cartPage.getCartItemCount(), 0);
    }

    @Test
    public void TC_CART_013_VerifyBadgeUpdate() {
        cartPage = addSingleProduct();
        cartPage.removeItemFromCart("Sauce Labs Backpack");
        Assert.assertEquals(inventoryPage.getCartCount(), 0);
    }

    @Test
    public void TC_CART_014_RemoveLastItem() {
        cartPage = addSingleProduct();
        cartPage.removeItemFromCart("Sauce Labs Backpack");
        Assert.assertTrue(cartPage.isCartEmpty());
    }

    // ===============================
    // Continue Shopping
    // ===============================

    @Test
    public void TC_CART_015_ClickContinueShopping() {
        cartPage = addSingleProduct();
        cartPage.goBackToInventoryPage();
        Assert.assertTrue(inventoryPage.isInventoryPageLoaded());
    }

    @Test
    public void TC_CART_016_ContinueShoppingAfterRemove() {
        cartPage = addSingleProduct();
        cartPage.removeAllItems();
        cartPage.goBackToInventoryPage();
        Assert.assertTrue(inventoryPage.isInventoryPageLoaded());
    }

    // ===============================
    // Checkout Tests
    // ===============================

    @Test
    public void TC_CART_017_CheckoutWithItems() {
        cartPage = addSingleProduct();
        cartPage.goToCheckoutPage();
        Assert.assertTrue(cartPage.isCheckoutPageLoaded());
    }

    @Test
    public void TC_CART_018_CheckoutMultipleItems() {
        cartPage = addMultipleProducts();
        cartPage.goToCheckoutPage();
        Assert.assertTrue(cartPage.isCheckoutPageLoaded());
    }

    @Test
    public void TC_CART_019_CheckoutAfterRemove() {
        cartPage = addMultipleProducts();
        cartPage.removeItemFromCart("Sauce Labs Backpack");
        cartPage.goToCheckoutPage();
        Assert.assertTrue(cartPage.isCheckoutPageLoaded());
    }

    // ===============================
    // Multiple Product Tests
    // ===============================

    @Test
    public void TC_CART_020_AddMultipleProducts() {
        cartPage = addMultipleProducts();
        Assert.assertTrue(cartPage.getCartItemCount() >= 3);
    }

    @Test
    public void TC_CART_021_RemoveOneItem() {
        cartPage = addMultipleProducts();
        cartPage.removeItemFromCart("Sauce Labs Backpack");
        Assert.assertTrue(cartPage.getCartItemCount() == 2);
    }

    @Test
    public void TC_CART_022_OrderPersistence() {
        cartPage = addMultipleProducts();
        driver.navigate().refresh();
        Assert.assertTrue(cartPage.getCartItemCount() >= 1);
    }

    // ===============================
    // Negative Cases
    // ===============================

    @Test
    public void TC_CART_027_CheckoutEmptyCart() {
        cartPage = inventoryPage.clickCart();
        cartPage.goToCheckoutPage();
        Assert.assertTrue(cartPage.isCheckoutBlocked());
    }

    // ===============================
    // Navigation
    // ===============================

    @Test
    public void TC_CART_033_BackButton() {
        cartPage = addSingleProduct();
        driver.navigate().back();
        Assert.assertTrue(inventoryPage.isInventoryPageLoaded());
    }

    @Test
    public void TC_CART_034_RefreshPage() {
        cartPage = addSingleProduct();
        driver.navigate().refresh();
        Assert.assertTrue(cartPage.getCartItemCount() >= 1);
    }

    // ===============================
    // Performance
    // ===============================

    @Test
    public void TC_CART_035_LoadTime() {
        long start = System.currentTimeMillis();
        cartPage = inventoryPage.clickCart();
        long end = System.currentTimeMillis();
        Assert.assertTrue((end - start) < 3000);
    }

    @Test
    public void TC_CART_036_MultipleProductsPerformance() {
        cartPage = addMultipleProducts();
        Assert.assertTrue(cartPage.getCartItemCount() >= 3);
    }

    // ===============================
    // Edge Cases
    // ===============================

    @Test
    public void TC_CART_041_RefreshAfterRemove() {
        cartPage = addSingleProduct();
        cartPage.removeAllItems();
        driver.navigate().refresh();
        Assert.assertEquals(cartPage.getCartItemCount(), 0);
    }

    @Test
    public void TC_CART_042_AddSameProductTwice() {
        inventoryPage.addProduct("Sauce Labs Backpack");
        inventoryPage.addProduct("Sauce Labs Backpack");
        cartPage = inventoryPage.clickCart();
        Assert.assertTrue(cartPage.getCartItemCount() >= 1);
    }
}


*/