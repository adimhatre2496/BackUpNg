package com.example.patientdetails.service;

import com.example.globalexpectionhandler.expection.CannotCreatePatientException;
import com.example.globalexpectionhandler.expection.PatientIdNotFoundException;
import com.example.patientdetails.entity.PatientEntity;
import com.example.patientdetails.entity.PracticeEntity;
import com.example.patientdetails.mapper.PatientMapper;
import com.example.patientdetails.model.PatientRequest;
import com.example.patientdetails.model.PatientResponse;
import com.example.patientdetails.model.PracticeRequest;
import com.example.patientdetails.repository.PatientRepository;
import com.example.patientdetails.repository.PracticeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class PatientService
    {
        private final PatientRepository patientRepository;
        private final PracticeRepository practiceRepository;
        private final PatientMapper patientMapper;

        public PatientResponse createPatientEntry(PatientRequest patientRequest)
        {
            PatientEntity patientEntity= patientMapper.patientToEntity(patientRequest);
            if (patientEntity != null){
            patientRepository.save(patientEntity);
                PatientResponse patientResponse=new PatientResponse();
                patientResponse.setId(patientEntity.getId());
                patientResponse.setFirstName(patientEntity.getFirstName());
                patientResponse.setLastName(patientRequest.getLastName());

                log.info("Creating Records....");

                return patientResponse;
            }else {
                throw new CannotCreatePatientException("PatientEntity is Body is Empty");
            }
        }

        public PatientRequest getById(Long id) {return patientRepository.findById(id).map(this::getPerson).orElseThrow(() -> new PatientIdNotFoundException("Id Not Found"));}
        private PatientRequest  getPerson(PatientEntity patientEntity)
        {
            log.info("Fetching....");
            return patientMapper.entityToPatient(patientEntity);
        }

        public PatientRequest updateByIdPatient(Long id, PatientRequest patientRequest)
        {
            Optional<PatientEntity> patientEntity= Optional.of(patientRepository.findById(id).orElseThrow(() -> new PatientIdNotFoundException("Id not found")));

            if(patientEntity.isPresent())
            {
                patientEntity= Optional.of(patientMapper.patientToEntity(patientRequest));
                patientEntity.get().setId(id);

                patientRepository.save(patientEntity.get());
                log.info("Updating...");
            }
            else{
                log.info("Empty Entity");

            }
            return patientRequest;

        }

        public PracticeRequest updateByPracticeId(Long id, PracticeRequest practiceRequest)
        {
            Optional<PracticeEntity> practiceEntity= Optional.of(practiceRepository.findById(id).orElseThrow(() -> new PatientIdNotFoundException("Practice id not Present")));
            if(practiceEntity.isPresent())
            {
                practiceEntity.get().setPracticeName(practiceRequest.getPracticeName());
                practiceEntity.get().setCopay(practiceRequest.getCopay());
                practiceRepository.save(practiceEntity.get());
                log.info("Updating...");
            }
            else
            {
                log.info("Empty Entity");             }
            return practiceRequest;
        }

        public void deleteById(Long id)
        {
            Optional<PatientEntity> patientEntity= Optional.of(patientRepository.findById(id).orElseThrow(() -> new PatientIdNotFoundException("Id not Found")));
            if (patientEntity.isPresent()) {
                patientRepository.delete(patientEntity.get());
                log.info("Deleting...");
            }  else {
                log.info("Empty Entity to delete");
            }
        }


        public List<PatientRequest> getAllRecords()
        {
            log.info("Fetching...");
            return patientMapper.entityListToPatientList(patientRepository.findAll());
        }

        public PatientRequest getByName(String firstName)
        {
            PatientEntity patientEntity= patientRepository.findByFirstName(firstName);
            PatientRequest patientRequest=new PatientRequest();
            if(patientEntity.getFirstName().equals(firstName) ){
                patientRequest=patientMapper.entityToPatient(patientEntity);
                log.info("Fetching....");
            }
            else {
                log.info("Name not Found");
            }
                return patientRequest;
        }
    }
