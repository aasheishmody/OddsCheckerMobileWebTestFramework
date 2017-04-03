@login
Feature: Login

  As a existing user
  I want to log in
  So that I can check my updated credit report

  Background:
    Given I am on the 'Landing' page

  Scenario: Successful Login
    And I click on the 'Login' link
    And I enter the following registered email in the 'Email' textbox
      |UserName                |
      |ashish.modi@outlook.com|
    And I enter the following valid password in the 'Password' textbox
      |Password|
      |K1rishna|
    When I click on the 'Login' button
    Then the 'Home' page is displayed