@Inventory
Feature: Inventory Page Functionality

  Background:
    Given user launches Swag Labs application
    When user login with valid credentials
    Then user should land on inventory page


  @smoke @regression
  Scenario: Verify inventory page title
    Then inventory page title should be "Products"
    
  
  @smoke
  Scenario: Verify product list is displayed
    Then all inventory products should be displayed
    And total product count should be 6
    
    @smoke
  Scenario: Verify all product elements are present
    Then all inventory items should contain name description price and button


  @smoke
  Scenario: Verify cart icon visibility
    Then cart icon should be visible

  @smoke
  Scenario: Verify hamburger menu visibility
    Then hamburger menu should be visible


  @smoke @regression
  Scenario: Verify all products displayed
    Then inventory should display 6 products

  @regression
  Scenario: Verify product names are unique
    Then all product names should be unique

  @regression
  Scenario: Verify product prices are valid
    Then all product prices should be valid

  @regression
  Scenario: Verify product images are not broken
    Then all product images should load successfully

  @smoke @regression
  Scenario: Verify single product add to cart
    When user adds product "Sauce Labs Backpack" to cart
    Then cart count should be 1
    
  @regression
  Scenario: Verify multiple products add to cart
    When user adds 3 products to cart
    Then cart count should be 3

  @regression
  Scenario: Verify cart persistence after refresh
    When user adds product "Sauce Labs Bolt T-Shirt" to cart
    And user refreshes inventory page
    Then cart count should remain same
    
   
   @regression
  Scenario: Verify remove product updates cart count
    Given user adds product "Sauce Labs Backpack" to cart
    When user removes product "Sauce Labs Backpack"
    Then cart count should decrease by 1

  @negative
  Scenario: Remove non existing product
    When user removes product "Invalid Product"
    Then application should not crash
    
  @edge
  Scenario: Verify rapid remove clicks
    Given user adds product "Sauce Labs Backpack" to cart
    When user removes product "Sauce Labs Backpack" multiple times
    Then cart count should be 0


  @regression
  Scenario: Verify button toggles back to Add To Cart
    Given user adds product "Sauce Labs Bike Light" to cart
    When user removes product "Sauce Labs Bike Light"
    Then add to cart button should be visible for "Sauce Labs Bike Light"
 
 #######################################################################
 # Sorting 
 
  @regression
  Scenario: Verify sort by name ascending
    When user sorts products by "Name (A to Z)"
    Then products should be sorted ascending

  @regression
  Scenario: Verify sort by name descending
    When user sorts products by "Name (Z to A)"
    Then products should be sorted descending

  @regression
  Scenario: Verify sort by price low to high
    When user sorts products by "Price (low to high)"
    Then product prices should be sorted ascending

  @regression
  Scenario: Verify sort by price high to low
    When user sorts products by "Price (high to low)"
    Then product prices should be sorted descending

   
    
