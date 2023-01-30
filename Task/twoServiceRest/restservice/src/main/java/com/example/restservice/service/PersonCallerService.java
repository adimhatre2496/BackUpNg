package com.example.restservice.service;

import com.example.restservice.expection.PersonNotFoundExpection;
import com.example.restservice.model.PatchPerson;
import com.example.restservice.model.Person;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class PersonCallerService {
    @Value("${resturl}")
    private String url;

    private final RestTemplate restTemplate;
    private final PersonClientCallerService personClientCallerService;

        public Person createPerson(Person person) {
            HttpEntity<Person>http=new HttpEntity<>(person);
            ResponseEntity<Person> response=restTemplate.exchange(url+"/persons", HttpMethod.POST,http, Person.class);
            return response.getBody();

        }

        public Person getPerson(Long id)
        {
       return personClientCallerService.getPerson(id);
        }

        public Person updatePerson(Long id, Person person) {
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            headers.add("Content-Type", "application/json");
            HttpEntity<Person> httpEntity=new HttpEntity<>(person);
            ResponseEntity<Person>response= restTemplate.exchange(url+"/updatepersons/"+id,HttpMethod.PUT,httpEntity,Person.class);
            return response.getBody();
        }

        public void deleteById(Long id) {
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            headers.add("Content-Type", "application/json");
            HttpEntity<Object> entity = new HttpEntity<>(null, headers);
           restTemplate.exchange(url + "/persons/" + id, HttpMethod.DELETE, entity, Void.class);

        }

        public void updateFirstName(Long id, PatchPerson patchPerson) {
            HttpEntity<PatchPerson> httpEntity=new HttpEntity<>(patchPerson);
             restTemplate.exchange(url+"/persons/"+id,HttpMethod.PATCH,httpEntity,Void.class);

        }

//        public PersonResponse updatePersonFirstName(Long id, PatchPerson personResponse) {
//            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
//            headers.add("Content-Type", "application/json");
//            HttpEntity<PatchPerson> httpEntity=new HttpEntity<>(personResponse);
//            ResponseEntity<PersonResponse>response= restTemplate.patchForObject(url+"personsfirstname"+id,null,PatchPerson.class,);
//            return response.getBody();
//        }
}
