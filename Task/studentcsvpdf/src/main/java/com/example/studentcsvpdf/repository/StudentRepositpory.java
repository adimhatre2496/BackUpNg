package com.example.studentcsvpdf.repository;

import com.example.studentcsvpdf.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface StudentRepositpory extends JpaRepository<StudentEntity,Long>
    {
        @Modifying(clearAutomatically = true)
        @Transactional
        @Query(value = "COPY (SELECT * from public.Student) TO 'D:/NGPay/Notes/trial.csv' (format csv,DELIMITER ',')",nativeQuery = true)
        Integer getCSV();
    }
