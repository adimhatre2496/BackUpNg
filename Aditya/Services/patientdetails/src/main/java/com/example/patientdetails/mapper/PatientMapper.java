package com.example.patientdetails.mapper;

import com.example.patientdetails.entity.PatientEntity;
import com.example.patientdetails.model.PatientRequest;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PatientMapper
    {
        PatientEntity patientToEntity (PatientRequest patientRequest);

        PatientRequest entityToPatient (PatientEntity patientEntity);

        List<PatientRequest> entityListToPatientList (List<PatientEntity> patientEntityList);
    }
