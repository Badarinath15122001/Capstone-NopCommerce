Feature: Product catalog and search

  Scenario: Search product
    Given I open the application
    When I search for "Laptop"
    Then search results are displayed

  Scenario: Navigate to category
    Given I open the application
    When I click category "Computers"
    Then list of products should appear
