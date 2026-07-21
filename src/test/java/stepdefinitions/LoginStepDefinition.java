package stepdefinitions;

import java.io.IOException;

import org.testng.Assert;

import context.Test_Context;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import swag.lab.base.BaseClass;
import swag.lab.pages.InventoryPage;
import swag.lab.pages.LoginPage;

public class LoginStepDefinition extends BaseClass {

	private Test_Context context;

	public LoginStepDefinition(Test_Context context) {
		this.context = context;
	}

	@After
	public void tearDown() {

		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}

	@Given("user launches Swag Labs application")
	public void user_launches_swag_labs_application() throws IOException {

		LoginPage loginPage = launchApplication();

		context.setLoginPage(loginPage);
	}

	
	
	@When("user login with valid credentials")
	public void user_login_with_valid_credentials() throws IOException {

		InventoryPage inventoryPage = context.getLoginPage().loginIntoSwagLab();

		context.setInventoryPage(inventoryPage);
	}

	@Then("user should navigate to inventory page")
	public void user_should_navigate_to_inventory_page() throws IOException {

		String actualUrl = driver.getCurrentUrl();

		Assert.assertEquals(actualUrl, "https://www.saucedemo.com/inventory.html");
	}

	@When("user enters username {string} and password {string}")
	public void user_enters_username_and_password(String username, String password) throws IOException {

		context.getLoginPage().loginIntoSwagLab(username, password);
	}

	@Then("login error message should be displayed")
	public void login_error_message_should_be_displayed() {

		Assert.assertTrue(context.getLoginPage().error.isDisplayed(), "Error message is not displayed");
	}

	@Then("error message should be {string}")
	public void error_message_should_be(String expectedMessage) {

		String actualMessage = context.getLoginPage().error.getText();

		Assert.assertEquals(actualMessage, expectedMessage);
	}

	@Then("username field should be visible")
	public void username_field_should_be_visible() {

		Assert.assertTrue(context.getLoginPage().username.isDisplayed());
	}

	@Then("password field should be visible")
	public void password_field_should_be_visible() {

		Assert.assertTrue(context.getLoginPage().password.isDisplayed());
	}

	@Then("login button should be visible")
	public void login_button_should_be_visible() {

		Assert.assertTrue(context.getLoginPage().loginBtn.isDisplayed());
	}

	@Then("error close button should be visible")
	public void error_close_button_should_be_visible() {

		Assert.assertTrue(context.getLoginPage().errorBtn.isDisplayed());
	}

	@Then("current URL should contain {string}")
	public void current_url_should_contain(String expectedText) throws IOException {

		String currentUrl = context.getLoginPage().getCurrentUrlOfLandingPage();

		Assert.assertTrue(currentUrl.contains(expectedText));
	}

	@Then("current URL should not contain {string}")
	public void current_url_should_not_contain(String expectedText) throws IOException {

		String currentUrl = context.getLoginPage().getCurrentUrlOfLandingPage();

		Assert.assertFalse(currentUrl.contains(expectedText));
	}
}