package com.ing.userservice.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User Address DTO.
 */
@Data
@NoArgsConstructor
public class Address {

    private String street;

    private String city;

    private String state;

    private String postcode;

}
