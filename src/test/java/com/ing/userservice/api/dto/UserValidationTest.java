package com.ing.userservice.api.dto;

import static org.junit.Assert.assertEquals;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.jetbrains.annotations.NotNull;
import org.junit.Before;
import org.junit.Test;

public class UserValidationTest {

    private Validator validator;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testWhenIdIsInvalidThenValidationFails() {
        User user = getUser();
        user.setId("invalid");

        Set<ConstraintViolation<User>> errors = validator.validate(user);
        assertEquals(1, errors.size());
        assertEquals("id", errors.iterator().next().getPropertyPath().toString());
        assertEquals(User.INVALID_USER_ID, errors.iterator().next().getMessage());
    }

    @Test
    public void testWhenTitleIsTooLongThenValidationFails() {
        User user = getUser();
        user.setTitle("LongTitle");

        Set<ConstraintViolation<User>> errors = validator.validate(user);
        assertEquals(1, errors.size());
        assertEquals("title", errors.iterator().next().getPropertyPath().toString());
        assertEquals(User.TITLE_TOO_LONG, errors.iterator().next().getMessage());
    }

    @Test
    public void testWhenFirstNameIsTooLongThenValidationFails() {
        User user = getUser();
        user.setFirstName("AVeryVeryLongFirstName");

        Set<ConstraintViolation<User>> errors = validator.validate(user);
        assertEquals(1, errors.size());
        assertEquals("firstName", errors.iterator().next().getPropertyPath().toString());
        assertEquals(User.FIRST_NAME_TOO_LONG, errors.iterator().next().getMessage());
    }

    @Test
    public void testWhenLastNameIsTooLongThenValidationFails() {
        User user = getUser();
        user.setLastName("AVeryVeryLongLastNameWillFail");

        Set<ConstraintViolation<User>> errors = validator.validate(user);
        assertEquals(1, errors.size());
        assertEquals("lastName", errors.iterator().next().getPropertyPath().toString());
        assertEquals(User.LAST_NAME_TOO_LONG, errors.iterator().next().getMessage());
    }

    @NotNull
    private User getUser() {
        User user = new User();
        user.setId("1");
        user.setTitle("Mr.");
        user.setGender(Gender.MALE);
        user.setFirstName("Isaac");
        user.setLastName("Newton");
        return user;
    }

}
