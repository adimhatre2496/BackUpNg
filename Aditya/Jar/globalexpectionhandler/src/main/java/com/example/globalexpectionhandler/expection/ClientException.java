package com.example.globalexpectionhandler.expection;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public class ClientException extends HttpClientErrorException
    {
        public ClientException(HttpStatus httpStatus, String message){
            super(httpStatus,message);
        }
    }
