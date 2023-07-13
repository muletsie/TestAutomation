
@tag
Feature: Error validation
  I want to use this template for my feature file

  @ErrorValidation
  Scenario Outline: Negative Login Test
    Given I landed on Ecommerce Page
    When I logged in with username <name> and password <password>
    Then I verify the error message "Incorrect email or password." is displayed

    Examples: 
      | name  								| password |
      | mrmulaudzi@yahoo.co.za | Test123 |
