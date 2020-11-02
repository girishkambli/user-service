Feature: Verify that authentication and authorization is enabled

  Background:
    Given baseUri is "http://localhost:9001/api"

  Scenario: Get an user without authentication fails
    Given content type is "application/json"
    When the client performs GET request on "/users/1"
    Then status code is 401
    And response contains property "error" with value "Unauthorized"

  Scenario: Get an user with invalid authentication fails
    Given content type is "application/json"
    And header Authorization with value "Basic bogus-value"
    When the client performs GET request on "/users/1"
    Then status code is 401

  Scenario: Get an user with valid authentication and valid role succeeds
    Given content type is "application/json"
    And header Authorization with value "Basic Z3Vlc3Q6Z3Vlc3Q="
    When the client performs GET request on "/users/1"
    Then status code is 200

  Scenario: Update of an user with valid authentication and invalid role is forbidden
    Given content type is "application/json"
    And header Authorization with value "Basic Z3Vlc3Q6Z3Vlc3Q="
    And request body from file "data/01_valid_user.json"
    When the client performs PUT request on "/users/1"
    Then status code is 403

  Scenario: Update of an user with valid authentication and valid role succeeds
    Given content type is "application/json"
    And header Authorization with value "Basic Z2lyaXNoOmcxcjE1aA=="
    And request body from file "data/01_valid_user.json"
    When the client performs PUT request on "/users/1"
    Then status code is 200
