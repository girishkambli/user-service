Feature: Verify PUT user

  Background:
    Given baseUri is "http://localhost:9001/api"

  Scenario: Update of an user with invalid id fails
    Given content type is "application/json"
    And header Authorization with value "Basic Z2lyaXNoOmcxcjE1aA=="
    And request body from file "data/01_valid_user.json"
    When the client performs PUT request on "/users/jkh"
    Then status code is 400
    And response contains "Invalid User Id"

  Scenario: Update of an user with invalid id in request fails
    Given content type is "application/json"
    And header Authorization with value "Basic Z2lyaXNoOmcxcjE1aA=="
    And request body from file "data/02_invalid_user_id.json"
    When the client performs PUT request on "/users/1"
    Then status code is 400
    And response contains "Invalid User Id"

  Scenario: Update of a valid user succeeds
    Given content type is "application/json"
    And header Authorization with value "Basic Z2lyaXNoOmcxcjE1aA=="
    And request body from file "data/01_valid_user.json"
    When the client performs PUT request on "/users/1"
    Then status code is 200

  Scenario: Update of a invalid first name fails
    Given content type is "application/json"
    And header Authorization with value "Basic Z2lyaXNoOmcxcjE1aA=="
    And request body from file "data/03_invalid_user_fname.json"
    When the client performs PUT request on "/users/1"
    Then status code is 400
    And response contains "Invalid first name"