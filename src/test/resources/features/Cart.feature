@cart
Feature: Cart Page Validation

  Background:
    Given user launches Swag Labs application
    When user login with valid credentials
    Then user should land on inventory page
    And user has added products to cart
    When user click on Cart Icon
    Then user is on Cart Page
