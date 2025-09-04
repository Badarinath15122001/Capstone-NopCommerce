Feature: Cart operations

  Scenario: Add product to cart and update quantity
    Given I open the application
    When I add product "Apple MacBook Pro 13-inch" to cart
    And I go to cart page
    Then cart should contain that product
    When I update quantity to "2"
    Then cart quantity updates

  Scenario: Remove product from cart
    Given I have product in cart
    When I remove all items from cart
    Then cart shows empty message
