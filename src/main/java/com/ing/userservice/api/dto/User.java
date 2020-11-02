package com.ing.userservice.api.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User Details DTO.
 */
@Data
@NoArgsConstructor
public class User {

    public static final String INVALID_USER_ID = "Invalid User Id";

    @NotBlank(message = INVALID_USER_ID)
    @Digits(integer = Integer.MAX_VALUE, fraction = 0, message = INVALID_USER_ID)
    private String id;

    private String title;

    private String firstName;

    private String lastName;

    private Gender gender;

    private Address address;


}
