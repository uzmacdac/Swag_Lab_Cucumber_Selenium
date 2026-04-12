package swag.lab.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutOverviewPage {
	
	WebDriver driver;
	
	WebDriverWait wait;
    JavascriptExecutor js;
	
	@FindBy(xpath="//div[@data-test='item-quantity']")
	List<WebElement> itemsQuantity;
	
	@FindBy(css=".inventory_item_name")
	List<WebElement> itemsName;
	
	@FindBy(css=".inventory_item_desc")
	List<WebElement> itemsDescription;
	
	@FindBy(css=".inventory_item_price")
	List<WebElement> itemsPrice;
	
	// <div class="summary_subtotal_label" data-test="subtotal-label" xpath="1">Item total: $55.97</div>
	@FindBy(css=".summary_subtotal_label")
	WebElement itemTotal;
	
	@FindBy(css=".summary_tax_label")
	WebElement tax;
	
	// <div class="summary_total_label" data-test="total-label" css="1">Total: $60.45</div>
	@FindBy(css=".summary_total_label")
	WebElement totalPrice;
	
	@FindBy(id="cancel")
	WebElement cancelBtn;
	
	@FindBy(id="finish")
	WebElement finishBtn;
	

	public CheckoutOverviewPage(WebDriver driver) {
		 this.driver = driver;
		 js = (JavascriptExecutor) driver;		
		 wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	     PageFactory.initElements(driver, this);
	}

	
	public List<WebElement> getItemsName() {
	    return itemsName;
	}

	public List<WebElement> getItemsDescription() {
	    return itemsDescription;
	}

	public List<WebElement> getItemsPrice() {
	    return itemsPrice;
	}

	public List<WebElement> getItemsQuantity() {
	    return itemsQuantity;
	}

	 public int getItemsCount() {
	     return itemsName.size();
	 }

	 public String getItemTotal() {
	     return itemTotal.getText();
	 }

	 public String getTax() {
	    return tax.getText();
	 }

	 public String getTotalPrice() {
	     return totalPrice.getText();
	     
	 }
	
	public OrderPlacedPage clickOnFinishBtn() {
		// https://www.saucedemo.com/checkout-complete.html
		
		// https://www.saucedemo.com/checkout-complete.html
		
		//finishBtn.click();
		js.executeScript("arguments[0].click();", finishBtn);
		
		return new OrderPlacedPage(driver);
	}
	
	
	public void clickCancel() {
        //cancelBtn.click();
        js.executeScript("arguments[0].click();", cancelBtn);
    }
	
	
	public double getItemTotalValue() {
	    return Double.parseDouble(
	        itemTotal.getText().replace("Item total: $", ""));
	}

	public double getTaxValue() {
	    return Double.parseDouble(
	        tax.getText().replace("Tax: $", ""));
	}

	public double getTotalValue() {
	    return Double.parseDouble(
	        totalPrice.getText().replace("Total: $", ""));
	}
	
	
	public boolean isTotalCorrect() {

	    double subtotal = getItemTotalValue();
	    double tax = getTaxValue();
	    double total = getTotalValue();

	    return Math.abs(total - (subtotal + tax)) < 0.01;
	}
	
	
	
	
	
	
}
