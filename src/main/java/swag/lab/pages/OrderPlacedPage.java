package swag.lab.pages;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPlacedPage {
	
	WebDriver driver ;
	LoginPage loginPage;
    CartPage cartPage;
    InventoryPage inventoryPage;
    CheckoutOverviewPage checkoutOverviewPage ;
    WebDriverWait wait;
    JavascriptExecutor js;
	
	@FindBy(css=".title")
	WebElement pageTitle;
	
	@FindBy(css=".complete-header")
	WebElement thankMsg;
	
	@FindBy(css="div.complete-text")
	WebElement completeText;
	
	@FindBy(id="back-to-products")
	WebElement backToProductBtn;
	
	
	public OrderPlacedPage(WebDriver driver) {
		this.driver = driver;
        PageFactory.initElements(driver, this);
        js = (JavascriptExecutor) driver;
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
	}

	public void goBackToProduct() {
		
		// https://www.saucedemo.com/inventory.html
		
		js.executeScript("arguments[0].click();", backToProductBtn);
		//backToProductBtn.click();
	}


	public String getPageTitle() {
		return pageTitle.getText();
	}
	
	public String getDispatchText() {
		return completeText.getText();
	}
	
	public String getThankMsg() {
		return thankMsg.getText();
	}
	
	

}
