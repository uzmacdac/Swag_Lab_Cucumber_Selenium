package swag.lab.pages;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.qameta.allure.Step;
import swag.lab.base.BaseClass;

public class LoginPageAllure {

WebDriver driver;
	
	WebDriverWait wait ;
	
	Properties property = new Properties();
	
	@FindBy(id="user-name")
	public WebElement username;
	
	@FindBy(id="password")
	public WebElement password;
	
	@FindBy(id="login-button")
	public WebElement loginBtn;
	
	@FindBy(xpath="//h3[@data-test='error']")
	public WebElement error;
	
	
	@FindBy(xpath="//button[@class='error-button' and @data-test='error-button']")
	public WebElement errorBtn;
	
	
	
	public LoginPageAllure(WebDriver driver) {
		
		this.driver = driver;
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		
		PageFactory.initElements(driver, this);
	}
	
	
	@Step("Enter Username")
	public void enterUsername(String name) {
		username.sendKeys(name);
	}

	@Step("Enter Password")
	public void enterPassword(String passwords) {
		password.sendKeys(passwords);
	}
	
	
	public void goToSwagLab() throws IOException {
		
		
		driver.get(new BaseClass().getPropertiesObject().getProperty("baseUrl"));
		
		//driver.get("https://www.saucedemo.com/");
	}
	
	public InventoryPage loginIntoSwagLab() throws IOException {
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
		wait.until(ExpectedConditions.visibilityOf(username));
		wait.until(ExpectedConditions.visibilityOf(password));
		
		username.sendKeys(new BaseClass().getPropertiesObject().getProperty("username"));
		password.sendKeys(new BaseClass().getPropertiesObject().getProperty("password"));
		
		loginBtn.click();
		
		InventoryPage inventoryPage = new InventoryPage(driver);
		return inventoryPage;
		
	}
	
	
	public void loginIntoSwagLab(String user_name, String pwd) throws IOException {
		username.sendKeys(user_name);
		password.sendKeys(pwd);
		
		loginBtn.click();
		
	}
	
	
	
	
	public String getCurrentUrlOfLandingPage() throws IOException {
		
		
		
		System.out.println("Current URL : "+ driver.getCurrentUrl());
		
		return driver.getCurrentUrl();
		
	}
	
	
	
	
	
	
	
}
