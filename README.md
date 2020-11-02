# user-service

A simple user service that can 1) find exisiting user, and 2) update user.

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
