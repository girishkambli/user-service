package com.ing.userservice;

import static org.junit.Assert.assertEquals;

import com.ing.userservice.api.dto.Address;
import com.ing.userservice.api.dto.Gender;
import com.ing.userservice.api.dto.User;
import com.ing.userservice.core.service.UserServiceImpl;
import java.util.concurrent.TimeUnit;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

public class CircuitBreakerIT {

    private static ConfigurableApplicationContext context;

    @BeforeClass
    public static void setup() {
        context = SpringApplication
            .run(UserServiceApplication.class);
    }

    @AfterClass
    public static void close() {
        context.close();
    }

    /**
     * A naive attempt to test Circuit breaker. Please ignore this test if it starts failing.
     *
     * @throws InterruptedException
     */
    @Test
    public void testCircuitBreaker() throws InterruptedException {
        UserServiceImpl userService = context.getBean(UserServiceImpl.class);

        int n = 5;
        for (int i = 0; i < n; i++) {
            if (i < 4) {
                try {
                    userService.updateUser(user("1", "VeryLongTitle"));
                } catch (Throwable e) {
                }
            } else {
                try {
                    userService.updateUser(user("1", "Ms."));
                } catch (Throwable e) {
                    assertEquals("Hystrix circuit short-circuited and is OPEN",
                        e.getCause().getMessage());
                }
            }
            TimeUnit.MILLISECONDS.sleep(200);
        }

        TimeUnit.SECONDS.sleep(2);

        try {
            userService.updateUser(user("1", "Ms."));
        } catch (Exception e) {
            Assert.fail("Should not throw exception now!");
        }
    }

    private static User user(String id, String title) {
        User userDetails = new User();
        userDetails.setId(id);
        userDetails.setFirstName("Girish");
        userDetails.setLastName("Kambli");
        userDetails.setTitle(title);
        userDetails.setGender(Gender.MALE);
        Address address = new Address();
        address.setStreet("1 Market St");
        address.setCity("Sydney");
        address.setState("NSW");
        address.setPostcode("2000");
        userDetails.setAddress(address);
        return userDetails;
    }
}
