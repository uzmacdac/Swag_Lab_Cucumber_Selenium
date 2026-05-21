@login
Feature: Login functionality of Swag Labs

  As a user
  I want to login to Swag Labs
  So that I can access inventory page

  Background:
    Given user launches Swag Labs application
    
    
  #################################################
  # Positive Login Test Cases
  #################################################

  @smoke @regression @positive
  Scenario: Verify login with valid credentials
    When user login with valid credentials
    Then user should navigate to inventory page
    
  @smoke @regression @positive
  Scenario Outline: Verify login with valid username and password
    When user enters username "<username>" and password "<password>"
    Then user should navigate to inventory page

    Examples:
      | username      | password     |
      | standard_user | secret_sauce |  
      
  
  
  ####################################################################################################
  # Negative Login Test Cases
  ####################################################################################################

  @regression @negative
  Scenario: Verify login with invalid username
    When user enters username "Harry" and password "secret_sauce"
    Then login error message should be displayed
    And error message should be "Epic sadface: Username and password do not match any user in this service"
    
   @regression @negative
  Scenario: Verify login with invalid password
    When user enters username "standard_user" and password "wrong_password"
    Then login error message should be displayed
    And error message should be "Epic sadface: Username and password do not match any user in this service" 
    
    
    @regression @negative
  Scenario: Verify login with invalid username and password
    When user enters username "wrong_user" and password "wrong_password"
    Then login error message should be displayed
    And error message should be "Epic sadface: Username and password do not match any user in this service" 
    
    
  #########################################################################################################
  # Validation Test Cases
  #########################################################################################################

  @regression @validation
  Scenario: Verify username required validation
    When user enters username "" and password "secret_sauce"
    Then login error message should be displayed
    And error message should be "Epic sadface: Username is required"  
    
  @regression @validation
  Scenario: Verify password required validation
    When user enters username "standard_user" and password ""
    Then login error message should be displayed
    And error message should be "Epic sadface: Password is required"

  @regression @validation
  Scenario: Verify username and password empty validation
    When user enters username "" and password ""
    Then login error message should be displayed
    And error message should be "Epic sadface: Username is required" 
    
    
  #################################################################################################
  # UI Validation Test Cases
  #################################################################################################

  @smoke @ui
  Scenario: Verify username field is visible
    Then username field should be visible

  @smoke @ui
  Scenario: Verify password field is visible
    Then password field should be visible

  @smoke @ui
  Scenario: Verify login button is visible
    Then login button should be visible
    
    
    
   #################################################
  # Error Handling
  #################################################

  @regression @ui
  Scenario: Verify error close button visibility
    When user enters username "invalid" and password "invalid"
    Then error close button should be visible

  ######################################################################################################
  # URL Validation
  #######################################################################################################

  @regression
  Scenario: Verify inventory URL after successful login
    When user login with valid credentials
    Then current URL should contain "inventory.html"

  @regression
  Scenario: Verify URL remains login page after invalid login
    When user enters username "wrong_user" and password "wrong_password"
    Then current URL should not contain "inventory.html"  
  