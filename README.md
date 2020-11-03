# user-service  
  
A simple user service that can   
1) Find an existing user   
2) Update an user  

## Tools and technologies

 - SpringBoot 2.0 Framework  
 - H2 Database  
 - PACT consumer driven tests  
 - Cucumber Integration tests  
 - JUnit 4  
 - Basic authentication using Spring Security  
 - Hystrix Circuit Breaker  
 - Lombok Java library  
 - Liquibase for DB schema management  
 - Hibernate validator for Bean validation  

**To run contract(pact) tests, integration(cucumber) tests and junit tests:**

     ./mvnw clean verify

 - PACT files location: pacts/user_client-user_service.json  
 - Cucumber Feature file location: src/itest/resources/*.feature  
 - Input data location: src/itest/resources/data/*.json
  
**To run the application**  

    ./mvnw spring-boot:run  

 - Data population script: src/main/resources/db/data/insertData.sql
 - Get User url: http://localhost:9001/api/users/{id}, method=GET
 - Update User url: http://localhost:9001/api/users/{id}, method=PUT      

**CURL**  
   Get User:     

    curl --user guest:guest  http://localhost:9001/api/users/1

  
Update User:

    curl -i --user girish:g1r15h -X PUT -H "Content-Type:application/json"  http://localhost:9001/api/users/1 --data-binary @- << EOF  
      {  
       "address": {  
         "city": "Sydney",  
         "postcode": "2000",  
         "state": "NSW",  
         "street": "5 Market St"  
       },  
       "firstName": "Sean",  
       "gender": "MALE",  
       "id": "1",  
       "lastName": "Matt",  
       "title": "Mr."  
     }  
    EOF

