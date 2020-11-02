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

    @Size(max = 25)
    private String street;

    @Size(max = 20)
    private String city;

    @Size(max = 20)
    private String state;

    @Size(max = 8)
    private String postcode;

}
