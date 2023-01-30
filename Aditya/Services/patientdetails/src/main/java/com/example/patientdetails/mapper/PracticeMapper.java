package com.example.patientdetails.mapper;

import com.example.patientdetails.entity.PracticeEntity;
import com.example.patientdetails.model.PracticeRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PracticeMapper
    {
        PracticeEntity practiceToEntity (PracticeRequest practiceRequest);

        PracticeRequest entityToPractice (PracticeEntity practiceEntity);
    }
