package com.crossasyst.cassandra.controller;

import com.crossasyst.cassandra.entity.PersonEntity;

import com.crossasyst.cassandra.model.PersonRequest;
import com.crossasyst.cassandra.model.PersonResponse;
import com.crossasyst.cassandra.service.PersonService;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
public class PersonController
    {
        private final PersonService personService;

    //    private PersonController(PersonService personService1){
    //        this.personService=personService1;
    //    }
        @GetMapping("/persons")
        public ResponseEntity<List<PersonEntity>> getAllPersons()
        {
            return new ResponseEntity<>(personService.findAll(), HttpStatus.FOUND);
        }

        @GetMapping("/persons/{id}")
        public ResponseEntity<PersonEntity> getPersonById(@PathVariable UUID id)
        {
            return new ResponseEntity<>(personService.findPersonById(id),HttpStatus.FOUND);
        }

        @PostMapping("/persons")
        public ResponseEntity<PersonResponse> createPerson(@RequestBody PersonRequest personRequest){
            PersonResponse personResponse=personService.createPerson(personRequest);
            return new ResponseEntity<>(personResponse,HttpStatus.CREATED);
        }

        @DeleteMapping("/persons/{id}")
        public ResponseEntity<Void> deleteById(@PathVariable UUID id){
            personService.deleteByID(id);
            return ResponseEntity.ok().build();
        }

        @GetMapping("/persons/name/{name}")
        public ResponseEntity<PersonEntity> getPersonById(@PathVariable String name)
        {
            return new ResponseEntity<>(personService.findPersonByName(name),HttpStatus.OK);
        }

        @DeleteMapping("/persons/name/{name}")
        public ResponseEntity<PersonEntity> deleteByName(@PathVariable String name)
        {
            return new ResponseEntity<>(personService.deleteByName(name),HttpStatus.OK);
        }
    }
