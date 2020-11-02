package com.ing.userservice.api.dto;

import javax.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User Address DTO.
 */
@Data
@NoArgsConstructor
public class Address {

    public static final String STREET_TOO_LONG = "Street too long";
    public static final String CITY_TOO_LONG = "City too long";
    public static final String STATE_TOO_LONG = "State too long";
    public static final String POSTCODE_TOO_LONG = "Postcode too long";

    @Size(max = 25, message = STREET_TOO_LONG)
    private String street;

    @Size(max = 20, message = CITY_TOO_LONG)
    private String city;

    @Size(max = 20, message = STATE_TOO_LONG)
    private String state;

    @Size(max = 8, message = POSTCODE_TOO_LONG)
    private String postcode;
}
