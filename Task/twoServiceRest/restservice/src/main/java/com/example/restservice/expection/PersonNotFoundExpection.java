package com.example.restservice.expection;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
@NoArgsConstructor
@ResponseStatus(HttpStatus.NOT_FOUND)
public class PersonNotFoundExpection extends RuntimeException{
    public PersonNotFoundExpection(String s) {
        super(s);
    }
}
