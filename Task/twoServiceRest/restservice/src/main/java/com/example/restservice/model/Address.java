package com.example.restservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Address {
    private String addressOne;

    private String addressTwo;

    private String city;

    private String state;

    private String zipCode;

}
