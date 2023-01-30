package com.example.restservice.controller;

import com.example.restservice.model.Person;
import com.example.restservice.response.PersonResponse;
import com.example.restservice.service.PersonCallerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequiredArgsConstructor
public class PersonCallerController
    {

        private final PersonCallerService personCallerService;

        @PostMapping(path = "/persons")
        public ResponseEntity<Person> createPerson(@RequestBody Person person) {
            Person personResponse = personCallerService.createPerson(person);
            return new ResponseEntity<>(personResponse, HttpStatus.CREATED);

        }

        @GetMapping(path = "/persons/{id}")
        public ResponseEntity<Person> getPerson(@PathVariable Long id) {
            Person person = personCallerService.getPerson(id);
            return new ResponseEntity<>(person, HttpStatus.FOUND);

        }

        @PutMapping(path = "/persons/{id}")
        public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody Person person) {
             personCallerService.updatePerson(id, person);
            return new ResponseEntity<>(person, HttpStatus.ACCEPTED);
        }

        @DeleteMapping(path = "/persons/{id}")
        public ResponseEntity<Void> deleteById(@PathVariable Long id) {
            personCallerService.deleteById(id);
            return ResponseEntity.ok().build();
        }
    }
