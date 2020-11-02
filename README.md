# user-service

A simple user service that can 1) Find an exisiting user, and 2) Update a user.

<h4>Tools and technologies</h4>
<h5>SpringBoot 2.0 Framework</h5>
<h5>H2 Database</h5>
<h5>PACT consumer driven tests</h5>
<h5>Cucumber Integration tests</h5>
<h5>JUnit 4</h5>
<h5>Basic authentication using Spring Security</h5>
<h5>Hystrix Circuit Breaker</h5>
<h5>Lombok Java library</h5>
<h5>Liquibase for DB schema management</h5>
<h5>Hibernate validator for Bean validation</h5>

<h4>To run contract(pact) tests, integration(cucumber) tests and junit tests:</h4>
<h5>./mvnw clean verify</h5>
<h6>PACT files location: pacts/user_client-user_service.json</h6>
<h6>Cucumber Feature file location: src/itest/resources/*.feature</h6>
<h6>Input data location: src/itest/resources/data/*.json</h6>

<h4>To run the application</h4>
<h5>./mvnw spring-boot:run<h5>
<h6>Get User url: http://localhost:9001/api/users/{id}, method=GET<h6>
Update User url: http://localhost:9001/api/users/{id}, method=PUT
  
<h4>CURL<h4>
<h5>Get user<h5>  
<h6>curl --user guest:guest  http://localhost:9001/api/users/1</h6>

<h5>Update user<h5>  
<h6>curl -i --user girish:g1r15h -X PUT -H "Content-Type:application/json"  http://localhost:9001/api/users/1 --data-binary @- << EOF</h6>
 <h6> {
  <h6> "address": {
   <h6>  "city": "Sydney",
   <h6>  "postcode": "2000",
   <h6>  "state": "NSW",
   <h6>  "street": "5 Market St"
  <h6> },
  <h6> "firstName": "Sean",
  <h6> "gender": "MALE",
   <h6>"id": "1",
   <h6>"lastName": "Matt",
  <h6> "title": "Mr."
 <h6>}
 <h6>EOF </h6>
