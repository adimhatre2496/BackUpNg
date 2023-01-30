package com.example.customValidation.service;

import com.example.customValidation.entity.AddressEntity;
import com.example.customValidation.entity.PersonEntity;
import com.example.customValidation.expectionHandler.PersonStatusException;
import com.example.customValidation.mapper.AddressMapper;
import com.example.customValidation.mapper.PersonMapper;
import com.example.customValidation.model.PatchPerson;
import com.example.customValidation.model.Person;
import com.example.customValidation.model.PersonResponse;

import com.example.customValidation.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@Log4j2
@RequiredArgsConstructor
public class PersonService
    {
        private final PersonRepository personRepository;
        private final PersonMapper personMapper;
        private final AddressMapper addressMapper;

        public PersonResponse createPerson(Person person)
        {
            try{
            PersonEntity personEntity = personMapper.personToEntity(person);
            personRepository.save(personEntity);

            PersonResponse personResponse = new PersonResponse();
            personResponse.setId(personEntity.getId());
            personResponse.setFirstName(personEntity.getFirstName());
            personResponse.setLastName(personEntity.getLastName());
            log.info("details of {} {}", person.getFirstName(), person.getLastName() + " Inserted successfully");
            return personResponse;
            }
            catch (Exception exception){
                throw new PersonStatusException("Person Not Found");

            }
        }

        public Person getPerson(Long id)
        {
            return personRepository.findById(id).map(this::getPerson).orElseThrow(() -> new PersonStatusException( "Person can not be found with id:"+id));
        }
        private Person getPerson(PersonEntity personEntity)
        {
            return personMapper.entityToPerson(personEntity);
        }

        public void deletePerson(Long id)
        {
           PersonEntity personEntity= personRepository.findById(id).orElseThrow(() -> new PersonStatusException("Person can not be found with id:" + id));
           personRepository.delete(personEntity);
        }

        public void updatePerson(Long id, Person person) {
            Optional<PersonEntity> personEntityOptional = Optional.of(personRepository.findById(id).orElseThrow(() -> new PersonStatusException("Person can not be found with id:" + id)));

                PersonEntity personEntity = personEntityOptional.get();
                AddressEntity addressEntity = personEntity.getAddressEntity();
                personEntity.setFirstName(person.getFirstName());
                personEntity.setLastName(person.getLastName());

                addressEntity.setAddressOne(person.getAddressEntity().getAddressOne());
                addressEntity.setAddressTwo(person.getAddressEntity().getAddressTwo());
                addressEntity.setCity(person.getAddressEntity().getCity());
                addressEntity.setState(person.getAddressEntity().getState());
                addressEntity.setZipCode(person.getAddressEntity().getZipCode());

                personEntity.setAddressEntity(addressEntity);
                personRepository.save(personEntityOptional.get());
                log.info("Person id {}", id + " updated successfully");

        }

        public PatchPerson patchPerson(Long id, PatchPerson patchPerson)
        {
            Optional<PersonEntity>personEntity= Optional.of(personRepository.findById(id).orElseThrow(() -> new PersonStatusException("Person can not be found with id:" + id)));

                PersonEntity personEntity1=personEntity.get();
                personEntity1.setFirstName(patchPerson.getFirstName());
                personEntity1.setLastName(patchPerson.getLastName());
                personRepository.save(personEntity1);
                log.info("Patching.....");
                return patchPerson;

        }

        public Person patchPersonRequestParam(Long id, String firstName)
        {
            Optional<PersonEntity> personEntity1= Optional.of(personRepository.findById(id).orElseThrow(() -> new PersonStatusException("Person can not be found with id:" + id)));
                PersonEntity personEntity= personEntity1.get() ;
                personEntity.setFirstName(firstName);
                personEntity1.get().setFirstName(firstName);

                Person person =new Person();
                person=personMapper.entityToPerson(personEntity1.get())
                personRepository.save(personEntity);

                return person;

        }
    }
