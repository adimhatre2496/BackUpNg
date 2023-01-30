package com.example.patientdetails.controller;

import com.example.patientdetails.model.PatientRequest;
import com.example.patientdetails.model.PatientResponse;
import com.example.patientdetails.model.PracticeRequest;
import com.example.patientdetails.service.PatientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor
public class PatientController
    {
        private final PatientService patientService;

        @PostMapping(path = "/patients")
        public ResponseEntity<PatientResponse> createPatientEntry (@RequestBody PatientRequest patientRequest)
        {
             PatientResponse patientResponse=patientService.createPatientEntry(patientRequest);
             log.info("Created Record Successfully");
             return new ResponseEntity<>(patientResponse, HttpStatus.CREATED);
        }

        @GetMapping(path = "/patients/{id}")
        public ResponseEntity<PatientRequest> getById(@PathVariable Long id)
        {
            PatientRequest patientRequest=patientService.getById(id);
            log.info("Fetched Record Successfully");
            return new ResponseEntity<>(patientRequest,HttpStatus.OK);
        }

        @GetMapping(path = "/patients-name/{firstName}")
        public ResponseEntity<PatientRequest> getById(@PathVariable String firstName)
        {
            PatientRequest patientRequest=patientService.getByName(firstName);
            log.info("Fetched Record Successfully");
            return new ResponseEntity<>(patientRequest,HttpStatus.OK);
        }

        @GetMapping(path = "/patients")
        public ResponseEntity<List<PatientRequest>> getAll()
        {
            List<PatientRequest> patientRequests=patientService.getAllRecords();
            log.info("Fetched All Records Successfully");
            return new ResponseEntity<>(patientRequests,HttpStatus.OK);
        }

        @PutMapping(path = "/patients/updatepatients/{id}")
        public ResponseEntity<PatientRequest> updateByIdPatient(@PathVariable Long id,@RequestBody PatientRequest patientRequest)
        {
            patientRequest=patientService.updateByIdPatient(id,patientRequest);
            log.info("Updated Record Successfully");
            return new ResponseEntity<>(patientRequest,HttpStatus.OK);
        }

        @PatchMapping(path = "/patients/updatepratices/{id}")
        public ResponseEntity<PracticeRequest> updateByIdPractice(@PathVariable Long id, @RequestBody PracticeRequest practiceRequest)
        {
            practiceRequest=patientService.updateByPracticeId(id,practiceRequest);
       return new ResponseEntity<>(practiceRequest,HttpStatus.OK);
        }

        @DeleteMapping(path = "/patient/{id}")
        public ResponseEntity<String> deleteById(@PathVariable Long id)
        {
            patientService.deleteById(id);
            log.info("Deleted Record Successfully");
            return ResponseEntity.ok().body("Deleted Successfully");
        }

    }
