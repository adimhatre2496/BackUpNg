package com.example.OracleConnection.service;

import com.example.relationmappings.one2one.entity.AddressEntity;
import com.example.relationmappings.one2one.entity.PersonEntity;
import com.example.relationmappings.one2one.mapper.AddressMapper;
import com.example.relationmappings.one2one.mapper.PersonMappper;
import com.example.relationmappings.one2one.model.Address;
import com.example.relationmappings.one2one.model.PatchPerson;
import com.example.relationmappings.one2one.model.Person;
import com.example.relationmappings.one2one.model.PersonResponse;
import com.example.relationmappings.one2one.repository.AddressRepository;
import com.example.relationmappings.one2one.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor

public class PersonService {

    @Autowired
    private final PersonRepository personRepository;

    @Autowired
    private  PersonMappper personMappper;

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private AddressRepository addressRepository;


    public PersonResponse createPerson(Person person) {
        AddressEntity addressEntity;
        addressEntity = addressMapper.addressToAddressEntity(person.getAddress());
        PersonEntity personEntity;

        personEntity = personMappper.personToEntity(person);
        personEntity.setAddressEntity(addressEntity);
        personRepository.save(personEntity);

        PersonResponse personResponse = new PersonResponse();
        personResponse.setId(personEntity.getId());
        log.info("details of {} {}", person.getFirstName(), person.getLastName() + " Inserted successfully");
        return personResponse;
    }

    public Person getPerson(Long id) {
        Optional<PersonEntity> optionalPersonEntity = personRepository.findById(id);
        Person person = new Person();
        Address address;
        if (optionalPersonEntity.isPresent()) {
            AddressEntity addressEntity = optionalPersonEntity.get().getAddressEntity();
            PersonEntity personEntity = optionalPersonEntity.get();

            person =personMappper.entityToPerson(personEntity);
            address = addressMapper.addressEntityToAddress(addressEntity);
            person.setAddress(address);

            log.info("person with id {}", id + " retrieved successfully");
        }
        else {
            log.info("Id Not Found");
        }
        return person;
    }

    public void deletePerson(Long id) {

        personRepository.deleteById(id);
        log.info("Person id {} ", id + " deleted Successfully");
    }


    public void updatePerson(Long id, Person person) {
        Optional<PersonEntity> personEntityOptional = personRepository.findById(id);
        if (personEntityOptional.isPresent()) {
            PersonEntity personEntity = personEntityOptional.get();
            AddressEntity addressEntity = personEntity.getAddressEntity();
            personEntity.setFirstName(person.getFirstName());
            personEntity.setLastName(person.getLastName());

            addressEntity.setAddressOne(person.getAddress().getAddressOne());
            addressEntity.setAddressTwo(person.getAddress().getAddressTwo());
            addressEntity.setCity(person.getAddress().getCity());
            addressEntity.setState(person.getAddress().getState());
            addressEntity.setZipCode(person.getAddress().getZipCode());

            personEntity.setAddressEntity(addressEntity);
            personRepository.save(personEntityOptional.get());
            log.info("Person id {}", id + " updated successfully");
        } else {
            log.info("Id Not Found");
        }
    }

    public void patchPerson(Long id, PatchPerson patchPerson)
    {
        Optional<PersonEntity>personEntityOptional=personRepository.findById(id);
        PersonEntity personEntity=personEntityOptional.get();
        if (personEntityOptional.isPresent()){
            personEntity.setFirstName(patchPerson.getFirstName());
            personEntity.setLastName(patchPerson.getLastName());

            }

            personRepository.save(personEntity);
        log.info("Patching.....");




    }
}

   /* public Person patchPerson(Long id, Map<String, Object> changes) {
        PersonEntity personEntity = null;
        Optional<PersonEntity> personEntityOptional = personRepository.findById(id);
        AddressEntity addressEntity = null;
        changes.forEach((change, value) -> {
            switch (change) {
                case "firstName":
                    personEntity.setFirstName((String) value);
                case "lastName":
                    personEntity.setLastName((String) value);
                case "addressone":
                    addressEntity.setAddressOne((String) value);
                case "addressTwo":
                    addressEntity.setAddressOne((String) value);
                case "city":
                    addressEntity.setCity((String) value);
                case "state":
                    addressEntity.setState((String) value);
                case "zipcode":
                    addressEntity.setZipCode((String) value);

            }

        });
*/

//        return null;
//    }

/*   public void updatePerson(Long personId, Person person) {


       PersonEntity personEntity1=personRepository.findById(personId).get();
       PersonEntity personEntity =personMappper.personToEntity(person);

       Long addressId= personEntity1.getAddressEntity().getId();
       log.info(personEntity1.getAddressEntity());

       personEntity.getAddressEntity().setId(addressId);
       personEntity.setId(personId);

       personRepository.save(personEntity);



   }*/




