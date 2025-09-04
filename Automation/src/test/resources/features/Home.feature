Feature: Home page validation

  Scenario: Verify homepage loads and main links work
    Given I open the application
    Then I should see the main menu elements

  Scenario: Subscribe to newsletter
    Given I open the application
    When I subscribe with email "auto+news@example.com"
    Then subscription confirmation or message should appear
