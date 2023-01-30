package com.example.restservice.service;

import com.example.list_of_Students.model.test;
import com.example.restservice.model.Person;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Service
@Log4j2
@RequiredArgsConstructor
public class PersonClientCallerService {

    @Value("${resturl}")
    private String url;

    private final RestTemplate restTemplate;


    public Person getPerson(Long id) {

        test dd=new test();
        dd.sum(1,3);

        try {
            return restTemplate.getForObject(url + "/persons/" + id, Person.class, id);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
//            throw new PersonNotFoundExpection("Person with id " + id + " not found");
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,"Plz enter the vaild id");
        }

    }


}
