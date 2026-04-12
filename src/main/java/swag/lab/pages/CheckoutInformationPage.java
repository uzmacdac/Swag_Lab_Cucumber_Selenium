package swag.lab.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutInformationPage {
	
	WebDriver driver ;
	WebDriverWait wait;
    JavascriptExecutor js;
    
    By firstNameField = By.id("first-name");
	By lastNameField = By.id("last-name");
	By postalCodeField = By.id("postal-code");
	
	@FindBy(css=".title")
	public WebElement pageTitle;
	
	@FindBy(id="first-name")
	public WebElement firstName;
	
	@FindBy(id="last-name")
	public WebElement lastName;
	
	@FindBy(id="postal-code")
	public WebElement postalCode;
	
	
	@FindBy(id="cancel")
	public WebElement cancelBtn;
	
	@FindBy(id="continue")
	public WebElement continueBtn;
	
	@FindBy(xpath="//h3[@data-test='error']")
	public WebElement error;
	
	@FindBy(css=".error-button")
	public WebElement cancelErrorMsgBtn;
	
	public enum Empty {
	    SKIP
	}
	
    public CheckoutInformationPage(WebDriver driver) {
        this.driver = driver;
        js = (JavascriptExecutor) driver;		
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    
    public void cancelOrder() {
    	
    	//cancelBtn.click();
    	//wait.until(ExpectedConditions.elementToBeClickable(cancelBtn)).click();
    	js.executeScript("arguments[0].click();", cancelBtn);
    	
    }
    
//  public void fillFirstName(String name) {
////	  wait.until(ExpectedConditions.visibilityOf(firstName));
////	  wait.until(ExpectedConditions.elementToBeClickable(firstName));
////	    
////	  firstName.click();          // important for React inputs
////	  firstName.clear();
////	  firstName.sendKeys(name);
//    
//   // js.executeScript("arguments[0].value='"+name+"'; arguments[0].dispatchEvent(new Event('input'));", firstName);
//	  
//	  
//	  WebElement element = wait.until(ExpectedConditions.elementToBeClickable(firstNameField));
//	  element.clear();
//	  element.sendKeys(name);
//	  
//	  
//  }

//  public void fillLastName(String surname) {
////	    wait.until(ExpectedConditions.visibilityOf(lastName));
////	    wait.until(ExpectedConditions.elementToBeClickable(lastName));
////	    
////	    lastName.click();
////	    lastName.clear();
////	    lastName.sendKeys(surname);
//	  
//	  WebElement element = wait.until(ExpectedConditions.elementToBeClickable(lastNameField));
//	  element.clear();
//	  element.sendKeys(surname);
//	}
    
    
//	public void fillPostalCode(String postal_code) {
//	    wait.until(ExpectedConditions.visibilityOf(postalCode));
//	    wait.until(ExpectedConditions.elementToBeClickable(postalCode));
//	    
//	    postalCode.click();
//	    postalCode.clear();
//	    postalCode.sendKeys(postal_code);
//	}
//    
  
//  public void fillFirstName(String name) {
//	    WebElement element = wait.until(
//	            ExpectedConditions.elementToBeClickable(firstName)
//	    );
//
//	    Actions actions = new Actions(driver);
//	    actions.moveToElement(element)
//	           .click()
//	           .sendKeys(name)
//	           .build()
//	           .perform();
//	}
//  
//
//public void fillLastName(String surname) {
//    WebElement element = wait.until(
//            ExpectedConditions.elementToBeClickable(lastName)
//    );
//
//    Actions actions = new Actions(driver);
//    actions.moveToElement(element)
//           .click()
//           .sendKeys(surname)
//           .build()
//           .perform();
//}
//
//public void fillPostalCode(String postal_code) {
//    WebElement element = wait.until(
//            ExpectedConditions.elementToBeClickable(postalCode)
//    );
//
//    Actions actions = new Actions(driver);
//    actions.moveToElement(element)
//           .click()
//           .sendKeys(postal_code)
//           .build()
//           .perform();
//}


//    public void fillCheckoutInformation(String first, String last, String postal) {
//
//    		wait.until(ExpectedConditions.visibilityOf(firstName));
//    	    js.executeScript("arguments[0].value='"+first+"'", firstName);
//
//    	    wait.until(ExpectedConditions.visibilityOf(lastName));
//    	    js.executeScript("arguments[0].value='"+last+"'", lastName);
//
//    	    wait.until(ExpectedConditions.visibilityOf(postalCode));
//    	    js.executeScript("arguments[0].value='"+postal+"'", postalCode);
//    }
    
    
//    public void fillCheckoutInformation(String first, String last, String postal) {
//
//        wait.until(ExpectedConditions.visibilityOf(firstName));
//        js.executeScript(
//            "arguments[0].value=arguments[1];" +
//            "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));" +
//            "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));",
//            firstName, first
//        );
//
//        wait.until(ExpectedConditions.visibilityOf(lastName));
//        js.executeScript(
//            "arguments[0].value=arguments[1];" +
//            "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));" +
//            "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));",
//            lastName, last
//        );
//
//        wait.until(ExpectedConditions.visibilityOf(postalCode));
//        js.executeScript(
//            "arguments[0].value=arguments[1];" +
//            "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));" +
//            "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));",
//            postalCode, postal
//        );
//    }
//    
    
    
//    public void fillCheckoutInformation(String first, String last, String postal) {
//
//        if(first != null && !first.isEmpty()) {
//            wait.until(ExpectedConditions.visibilityOf(firstName));
//            js.executeScript(
//                "arguments[0].value=arguments[1];" +
//                "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));" +
//                "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));"+
//                "arguments[0].dispatchEvent(new Event('blur', { bubbles: true }));",
//                firstName, first
//            );
//        }
//
//        if(last != null && !last.isEmpty()) {
//            wait.until(ExpectedConditions.visibilityOf(lastName));
//            js.executeScript(
//                "arguments[0].value=arguments[1];" +
//                "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));" +
//                "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));"+
//                "arguments[0].dispatchEvent(new Event('blur', { bubbles: true }));",
//                lastName, last
//            );
//        }
//
//        if(postal != null && !postal.isEmpty()) {
//            wait.until(ExpectedConditions.visibilityOf(postalCode));
//            js.executeScript(
//                "arguments[0].value=arguments[1];" +
//                "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));" +
//                "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));"+
//                "arguments[0].dispatchEvent(new Event('blur', { bubbles: true }));",
//                postalCode, postal
//            );
//        }
//    }
    
//    public void fillCheckoutInformationPage(String first, String last, String postal) {
//
//        wait.until(ExpectedConditions.elementToBeClickable(firstName));
//        firstName.clear();
//        firstName.sendKeys(first);
//        firstName.sendKeys(Keys.TAB);
//
//        wait.until(ExpectedConditions.elementToBeClickable(lastName));
//        lastName.clear();
//        lastName.sendKeys(last);
//
//        wait.until(ExpectedConditions.elementToBeClickable(postalCode));
//        postalCode.clear();
//        postalCode.sendKeys(postal);
//    }
    
    

    public void fillCheckoutDetail(CharSequence first, CharSequence last, CharSequence postal) {

        wait.until(ExpectedConditions.elementToBeClickable(firstName));
        firstName.clear();
        firstName.sendKeys(first);

        wait.until(ExpectedConditions.elementToBeClickable(lastName));
        lastName.clear();
        lastName.sendKeys(last);

        wait.until(ExpectedConditions.elementToBeClickable(postalCode));
        postalCode.clear();
        postalCode.sendKeys(postal);
    }
    
    public void fill(CharSequence first, CharSequence last, CharSequence postal) {

        if(!(first instanceof Keys)) {
            wait.until(ExpectedConditions.elementToBeClickable(firstName));
            firstName.clear();
            firstName.sendKeys(first);
        }

        if(!(last instanceof Keys)) {
            wait.until(ExpectedConditions.elementToBeClickable(lastName));
            lastName.clear();
            lastName.sendKeys(last);
        }

        if(!(postal instanceof Keys)) {
            wait.until(ExpectedConditions.elementToBeClickable(postalCode));
            postalCode.clear();
            postalCode.sendKeys(postal);
        }
    }
    
    public void fill(Object first, Object last, Object postal) {

        if(!(first instanceof Empty)) {
            wait.until(ExpectedConditions.elementToBeClickable(firstName));
            firstName.clear();
            firstName.sendKeys(first.toString());
        }

        if(!(last instanceof Empty)) {
            wait.until(ExpectedConditions.elementToBeClickable(lastName));
            lastName.clear();
            lastName.sendKeys(last.toString());
        }

        if(!(postal instanceof Empty)) {
            wait.until(ExpectedConditions.elementToBeClickable(postalCode));
            postalCode.clear();
            postalCode.sendKeys(postal.toString());
        }
    }
    
    public void setInput(WebElement element, String value) {

        wait.until(ExpectedConditions.elementToBeClickable(element));

        js.executeScript(
            "var nativeInputValueSetter = Object.getOwnPropertyDescriptor(window.HTMLInputElement.prototype, 'value').set;" +
            "nativeInputValueSetter.call(arguments[0], arguments[1]);" +
            "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));",
            element, value
        );
    }
    
    
    public void fillCheckoutInformation(String first, String last, String postal) {
        setInput(firstName, first);
        setInput(lastName, last);
        setInput(postalCode, postal);
    }
    
    
    public CheckoutOverviewPage continueWithOrderProcess() {
    	//continueBtn.click();
    	js.executeScript("arguments[0].click();", continueBtn);
    	return new CheckoutOverviewPage(driver);
    	
    }
    
    public CheckoutOverviewPage goToCheckoutOverviewPage(String first, String last, String postal) {
    	setInput(firstName, first);
        setInput(lastName, last);
        setInput(postalCode, postal);
    	//continueBtn.click();
    	js.executeScript("arguments[0].click();", continueBtn);
    	return new CheckoutOverviewPage(driver);
    	
    }
    
    public String getErrorMsg() {
    	// Error: First Name is required
    	// Error: Last Name is required
    	// Error: Postal Code is required
    	if(error.isDisplayed()) 
    		return error.getText();
    	return "Error Msg is not displayed";
    }
    
    public void cancelErrorMsg() {
    	cancelErrorMsgBtn.click();
    	
    }
    
    public String checkoutInformationPageTitle() {
    	
    	return pageTitle.getText();    	
    }
 
    
    
    public void fillFirstName(String first) {

        WebElement element = wait.until(
                ExpectedConditions.elementToBeClickable(firstName));

        js.executeScript(
            "var nativeInputValueSetter = Object.getOwnPropertyDescriptor(window.HTMLInputElement.prototype, 'value').set;" +
            "nativeInputValueSetter.call(arguments[0], arguments[1]);" +
            "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));",
            element, first
        );
    }

    public void fillLastName(String last) {

        WebElement element = wait.until(
                ExpectedConditions.elementToBeClickable(lastName));

        js.executeScript(
            "var nativeInputValueSetter = Object.getOwnPropertyDescriptor(window.HTMLInputElement.prototype, 'value').set;" +
            "nativeInputValueSetter.call(arguments[0], arguments[1]);" +
            "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));",
            element, last
        );
    }

    public void fillPostalCode(String postal) {

        WebElement element = wait.until(
                ExpectedConditions.elementToBeClickable(postalCode));

        js.executeScript(
            "var nativeInputValueSetter = Object.getOwnPropertyDescriptor(window.HTMLInputElement.prototype, 'value').set;" +
            "nativeInputValueSetter.call(arguments[0], arguments[1]);" +
            "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));",
            element, postal
        );
    }

    
//    public void setValue(By locator, String value) {
//
//        WebElement element = wait.until(
//                ExpectedConditions.visibilityOfElementLocated(locator));
//
//        js.executeScript(
//            "arguments[0].focus();" +
//            "arguments[0].value = arguments[1];" +
//            "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));" +
//            "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));" +
//            "arguments[0].dispatchEvent(new Event('blur', { bubbles: true }));",
//            element,
//            value
//        );
//
//        System.out.println("Value entered: " + element.getAttribute("value"));
//    }
    

    
    

    
    
}
