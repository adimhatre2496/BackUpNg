package com.example.customValidation.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Address
    {
        private String addressOne;

        private String addressTwo;

        private String city;

        private String state;

        private String zipCode;




    }
