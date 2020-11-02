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

public class AddressValidationTest {

    private Validator validator;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testWhenStreetNameIsTooLongThenValidationFails() {
        Address address = getAddress();
        address.setStreet("AVeryVeryLongStreetNameWillFail");

        Set<ConstraintViolation<Address>> errors = validator.validate(address);
        assertEquals(1, errors.size());
        assertEquals("street", errors.iterator().next().getPropertyPath().toString());
        assertEquals(Address.STREET_TOO_LONG, errors.iterator().next().getMessage());
    }

    @Test
    public void testWhenCityNameIsTooLongThenValidationFails() {
        Address address = getAddress();
        address.setCity("AVeryVeryLongCityName");

        Set<ConstraintViolation<Address>> errors = validator.validate(address);
        assertEquals(1, errors.size());
        assertEquals("city", errors.iterator().next().getPropertyPath().toString());
        assertEquals(Address.CITY_TOO_LONG, errors.iterator().next().getMessage());
    }

    @Test
    public void testWhenStateNameIsTooLongThenValidationFails() {
        Address address = getAddress();
        address.setState("AVeryVeryLongStateNameWillFail");

        Set<ConstraintViolation<Address>> errors = validator.validate(address);
        assertEquals(1, errors.size());
        assertEquals("state", errors.iterator().next().getPropertyPath().toString());
        assertEquals(Address.STATE_TOO_LONG, errors.iterator().next().getMessage());
    }

    @Test
    public void testWhenPostCodeIsTooLongThenValidationFails() {
        Address address = getAddress();
        address.setPostcode("123456789");

        Set<ConstraintViolation<Address>> errors = validator.validate(address);
        assertEquals(1, errors.size());
        assertEquals("postcode", errors.iterator().next().getPropertyPath().toString());
        assertEquals(Address.POSTCODE_TOO_LONG, errors.iterator().next().getMessage());
    }

    @NotNull
    private Address getAddress() {
        Address address = new Address();
        address.setStreet("1 Market St");
        address.setCity("Sydney");
        address.setState("NSW");
        address.setPostcode("2000");
        return address;
    }
}
