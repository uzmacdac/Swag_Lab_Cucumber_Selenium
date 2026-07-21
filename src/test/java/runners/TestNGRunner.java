package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
        features = "src/test/resources/features",
        glue = "stepdefinitions",    
        tags = "@cart",
        plugin = {
                "pretty",
                "html:target/cucumber-report.html",
                "json:target/cucumber.json"
        },
        monochrome = true
)
public class TestNGRunner extends AbstractTestNGCucumberTests{
	//tags =  "@login",
    //tags = " @Inventory",
    //tags = "@fail",
}
