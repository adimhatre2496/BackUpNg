package com.example.customValidation.expectionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.time.LocalDateTime;

@ControllerAdvice
public class PersonHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = PersonStatusException.class)
    public ResponseEntity<Object> responseEntity(PersonStatusException statusException, WebRequest webRequest){
        return new ResponseEntity<>(new ErrorResponse(statusException.getMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now(),webRequest.getDescription(false)),HttpStatus.NOT_FOUND);

    }
}
