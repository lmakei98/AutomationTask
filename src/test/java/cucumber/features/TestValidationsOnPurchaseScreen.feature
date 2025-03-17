Feature: Test validations on purchase screen

  Background:
    Given Product purchase is initiated

  @Regression
  Scenario: Validate Shipping destination is required

    When User leaves Shipping destination field empty
    Then Validate Shipping destination required error message is displayed

  @Regression
  Scenario: Validate Discount Code

    When User enters invalid discount code in the Discount code field and clicks Apply
    Then Validate Discount code error message is displayed

  @Regression
  Scenario: Validate Shipping options is required

    When User selects Shipping destination
    And User clicks Continue button in Shipping Information form
    Then Validate Shipping option required error message is displayed

  @Regression
  Scenario: Validate required fields in Contact Information

    Given Contact Information form is displayed
    When User clicks Continue button in Contact Information
    Then Validate Email address error message is displayed
    And Validate Phone Number required error message
    And Validate Full Name required error message
    And Validate Special requests required error message

  @Regression
  Scenario Outline: Validate Email

    Given Contact Information form is displayed
    When User enters <emailAddress> in the Email field
    Then Validate Email address error message is displayed
    Examples:
      | emailAddress |
      | test         |
      | test@mailcom |

  @Regression
  Scenario Outline: Validate Phone Number

    Given Contact Information form is displayed
    When User enters <phoneNumber> in the Phone number field
    Then Validate Phone number error message is displayed
    Examples:
      | phoneNumber          |
      | 203                  |
      | 60355029345          |


