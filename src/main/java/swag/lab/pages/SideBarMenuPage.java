package swag.lab.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SideBarMenuPage {
	  	WebDriver driver;
	    WebDriverWait wait;

	    public SideBarMenuPage(WebDriver driver) {
	        this.driver = driver;
	        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        PageFactory.initElements(driver, this);
	    }

	    // Open Menu Button
	    @FindBy(id = "react-burger-menu-btn")
	    WebElement menuBtn;

	    // Logout Button
	    @FindBy(id = "logout_sidebar_link")
	    WebElement logoutBtn;

	    // All Items
	    @FindBy(id = "inventory_sidebar_link")
	    WebElement allItems;

	    // About
	    @FindBy(id = "about_sidebar_link")
	    WebElement about;

	    // Reset
	    @FindBy(id = "reset_sidebar_link")
	    WebElement resetAppState;

    // use locator
	    By logoutLocator = By.id("logout_sidebar_link");
	    
	    
	    // Open Menu
	    public void openMenu() {
	        wait.until(ExpectedConditions.elementToBeClickable(menuBtn)).click();

	        // wait for sidebar container
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".bm-menu-wrap")));
	    }
    
	    
	    public LoginPage clickLogout() {

	        JavascriptExecutor js = (JavascriptExecutor) driver;

	        // click menu
	        js.executeScript("arguments[0].click();", menuBtn);

	        // wait sidebar visible
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".bm-menu")));

	        // click logout
	        WebElement logout = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout_sidebar_link")));

	        js.executeScript("arguments[0].click();", logout);

	        return new LoginPage(driver);
	    }
	    
	    
	    

	    // Menu visible validation
	    public boolean isMenuVisible() {
	        return logoutBtn.isDisplayed();
	    }

}
