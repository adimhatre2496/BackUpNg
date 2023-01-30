package com.example.customValidation.expectionHandler;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PersonStatusException extends RuntimeException{
    public PersonStatusException(String msg) {
        super(msg);

    }
}
