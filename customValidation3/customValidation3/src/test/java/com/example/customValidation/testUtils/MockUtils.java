package com.example.customValidation.testUtils;

import com.example.customValidation.entity.AddressEntity;
import com.example.customValidation.entity.PersonEntity;
import com.example.customValidation.model.Address;
import com.example.customValidation.model.PatchPerson;
import com.example.customValidation.model.Person;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class MockUtils
{
    public static final PersonEntity entitySetUp()
    {
        PersonEntity personEntity=new PersonEntity();
        AddressEntity addressEntity=new AddressEntity();

        personEntity.setId(101L);
        personEntity.setFirstName("Aditya");
        personEntity.setLastName("Mhatre");

        addressEntity.setAddressOne("gyusd");
        addressEntity.setAddressTwo("ghd");
        addressEntity.setCity("sdjgz");
        addressEntity.setState("TAsd");
        addressEntity.setZipCode("345678");

        personEntity.setAddressEntity(addressEntity);
        return personEntity;
    }

    public static final Person modelSetUp()
    {
        Person person=new Person();
        person.setFirstName("Adi");
        person.setLastName("Mhatre");

        Address address=new Address();
        address.setAddressOne("iuh");
        address.setAddressTwo("gyu");
        address.setCity("fg");
        address.setState("tf");
        address.setZipCode("987654");

        person.setAddressEntity(address);
        return person;

    }

    public static final PatchPerson patchSetUp()
    {
        PatchPerson patchPerson=new PatchPerson();
        patchPerson.setFirstName("Adi");
        patchPerson.setLastName("Mhatre");
        return patchPerson;
    }
}
