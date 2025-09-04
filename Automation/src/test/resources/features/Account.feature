Feature: My Account

  Scenario: Access My Account sections
    Given I open the application
    When I login with username "${valid.email}" and password "${valid.password}"
    Then I open My account page
    Then account sections should be visible
