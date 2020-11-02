# user-service

A simple user service that can 1) find exisiting user, and 2) update user.

<h4>To run contract(pact) tests, integration(cucumber) tests and junit tests:</h4>
<h5>./mvnw clean verify</h5>
<h5>Feature file location: src/itest/resources/*.feature</h5>
<h5>Input data location: src/itest/resources/data/*.json</h5>

<h4>To run the application</h4>
<h5>./mvnw spring-boot:run<h5>
<h6>Get User url: http://localhost:9001/api/users/{id}, method=GET<h6>
Update User url: http://localhost:9001/api/users/{id}, method=PUT
