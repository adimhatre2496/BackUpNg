package com.example.globalexpectionhandler.expection;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
    public class PatientIdNotFoundException extends RuntimeException
    {
            public PatientIdNotFoundException(String message) {super(message);}
    }
