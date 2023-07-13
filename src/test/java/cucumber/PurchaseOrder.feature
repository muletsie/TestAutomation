@tag
Feature: Purchase order from Ecommerce Website
  I want to use this template for my feature file

	Background:
	Given I landed on Ecommerce Page

  @Regress
  Scenario Outline: Positive Test of submitting the order
    Given I logged in with username <name> and password <password>
    When I add producname <productName> to cart
    And Checkout <productName> and submit the order
    Then I verify the message <status> is displayed on ConfirmationPage

    Examples: 
      | name  								| password | productName 				| status 									|
      | mrmulaudzi@yahoo.co.za | Test1234 | ADIDAS ORIGINAL 		| THANKYOU FOR THE ORDER. |
