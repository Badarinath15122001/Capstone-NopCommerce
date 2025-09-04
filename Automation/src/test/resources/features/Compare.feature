Feature: Compare products

  Scenario: Add two products to compare
    Given I open the application
    When I add product "Apple MacBook Pro 13-inch" to compare
    And I add product "HTC One M8 Android" to compare
    And I go to compare page
    Then compare should show both products
