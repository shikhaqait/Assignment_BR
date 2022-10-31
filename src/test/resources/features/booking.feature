Feature: Customer booking service

  Scenario: Create booking
    Given I have a token to access the application
    Then I have created a booking for the following user:
      | John Macintosh  |
      | Dee Jay         |
      | Bloomy Reachson |

  Scenario: Update customer Name
    Given I have booking details for customer:
      | Dee Jay |
    When I have updated the customer details:
      | name | Dee Jayson |
    Then following booking value should be updated:
      | name | Dee Jayson |


  Scenario: Update booking date & price
    Given I have booking details for customer:
      | Bloomy Reachson |
    When I have updated the customer details:
      | checkout | 7    |
      | price    | 1000 |
    Then following booking value should be updated:
      | checkout | 7    |
      | price    | 1000 |

  Scenario: Delete customer
    When I have booking details for customer:
      | John Macintosh |
    When I delete the customer
    Then customer should be vanished
    * delete all other created booking


