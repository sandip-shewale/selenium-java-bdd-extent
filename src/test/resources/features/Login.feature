Feature: Login functionality

  Scenario: Login with valid credentials
    Given I am on the login page
    When I enter username "student" and password "Password123"
    And I click the login button
    Then I should see the homepage

  Scenario: Login with invalid credentials - user2
    Given I am on the login page
    When I enter username "user2" and password "pass2"
    And I click the login button
    Then I should see an error message

  Scenario: Login with invalid credentials - wrong
    Given I am on the login page
    When I enter username "wrong" and password "wrong"
    And I click the login button
    Then I should see an error message 