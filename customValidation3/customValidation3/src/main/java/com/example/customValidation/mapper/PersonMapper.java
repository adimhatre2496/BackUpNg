package com.example.customValidation.mapper;

import com.example.customValidation.entity.PersonEntity;
import com.example.customValidation.model.Person;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    PersonEntity personToEntity(Person person);
    Person entityToPerson(PersonEntity personEntity);
}
