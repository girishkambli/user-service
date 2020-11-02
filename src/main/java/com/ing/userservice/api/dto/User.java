package com.ing.userservice.api.dto;

import javax.validation.constraints.Max;
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

    @Pattern(regexp = "[\\d]{1,20}", message = INVALID_USER_ID)
    private String id;

    @Size(max = 5)
    private String title;

    @Size(max = 20, message = "Invalid first name")
    private String firstName;

    @Size(max = 25)
    private String lastName;

    private Gender gender;

    private Address address;


}
