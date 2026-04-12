package swag.lab.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductDetailsPage {

	  WebDriver driver;
	  
	  @FindBy(id="back-to-products")
	  WebElement backToInveontory;
	  
	  @FindBy(id="remove")
	  WebElement removeBtn;
	  
	  @FindBy(css=".inventory_details_img")
	  WebElement itemImage;
	  
	  @FindBy(css=".inventory_details_name")
	  WebElement itemName;
	  
	  @FindBy(css=".inventory_details_desc")
	  WebElement itemDescription;
	  
	  @FindBy(css=".inventory_details_price")
	  WebElement itemPrice;
	  
	  @FindBy(css=".shopping_cart_link")
	  WebElement cartLink;
	  

	  public ProductDetailsPage(WebDriver driver) {
	      this.driver = driver;
	      PageFactory.initElements(driver, this);
	  }

	  public String getCurrentUrl() {
	      return driver.getCurrentUrl();
	  }
	  
	  public void goToCart() {
		  cartLink.click();
	  }
	  
	  public void goToInventoryPage() {
		  backToInveontory.click();
	  }
	
	
	
	
}
