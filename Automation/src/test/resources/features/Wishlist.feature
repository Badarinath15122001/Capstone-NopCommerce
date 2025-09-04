Feature: Wishlist

  Scenario: Add item to wishlist and move to cart
    Given I open the application
    When I add product "HTC One M8 Android" to wishlist
    And I go to wishlist page
    Then wishlist shows the product
    When I move first wishlist item to cart
    Then cart should contain that item
