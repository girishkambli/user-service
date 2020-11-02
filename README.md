# user-service
User Details Service

To run cucumber and junit tests:
./mvnw clean verify
Feature file location: src/itest/resources/*.feature

Input data location: src/itest/resources/data/*.json

To run the application
./mvnw spring-boot:run
Get User url: http://localhost:9001/api/users/{id}, method=GET
Update User url: http://localhost:9001/api/users/{id}, method=PUT
