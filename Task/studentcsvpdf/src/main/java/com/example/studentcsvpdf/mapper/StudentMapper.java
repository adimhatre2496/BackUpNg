package com.example.studentcsvpdf.mapper;

import com.example.studentcsvpdf.entity.StudentEntity;
import com.example.studentcsvpdf.model.StudentDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper
    {
        StudentEntity modelToEntity (StudentDTO studentDTO);

        StudentDTO entityToModel(StudentEntity studentEntity);

    }
