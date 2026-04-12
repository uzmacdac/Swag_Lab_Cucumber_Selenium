package swag.lab.tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import swag.lab.base.BaseClass;
import swag.lab.base.CommonCode;
import swag.lab.pages.LoginPage;

public class LoginTest extends BaseClass{

	LoginPage loginPage;
	
	@BeforeMethod(alwaysRun = true)
	public void setUp() throws IOException {
		
		loginPage = launchApplication();
		
	}
	
	@Test(enabled=false, groups = { "smoke", "Regression", "login" })
	public void validLogin() throws IOException {
		
		//LoginPage loginPage = launchApplication();
		
		loginPage.loginIntoSwagLab();
		
		System.out.print("Landing url : "+loginPage.getCurrentUrlOfLandingPage() );
		
		Assert.assertTrue("https://www.saucedemo.com/inventory.html".equalsIgnoreCase(loginPage.getCurrentUrlOfLandingPage()), null);
		
	}
	
	@Test(enabled=false,  groups = { "smoke", "Regression", "login" })
	public void invalidLoginWhenUsernameAndPawordNotMatch() throws IOException {
		
		loginPage.loginIntoSwagLab("Harry", "secret_sauce");
		
		System.out.println("Error Occured  : "+loginPage.error.getText());
		
		Assert.assertTrue(loginPage.error.isDisplayed());
		Assert.assertEquals(loginPage.error.getText(), "Epic sadface: Username and password do not match any user in this service");
		
	}
	
	@Test(enabled=false,   groups = { "smoke", "Regression", "login" })
	public void invalidLoginUsernameRequired() throws IOException {
		
		loginPage.loginIntoSwagLab("", "secret_sauce");
		
		System.out.println("Error Occured  : "+loginPage.error.getText());
		
		Assert.assertTrue(loginPage.error.isDisplayed());
		Assert.assertEquals(loginPage.error.getText(), "Epic sadface: Username is required");
		
	}
	
	@Test(enabled=true,   groups = { "smoke", "Regression", "login" })
	public void invalidLoginPasswordRequired() throws IOException {
		
		loginPage.loginIntoSwagLab("standard_user", "");
		
		System.out.println("Error Occured  : "+loginPage.error.getText());
		
		Assert.assertTrue(loginPage.error.isDisplayed());
		Assert.assertEquals(loginPage.error.getText(), "Epic sadface: Password is required");
		
	}
	

}
