Feature: Register

  Scenario: Register new user
    Given I open the application
    When I navigate to register page
    And I register new user with random email
    Then registration should complete
