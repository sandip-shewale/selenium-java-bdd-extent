Feature: Login functionality

  Scenario Outline: Login with different credentials
    Given I am on the login page
    When I enter username "<username>" and password "<password>"
    And I click the login button
    Then I should see <outcome>

    Examples:
      | username | password     | outcome              |
      | student  | Password123  | the homepage         |
      | user2    | pass2       | an error message     |
      | wrong    | wrong       | an error message     | 