Feature: Login and Registration Tests

  Scenario: Register, Logout, and Login successfully
    Given User opens the application
    When User registers with "John", "Doe", "<email>", and "Test@123"
    And User logs out
    And User logs in with registered credentials
    Then User should be logged in successfully

  Scenario: Login with wrong credentials
    Given User opens the application
    When User enters invalid credentials "wronguser@demo.com" and "WrongPassword123"
    Then An error message "Login was unsuccessful" should be displayed
