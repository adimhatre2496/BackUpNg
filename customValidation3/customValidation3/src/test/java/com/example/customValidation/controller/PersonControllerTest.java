package com.example.customValidation.controller;

import com.example.customValidation.model.PatchPerson;
import com.example.customValidation.model.Person;
import com.example.customValidation.model.PersonResponse;
import com.example.customValidation.service.PersonService;
import com.example.customValidation.testUtils.MapUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.UriComponentsBuilder;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@Log4j2
public class PersonControllerTest extends Assertions
    {
        private static final String POST_URL = "/persons";
        private static final String GET_URL = "/persons/1";
        private static final String PUT_URL = "/updatepersons/1";
        private static final String DELETE_URL = "/persons/1";
        private static final String PATCH_URL = "/persons/1";
        @InjectMocks
        private  PersonController personController;

        @Mock
        private PersonService personService;

        private MockMvc mockMvc;
        private  Person person;
        private PatchPerson patchPerson;
        private JacksonTester<Person> personJacksonTester;
        private JacksonTester<PatchPerson> personJacksonTester1;


        @BeforeEach
        void setup() throws Exception
        {
            patchPerson=MapUtils.getObjectFromJsonFile(MapUtils.readFile("src/test/resources/PatchPerson.json"),PatchPerson.class);
            person= MapUtils.getObjectFromJsonFile(MapUtils.readFile("src/test/resources/Person.json"), Person.class);
            mockMvc= MockMvcBuilders.standaloneSetup(personController).build();
            JacksonTester.initFields(this,new ObjectMapper());
        }

        @Test
        void testCreatePersonIfStatusCodeIsCreated() throws Exception {
            Mockito.when(personService.createPerson(Mockito.any(Person.class))).thenReturn(Mockito.any(PersonResponse.class));
            mockMvc.perform(MockMvcRequestBuilders
                        .post(String.format(POST_URL))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(personJacksonTester.write(person).getJson())
                        .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated());
            Mockito.verify(personService).createPerson(Mockito.any(Person.class));
        }

       /* @Test
        void testCreatePersonIfStatusCodeIsBadRequest() throws Exception {
            Mockito.when(personService.createPerson(Mockito.any())).thenReturn(Mockito.any());
            mockMvc.perform(MockMvcRequestBuilders
                            .post(String.format(POST_URL))
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                            .content(personJacksonTester.write(person).getJson())
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());
            Mockito.verify(personService).createPerson(Mockito.any(Person.class));
        }*/

        @Test
        void testGetPersonsIfStatusCodeIsOk() throws Exception {
            Mockito.when(personService.getPerson(Mockito.anyLong())).thenReturn(Mockito.any(Person.class));
            mockMvc.perform(MockMvcRequestBuilders
                        .get(String.format(GET_URL))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(personJacksonTester.write(person).getJson()))
                    .andExpect(status().isOk());
            Mockito.verify(personService).getPerson(Mockito.anyLong());
        }

        @Test
        void testUpdatePersonIfStatusCodeIsOk() throws Exception {
            Mockito.doNothing().when(personService).updatePerson(Mockito.anyLong(),Mockito.any(Person.class));
            mockMvc.perform(MockMvcRequestBuilders
                        .put(String.format(PUT_URL))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(personJacksonTester.write(person).getJson())
                        .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
            Mockito.verify(personService).updatePerson(Mockito.anyLong(),Mockito.any(Person.class));
        }

        @Test
        void testDeletePersonIfStatusCodeIsOk() throws Exception {
            Mockito.doNothing().when(personService).deletePerson(Mockito.anyLong());
            mockMvc.perform(MockMvcRequestBuilders
                        .delete(String.format(DELETE_URL))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(personJacksonTester.write(person).getJson())
                        .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
            Mockito.verify(personService).deletePerson(Mockito.anyLong());
        }

        @Test
        void testPatchCartIfStatusCodeIsOk() throws Exception {
            Mockito.when(personService.patchPerson(Mockito.anyLong(), Mockito.any(PatchPerson.class))).thenReturn(patchPerson);
            mockMvc.perform(MockMvcRequestBuilders
                        .patch(String.format(PATCH_URL))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(personJacksonTester1.write(patchPerson).getJson())
                        .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());

            Mockito.verify(personService).patchPerson(Mockito.anyLong(),Mockito.any(PatchPerson.class));
        }

        @Test
        void testPatchPersonRequestParamIfStatusCodeIsOk() throws Exception {
    //        Mockito.when(personService.patchPersonRequestParam(Mockito.anyLong(),Mockito.anyString())).thenReturn(Mockito.any(Person.class));
            Mockito.doNothing().when(personService).patchPersonRequestParam(Mockito.anyLong(),Mockito.anyString());
            String requestUri = UriComponentsBuilder.fromUriString("/personsfirstname/1").queryParam("firstName","Adi").build().toUriString();
            MvcResult mvcResult= mockMvc.perform(MockMvcRequestBuilders
                                .patch(requestUri)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(personJacksonTester.write(person).getJson())
                                .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk()).andReturn();

    //       Assertions.assertEquals("Adi",result);

        }
    }
