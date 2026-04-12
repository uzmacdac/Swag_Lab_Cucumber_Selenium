package swag.lab.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {
	
	WebDriver driver;
	
	WebDriverWait wait;
	
	JavascriptExecutor js;
	
	@FindBy(css="span.title")
	public WebElement cartPageTitle;
	
	@FindBy(css=".cart_item")
	public List<WebElement> cartItems;

	@FindBy(css=".cart_quantity")
	public List<WebElement> itemsQuantity;
	
	@FindBy(css=".inventory_item_name")
	public List<WebElement> itemsName;
	
	@FindBy(css="div.inventory_item_desc")
	public List<WebElement> itemsDesc;
	
	@FindBy(css=".inventory_item_price")
	public List<WebElement> itemsPrice;
	
	@FindBy(css=".cart_button")
	public List<WebElement> removeBtn;
	
	@FindBy(id="continue-shopping")
	public WebElement continueShoppingBtn;
	
	@FindBy(id="checkout")
	public WebElement checkoutBtn;
	
	@FindBy(xpath="//div[@class='inventory_item_name']/parent::a")
	public List<WebElement> itemsDetailLink;
	
	
    public CartPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        js = (JavascriptExecutor)driver;
        PageFactory.initElements(driver, this);
    }
    
    
    // Wait for cart page load
    public void waitForCartPage() {
        wait.until(ExpectedConditions.visibilityOfAllElements(cartItems));
    }
    
    
    public String getCartPageTitle() {
    	return cartPageTitle.getText();
    }
    
    public int getCartItemsCount() {
        //waitForCartPage();
        return cartItems.size();
    }
    
    public int getCartItemCount() {
        waitForCartPage();
        return cartItems.size();
    }
    
    public void visibilityOfElementLocated(By locator) {
    	wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).click();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
    
    public CheckoutInformationPage goToCheckoutPage() {
    	
    	//checkoutBtn.click();
    	//js.executeScript("arguments[0].click();", checkoutBtn);
    	//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("checkout"))).click();
    	
    	wait.until(ExpectedConditions.visibilityOf(checkoutBtn));
    	js.executeScript("arguments[0].click();", checkoutBtn);
    	
        wait.until(ExpectedConditions.urlContains("checkout-step-one"));
    	return new CheckoutInformationPage(driver);
    }
    
    public void goBackToInventoryPage() {
    	continueShoppingBtn.click();
    }
    
    
    public boolean isProductDescriptionDisplayed() {

        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(itemsDesc));
            return itemsDesc.size() > 0;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isProductPriceDisplayed() {

        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(itemsPrice));
            return itemsPrice.size() > 0;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isItemPresent(String itemName) {

        return cartItems.stream()
                .anyMatch(item -> item
                        .findElement(By.cssSelector(".inventory_item_name"))
                        .getText()
                        .equalsIgnoreCase(itemName));
    }
    
    /*
     public void removeItemFromCart(String cartItemName) {

    wait.until(ExpectedConditions.visibilityOfAllElements(cartItems));

    for (WebElement item : cartItems) {

        String itemName = item
                .findElement(By.cssSelector(".inventory_item_name"))
                .getText();

        if (itemName.equalsIgnoreCase(cartItemName)) {

            WebElement removeBtn = item.findElement(By.tagName("button"));

            wait.until(ExpectedConditions.elementToBeClickable(removeBtn));

            try {
                removeBtn.click();
            } catch (Exception e) {

                // fallback JS click
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", removeBtn);
            }

            // wait for item removal
            wait.until(ExpectedConditions.stalenessOf(item));

            break;
        }
    }
}
     */
    
    
    
    
    /*
     public void removeItemFromCart(String cartItemName) {

    wait.until(ExpectedConditions.visibilityOfAllElements(cartItems));

    for (WebElement item : cartItems) {

        String itemName = item
                .findElement(By.cssSelector(".inventory_item_name"))
                .getText();

        if (itemName.equalsIgnoreCase(cartItemName)) {

            WebElement removeBtn = item
                    .findElement(By.cssSelector("button[data-test^='remove']"));

            wait.until(ExpectedConditions.elementToBeClickable(removeBtn)).click();

            wait.until(ExpectedConditions.stalenessOf(item));

            break;
        }
    }
}
 */
    
    
 /*
  public void removeItemFromCart(String cartItemName) {

    wait.until(ExpectedConditions.visibilityOfAllElements(cartItems));

    int beforeCount = cartItems.size();

    for (WebElement item : cartItems) {

        String itemName = item
                .findElement(By.cssSelector(".inventory_item_name"))
                .getText();

        if (itemName.equalsIgnoreCase(cartItemName)) {

            WebElement removeBtn =
                    item.findElement(By.cssSelector("button"));

            wait.until(ExpectedConditions.elementToBeClickable(removeBtn));

            try {
                removeBtn.click();
            } catch (Exception e) {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", removeBtn);
            }

            // Wait until cart count decreases
            wait.until(driver -> cartItems.size() < beforeCount);

            break;
        }
    }
}
  */

    public void removeItemFromCart(String cartItemName) {

        int beforeCount = getCartItemCount();

        WebElement item = cartItems.stream()
                .filter(e -> e.findElement(By.cssSelector(".inventory_item_name"))
                        .getText()
                        .equalsIgnoreCase(cartItemName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Item not found: " + cartItemName));

        WebElement removeBtn = item.findElement(By.tagName("button"));

        //wait.until(ExpectedConditions.elementToBeClickable(removeBtn)).click();
        
        js.executeScript("arguments[0].click();", removeBtn);
        wait.until(driver -> getCartItemsCount() < beforeCount);
    }
    
    
    public void removeItemFromCartPage(String productName) {

        int before = getCartItemCount();

        driver.findElement(By.xpath("//div[text()='" + productName + "']/ancestor::div[@class='cart_item']//button"))
                .click();

        wait.until(ExpectedConditions.numberOfElementsToBeLessThan(
                By.className("cart_item"), before));
    }
    
    
    public ProductDetailsPage goToProductDetail(String cartItemName) {
    	for (WebElement item : cartItems) {

            String itemName = item.findElement(By.cssSelector(".inventory_item_name")).getText();

            if (itemName.equalsIgnoreCase(cartItemName)) {

                // Click the anchor tag wrapping the product name
                WebElement productLink = item.findElement(By.cssSelector("a"));

                //productLink.click();
                
                js.executeScript("arguments[0].click();", productLink);
                
                
                // or recommended 
               // item.findElement(By.cssSelector(".cart_item_label a")).click();
                
                
                return new ProductDetailsPage(driver);
            }
        }
		throw new RuntimeException("Product not found: " + cartItemName);
    	
    }
    
    
    
    
    
    
   
    
}
