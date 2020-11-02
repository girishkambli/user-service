Feature: Verify GET user

  Background:
    Given baseUri is "http://localhost:9001/api"

  Scenario: Get an user with invalid id fails
    And header Authorization with value "Basic Z3Vlc3Q6Z3Vlc3Q="
    When the client performs GET request on "/users/xyz"
    Then status code is 400
    And response contains "Invalid User Id"

  Scenario: Get an user with an unknown id fails
    And header Authorization with value "Basic Z3Vlc3Q6Z3Vlc3Q="
    When the client performs GET request on "/users/999"
    Then status code is 404
    And response contains "User Not Found"

  Scenario: Get an user with valid id succeeds
    And header Authorization with value "Basic Z3Vlc3Q6Z3Vlc3Q="
    When the client performs GET request on "/users/1"
    Then status code is 200