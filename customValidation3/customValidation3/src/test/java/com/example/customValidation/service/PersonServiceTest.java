package com.example.customValidation.service;

import com.example.customValidation.entity.PersonEntity;
import com.example.customValidation.expectionHandler.PersonStatusException;
import com.example.customValidation.mapper.PersonMapper;
import com.example.customValidation.model.PatchPerson;
import com.example.customValidation.model.Person;
import com.example.customValidation.model.PersonResponse;
import com.example.customValidation.repository.PersonRepository;

import com.example.customValidation.testUtils.MapUtils;
import com.example.customValidation.testUtils.MockUtils;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@Log4j2
public class PersonServiceTest extends Assertions
    {
        @InjectMocks
        public PersonService personService;
        @Mock
        private PersonRepository personRepository;
        private PersonEntity personEntity;
        private Person person;
        private PatchPerson patchPerson;

        @BeforeEach
        void setup() throws IOException
        {
            PersonMapper personMapper = Mappers.getMapper(PersonMapper.class);
            ReflectionTestUtils.setField(personService, "personMapper", personMapper);

            person= MapUtils.getObjectFromJsonFile(MapUtils.readFile("src/test/resources/Person.json"), Person.class);
            personEntity=MapUtils.getObjectFromJsonFile(MapUtils.readFile("src/test/resources/PersonEntity.json"), PersonEntity.class);
            patchPerson=MapUtils.getObjectFromJsonFile(MapUtils.readFile("src/test/resources/PatchPerson.json"),PatchPerson.class);
        }

        @Test
        void testCreatePerson()
        {
            Mockito.when(personRepository.save(Mockito.any(PersonEntity.class))).thenReturn(personEntity);
            PersonResponse personResponse1 = personService.createPerson(person);

            assertEquals("firstName",personResponse1.getFirstName());
            assertNotNull(personResponse1.getFirstName());
        }

        @Test
        void testCreatePersonWhenCreatePersonExceptionOccurs()
        {
            Mockito.when(personRepository.save(Mockito.any(PersonEntity.class))).thenThrow(PersonStatusException.class);
            assertThrows(PersonStatusException.class ,() -> personService.createPerson(Mockito.any(Person.class)));
            Mockito.verify(personRepository).save(Mockito.any());
        }

        @ParameterizedTest
        @ValueSource(longs = {1L,2L})
        void testGetPerson(Long id)
        {
          Mockito.when(personRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(personEntity));
          Person person= personService.getPerson(id);

          assertEquals("firstName",person.getFirstName());
          assertEquals("lastName",person.getLastName());
        }

        @Test
        void testGetPerson()
        {
            Mockito.when(personRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(personEntity));
            Person person= personService.getPerson(Mockito.anyLong());

            assertEquals("firstName",person.getFirstName());
            assertEquals("lastName",person.getLastName());
        }

        @Test
        void testGetPersonWhenIdNotFoundExceptionOccurs()
        {
            Mockito.when(personRepository.findById(Mockito.anyLong())).thenThrow(PersonStatusException.class);
            assertThrows(PersonStatusException.class, ()-> personService.getPerson(Mockito.anyLong()));
            Mockito.verify(personRepository).findById(Mockito.anyLong());
        }

        @Test
        void testDeletePerson()
        {
            Mockito.when(personRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(personEntity));
//            Mockito.verify(personRepository).delete(Mockito.any(PersonEntity.class));
            personService.deletePerson(Mockito.anyLong());
//            Mockito.doNothing().when(personRepository).delete(personEntity);
        }

        @Test
        void testDeletePersonWhenIdNotFound()
        {
            Mockito.when(personRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
            assertThrows(PersonStatusException.class, ()-> personService.deletePerson(19L));
            Mockito.verify(personRepository).findById(Mockito.anyLong());
        }

        @Test
        void testUpdatePerson()
        {
            Mockito.when(personRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(personEntity));
            personService.updatePerson(101L,person);
        }

        @Test
        void testUpdatePersonWhenIdNotFoundExceptionOccurs()
        {
            Mockito.when(personRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
            assertThrows(PersonStatusException.class, ()-> personService.updatePerson(19L,MockUtils.modelSetUp()));
            Mockito.verify(personRepository).findById(Mockito.anyLong());
        }

        @Test
        void testPatchPerson()
        {
            Mockito.when(personRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(personEntity));
            personService.patchPerson(101L, patchPerson);
            assertEquals("firstName",personEntity.getFirstName());
            assertEquals("lastName",personEntity.getLastName());
            assertNotNull(personEntity);
        }

        @Test
        void testPatchPersonWhenIdNotFoundExceptionOccurs()
        {
            Mockito.when(personRepository.findById(Mockito.anyLong())).thenThrow(PersonStatusException.class);
            assertThrows(PersonStatusException.class, ()-> personService.getPerson(Mockito.anyLong()));
            Mockito.verify(personRepository).findById(Mockito.anyLong());
        }

        @Test
        void testPatchPersonRequestParam()
        {
            Mockito.when(personRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(personEntity));
            personService.patchPersonRequestParam(101L,"Shiv");
            assertEquals("Shiv",personEntity.getFirstName());
        }

    }