@regression @youtube
Feature: Youtube search box
  @loadTesting
  Scenario Outline: : testing search box in youtube home page
    Given the user lands to the youtube home page
    When user enter "<Input>"
    Then  user should see a corespending "<Result>"
    Examples:
      |Input|Result|
      |  galaxy7   |  galaxy7  |
      |  iphone 11   |  galaxy7 |
      |  battery   |  galaxy7  |
