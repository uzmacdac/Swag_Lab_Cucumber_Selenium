package hook;

import java.io.IOException;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import swag.lab.base.BaseClass;
import swag.lab.pages.LoginPage;

public class Hooks extends BaseClass {

	 public static LoginPage loginPage;

    @Before
    public void setup() throws IOException {

        // Launch browser and application
        loginPage = launchApplication();

        System.out.println("Browser launched successfully");
    }

    @After
    public void tearDown() {

        if (driver != null) {

            driver.quit();

            System.out.println("Browser closed successfully");
        }
    }
}