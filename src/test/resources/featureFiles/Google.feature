@regression @google
Feature: Google search box
  @smoke
  Scenario Outline: : testing search box in google home page
    Given the user lands to the google home page
    When user enter "<Input>"
    Then  user should see a corespending "<Result>"
    Examples:
      |Input|Result|
      |  galaxy7   |  galaxy7  |
      |  iphone 11   |  galaxy7 |
      |  battery   |  galaxy7  |