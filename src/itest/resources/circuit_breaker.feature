Feature: Circuit Breaker trigger

  Background:
    Given baseUri is "http://localhost:9001/api"

  Scenario: After Circuit breaker trips, dummy user is returned
    And header Authorization with value "Basic Z3Vlc3Q6Z3Vlc3Q="
    When the client performs GET request on "/users/1?triggerCircuitBreaker=true"
    Then status code is 200
    And response contains property "id" with value "0"
    And response contains property "firstName" with value "Circuit"
    And response contains property "lastName" with value "Breaker"
    Then should wait at most 5 seconds with interval 1 seconds until property "id" equal to "1"
    And header Authorization with value "Basic Z3Vlc3Q6Z3Vlc3Q="
    And the client performs GET request on "/users/1"
    Then response contains property "id" with value "1"
