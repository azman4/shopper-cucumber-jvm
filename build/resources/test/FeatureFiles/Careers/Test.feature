Feature: Learn Platform

  @Test
  Scenario Outline: Login to the learn platform
    Given User launches the learn site
    When User enters <email> and <password>
    And User clicks on login button
    Then Choose a program page should be displayed

    Examples:
      | email                            | password   |
      | "internal.student@upgrad.com"    | "upgrad123"|
      | "azman.ansari+student@upgrad.com"| "upgrad123"|


