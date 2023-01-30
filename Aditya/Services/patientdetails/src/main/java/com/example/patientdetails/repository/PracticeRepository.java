package com.example.patientdetails.repository;

import com.example.patientdetails.entity.PracticeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PracticeRepository extends JpaRepository<PracticeEntity,Long>
    {

    }
