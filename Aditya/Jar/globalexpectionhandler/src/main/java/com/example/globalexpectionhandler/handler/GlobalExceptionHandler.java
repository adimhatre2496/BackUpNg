package com.example.globalexpectionhandler.handler;

import com.example.globalexpectionhandler.expection.BadRequestException;
import com.example.globalexpectionhandler.expection.CannotCreatePatientException;
import com.example.globalexpectionhandler.expection.ClientException;
import com.example.globalexpectionhandler.expection.PatientIdNotFoundException;
import com.example.globalexpectionhandler.expection.PatientNotFoundException;
import com.example.globalexpectionhandler.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler
    {
        @ExceptionHandler(value = PatientIdNotFoundException.class)
        @ResponseStatus(HttpStatus.NOT_FOUND)
        public ResponseEntity<Object> patientIdNotFoundException(PatientIdNotFoundException idNotFoundException, WebRequest webRequest)
        {
            return new ResponseEntity<>(new ErrorResponse(
                            idNotFoundException.getMessage(),
                            HttpStatus.NOT_FOUND,
                            LocalDateTime.now(),
                            webRequest.getDescription(false)),
                    HttpStatus.NOT_FOUND);
        }

        @ExceptionHandler(value = PatientNotFoundException.class)
        @ResponseStatus(HttpStatus.NOT_FOUND)
        public ResponseEntity<Object> patientNotFoundException(PatientNotFoundException notFoundException, WebRequest webRequest)
        {
            return new ResponseEntity<>(new ErrorResponse(
                        notFoundException.getMessage(),
                        HttpStatus.NOT_FOUND,
                        LocalDateTime.now(),
                        webRequest.getDescription(false)),
                    HttpStatus.NOT_FOUND);
        }

        @ExceptionHandler(BadRequestException.class)
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        public ResponseEntity<ErrorResponse> badRequest(BadRequestException badRequestException, WebRequest webRequest)
        {
            return new ResponseEntity<>( new ErrorResponse(
                        badRequestException.getMessage(),
                        HttpStatus.BAD_REQUEST,
                        LocalDateTime.now(),
                        webRequest.getDescription(false)),
                    HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(ClientException.class)
        @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
        public ResponseEntity<ErrorResponse> clientException(ClientException clientException, WebRequest webRequest) {
            return new ResponseEntity<>(new ErrorResponse(
                        clientException.getMessage(),
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        LocalDateTime.now(), webRequest.getDescription(false)),
                    HttpStatus.BAD_GATEWAY);
        }

        @ExceptionHandler(value =CannotCreatePatientException.class)
        @ResponseStatus(HttpStatus.NOT_FOUND)
        public ResponseEntity<Object>cannotCreatePatientException (CannotCreatePatientException cannotCreatePatient, WebRequest webRequest)
        {
            return new ResponseEntity<>(new ErrorResponse(
                    cannotCreatePatient.getMessage(),
                    HttpStatus.NOT_FOUND,
                    LocalDateTime.now(),
                    webRequest.getDescription(false)),
                    HttpStatus.NOT_FOUND);
        }

    }
