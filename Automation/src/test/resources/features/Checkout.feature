Feature: Checkout

  Scenario: Guest checkout flow (basic)
    Given I open the application
    When I add product "Apple MacBook Pro 13-inch" to cart
    And I proceed to checkout as guest
    Then I should reach billing address page
