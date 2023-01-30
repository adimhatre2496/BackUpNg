package com.example.globalexpectionhandler.expection;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class CannotCreatePatientException extends RuntimeException{
    public CannotCreatePatientException(String message)
    {
        super(message);
    }
}
