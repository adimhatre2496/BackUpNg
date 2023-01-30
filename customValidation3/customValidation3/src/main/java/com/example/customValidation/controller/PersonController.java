package com.example.customValidation.controller;

import com.example.customValidation.model.PatchPerson;
import com.example.customValidation.model.Person;
import com.example.customValidation.model.PersonResponse;
import com.example.customValidation.service.PersonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequiredArgsConstructor
public class PersonController {

    @Autowired
    private final PersonService personService;

    @PostMapping(path = "/persons", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonResponse> createPerson(@RequestBody @Valid Person person) {
        PersonResponse personResponse = personService.createPerson(person);
        log.info("Insert Successfull");
        return new ResponseEntity<>(personResponse, HttpStatus.CREATED);
    }

    @GetMapping(path = "/persons/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> getPersons(@PathVariable Long id) {
        Person person = personService.getPerson(id);
        log.info("Fetch Successfull");
        return new ResponseEntity<>(person, HttpStatus.OK);
    }


    @PutMapping(path = "/updatepersons/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updatePerson(@PathVariable Long id, @RequestBody Person person) {
        personService.updatePerson(id, person);
        log.info("Update Successfull");
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/persons/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
        log.info("Delete Successfull");
        return ResponseEntity.ok().build();
    }

    @PatchMapping(path = "/persons/{id}")
    public ResponseEntity<PatchPerson> patchCart(@PathVariable Long id, @RequestBody PatchPerson patchPerson){
        PatchPerson patchPerson1=personService.patchPerson(id,patchPerson);
        log.info("Patch Successfully");
        return new ResponseEntity<>(patchPerson1,HttpStatus.OK);
    }

    @PatchMapping(path = "personsfirstname/{id}")
    public ResponseEntity<Void> patchPersonRequestParam(@PathVariable Long id,@RequestParam String firstName){
        personService.patchPersonRequestParam(id,firstName);
        return ResponseEntity.ok().build();
    }
}
