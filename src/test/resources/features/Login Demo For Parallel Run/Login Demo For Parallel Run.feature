@loginDemo
Feature: Login Demo for Parallel Run

  This File has been created to demonstrate parallel run

  Background:
    Given I am on the 'Landing' page

  Scenario: Successful Login - Demo
    And I click on the 'Login' link
    And I enter the following registered email in the 'Email' textbox
      |UserName                |
      |ashish.modi@outlook.com|
    And I enter the following valid password in the 'Password' textbox
      |Password|
      |K1rishna|
    When I click on the 'Login' button
    Then the 'Home' page is displayed