Feature: Login

  Scenario: Successful login with valid credentials
    Given I open the application
    When I navigate to login page
    And I login with username "${valid.email}" and password "${valid.password}"
    Then I should see My account link

  Scenario: Invalid login shows error
    Given I open the application
    When I navigate to login page
    And I login with username "wrong@example.com" and password "badpass"
    Then login error should be displayed
