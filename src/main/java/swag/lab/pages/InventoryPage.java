package swag.lab.pages;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InventoryPage {

	WebDriver driver;
	
	JavascriptExecutor js;
	
	WebDriverWait wait ; 
	
	Properties property = new Properties();
	
	@FindBy(css=".app_logo")
	WebElement app_logo;
	
	@FindBy(css="span[data-test='title']")
	WebElement titleOfInventoryPage;
	
	@FindBy(id="inventory_container")
	WebElement inventory_container;
	
	// List of all inventory items
    @FindBy(css = ".inventory_item")
	public List<WebElement> items;
	
    // Child elements (global lists mapped by index)
    @FindBy(css = ".inventory_item_name")
    List<WebElement> itemNames;

    @FindBy(css = ".inventory_item_desc")
    List<WebElement> itemDescriptions;

    @FindBy(css = ".inventory_item_price")
    List<WebElement> itemPrices;

    @FindBy(css = ".inventory_item button")
    List<WebElement> itemButtons;

    // Sort dropdown
    @FindBy(css = ".product_sort_container")
    WebElement sortDropdown;
	
	
	// Add To Cart Button
	// add to cart button ID = add-to-cart-sauce-labs-onesie
	// item name = Sauce Labs Onesie , cart = add-to-cart-sauce-labs-onesie
	// item name =Test.allTheThings() T-Shirt (Red) , cart =  add-to-cart-test.allthethings()-t-shirt-(red)


    // Add to cart buttons
    // @FindBy(css = "button.btn_inventory")
    // List<WebElement> addToCartButtons;
    
    // Buttons (Add/Remove both share same class)
    @FindBy(css = "button.btn_inventory")
    List<WebElement> actionButtons;

    // Cart badge
    @FindBy(css = ".shopping_cart_badge")
    WebElement cartBadge;

    // Cart icon
    //@FindBy(id = "shopping_cart_container")
    @FindBy(xpath="//a[@class='shopping_cart_link']")
    WebElement cartIcon;
	
	// count of items in the cart 
	@FindBy(css=".shopping_cart_badge")
	WebElement itemsInCart;
	
	// css = .select_container
	@FindBy(css=".product_sort_container")
	WebElement itemsFilter;
	
	@FindBy(css="img.inventory_item_img")
	List<WebElement> items_images;
	
	@FindBy(id = "react-burger-menu-btn")
    WebElement menuBtn;
    
    @FindBy(id = "inventory_sidebar_link")
    WebElement allItems;

    @FindBy(id = "about_sidebar_link")
    WebElement about;

    @FindBy(id = "logout_sidebar_link")
    WebElement logout;

    @FindBy(id = "reset_sidebar_link")
    WebElement resetAppState;
	
	public InventoryPage(WebDriver driver) {
		
		this.driver = driver ;
		wait  = new WebDriverWait(driver, Duration.ofSeconds(10));
		js = (JavascriptExecutor)driver;
		PageFactory.initElements(driver, this);
	}
	
	

	public String getPageTitle() {
		
		return titleOfInventoryPage.getText();
	}
	
	
	public int totalProductDisplayed() {
		
		return items.size();
		
	}
	
	
	 public boolean isProductListDisplayed() {
		 if (items != null && !items.isEmpty()) {
	            // Optional: You can iterate through the list to ensure at least one is displayed
	            for (WebElement item : items) {
	                if (item.isDisplayed()) {
	                    return true;
	                }
	            }
	        }
	        return false;
	 }
	
	 
	
	
	 // Getter methods
	  public int getItemCount() {
	       return items.size();
	  }
	   
	  /*
	   	 for (int i = 0; i < items.size(); i++) {

		   	        WebElement item = items.get(i);

		   	        // Locate elements inside item
		   	        WebElement name = item.findElement(By.cssSelector(".inventory_item_name"));
		   	        WebElement desc = item.findElement(By.cssSelector(".inventory_item_desc"));
		   	        WebElement price = item.findElement(By.cssSelector(".inventory_item_price"));
		   	        WebElement button = item.findElement(By.cssSelector("button"));
	   }
	   
	   */

	  public String getItemName(int index) {
	     return itemNames.get(index).getText();
	  }

	  public String getItemDescription(int index) {
	      return itemDescriptions.get(index).getText();
	  }

	  public String getItemPrice(int index) {
	      return itemPrices.get(index).getText();
	  }

	  public String getButtonText(int index) {
	      return itemButtons.get(index).getText();
	   }

	  public boolean isItemDisplayed(int index) {
	        return items.get(index).isDisplayed();
	   }

	  public boolean isCartIconVisible() {
		  if(cartIcon.isDisplayed()) {
	    	 return true;
		  }
	    	 	
		return false;   
	  }



	public boolean isHambergerMenuVisible() {
		if(menuBtn.isDisplayed()) {
			return true;
		}
			
		return false;
	}
	    
	 // 1. Verify all products displayed
	    public boolean areProductsDisplayed(int expectedCount) {
	        return items.size() == expectedCount;
	    }

	    // 2. Verify product names are unique
	    public boolean areProductNamesUnique() {
	        Set<String> uniqueNames = new HashSet<>();

	        for (WebElement name : itemNames) {
	            String text = name.getText().trim();
	            if (!uniqueNames.add(text)) {
	                return false; // duplicate found
	            }
	        }
	        return true;
	    }

	    // 3. Verify product prices are valid (> 0 and numeric)
	    public boolean arePricesValid() {
	        for (WebElement price : itemPrices) {
	            String priceText = price.getText().replace("$", "").trim();
	            System.out.println("Item Price : "+priceText);

	            try {
	                double value = Double.parseDouble(priceText);
	                if (value <= 0) {
	                    return false;
	                }
	            } catch (NumberFormatException e) {
	                return false;
	            }
	        }
	        return true;
	    }

	    // 4. Verify images are not broken
	    public boolean areImagesValid() {
	        try {
	            for (WebElement img : items_images) {
	                String src = img.getAttribute("src");
	                System.out.println("src : "+src);
	                
	                if (src == null || src.isEmpty()) {
	                    return false;
	                }

	                HttpURLConnection connection = (HttpURLConnection) new URL(src).openConnection();
	                connection.setRequestMethod("HEAD");
	                connection.connect();

	                int responseCode = connection.getResponseCode();
	                System.out.println("Response Coode  : "+responseCode);

	                if (responseCode >= 400) {
	                    return false; // broken image
	                }
	            }
	        } catch (Exception e) {
	            return false;
	        }
	        return true;
	    }
	
	   
	    // Add product by index
	    public void addProductByIndex(int index) {
	    	actionButtons.get(index).click();
	    }



	    public void addProductByName(String productName) {
//	        for (int i = 0; i < itemNames.size(); i++) {
//	            if (itemNames.get(i).getText().equalsIgnoreCase(productName)) {
//	                actionButtons.get(i).click();
//	                PageFactory.initElements(driver, this); // ✅ refresh elements
//	                break;
//	            }
//	        }
	    	
	    	
	    	for (WebElement item : items) {

	            String itemName = item.findElement(By.cssSelector(".inventory_item_name")).getText();

	            if (itemName.equalsIgnoreCase(productName)) {

	                WebElement button = item.findElement(By.tagName("button"));

	                if (button.getText().equalsIgnoreCase("Add to cart")) {
	                	
	                    button.click();
	                    System.out.println("Add To Cart Button pressed");
	                    return;
	                }
	                return;
	            }
	        }
	        throw new RuntimeException("Product not found: " + productName);
	    	
	    	
	    }
	    
	    
	    public void addMultipleProducts(int count) {
	        int added = 0;

//	        for (int i = 0; i < itemNames.size(); i++) {
//	            if (actionButtons.get(i).getText().equalsIgnoreCase("Add to cart")) {
//	                actionButtons.get(i).click();
//	                added++;
//
//	                if (added == count) break;
//	            }
//	        }
	       // PageFactory.initElements(driver, this);
	        for (WebElement item : items) {

	            WebElement button = item.findElement(By.tagName("button"));

	            if (button.getText().equalsIgnoreCase("Add to cart")) {
	                button.click();
	                added++;

	                if (added == count) break;
	            }
	        }
	    }

    
	    // Refresh page
	    public void refreshPage() {
	        driver.navigate().refresh();
	        PageFactory.initElements(driver, this); // re-init elements
	    }

	    // Get cart count
	    public int getCartCount() {
	        try {
	            return Integer.parseInt(cartBadge.getText());
	        } catch (Exception e) {
	            return 0;
	        }
	    }

	    // Verify button changed to Remove
	    public boolean isProductAdded(String productName) {
	        for (int i = 0; i < itemNames.size(); i++) {
	            if (itemNames.get(i).getText().equalsIgnoreCase(productName)) {
	            	PageFactory.initElements(driver, this); 
	                return actionButtons.get(i).getText().equalsIgnoreCase("Remove");
	            }
	        }
	        return false;
	    }
	    
	   
	    // Remove product by name (PageFactory only)
	    public void removeProductByName(String productName) {
//	        for (int i = 0; i < itemNames.size(); i++) {
//	            if (itemNames.get(i).getText().equalsIgnoreCase(productName)) {
//	                if (actionButtons.get(i).getText().equalsIgnoreCase("Remove")) {
//	                    actionButtons.get(i).click();
//	                }
//	                break;
//	            }
//	        }
	    	
	    	
	    	for (WebElement item : items) {

	            String itemName = item.findElement(By.cssSelector(".inventory_item_name")).getText();

	            if (itemName.equalsIgnoreCase(productName)) {

	                WebElement button = item.findElement(By.tagName("button"));

	                if (button.getText().equalsIgnoreCase("Remove")) {
	                    button.click();
	                }
	                return;
	            }
	        }
	    	
	    	
	    }
	    
	    
	    
	    public void removeProduct(String productName) {

	        List<WebElement> items = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("inventory_item")));

	        for (WebElement item : items) {

	            String itemName = item.findElement(By.cssSelector(".inventory_item_name")).getText();

	            if (itemName.equalsIgnoreCase(productName)) {

	                WebElement button = item.findElement(By.tagName("button"));

	                if (button.getText().equalsIgnoreCase("Remove")) {

	                    button.click();

	                    // Wait until cart badge disappears instead (more reliable)
	                    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("shopping_cart_badge")));
	                }

	                return;
	            }
	        }
	    }
	    
	    
	    public void removeProductByNameIndustrySolution(String productName) {

	        int before = getCartCount();

	        List<WebElement> items = driver.findElements(By.className("inventory_item"));

	        for (WebElement item : items) {

	            String name = item.findElement(
	                    By.cssSelector(".inventory_item_name")).getText();

	            if (name.equalsIgnoreCase(productName)) {

	                item.findElement(By.tagName("button")).click();

	                wait.until(driver ->
	                        getCartCount() < before);

	                return;
	            }
	        }
	    }
	    
	    
	    public void removeProductById(String productName) {

	        String id = "remove-" + productName
	                .toLowerCase()
	                .replace(" ", "-");

	        WebElement removeBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id(id)));

	        removeBtn.click();

	        wait.until(ExpectedConditions.numberOfElementsToBe(By.className("shopping_cart_badge"), 0));
	    }
	    
	    

	    // Add product (helper for test setup)
	    public void addProductByNameToCart(String productName) {
	        for (int i = 0; i < itemNames.size(); i++) {
	            if (itemNames.get(i).getText().equalsIgnoreCase(productName)) {
	                if (actionButtons.get(i).getText().equalsIgnoreCase("Add to cart")) {
	                    actionButtons.get(i).click();
	                }
	                break;
	            }
	        }
	    }

	   
	    // Verify button is "Add to cart"
	    public boolean isProductRemoved(String productName) {
	        for (int i = 0; i < itemNames.size(); i++) {
	            if (itemNames.get(i).getText().equalsIgnoreCase(productName)) {
	                return actionButtons.get(i).getText().equalsIgnoreCase("Add to cart");
	            }
	        }
	        return false;
	    }
	    
	    
	    public List<String> getProductNames() {
	        List<String> names = new ArrayList<>();
	        for (WebElement e : itemNames) {
	            names.add(e.getText().trim());
	        }
	        return names;
	    }

	    public List<Double> getProductPrices() {
	        List<Double> prices = new ArrayList<>();
	        for (WebElement e : itemPrices) {
	            prices.add(Double.parseDouble(e.getText().replace("$", "").trim()));
	        }
	        return prices;
	    }

	    public boolean isSortedAscending(List<? extends Comparable> actual) {
	        List<Comparable> sorted = new ArrayList<>(actual);
	        Collections.sort(sorted);
	        return sorted.equals(actual);
	    }

	    public boolean isSortedDescending(List<? extends Comparable> actual) {
	        List<Comparable> sorted = new ArrayList<>(actual);
	        Collections.sort(sorted, Collections.reverseOrder());
	        return sorted.equals(actual);
	    }



	    public void selectSortOption(String option) {
	        Select select = new Select(sortDropdown);
	        select.selectByVisibleText(option);
	    }


	 // Click product by name → navigate to details page
	    public void clickProduct(String productName) {
	        for (WebElement item : items) {
	            String name = item.findElement(
	                    org.openqa.selenium.By.cssSelector(".inventory_item_name")).getText();

	            if (name.equalsIgnoreCase(productName)) {
	                item.findElement(org.openqa.selenium.By.cssSelector(".inventory_item_name")).click();
	                return;
	            }
	        }
	        throw new RuntimeException("Product not found: " + productName);
	    }

	    // Click cart
	    public CartPage clickCart() {

	        WebElement cart = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("shopping_cart_link")));     
	        
	        System.out.println("Before click: " + driver.getCurrentUrl());
	        //cart.click();
	        js.executeScript("arguments[0].click();", cart);
	        System.out.println("After click: " + driver.getCurrentUrl());

	        return new CartPage(driver);
	    }

	    // Open menu
	    public void openMenu() {
	    	// wait for menu button clickable
	        wait.until(ExpectedConditions.elementToBeClickable(menuBtn)).click();

	        // wait for sidebar container
	        wait.until(ExpectedConditions.visibilityOfElementLocated(
	                By.cssSelector(".bm-menu-wrap")));
	    }
	    
	    public SideBarMenuPage openSideBar() {
	        return new SideBarMenuPage(driver);
	    }

	    // Menu options
	    public boolean isMenuOptionsVisible() {
	        return allItems.isDisplayed() &&
	               about.isDisplayed() &&
	               logout.isDisplayed() &&
	               resetAppState.isDisplayed();
	    }

	    public void clickLogout() {
	        
	        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout_sidebar_link"))).click();
	    	openMenu();
	        wait.until(ExpectedConditions.visibilityOf(logout)).click();
	               
	        
	    }

	    public void clickAllItems() {
	        allItems.click();
	    }

	    public void clickAbout() {
	        about.click();
	    }

	    public void clickResetAppState() {
	        resetAppState.click();
	    }

	    // Add product (reuse)
	    public void addProduct(String name) {
	        for (WebElement item : items) {
	            String itemName = item.findElement(
	                    org.openqa.selenium.By.cssSelector(".inventory_item_name")).getText();

	            if (itemName.equalsIgnoreCase(name)) {
	                WebElement btn = item.findElement(org.openqa.selenium.By.tagName("button"));
	                if (btn.getText().equalsIgnoreCase("Add to cart")) {
	                    //btn.click();
	                    js.executeScript("arguments[0].click();", btn);
	                }
	                return;
	            }
	        }
	    }

	    public String getCurrentUrl() {
	        return driver.getCurrentUrl();
	    }
	    
	    
	    // InventoryPage.java
	    public boolean hasBrokenImages() {
	        for (WebElement item : items) {
	            WebElement img = item.findElement(By.tagName("img"));
	            String src = img.getAttribute("src");

	            if (src == null || src.isEmpty()) return true;

	            // browser-side check
	            Boolean loaded = (Boolean) js.executeScript("return arguments[0].complete && arguments[0].naturalWidth > 0", img);

	            if (!loaded) return true;
	        }
	        return false;
	    }
	    
	    public boolean isPriceFormatValid() {
	        return itemPrices.stream().allMatch(e -> e.getText().matches("^\\$\\d+\\.\\d{2}$"));
	    }
	    
	    public boolean hasDuplicateNames() {
	        Set<String> set = new HashSet<>();
	        for (WebElement e : itemNames) {
	            if (!set.add(e.getText())) return true;
	        }
	        return false;
	    }



		public double getPriceByName(String product) {
			// TODO Auto-generated method stub
			return 0;
		}
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
}
