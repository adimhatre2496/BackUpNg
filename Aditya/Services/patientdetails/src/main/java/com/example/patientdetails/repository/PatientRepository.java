package com.example.patientdetails.repository;

import com.example.patientdetails.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity,Long>
    {
        PatientEntity findByFirstName(String firstName);
    }
