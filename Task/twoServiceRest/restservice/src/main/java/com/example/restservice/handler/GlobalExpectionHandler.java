package com.example.restservice.handler;

import com.example.restservice.expection.BadRequestExpection;
import com.example.restservice.expection.ClientExpection;
import com.example.restservice.expection.PersonNotFoundExpection;
import com.example.restservice.response.ErrorResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

public class GlobalExpectionHandler
    {
        @ExceptionHandler(PersonNotFoundExpection.class)
        @ResponseStatus(HttpStatus.NOT_FOUND)
        public ResponseEntity<ErrorResponse> IdNotFound(PersonNotFoundExpection personNotFoundExpection, WebRequest webRequest) {
            ErrorResponse errorDetails = new ErrorResponse(personNotFoundExpection.getMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now(), webRequest.getDescription(false));
            return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
        }

        @ExceptionHandler(BadRequestExpection.class)
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        public ResponseEntity<ErrorResponse> badRequest(BadRequestExpection badRequestExpection, WebRequest webRequest) {
            ErrorResponse errorDetails = new ErrorResponse(badRequestExpection.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now(), webRequest.getDescription(false));
            return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(ClientExpection.class)
        @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
        public ResponseEntity<ErrorResponse> badRequest(ClientExpection clientExpection, WebRequest webRequest) {
            ErrorResponse errorDetails = new ErrorResponse(clientExpection.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now(), webRequest.getDescription(false));
            return new ResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        @ExceptionHandler({HttpClientErrorException.class})
//    @ResponseStatus(HttpStatus.NOT_FOUND)
        public ResponseEntity<ErrorResponse> handleHttpStatusCodeException(Exception ex, WebRequest request) throws JsonProcessingException {

            ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now(), request.getDescription(false));

            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }
