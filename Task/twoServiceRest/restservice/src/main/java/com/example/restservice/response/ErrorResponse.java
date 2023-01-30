package com.example.restservice.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ErrorResponse
    {
        private String message;
        private HttpStatus httpStatus;
        private LocalDateTime timesnap;
        private String path;
    }
