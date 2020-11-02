package com.ing.userservice.api.dto;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User Details DTO.
 */
@Data
@NoArgsConstructor
public class User {

    public static final String INVALID_USER_ID = "Invalid User Id";
    public static final String FIRST_NAME_TOO_LONG = "First name too long";
    public static final String LAST_NAME_TOO_LONG = "Last name too long";
    public static final String TITLE_TOO_LONG = "Title too long";

    @Pattern(regexp = "[\\d]{1,20}", message = INVALID_USER_ID)
    private String id;

    @Size(max = 5, message = TITLE_TOO_LONG)
    private String title;

    @Size(max = 20, message = FIRST_NAME_TOO_LONG)
    private String firstName;

    @Size(max = 25, message = LAST_NAME_TOO_LONG)
    private String lastName;

    private Gender gender;

    private Address address;
}
