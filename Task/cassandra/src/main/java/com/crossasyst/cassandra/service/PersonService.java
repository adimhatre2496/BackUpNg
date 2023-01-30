package com.crossasyst.cassandra.service;

import com.crossasyst.cassandra.entity.PersonEntity;
import com.crossasyst.cassandra.model.PersonRequest;
import com.crossasyst.cassandra.model.PersonResponse;
import com.crossasyst.cassandra.repository.PersonRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PersonService
    {
        private final PersonRepository personRepository;

        public List<PersonEntity> findAll() {
        return personRepository.findAll();
        }

        public PersonEntity findPersonById(UUID id)
        {
            return personRepository.findById(id).get();
        }

        public PersonResponse createPerson(PersonRequest personRequest)
        {
            PersonEntity personEntity=new PersonEntity();
            personEntity.setName(personRequest.getName());
            personEntity.setPersonId(personRequest.getPersonId());
            personEntity.setStatus(personRequest.getStatus());
            personEntity.setDate(LocalDate.now());
            personEntity.setTimestamp(new Date());// if we use data type LocalDateTime for timeStamp we can use Static Method localDateTime.now()
            personRepository.save(personEntity);

            PersonResponse personResponse=new PersonResponse();

            personResponse.setId(personEntity.getId());
            personResponse.setName(personEntity.getName());

            return personResponse;
        }

        public void deleteByID(UUID id)
        {
            personRepository.deleteById(id);
        }

        public PersonEntity findPersonByName(String name) {

        return personRepository.getByName(name);
        }

        public PersonEntity deleteByName(String name) {
            return personRepository.deleteByName(name);
        }
    }
