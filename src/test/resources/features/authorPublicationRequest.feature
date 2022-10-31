Feature: Create, request and Publish document


  Scenario Outline: Admin create document
    Given I launch application
    And login using 'admin' credentials
    And I click on Content tab
    When I add 'Resource Bundle' type new document in my existing project
    And Add <key> <values> and <description> in document
    And I give 'ID' value
    And I click 'Save & Close'
    Then New document get created in 'Offline' mode with small red color tick mark on top of the document

    Examples:
      | key     | values | description        |
      | "test1" | "001"  | "test description" |

  Scenario: Admin publish the document
    Given I launch application
    And login using "admin" credentials
    And I click on Content tab
    And I select an unpublished document
    When I click on Publication drop-down
    And I select 'Publish' option
   Then small tick mark on top of the document changes to green
