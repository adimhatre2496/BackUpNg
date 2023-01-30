package com.example.customValidation.model;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Person {
//    @NotBlank(message = "First name Should not be null")
    private  String firstName;

//    @NotBlank(message = "Last name Should not be null")
    private  String lastName;

    private Address addressEntity;



}
